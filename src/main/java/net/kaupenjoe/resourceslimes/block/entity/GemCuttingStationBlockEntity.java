package net.kaupenjoe.resourceslimes.block.entity;

import net.kaupenjoe.resourceslimes.block.custom.GemCuttingStationBlock;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.recipe.GemCuttingStationRecipe;
import net.kaupenjoe.resourceslimes.screen.GemCuttingStationMenu;
import net.kaupenjoe.resourceslimes.util.KaupenEnergyStorage;
import net.kaupenjoe.resourceslimes.util.ModTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class GemCuttingStationBlockEntity extends ModSlimeBlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent();
                case 1 -> stack.is(ModTags.Items.UNCUT_GEMS);
                case 2 -> stack.getItem() == ModItems.GEM_CUTTER_TOOL.get();
                case 3 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private final FluidTank FLUID_TANK = createFluidTank();
    private final KaupenEnergyStorage ENERGY_STORAGE = createEnergyStorage();

    @Override
    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    @NotNull
    @Override
    protected FluidTank createFluidTank() {
        return new FluidTank(16000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if (!level.isClientSide) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack.getFluid() == Fluids.WATER;
            }
        };
    }


    @NotNull
    @Override
    protected KaupenEnergyStorage createEnergyStorage() {
        return new KaupenEnergyStorage(60000, 200) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 3, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 1 || index == 2,
                            (index, stack) -> itemHandler.isItemValid(1, stack) || itemHandler.isItemValid(2, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 3, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2,
                            (index, stack) -> itemHandler.isItemValid(2, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0 || index == 1,
                            (index, stack) -> itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack))));


    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public ItemStack getRenderStack() {
        ItemStack stack;

        if(!itemHandler.getStackInSlot(3).isEmpty()) {
            stack = itemHandler.getStackInSlot(3);
        } else {
            stack = itemHandler.getStackInSlot(1);
        }

        return stack;
    }

    public boolean hasGemCuttingTools() {
        return this.itemHandler.getStackInSlot(2).getItem() == ModItems.GEM_CUTTER_TOOL.get();
    }

    @Override
    @Deprecated(forRemoval = true)
    public void setHandler(ItemStackHandler handler) {
        copyHandlerContents(handler);
    }

    @Deprecated(forRemoval = true)
    private void copyHandlerContents(ItemStackHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, handler.getStackInSlot(i));
        }
    }

    @Override
    public ItemStackHandler getItemStackHandler() {
        return this.itemHandler;
    }
    @Deprecated(forRemoval = true)
    public void setFluid(FluidStack fluidStack) {
        this.FLUID_TANK.setFluid(fluidStack);
    }

    public FluidStack getFluid() {
        return this.FLUID_TANK.getFluid();
    }
    @Deprecated(forRemoval = true)
    public void setEnergyLevel(int energyLevel) {
        this.ENERGY_STORAGE.setEnergy(energyLevel);
    }

    public GemCuttingStationBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GEM_CUTTING_STATION_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemCuttingStationBlockEntity.this.progress;
                    case 1 -> GemCuttingStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemCuttingStationBlockEntity.this.progress = value;
                    case 1 -> GemCuttingStationBlockEntity.this.maxProgress = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Gem Cutting Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new GemCuttingStationMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(GemCuttingStationBlock.FACING);

                if(side == Direction.UP || side == Direction.DOWN) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if(this.getBlockState().getValue(GemCuttingStationBlock.FACING).getClockWise() == side) {
                return lazyFluidHandler.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
        lazyEnergyHandler.invalidate();
        for (Direction dir : Direction.values()) {
            if(directionWrappedHandlerMap.containsKey(dir)) {
                directionWrappedHandlerMap.get(dir).invalidate();
            }
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("gem_cutting_station.progress", progress);
        tag = FLUID_TANK.writeToNBT(tag);
        tag.put("energy", ENERGY_STORAGE.serializeNBT());

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("gem_cutting_station.progress");
        FLUID_TANK.readFromNBT(nbt);
        ENERGY_STORAGE.deserializeNBT(nbt.get("energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, GemCuttingStationBlockEntity pBlockEntity) {
        if (!pBlockEntity.itemHandler.getStackInSlot(1).isEmpty() && !pBlockEntity.itemHandler.getStackInSlot(2).isEmpty()) {
            if (hasRecipe(pBlockEntity) && hasEnoughEnergy(pBlockEntity)) {
                pBlockEntity.progress++;
                extractEnergy(pBlockEntity);
                if (pBlockEntity.progress >= pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);
                }
                setChanged(pLevel, pPos, pState);
            } else {
                pBlockEntity.resetProgress();
            }
        } else {
            pBlockEntity.resetProgress();
        }
        if (hasWaterSourceInSlot(pBlockEntity)) {
            transferItemWaterToWaterTank(pBlockEntity);
        }
    }

    private static void extractEnergy(GemCuttingStationBlockEntity entity) {
        entity.ENERGY_STORAGE.extractEnergy(100, false);
    }

    private static boolean hasEnoughEnergy(GemCuttingStationBlockEntity entity) {
        return entity.ENERGY_STORAGE.getEnergyStored() >= 100;
    }

    private static boolean hasWaterSourceInSlot(GemCuttingStationBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(0).getCount() > 0;
    }

    private static void transferItemWaterToWaterTank(GemCuttingStationBlockEntity entity) {
        entity.itemHandler.getStackInSlot(0).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
            int drainAmount = Math.min(entity.FLUID_TANK.getSpace(), 1000);

            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if(stack.getFluid() == Fluids.WATER) {
                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                fillTankWithWater(entity, stack, handler.getContainer());
            }
        });
    }

    private static void fillTankWithWater(GemCuttingStationBlockEntity entity, FluidStack fluidStack, ItemStack stack) {
        entity.FLUID_TANK.fill(fluidStack, IFluidHandler.FluidAction.EXECUTE);

        entity.itemHandler.extractItem(0, 1, false);
        entity.itemHandler.insertItem(0, stack, false);
    }

    private static boolean hasRecipe(GemCuttingStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<GemCuttingStationRecipe> match = level.getRecipeManager()
                .getRecipeFor(GemCuttingStationRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasWaterInTank(entity, match.get()) && hasToolsInToolSlot(entity);
    }

    private static boolean hasWaterInTank(GemCuttingStationBlockEntity entity, GemCuttingStationRecipe recipe) {
        return entity.FLUID_TANK.getFluid().getAmount() >= recipe.getWaterAmount();
    }

    private static boolean hasToolsInToolSlot(GemCuttingStationBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(2).getItem() == ModItems.GEM_CUTTER_TOOL.get();
    }

    private static void craftItem(GemCuttingStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<GemCuttingStationRecipe> match = level.getRecipeManager()
                .getRecipeFor(GemCuttingStationRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.FLUID_TANK.drain(match.get().getWaterAmount(), IFluidHandler.FluidAction.EXECUTE);
            entity.itemHandler.extractItem(1,1, false);
            if(entity.itemHandler.getStackInSlot(2).hurt(1, new Random(), null)) {
                entity.itemHandler.extractItem(2,1, false);
            }

            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        if (this.progress != 0) {
            this.progress = 0;
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() > inventory.getItem(3).getCount();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        if (Minecraft.getInstance().player.containerMenu instanceof GemCuttingStationMenu gemCuttingStationMenu && gemCuttingStationMenu.blockEntity == this) {
            gemCuttingStationMenu.setFluid(getFluid());
        }
    }
}

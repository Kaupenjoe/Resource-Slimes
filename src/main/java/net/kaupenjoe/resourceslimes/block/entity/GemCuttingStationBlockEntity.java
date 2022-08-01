package net.kaupenjoe.resourceslimes.block.entity;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.kaupenjoe.resourceslimes.block.custom.GemCuttingStationBlock;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.networking.ModMessages;
import net.kaupenjoe.resourceslimes.networking.packets.PacketSyncEnergyToClient;
import net.kaupenjoe.resourceslimes.networking.packets.PacketSyncFluidStackToClient;
import net.kaupenjoe.resourceslimes.networking.packets.PacketSyncItemStackToClient;
import net.kaupenjoe.resourceslimes.recipe.GemCuttingStationRecipe;
import net.kaupenjoe.resourceslimes.screen.GemCuttingStationMenu;
import net.kaupenjoe.resourceslimes.util.KaupenEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
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

public class GemCuttingStationBlockEntity extends AbstractRSMachineBlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                ModMessages.sendToClients(new PacketSyncItemStackToClient(this, worldPosition));
            }
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
                if (!level.isClientSide()) {
                    ModMessages.sendToClients(new PacketSyncFluidStackToClient(this.fluid, worldPosition));
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
                ModMessages.sendToClients(new PacketSyncEnergyToClient(this.energy, worldPosition));
            }
        };
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
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
    public void setHandler(ItemStackHandler handler) {
        copyHandlerContents(handler);
    }

    private void copyHandlerContents(ItemStackHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            itemHandler.setStackInSlot(i, handler.getStackInSlot(i));
        }
    }

    @Override
    public ItemStackHandler getItemStackHandler() {
        return this.itemHandler;
    }

    public void setFluid(FluidStack fluidStack) {
        this.FLUID_TANK.setFluid(fluidStack);
    }

    public FluidStack getFluid() {
        return this.FLUID_TANK.getFluid();
    }

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
        return Component.literal("Gem Cutting Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        ModMessages.sendToClients(new PacketSyncFluidStackToClient(this.FLUID_TANK.getFluid(), worldPosition));
        return new GemCuttingStationMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
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
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("gem_cutting_station.progress", progress);
        tag = FLUID_TANK.writeToNBT(tag);
        tag.putInt("energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("gem_cutting_station.progress");
        FLUID_TANK.readFromNBT(nbt);
        ENERGY_STORAGE.setEnergy(nbt.getInt("energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, GemCuttingStationBlockEntity pBlockEntity) {
        if(pLevel.isClientSide()) {
            return;
        }

        if(hasRecipe(pBlockEntity) && hasEnoughEnergy(pBlockEntity)) {
            pBlockEntity.progress++;
            extractEnergy(pBlockEntity);
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }

        if(hasWaterSourceInSlot(pBlockEntity)) {
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
            if(entity.itemHandler.getStackInSlot(2).hurt(1, level.random, null)) {
                entity.itemHandler.extractItem(2,1, false);
            }

            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
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
        CompoundTag compound = saveWithoutMetadata();
        load(compound);

        return compound;
    }
}

package net.kaupenjoe.resourceslimes.block.entity;

import net.kaupenjoe.resourceslimes.block.custom.GemCuttingStationBlock;
import net.kaupenjoe.resourceslimes.block.custom.SlimeIncubationStationBlock;
import net.kaupenjoe.resourceslimes.entity.ModEntityTypes;
import net.kaupenjoe.resourceslimes.entity.ResourceSlimeEntity;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.recipe.SlimeIncubationStationRecipe;
import net.kaupenjoe.resourceslimes.screen.SlimeIncubationStationMenu;
import net.kaupenjoe.resourceslimes.util.KaupenEnergyStorage;
import net.kaupenjoe.resourceslimes.util.ModTags;
import net.kaupenjoe.resourceslimes.util.resources.SlimeResource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

public class SlimeIncubationStationBlockEntity extends ModSlimeBlockEntity {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
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
                case 0 -> stack.is(Tags.Items.SLIMEBALLS);
                case 1 -> true;
                case 2 -> stack.is(ModTags.Items.CUT_GEMS) || stack.is(ModItems.PINK_SLIME_PEARL.get());
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private final KaupenEnergyStorage ENERGY_STORAGE = createEnergyStorage();

    @Override
    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    @Override
    FluidTank createFluidTank() {
        return null;
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

    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> false, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0 || index == 2,
                            (index, stack) -> itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack) || itemHandler.isItemValid(2, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> false, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> false,
                            (index, stack) -> itemHandler.isItemValid(2, stack) && index != 1)),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> false,
                            (index, stack) -> itemHandler.isItemValid(0, stack))));

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public float getScaledProgress() {
        float slimeSize = 1f;
        int progess = data.get(0);
        int maxProgress = data.get(1);

        return maxProgress != 0 && progess != 0 ? progess * slimeSize / maxProgress : 0;
    }

    public ItemStack getRenderStack() {
        ItemStack stack;

        if(!itemHandler.getStackInSlot(2).isEmpty()) {
            stack = itemHandler.getStackInSlot(2);
        } else {
            stack = itemHandler.getStackInSlot(1);
        }

        return stack;
    }

    private ResourceSlimeEntity slime;
    public ResourceSlimeEntity getSlimeEntity() {
        ResourceSlimeEntity resourceSlimeEntity = new ResourceSlimeEntity(ModEntityTypes.RESOURCE_SLIME.get(), level);
        SlimeResource resource = SlimeResource.getResourceByCraftingItem
                (itemHandler.getStackInSlot(1).getItem());
        resourceSlimeEntity.setResource(new ItemStack(resource.getSlimeyExtractItem()));

        return resourceSlimeEntity;
    }

    public ResourceSlimeEntity getRenderEntity() {
        return slime;
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
        // TODO: Refactor the interfaces
    }

    public FluidStack getFluid() {
        return FluidStack.EMPTY;
    }

    public void setEnergyLevel(int energyLevel) {
        this.ENERGY_STORAGE.setEnergy(energyLevel);
    }

    public SlimeIncubationStationBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SLIME_INCUBATION_STATION_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> SlimeIncubationStationBlockEntity.this.progress;
                    case 1 -> SlimeIncubationStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> SlimeIncubationStationBlockEntity.this.progress = value;
                    case 1 -> SlimeIncubationStationBlockEntity.this.maxProgress = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Slime Incubation Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new SlimeIncubationStationMenu(pContainerId, pInventory, this, this.data);
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

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
        slime = new ResourceSlimeEntity(ModEntityTypes.RESOURCE_SLIME.get(), level);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("slime_incubation.progress", progress);
        tag.putInt("energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("slime_incubation.progress");
        ENERGY_STORAGE.setEnergy(nbt.getInt("energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, SlimeIncubationStationBlockEntity pBlockEntity) {
        if (!pBlockEntity.itemHandler.getStackInSlot(0).isEmpty() && !pBlockEntity.itemHandler.getStackInSlot(1).isEmpty() && !pBlockEntity.itemHandler.getStackInSlot(2).isEmpty()) {
            if (hasRecipe(pBlockEntity) && hasEnoughEnergy(pBlockEntity)) {
                pBlockEntity.updateProgress();

                extractEnergy(pBlockEntity);
                setChanged(pLevel, pPos, pState);
                if (pBlockEntity.progress >= pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);
                }
            } else {
                pBlockEntity.resetProgress();
            }
        } else {
            pBlockEntity.resetProgress();
        }
    }
    private static void extractEnergy(SlimeIncubationStationBlockEntity entity) {
        entity.ENERGY_STORAGE.extractEnergy(100, false);
    }

    private static boolean hasEnoughEnergy(SlimeIncubationStationBlockEntity entity) {
        return entity.ENERGY_STORAGE.getEnergyStored() >= 100;
    }

    private static boolean hasRecipe(SlimeIncubationStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SlimeIncubationStationRecipe> match = level.getRecipeManager()
                .getRecipeFor(SlimeIncubationStationRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static void craftItem(SlimeIncubationStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<SlimeIncubationStationRecipe> match = level.getRecipeManager()
                .getRecipeFor(SlimeIncubationStationRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(0,1, false);
            ItemStack craftingItem = entity.itemHandler.extractItem(1,1, false);
            entity.itemHandler.extractItem(2,1, false);

            spawnSlime(entity, craftingItem);

            entity.resetProgress();
        }
    }

    private static void spawnSlime(SlimeIncubationStationBlockEntity entity, ItemStack stack) {
        if(!entity.level.isClientSide()) {
            ResourceSlimeEntity slime = entity.getSlimeEntity();
            float rot = entity.getBlockState().getValue(SlimeIncubationStationBlock.FACING).toYRot();
            Vec3 pos = new Vec3(0.0d, 0.75d, 0.1875d).yRot(-Mth.DEG_TO_RAD * rot).add(entity.getBlockPos().getX() + 0.5, entity.getBlockPos().getY(), entity.getBlockPos().getZ() + 0.5);
            slime.setPos(pos);
            slime.yBodyRot = Mth.wrapDegrees(rot);
            slime.yHeadRot = Mth.wrapDegrees(rot);
            slime.setResource(new ItemStack(SlimeResource.getResourceByCraftingItem(stack.getItem()).getSlimeyExtractItem()));
            entity.level.addFreshEntity(slime);
        }
    }

    private void resetProgress() {
        if (progress != 0) {
            this.progress = 0;
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            setChanged();
        }
    }

    private void updateProgress() {
        progress++;
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
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
        if (!itemHandler.getStackInSlot(1).isEmpty()) {
            this.slime = this.getSlimeEntity();
        }
    }
}

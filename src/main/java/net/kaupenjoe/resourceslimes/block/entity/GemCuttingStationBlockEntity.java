package net.kaupenjoe.resourceslimes.block.entity;

import net.kaupenjoe.resourceslimes.block.custom.GemCuttingStationBlock;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.recipe.GemCuttingStationRecipe;
import net.kaupenjoe.resourceslimes.screen.GemCuttingStationMenu;
import net.kaupenjoe.resourceslimes.networking.ModMessages;
import net.kaupenjoe.resourceslimes.networking.packets.PacketSyncFluidStackToClient;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
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
import java.util.Optional;
import java.util.Random;

public class GemCuttingStationBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private final FluidTank fluidTank = new FluidTank(16000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            if(!level.isClientSide()) {
                ModMessages.sendToClients(new PacketSyncFluidStackToClient(this.fluid, worldPosition));
            }
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public void setFluid(FluidStack fluidStack) {
        this.fluidTank.setFluid(fluidStack);
    }

    public FluidStack getFluid() {
        return this.fluidTank.getFluid();
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
        ModMessages.sendToClients(new PacketSyncFluidStackToClient(this.fluidTank.getFluid(), worldPosition));
        return new GemCuttingStationMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
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
        lazyFluidHandler = LazyOptional.of(() -> fluidTank);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("gem_cutting_station.progress", progress);
        tag = fluidTank.writeToNBT(tag);

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("gem_cutting_station.progress");
        fluidTank.readFromNBT(nbt);
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

        if(hasRecipe(pBlockEntity)) {
            pBlockEntity.progress++;
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

    private static boolean hasWaterSourceInSlot(GemCuttingStationBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(0).getCount() > 0;
    }

    private static void transferItemWaterToWaterTank(GemCuttingStationBlockEntity entity) {
        if(entity.itemHandler.getStackInSlot(0).getItem() instanceof PotionItem
                && PotionUtils.getPotion(entity.itemHandler.getStackInSlot(0)).equals(Potions.WATER)) {
            entity.fluidTank.fill(new FluidStack(Fluids.WATER, 250), IFluidHandler.FluidAction.EXECUTE);

            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.insertItem(0, new ItemStack(Items.GLASS_BOTTLE), false);
        }

        if(entity.itemHandler.getStackInSlot(0).getItem() instanceof BucketItem bucketItem
                && bucketItem.getFluid().equals(Fluids.WATER)) {
            entity.fluidTank.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);

            entity.itemHandler.extractItem(0, 1, false);
            entity.itemHandler.insertItem(0, new ItemStack(Items.BUCKET), false);
        }
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
        return entity.fluidTank.getFluid().getAmount() >= recipe.getWaterAmount();
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
            entity.fluidTank.drain(match.get().getWaterAmount(), IFluidHandler.FluidAction.EXECUTE);
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

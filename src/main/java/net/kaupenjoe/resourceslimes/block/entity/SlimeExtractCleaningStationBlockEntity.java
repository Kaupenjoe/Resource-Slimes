package net.kaupenjoe.resourceslimes.block.entity;

import net.kaupenjoe.resourceslimes.block.custom.GemInfusingStationBlock;
import net.kaupenjoe.resourceslimes.fluid.ModFluids;
import net.kaupenjoe.resourceslimes.item.ModItems;
import net.kaupenjoe.resourceslimes.networking.ModMessages;
import net.kaupenjoe.resourceslimes.networking.packets.PacketSyncEnergyToClient;
import net.kaupenjoe.resourceslimes.networking.packets.PacketSyncFluidStackToClient;
import net.kaupenjoe.resourceslimes.networking.packets.PacketSyncItemStackToClient;
import net.kaupenjoe.resourceslimes.recipe.GemInfusingStationRecipe;
import net.kaupenjoe.resourceslimes.screen.GemInfusingStationMenu;
import net.kaupenjoe.resourceslimes.util.KaupenEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
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
import java.util.Optional;

public class SlimeExtractCleaningStationBlockEntity extends ModSlimeBlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                ModMessages.sendToClients(new PacketSyncItemStackToClient(this, worldPosition));
            }
        }
    };

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

    public ItemStack getRenderStack() {
        ItemStack stack;

        if(!itemHandler.getStackInSlot(2).isEmpty()) {
            stack = itemHandler.getStackInSlot(2);
        } else {
            stack = itemHandler.getStackInSlot(1);
        }

        return stack;
    }

    private final FluidTank FLUID_TANK = createFluidTank();
    private final FluidTank FLUID_TANK_WASTE = createWasteFluidTank();
    private final KaupenEnergyStorage ENERGY_STORAGE = createEnergyStorage();

    @Override
    public IEnergyStorage getEnergyStorage() {
        return ENERGY_STORAGE;
    }

    @NotNull
    @Override
    protected FluidTank createFluidTank() {
        return new FluidTank(64000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if (!level.isClientSide()) {
                    ModMessages.sendToClients(new PacketSyncFluidStackToClient(this.fluid, worldPosition));
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return true;
            }
        };
    }

    @NotNull
    protected FluidTank createWasteFluidTank() {
        return new FluidTank(32000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if (!level.isClientSide()) {
                    ModMessages.sendToClients(new PacketSyncFluidStackToClient(this.fluid, worldPosition));
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return true;
            }
        };
    }

    @Override
    public void setFluid(FluidStack fluidStack) {
        this.FLUID_TANK.setFluid(fluidStack);
    }

    @Override
    public FluidStack getFluid() {
        return this.FLUID_TANK.getFluid();
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

    @Override
    public void setEnergyLevel(int energyLevel) {
        this.ENERGY_STORAGE.setEnergy(energyLevel);
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyWasteFluidHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;



    public SlimeExtractCleaningStationBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.GEM_INFUSING_STATION_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> SlimeExtractCleaningStationBlockEntity.this.progress;
                    case 1 -> SlimeExtractCleaningStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> SlimeExtractCleaningStationBlockEntity.this.progress = value;
                    case 1 -> SlimeExtractCleaningStationBlockEntity.this.maxProgress = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Gem Infusing Station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        ModMessages.sendToClients(new PacketSyncFluidStackToClient(this.FLUID_TANK.getFluid(), worldPosition));
        return new GemInfusingStationMenu(pContainerId, pInventory, this, this.data);
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
            if(this.getBlockState().getValue(GemInfusingStationBlock.FACING).getClockWise() == side) {
                return lazyFluidHandler.cast();
            }
            if(this.getBlockState().getValue(GemInfusingStationBlock.FACING).getCounterClockWise() == side) {
                return lazyWasteFluidHandler.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
        lazyWasteFluidHandler = LazyOptional.of(() -> FLUID_TANK_WASTE);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
        lazyWasteFluidHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("gem_infusion_station.progress", progress);
        tag = FLUID_TANK.writeToNBT(tag);
        CompoundTag fluidTag = new CompoundTag();
        fluidTag = FLUID_TANK_WASTE.writeToNBT(fluidTag);
        tag.put("wasteFluid", fluidTag);

        tag.putInt("energy", ENERGY_STORAGE.getEnergyStored());

        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("gem_infusion_station.progress");
        FLUID_TANK.readFromNBT(nbt);
        FLUID_TANK_WASTE.readFromNBT(nbt.getCompound("wasteFluid"));

        ENERGY_STORAGE.setEnergy(nbt.getInt("energy"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SlimeExtractCleaningStationBlockEntity pBlockEntity) {
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

    private static void extractEnergy(SlimeExtractCleaningStationBlockEntity entity) {
        entity.ENERGY_STORAGE.extractEnergy(100, false);
    }

    private static boolean hasEnoughEnergy(SlimeExtractCleaningStationBlockEntity entity) {
        return entity.ENERGY_STORAGE.getEnergyStored() >= 100;
    }

    private static boolean hasWaterSourceInSlot(SlimeExtractCleaningStationBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(1).getCount() > 0;
    }

    private static boolean hasSpaceInTank(SlimeExtractCleaningStationBlockEntity entity, int fillAmount) {
        return entity.FLUID_TANK.getSpace() >= fillAmount;
    }

    private static void transferItemWaterToWaterTank(SlimeExtractCleaningStationBlockEntity entity) {
        if(entity.itemHandler.getStackInSlot(1).getItem() instanceof BucketItem) {
            if(hasSpaceInTank(entity, 1000) && hasSoapInSlot(entity)) {
                fillTankWithFluid(entity, 1000);
            }
        }
    }

    private static boolean hasSoapInSlot(SlimeExtractCleaningStationBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(0).getItem() == ModItems.SOAP.get();
    }

    private static void fillTankWithFluid(SlimeExtractCleaningStationBlockEntity entity, int amount) {
        entity.FLUID_TANK.fill(new FluidStack(ModFluids.SOAPY_WATER_FLUID.get(), amount), IFluidHandler.FluidAction.EXECUTE);

        entity.itemHandler.extractItem(0, 1, false);
        entity.itemHandler.extractItem(1, 1, false);
        entity.itemHandler.insertItem(1, new ItemStack(Items.BUCKET), false);
    }

    private static boolean hasRecipe(SlimeExtractCleaningStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<GemInfusingStationRecipe> match = level.getRecipeManager()
                .getRecipeFor(GemInfusingStationRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) && hasSpaceInTank(entity)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasWaterInTank(entity, match.get());
    }

    private static boolean hasWaterInTank(SlimeExtractCleaningStationBlockEntity entity, GemInfusingStationRecipe recipe) {
        return entity.FLUID_TANK.getFluid().getAmount() >= recipe.getFluid().getAmount();
    }

    private static boolean hasSpaceInTank(SlimeExtractCleaningStationBlockEntity entity) {
        return entity.FLUID_TANK_WASTE.getSpace() >= 500; // TODO: Maybe not hardcode it!
    }

    private static void craftItem(SlimeExtractCleaningStationBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<GemInfusingStationRecipe> match = level.getRecipeManager()
                .getRecipeFor(GemInfusingStationRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.FLUID_TANK.drain(match.get().getFluid().getAmount(), IFluidHandler.FluidAction.EXECUTE);
            entity.itemHandler.extractItem(2,1, false);

            entity.itemHandler.setStackInSlot(3, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));
            entity.FLUID_TANK_WASTE.fill(new FluidStack(ModFluids.SOAPY_WATER_FLUID.get(), 500), IFluidHandler.FluidAction.EXECUTE);

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

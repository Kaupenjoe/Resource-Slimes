package net.kaupenjoe.resourceslimes.potion;

import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

// BetterBrewingRecipe Class by CAS-ual-TY from https://github.com/CAS-ual-TY/Extra-Potions (GPL-3.0 License)
// https://github.com/CAS-ual-TY/Extra-Potions/blob/main/LICENSE
public record ResourceSlimesBrewingRecipe(Potion input, Item ingredient, Potion output) implements IBrewingRecipe, IJeiBrewingRecipe
{

    @Override
    public boolean isInput(@NotNull ItemStack input) {
        return PotionUtils.getPotion(input) == this.input;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient) {
        return ingredient.getItem() == this.ingredient;
    }

    @Override
    public @NotNull ItemStack getOutput(@NotNull ItemStack input, @NotNull ItemStack ingredient) {
        ItemStack itemStack = new ItemStack(input.getItem());
        itemStack.setTag(new CompoundTag());
        PotionUtils.setPotion(itemStack, this.output);
        return !this.isInput(input) || !this.isIngredient(ingredient) ? ItemStack.EMPTY : itemStack;
    }

    @Override
    public @Unmodifiable List<ItemStack> getPotionInputs()
    {
        return null;
    }

    @Override
    public @Unmodifiable List<ItemStack> getIngredients()
    {
        return null;
    }

    @Override
    public ItemStack getPotionOutput()
    {
        return null;
    }

    @Override
    public int getBrewingSteps()
    {
        return 0;
    }
}
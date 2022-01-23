package co.eltrut.differentiate.common.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

public class FuelBlockItem extends BlockItem {

    private final int burnTime;

    public FuelBlockItem(Block p_40565_, Properties p_40566_, int burnTime) {
        super(p_40565_, p_40566_);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return this.burnTime;
    }

}

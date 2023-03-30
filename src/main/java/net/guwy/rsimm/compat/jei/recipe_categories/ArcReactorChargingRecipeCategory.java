package net.guwy.rsimm.compat.jei.recipe_categories;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.compat.jei.JEIRsimmPlugin;
import net.guwy.rsimm.index.ModBlocks;
import net.guwy.rsimm.recipes.ArcReactorChargingRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ArcReactorChargingRecipeCategory implements IRecipeCategory<ArcReactorChargingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(RsImm.MOD_ID, "arc_reactor_charging");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(RsImm.MOD_ID, "textures/jei/jei_this_to_that.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ArcReactorChargingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 112, 36);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ARC_REACTOR_CHARGER.get()));
    }


    @Override
    public RecipeType getRecipeType() {
        return JEIRsimmPlugin.ARC_REACTOR_CHARGING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.rsimm.arc_reactor_charger");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ArcReactorChargingRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 16, 10).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 10).addItemStack(recipe.getResultItem());

    }

}

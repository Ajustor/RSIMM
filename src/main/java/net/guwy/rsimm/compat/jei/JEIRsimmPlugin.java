package net.guwy.rsimm.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.guwy.rsimm.RsImm;
import net.guwy.rsimm.compat.jei.recipe_categories.ArcReactorChargingRecipeCategory;
import net.guwy.rsimm.content.recipe.ArcReactorChargerRecipe;
import net.guwy.rsimm.index.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIRsimmPlugin implements IModPlugin {
    public static RecipeType<ArcReactorChargerRecipe> ARC_REACTOR_CHARGING_TYPE =
            new RecipeType<>(ArcReactorChargingRecipeCategory.UID, ArcReactorChargerRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(RsImm.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                ArcReactorChargingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<ArcReactorChargerRecipe> recipesArcReactorCharging = rm.getAllRecipesFor(ArcReactorChargerRecipe.Type.INSTANCE);
        registration.addRecipes(ARC_REACTOR_CHARGING_TYPE, recipesArcReactorCharging);


    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ARC_REACTOR_CHARGER.get()), ARC_REACTOR_CHARGING_TYPE);
        IModPlugin.super.registerRecipeCatalysts(registration);
    }
}

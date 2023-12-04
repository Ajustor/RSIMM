package net.guwy.rsimm.index;

import net.guwy.rsimm.RsImm;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum RsImmArmorMaterials implements ArmorMaterial{
    TEST_ARMOR("test_armor", 2, new int[]{2, 5, 6, 2}, 9,
    SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () ->
            Ingredient.of(RsImmDeveloperItems.DEV_WAND_1.get())),


    MARK_1_ARMOR("mark_1_armor", 3, new int[]{3, 6, 8, 3}, 0,
    null, 2.0F, 0.1F, () ->
            Ingredient.of(RsImmDeveloperItems.DEV_WAND_1.get())),

    MARK_1_OPEN_ARMOR("mark_1_open_armor", 3, new int[]{3, 6, 8, 1}, 0,
    null, 2.0F, 0.1F, () ->
            Ingredient.of(RsImmDeveloperItems.DEV_WAND_1.get())),


    // Armor structure
    ARMOR_STRUCTURE("structure_armor", 3, new int[]{ 7, 9, 4}, 0,
            null, 0.0F, 0.0F, () ->
            Ingredient.of(RsImmDeveloperItems.DEV_WAND_1.get())),


    MARK_2_ARMOR("mark_2_armor", 3, new int[]{4, 7, 9, 4}, 0,
            null, 0.0F, 0.0F, () ->
            Ingredient.of(RsImmDeveloperItems.DEV_WAND_1.get())),

    MARK_2_OPEN_ARMOR("mark_2_open_armor", 3, new int[]{4, 7, 9, 1}, 0,
            null, 0.0F, 0.0F, () ->
            Ingredient.of(RsImmDeveloperItems.DEV_WAND_1.get())),


    ARC_REACTOR_CONNECTOR("arc_reactor_connector", 0, new int[]{0, 0, 0, 0}, 0,
    SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () ->
            Ingredient.of(ItemStack.EMPTY)),

    EDITH_GLASSES("edith_glasses", 0, new int[]{0, 0, 0, 0}, 0,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () ->
            Ingredient.of(ItemStack.EMPTY));




    private static final int[] HEALTH_PER_SLOT = new int[]{250, 250, 250, 250};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    RsImmArmorMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return RsImm.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}

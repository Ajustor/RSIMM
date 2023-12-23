package net.guwy.rsimm.content.items.armors.gen_2;

import net.guwy.rsimm.utils.AtmosphericDensity;
import net.guwy.rsimm.utils.EnvironmentalTemp;
import net.guwy.sticky_foundations.mechanics.air_density.AirDensitySystem;
import net.guwy.sticky_foundations.utils.ItemTagUtils;
import net.guwy.sticky_foundations.utils.NumberToTextConverter;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HullItem extends Item {
    double maxSpeed, dragAtSeaLevel, overheatTemp, freezeTemp, freezeSpeed, tempExchangeRate;


    String TEMP_TAG_KEY = "temp",
    FREEZE_TAG_KEY = "freeze",
    IS_FROZEN_TAG_KEY = "frozen";

    int ARMOR_DAMAGE_AMOUNT_ON_ICE_BREAK = 500;


    public HullItem(Properties pProperties, double maxSpeed, double dragAtSeaLevel,
                    double overheatTempC, double freezeTempC, double freezeSpeed, double tempExchangeRate) {
        super(pProperties);
        this.maxSpeed = maxSpeed;
        this.dragAtSeaLevel = dragAtSeaLevel;
        this.overheatTemp = overheatTempC + 273;
        this.freezeTemp = freezeTempC + 273;
        this.freezeSpeed = freezeSpeed;
        this.tempExchangeRate = tempExchangeRate;
    }


    /** Max speed the hull can take depending on the damage and atmospheric density */
    public double getMaxSpeed(ItemStack itemStack, Entity entity){
        double damagePercent = (double) itemStack.getDamageValue() / itemStack.getMaxDamage();
        double maxSpeed = Math.pow( this.maxSpeed * (1-damagePercent), 1f/3);
        return Math.max(0, maxSpeed / Math.min(0.00001, AtmosphericDensity.getAtmosphericDensity(entity)));
    }

    public double getDrag(ItemStack itemStack, Entity entity){
        double atmosphericDensty = AtmosphericDensity.getAtmosphericDensity(entity);
        return atmosphericDensty * dragAtSeaLevel;
    }

    public double getTemp(ItemStack itemStack){
        return ItemTagUtils.getDouble(itemStack, TEMP_TAG_KEY);
    }
    public void setTemp(ItemStack itemStack, double temp){
        ItemTagUtils.putDouble(itemStack, TEMP_TAG_KEY, temp);
    }

    public double getOverheatingTemp(){
        return this.overheatTemp;
    }
    public boolean isOverheating(ItemStack itemStack){
        return getTemp(itemStack) > getOverheatingTemp();
    }

    public boolean isFreezing(ItemStack itemStack){
        return getTemp(itemStack) < getFreezingTemp() && getFreeze(itemStack) < 1;
    }
    public boolean isFrozen(ItemStack itemStack){
        return ItemTagUtils.getBoolean(itemStack, IS_FROZEN_TAG_KEY);
    }
    public double getFreeze(ItemStack itemStack) {
        return ItemTagUtils.getDouble(itemStack, FREEZE_TAG_KEY);
    }
    public void setFreeze(ItemStack itemStack, double val){
        ItemTagUtils.putDouble(itemStack, FREEZE_TAG_KEY, val);
    }

    public double getFreezingTemp(){
        return this.freezeTemp;
    }
    public double getFreezingSpeed(){
        return this.freezeSpeed;
    }

    public double getTempExchangeRate(){
        return this.tempExchangeRate;
    }



    /** Increases the temperature by the "value * exchange rate"
     * @param hullItem the hull item to process
     *  @param val the value to increase the temp by
     *  @return the hull item with updated tags*/
    public ItemStack increaseHeat(ItemStack hullItem, double val){
        double currentTemp = getTemp(hullItem);

        currentTemp = currentTemp + (val * tempExchangeRate);

        setTemp(hullItem, currentTemp);
        return hullItem;
    }

    /** Central temperature processing function, handles external temp, freezing, thawing, etc.
     * @param hullItem Item to Process
     * @param entity entity to get the position
     * @return the item stack with the updated tags */
    public ItemStack processTemp(ItemStack hullItem, Entity entity){

        // Process external temperature
        hullItem = processExternalTemp(hullItem, entity);


        // Increase freezing if the temperature is lower than the freezing point
        hullItem = processFreezing(hullItem);

        return hullItem;
    }

    /** Breaks the ice on the hull allowing it to operate again, damages the hull depending on the amount of ice broken
     * @param hullItem Item to process
     * @return hull item with the new tags and damage*/
    public ItemStack breakTheIce(ItemStack hullItem){
        double freezingAmount = getFreeze(hullItem);

        // do not break if it isn't frozen or will freeze immediately after
        if(freezingAmount < 1 && isFrozen(hullItem)){
            ItemTagUtils.putBoolean(hullItem, IS_FROZEN_TAG_KEY, false);

            int damage = (int) (ARMOR_DAMAGE_AMOUNT_ON_ICE_BREAK * freezingAmount);
            hullItem.setDamageValue(Math.min(hullItem.getMaxDamage(), hullItem.getDamageValue() + damage));
        }

        return hullItem;
    }



    /** Equalizes the current temperature to the external temp
     * @param hullItem item to process
     * @param entity entity to get the position
     * @return the hull item with updated tags*/
    private ItemStack processExternalTemp(ItemStack hullItem, Entity entity){
        double currentTemp = getTemp(hullItem);
        double externalTemp = EnvironmentalTemp.getAreaTemp(entity);

        double exchange = externalTemp - currentTemp;
        exchange *= this.tempExchangeRate;

        setTemp(hullItem, currentTemp + exchange);
        return hullItem;
    }

    /** Increases freezing when below freezing point, decreases otherwise
     * @param hullItem item to process
     * @return the hull item with updated tags*/
    private ItemStack processFreezing(ItemStack hullItem){
        // Increase freezing if the temperature is lower than the freezing point
        if(isFreezing(hullItem)){
            double freezeAmount = getTemp(hullItem) - this.freezeTemp;
            freezeAmount *= this.freezeSpeed;
            setFreeze(hullItem, Math.min(1, getFreeze(hullItem) + freezeAmount));

            // set the frozen tag to true if the freezing is complete
            if(getFreeze(hullItem) >= 1){
                ItemTagUtils.putBoolean(hullItem, IS_FROZEN_TAG_KEY, true);
            }
        }
        // Else decrease it if there is still freezing
        else if(getFreeze(hullItem) > 0){
            double thawAmount = this.freezeTemp - getTemp(hullItem);
            thawAmount *= freezeSpeed;
            setFreeze(hullItem, Math.max(0, getFreeze(hullItem) - thawAmount));

            // remove frozen tag if completely thawed out
            if(getFreeze(hullItem) <= 0){
                ItemTagUtils.putBoolean(hullItem, IS_FROZEN_TAG_KEY, false);
            }
        }
        return hullItem;
    }



    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.rsimm.mark_2_hull.s.1"));
            String text;
            text = Component.translatable("tooltip.rsimm.mark_2_hull.s.2").getString() +
                    NumberToTextConverter.EnergyToText(pStack.getMaxDamage() - pStack.getDamageValue()) + "/" +
                    NumberToTextConverter.EnergyToText(pStack.getMaxDamage());
            pTooltipComponents.add(Component.literal(text));
            text = Component.translatable("tooltip.rsimm.mark_2_hull.s.3").getString() +
                    (Math.floor(this.maxSpeed * 10)/10) + "m/s";
            pTooltipComponents.add(Component.literal(text));
            text = Component.translatable("tooltip.rsimm.mark_2_hull.s.4").getString() +
                    (Math.floor(this.dragAtSeaLevel * 1000)/1000) + "xV";
            pTooltipComponents.add(Component.literal(text));
            text = Component.translatable("tooltip.rsimm.mark_2_hull.s.5").getString() +
                    Math.round(this.overheatTemp - 273) + "°C";
            pTooltipComponents.add(Component.literal(text));
            text = Component.translatable("tooltip.rsimm.mark_2_hull.s.6").getString() +
                    Math.round(this.freezeTemp - 273) + "°C";
            pTooltipComponents.add(Component.literal(text));
            text = Component.translatable("tooltip.rsimm.mark_2_hull.s.7").getString() +
                    (Math.floor(this.freezeSpeed * 1000)/1000) + "Δ°K/t";
            pTooltipComponents.add(Component.literal(text));
            text = Component.translatable("tooltip.rsimm.mark_2_hull.s.8").getString() +
                    (Math.floor(this.tempExchangeRate * 1000)/1000) + "Δ°K/t";
            pTooltipComponents.add(Component.literal(text));

        } else {
            pTooltipComponents.add(Component.translatable("tooltip.rsimm.mark_2_hull"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}

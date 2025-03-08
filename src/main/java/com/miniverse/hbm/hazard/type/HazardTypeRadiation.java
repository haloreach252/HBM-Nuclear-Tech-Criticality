package com.miniverse.hbm.hazard.type;

import com.miniverse.hbm.config.GeneralConfig;
import com.miniverse.hbm.hazard.modifier.HazardModifier;
import com.miniverse.hbm.items.ModItems;
import com.miniverse.hbm.util.BobMathUtil;
import com.miniverse.hbm.util.ContaminationUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class HazardTypeRadiation extends HazardTypeBase {
    @Override
    public void onUpdate(LivingEntity target, float level, ItemStack stack) {
        boolean reacher = false;

        if (target instanceof Player) {
            reacher = ((Player)target).getInventory().items.contains(ModItems.REACHER.get());
        }

        level *= stack.getCount();

        if (level > 0) {
            float rad = level / 20F;

            if (GeneralConfig.enable528.get() && reacher) {
                rad = (float) (rad / 49f); // More realistic function for 528: x / distance^2
            } else if (reacher) {
                rad = (float) BobMathUtil.squirt(rad); // Reworked radiation function: sqrt(x+1/(x+2)^2)-1/(x+2)
            }

            ContaminationUtil.contaminate(target, ContaminationUtil.HazardType.RADIATION, ContaminationUtil.ContaminationType.CREATIVE, rad);
        }
    }

    @Override
    public void updateEntity(ItemEntity item, float level) {}

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addHazardInformation(Player player, List list, float level, ItemStack stack, List<HazardModifier> modifiers) {
        level = HazardModifier.evalAllModifiers(stack, player, level, modifiers);

        if (level < 1e-5) {
            return;
        }

        list.add(ChatFormatting.GREEN + "[" + I18n.get("trait.radioactive") + "]");
        String rad = "" + (Math.floor(level * 1000) / 1000);
        list.add(ChatFormatting.YELLOW + (rad + "RAD/s"));

        if (stack.getCount() > 1) {
            list.add(ChatFormatting.YELLOW + "Stack: " + ((Math.floor(level * 1000 * stack.getCount()) / 1000) + "RAD/s"));
        }
    }
}

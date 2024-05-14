/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm;

import net.ctrlaltmilk.mtm.capability.CasterCapability;
import net.ctrlaltmilk.mtm.item.CastingFocus;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(MagicTestMod.MOD_ID)
@Mod.EventBusSubscriber
public class MagicTestMod {
    public static final String MOD_ID = "mtm";

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        var focus = CastingFocus.get(event.getItemStack());

        if (focus != null) {
            event.getEntity().startUsingItem(event.getHand());
            event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide()));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onStartUsing(LivingEntityUseItemEvent.Start event) {
        var focus = CastingFocus.get(event.getItem());

        if (focus != null) {
            event.getEntity().getCapability(CasterCapability.CAPABILITY).ifPresent(CasterCapability::startCast);
            event.setDuration(72000);
        }
    }

    @SubscribeEvent
    public static void onStopUsing(LivingEntityUseItemEvent.Stop event) {
        event.getEntity().getCapability(CasterCapability.CAPABILITY).ifPresent(CasterCapability::endCast);
    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(CasterCapability.KEY, new CasterCapability());
        }
    }
}

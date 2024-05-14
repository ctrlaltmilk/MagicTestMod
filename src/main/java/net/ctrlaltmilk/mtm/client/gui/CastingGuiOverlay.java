/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm.client.gui;

import com.google.common.base.Joiner;
import net.ctrlaltmilk.mtm.capability.CasterCapability;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class CastingGuiOverlay implements IGuiOverlay {
    private static final ResourceLocation ALT_FONT = new ResourceLocation("minecraft", "alt");
    private static final Style STYLE = Style.EMPTY.withFont(ALT_FONT);

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        var player = gui.getMinecraft().player;

        if (player == null) return;

        var font = gui.getFont();
        var pose = guiGraphics.pose();

        var optionalCap = player.getCapability(CasterCapability.CAPABILITY);

        if (!optionalCap.isPresent()) return;
        var cap = optionalCap.orElseThrow(RuntimeException::new);

        if (cap.currentlyCasting()) {
            var glyphString = Joiner.on("").join(cap.getGlyphSequence());
            var glyphComponent = Component.literal(glyphString).withStyle(STYLE);

            pose.pushPose();
            pose.translate(screenWidth / 2.0, screenHeight / 2.0, 0);
            pose.scale(4.0F, 4.0F, 4.0F);

            var width = font.width(glyphComponent);
            guiGraphics.drawString(font, glyphComponent, width / 2, -10, 0xFFFFFF);

            pose.popPose();
        }
    }
}

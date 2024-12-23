/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm.client.gui;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.ctrlaltmilk.mtm.attachment.MTMAttachments.SPELL_PROGRESS;
import static net.minecraft.client.Minecraft.ALT_FONT;

@ParametersAreNonnullByDefault
public class SpellGuiLayer implements LayeredDraw.Layer {
    private static final Style STYLE = Style.EMPTY.withFont(ALT_FONT);

    @Override
    public void render(GuiGraphics gui, DeltaTracker delta) {
        var minecraft = Minecraft.getInstance();

        if (minecraft.player != null) {
            var spellData = minecraft.player.getData(SPELL_PROGRESS);

            if (spellData.isCasting()) {
                var pose = gui.pose();

                pose.pushPose();
                pose.translate(gui.guiWidth() / 2.0, gui.guiHeight() / 2.0, 0);
                pose.scale(4.0f, 4.0f, 4.0f);

                var fmtCharSeq = FormattedCharSequence.forward(spellData.getSpellText(), STYLE);

                gui.drawCenteredString(minecraft.font, fmtCharSeq, 0, 5, 0xFFFFFFFF);
                pose.popPose();
            }
        }
    }
}

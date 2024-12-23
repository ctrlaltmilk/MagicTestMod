/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm;

import net.ctrlaltmilk.mtm.attachment.MTMAttachments;
import net.ctrlaltmilk.mtm.item.MTMItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(MagicTestMod.MOD_ID)
public class MagicTestMod {
    public static final String MOD_ID = "mtm";

    public MagicTestMod(IEventBus modBus) {
        MTMAttachments.ATTACHMENT_TYPES.register(modBus);
        MTMItems.ITEMS.register(modBus);
    }
}

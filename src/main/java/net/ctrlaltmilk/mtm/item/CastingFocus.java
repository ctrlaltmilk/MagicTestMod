/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public interface CastingFocus {
    CastingFocus STICK = new CastingFocus() {};

    static CastingFocus get(ItemStack item) {
        if (item.getItem() instanceof CastingFocus focus) {
            return focus;
        } else if (item.getItem() == Items.STICK && item.getHoverName().getString().equals("Wand")) {
            return STICK;
        }

        return null;
    }
}

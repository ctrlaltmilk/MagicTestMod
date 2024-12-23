/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.ctrlaltmilk.mtm.MagicTestMod.MOD_ID;

public class MTMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final Supplier<FocusItem> TEST_FOCUS = ITEMS.register(
            "test_focus", () -> new FocusItem(new Item.Properties().durability(384)));
}

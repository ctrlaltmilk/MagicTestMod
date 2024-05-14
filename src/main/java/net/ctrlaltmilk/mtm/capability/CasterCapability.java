/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.ctrlaltmilk.mtm.MagicTestMod.MOD_ID;

public class CasterCapability implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<CasterCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    public static final ResourceLocation KEY = new ResourceLocation(MOD_ID, "caster");

    private final LazyOptional<CasterCapability> capability = LazyOptional.of(() -> this);
    private boolean currentlyCasting = false;
    private final List<Character> glyphs = new ArrayList<>(16);

    public void startCast() {
        glyphs.clear();
        currentlyCasting = true;
    }

    public void endCast() {
        currentlyCasting = false;
    }

    public boolean currentlyCasting() {
        return currentlyCasting;
    }

    public List<Character> getGlyphSequence() {
        return List.copyOf(glyphs);
    }

    public void pushGlyph(char key) {
        glyphs.add(key);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return CAPABILITY.orEmpty(cap, this.capability);
    }

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }
}

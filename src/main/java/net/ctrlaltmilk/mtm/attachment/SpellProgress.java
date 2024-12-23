/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.ctrlaltmilk.mtm.attachment;

public class SpellProgress {
    private boolean casting;
    private final char[] glyphArray = new char[16];
    private byte glyphs;

    public boolean isCasting() {
        return casting;
    }

    public void startCast() {
        glyphs = 0;
        casting = true;
    }

    public void addGlyph(char c) {
        if (glyphs < glyphArray.length) {
            glyphArray[glyphs++] = c;
        }
    }

    public void endCast() {
        casting = false;
    }

    public String getSpellText() {
        return new String(glyphArray, 0, glyphs);
    }
}

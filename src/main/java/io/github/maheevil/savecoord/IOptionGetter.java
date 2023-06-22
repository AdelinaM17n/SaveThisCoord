package io.github.maheevil.savecoord;

import net.minecraft.client.KeyMapping;

public interface IOptionGetter {
    KeyMapping getCoordQuickSaveKey();
    KeyMapping getCoordCopyKey();
    KeyMapping getCoordSaveKey();
}

package io.github.maheevil.savecoord.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.maheevil.savecoord.IOptionGetter;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class OptionsMixin implements IOptionGetter {
    @Shadow
    @Final
    @Mutable
    public KeyMapping[] keyMappings;

    public KeyMapping quickSaveMapping;
    public KeyMapping copyKeyMapping;
    public KeyMapping saveCoordKeyMapping;

    @SuppressWarnings("NoTranslation")
    @Inject(method = "<init>", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/client/Options;load()V"))
    private void initKeys(CallbackInfo ci) {
        quickSaveMapping = new KeyMapping("Quick Save Key", GLFW.GLFW_KEY_O,"SaveThisCoord");
        copyKeyMapping = new KeyMapping("Optional : Dedicated Copy Key", InputConstants.UNKNOWN.getValue(), "SaveThisCoord");
        saveCoordKeyMapping = new KeyMapping("Optional: Dedicated Save Key", InputConstants.UNKNOWN.getValue(), "SaveThisCoord");

        keyMappings = ArrayUtils.addAll(keyMappings,
                quickSaveMapping,
                copyKeyMapping,
                saveCoordKeyMapping
        );
    }

    @Override
    public KeyMapping getCoordQuickSaveKey(){
        return quickSaveMapping;
    }

    @Override
    public KeyMapping getCoordCopyKey(){
        return copyKeyMapping;
    }

    @Override
    public KeyMapping getCoordSaveKey(){
        return saveCoordKeyMapping;
    }
}

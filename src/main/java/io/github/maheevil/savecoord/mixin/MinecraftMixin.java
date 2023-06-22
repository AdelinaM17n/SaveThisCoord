package io.github.maheevil.savecoord.mixin;

import io.github.maheevil.savecoord.IOptionGetter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Final public Options options;

    @Shadow @Nullable public LocalPlayer player;

    @Inject(
            method = "handleKeybinds",
            at = @At("RETURN")
    )
    private void handleKeybinds$NoDoubleSlabPlacement(CallbackInfo callbackInfo){
        while(((IOptionGetter) options).getCoordQuickSaveKey().consumeClick()){
            // Only called when no gui is opened
            if(Screen.hasShiftDown() && ((IOptionGetter) options).getCoordSaveKey().isDefault()){
                handleCoordSave();

            }else if(Screen.hasControlDown() && ((IOptionGetter) options).getCoordSaveKey().isDefault()){
                handleCoordCopy();

            }
            assert player != null;
            String shortString = player.blockPosition().toShortString();
            handleQuickSave();
        }
    }

    private void handleQuickSave(){

    }

    private void handleCoordSave(){

    }

    private void handleCoordCopy(){

    }
}

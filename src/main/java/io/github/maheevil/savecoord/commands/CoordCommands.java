package io.github.maheevil.savecoord.commands;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;

public class CoordCommands {
    public static Minecraft instance = Minecraft.getInstance();
    @SuppressWarnings("SameReturnValue")
    public static int SendLocCommand(CommandContext<FabricClientCommandSource> context, boolean hasPlayer){
        assert instance.player != null;

        LocalPlayer localPlayer = instance.player;
        String playerLocation = localPlayer.blockPosition().toShortString();

        if(hasPlayer){
            // may the deity you believe in have mercy for this, gods given up on me long ago
            String playerInput = context.getInput().split(" ")[1];
            localPlayer.connection.sendCommand("/msg " + playerInput + " " + playerLocation);
            return 1;
        }

        localPlayer.connection.sendChat(playerLocation);

        return 1;
    }

    public static int CopyLocCommand(CommandContext<FabricClientCommandSource> context){
        assert instance.player != null;

        String postString = instance.player.blockPosition().toShortString();
        instance.keyboardHandler.setClipboard(postString);
        instance.player.sendSystemMessage(Component.literal("Location " + postString + " successfully saved"));

        return 1;
    }

    public static int SaveLocCommand(){
        return 1;
    }
}

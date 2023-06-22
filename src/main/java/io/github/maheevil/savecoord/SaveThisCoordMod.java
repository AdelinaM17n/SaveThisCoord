package io.github.maheevil.savecoord;

import io.github.maheevil.savecoord.commands.CoordCommands;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.commands.arguments.EntityArgument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveThisCoordMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("savecoords");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		LOGGER.info("Hello Fabric world!");
		initCommands();

		// TODO - USE THE FUCKING API GIRL
		// hello it is future me, I am too lazy + mixins work
		/* KeyMapping saveKeyMap = KeyBindingHelper.registerKeyBinding(
				new KeyMapping("SaveThisCoord Toggle", GLFW.GLFW_KEY_O, "SaveThisCoord")
		); */

	}

	private static void initCommands(){
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
					dispatcher.register(
							ClientCommandManager.literal("sendLoc")
									.executes(
											context -> CoordCommands.SendLocCommand(context, false)
									)
									.then(
											ClientCommandManager.argument("player", EntityArgument.players())
													.executes(
															context ->
																	CoordCommands.SendLocCommand(
																			context,
																			true
																	)
													)
									)
					);

					dispatcher.register(
							ClientCommandManager.literal("copyLoc")
									.executes(
											CoordCommands::CopyLocCommand
									)
					);
				}
		);
		System.err.println("EA GAMES");
	}
}

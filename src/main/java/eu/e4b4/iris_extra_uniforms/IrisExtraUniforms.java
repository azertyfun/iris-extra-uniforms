package eu.e4b4.iris_extra_uniforms;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Enderman;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ReadOnlyScoreInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IrisExtraUniforms implements ModInitializer {
	public static final String MOD_ID = "iris-extra-uniforms";
	public static final int SCOREBOARD_OBJECTIVE_COUNT = 8;

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		PayloadTypeRegistry.clientboundPlay().register(UniformsPayload.TYPE, UniformsPayload.CODEC);
		ServerTickEvents.START_LEVEL_TICK.register(level -> {
			for (ServerPlayer player : PlayerLookup.level(level)) {
				boolean targetedByEnderman = !level.getEntitiesOfClass(Enderman.class, player.getBoundingBox().inflate(100.0), enderman -> enderman.getTarget() == player).isEmpty();

				long[] scoreboards = new long[SCOREBOARD_OBJECTIVE_COUNT];
				for (int i = 0; i < SCOREBOARD_OBJECTIVE_COUNT; ++i) {
					Objective objective = level.getScoreboard().getObjective("iris-uniforms-" + i);
					ReadOnlyScoreInfo score = level.getScoreboard().getPlayerScoreInfo(player, objective);
					scoreboards[i] = score == null ? 0 : score.value();
				}

				ServerPlayNetworking.send(player, new UniformsPayload(targetedByEnderman, scoreboards));
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

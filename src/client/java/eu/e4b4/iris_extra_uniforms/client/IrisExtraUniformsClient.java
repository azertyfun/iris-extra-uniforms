package eu.e4b4.iris_extra_uniforms.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import eu.e4b4.iris_extra_uniforms.IrisExtraUniforms;
import eu.e4b4.iris_extra_uniforms.UniformsPayload;
import net.fabricmc.api.ClientModInitializer;

public class IrisExtraUniformsClient implements ClientModInitializer {
	public static boolean targeted_by_enderman = false;
	public static long scoreboards[] = new long[IrisExtraUniforms.SCOREBOARD_OBJECTIVE_COUNT];

	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(UniformsPayload.TYPE, (payload, context) -> {
			IrisExtraUniformsClient.targeted_by_enderman = payload.targetedByEnderman();
			IrisExtraUniformsClient.scoreboards = payload.scoreboards();
		});
	}
}

package eu.e4b4.iris_extra_uniforms.client.mixin;

import eu.e4b4.iris_extra_uniforms.client.IrisExtraUniformsClient;
import eu.e4b4.iris_extra_uniforms.IrisExtraUniforms;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.irisshaders.iris.uniforms.IrisExclusiveUniforms;
import net.irisshaders.iris.gl.uniform.UniformHolder;
import net.irisshaders.iris.gl.uniform.UniformUpdateFrequency;
import net.irisshaders.iris.uniforms.FrameUpdateNotifier;

@Mixin(IrisExclusiveUniforms.class)
public class UniformMixin {
	@Inject(at = @At("HEAD"), method = "addIrisExclusiveUniforms")
	private static void addUniformsNewIris(UniformHolder uniforms, FrameUpdateNotifier updateNotifier, CallbackInfo ci) {
		uniforms.uniform1b(UniformUpdateFrequency.PER_TICK, "eu_e4b4_ieu_targeted_by_enderman", () -> IrisExtraUniformsClient.targeted_by_enderman);

		for (int i = 0; i < IrisExtraUniforms.SCOREBOARD_OBJECTIVE_COUNT; ++i) {
			final int idx = i;
			uniforms.uniform1i(UniformUpdateFrequency.PER_TICK, "eu_e4b4_ieu_scoreboard_" + idx, () -> (int) IrisExtraUniformsClient.scoreboards[idx]);
		}
	}
}

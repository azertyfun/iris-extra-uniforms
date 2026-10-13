package eu.e4b4.iris_extra_uniforms;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record UniformsPayload(boolean targetedByEnderman, long[] scoreboards) implements CustomPacketPayload {
    public static final Identifier UNIFORMS_PAYLOAD_ID = IrisExtraUniforms.id("uniforms");
    public static final CustomPacketPayload.Type<UniformsPayload> TYPE = new CustomPacketPayload.Type<>(UNIFORMS_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, UniformsPayload> CODEC = StreamCodec.composite(
        ByteBufCodecs.BOOL, UniformsPayload::targetedByEnderman,
        ByteBufCodecs.LONG_ARRAY, UniformsPayload::scoreboards,
        UniformsPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

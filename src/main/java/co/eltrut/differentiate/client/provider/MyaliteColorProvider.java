package co.eltrut.differentiate.client.provider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.List;

/**
 * author: Vazkii
 */
public class MyaliteColorProvider {
	
	public static final float myaliteS = 0.7F;
	public static final float myaliteB = 0.8F;
	public static final PerlinSimplexNoise NOISE = new PerlinSimplexNoise(new LegacyRandomSource(4543543),
			List.of(-4, -3, -2, -1, 0, 1, 2, 3, 4));
	
    @OnlyIn(Dist.CLIENT)
	public static BlockColor getBlockColor() {
		return (state, world, pos, tintIndex) -> getColor(pos);
	}
	
    @OnlyIn(Dist.CLIENT)
	public static ItemColor getItemColor() {
		return (stack, tintIndex) -> {
			Minecraft mc = Minecraft.getInstance();
			if(mc.player == null)
				return getColor(BlockPos.ZERO);
			
			BlockPos pos = mc.player.blockPosition();
			HitResult res = mc.hitResult;
			if(res instanceof BlockHitResult)
				pos = ((BlockHitResult) res).getBlockPos();
			
			return getColor(pos);
		};
	}

	public static int getColor(BlockPos pos) {
		final float sp = 0.15f;
		final double range = 0.3;
		final double shift = 0.05;

		if(pos == null)
			pos = BlockPos.ZERO;

		float x = pos.getX() * sp;
		float y = pos.getY() * sp;
		float z = pos.getZ() * sp;

		double xv = x + Mth.sin(z) * 2;
		double zv = z + Mth.cos(x) * 2;
		double yv = y + Mth.sin(y + Mth.PI / 4) * 2;

		double noiseVal = NOISE.getValue(xv + yv, zv + (yv * 2), false);

		double h = noiseVal * (range / 2) - range + shift;

		return Color.HSBtoRGB((float) h, myaliteS, myaliteB);
	}
	
}


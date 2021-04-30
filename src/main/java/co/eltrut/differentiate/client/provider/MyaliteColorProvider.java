package co.eltrut.differentiate.client.provider;

import java.awt.Color;
import java.util.stream.IntStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MyaliteColorProvider {
	
	public static final float myaliteS = 0.7F;
	public static final float myaliteB = 0.8F;
	public static final PerlinNoiseGenerator NOISE = new PerlinNoiseGenerator(new SharedSeedRandom(4543543), IntStream.rangeClosed(-4, 4));
	
    @OnlyIn(Dist.CLIENT)
	public static IBlockColor getBlockColor() {
		return (state, world, pos, tintIndex) -> getColor(pos, myaliteS, myaliteB);
	}
	
    @OnlyIn(Dist.CLIENT)
	public static IItemColor getItemColor() {
		return (stack, tintIndex) -> {
			Minecraft mc = Minecraft.getInstance();
			if(mc.player == null)
				return getColor(BlockPos.ZERO, myaliteS, myaliteB);
			
			BlockPos pos = mc.player.blockPosition();
			RayTraceResult res = mc.hitResult;
			if(res != null && res instanceof BlockRayTraceResult)
				pos = ((BlockRayTraceResult) res).getBlockPos();
			
			return getColor(pos, myaliteS, myaliteB);
		};
	}
	
	public static int getColor(BlockPos pos, float s, float b) {
		final double sp = 0.15;
    	final double range = 0.3;
    	final double shift = 0.05;
	
		double x = pos.getX() * sp;
		double y = pos.getY() * sp;
		double z = pos.getZ() * sp;
		
		double xv = x + Math.sin(z) * 2;
		double zv = z + Math.cos(x) * 2;
		double yv = y + Math.sin(y + Math.PI / 4) * 2;
		
		double noise = NOISE.getValue(xv + yv, zv + (yv * 2), false);
		
    	double h = noise * (range / 2) - range + shift;

		return Color.HSBtoRGB((float) h, s, b);
    }
	
}


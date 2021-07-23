package co.eltrut.differentiate.client.provider;

import java.awt.Color;
import java.util.stream.IntStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.SimpleRandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MyaliteColorProvider {
	
	public static final float myaliteS = 0.7F;
	public static final float myaliteB = 0.8F;
	public static final PerlinNoise NOISE = new PerlinNoise(new SimpleRandomSource(4543543), IntStream.rangeClosed(-4, 4));
	
    @OnlyIn(Dist.CLIENT)
	public static BlockColor getBlockColor() {
		return (state, world, pos, tintIndex) -> getColor(pos, myaliteS, myaliteB);
	}
	
    @OnlyIn(Dist.CLIENT)
	public static ItemColor getItemColor() {
		return (stack, tintIndex) -> {
			Minecraft mc = Minecraft.getInstance();
			if(mc.player == null)
				return getColor(BlockPos.ZERO, myaliteS, myaliteB);
			
			BlockPos pos = mc.player.blockPosition();
			HitResult res = mc.hitResult;
			if(res != null && res instanceof BlockHitResult)
				pos = ((BlockHitResult) res).getBlockPos();
			
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
		
		double noise = NOISE.getValue(xv + yv, zv + (yv * 2), 0.0D);
		
    	double h = noise * (range / 2) - range + shift;

		return Color.HSBtoRGB((float) h, s, b);
    }
	
}


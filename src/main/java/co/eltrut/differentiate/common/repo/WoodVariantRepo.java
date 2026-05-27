package co.eltrut.differentiate.common.repo;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WoodVariantRepo {

    private final VariantBlocksRepo strippedWoods;
    private final VariantBlocksRepo woods;

    public WoodVariantRepo(VariantBlocksRepo strippedWoods, VariantBlocksRepo woods) {
        this.strippedWoods = strippedWoods;
        this.woods = woods;
    }

    public VariantBlocksRepo getStrippedWoods() {
        return this.strippedWoods;
    }

    public VariantBlocksRepo getWoods() {
        return this.woods;
    }

    public Map<DeferredBlock<Block>, DeferredBlock<Block>> getBlocksAsMap() {
        Map<DeferredBlock<Block>, DeferredBlock<Block>> map = new HashMap<>();

        List<DeferredBlock<Block>> woodList = this.woods.getBlocksAsList();
        List<DeferredBlock<Block>> strippedList = this.strippedWoods.getBlocksAsList();

        for (int i = 0; i < woodList.size(); i++)
            map.put(woodList.get(i), strippedList.get(i));

        return map;
    }

}

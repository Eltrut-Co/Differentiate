package co.eltrut.differentiate.common.repo;

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

}

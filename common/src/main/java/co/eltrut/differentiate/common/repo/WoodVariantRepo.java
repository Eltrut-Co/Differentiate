package co.eltrut.differentiate.common.repo;

public record WoodVariantRepo(VariantBlocksRepo strippedWoods,
        VariantBlocksRepo woods) {

    public VariantBlocksRepo getStrippedWoods() {
        return this.strippedWoods;
    }

    public VariantBlocksRepo getWoods() {
        return this.woods;
    }
}
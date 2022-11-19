package co.eltrut.differentiate.platform.fabric;

import co.eltrut.differentiate.platform.Instance;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class InstanceBuilderImpl {
    public static Instance builder(String modId, Runnable common, Runnable postCommon, Runnable client, Runnable postClient) {
        return new Instance(modId, common, postCommon, client, postClient) {
            @Override
            public void load() {
                this.onCommon.run();
                this.onPostCommon.run();
                if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                    this.onClient.run();
                    this.onPostClient.run();
                }
            }
        };
    }
}
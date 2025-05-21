package eu.midnightdust.polyposa;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.extras.api.ResourcePackExtras;
import net.fabricmc.api.ModInitializer;

import static dev.doctor4t.mariposa.Mariposa.id;

public class PolymerMariposaMain implements ModInitializer {
    public void onInitialize() {
        PolymerResourcePackUtils.addModAssets("mariposa");
        PolymerResourcePackUtils.addModAssets("polyposa");
        ResourcePackExtras.forDefault().addBridgedModelsFolder(id("block"), id("item"));
    }
}

package eu.midnightdust.polyposa.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.doctor4t.mariposa.Mariposa;
import dev.doctor4t.mariposa.index.MariposaItems;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Function;

@Mixin(value = MariposaItems.class, remap = false)
public interface MixinMariposaItems {
    @WrapMethod(method = "create")
    private static Item polyposa$polymerBlockItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings, Operation<Item> original) {
        return switch (name) { // TODO make the items placeable
            case "sequoia_hanging_sign" -> Items.register(RegistryKey.of(RegistryKeys.ITEM, Mariposa.id(name)), (itemSettings) -> new SimplePolymerItem(itemSettings, Items.FLINT, true), settings);
            case "sequoia_sign" -> Items.register(RegistryKey.of(RegistryKeys.ITEM, Mariposa.id(name)), (itemSettings) -> new SimplePolymerItem(itemSettings, Items.FLINT, true), settings);
            case "sequoia_door" -> Items.register(RegistryKey.of(RegistryKeys.ITEM, Mariposa.id(name)), (itemSettings) -> new SimplePolymerItem(itemSettings, Items.FLINT, true), settings);
            default -> original.call(name, factory, settings);
        };
    }
}

package eu.midnightdust.polyposa.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.doctor4t.mariposa.index.MariposaBlocks;
import dev.doctor4t.mariposa.index.MariposaItems;
import eu.midnightdust.polyposa.block.*;
import eu.pb4.factorytools.api.item.FactoryBlockItem;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Function;

import static dev.doctor4t.mariposa.index.MariposaBlocks.*;

@Mixin(value = MariposaBlocks.class, remap = false)
public interface MixinMariposaBlocks {
    @WrapOperation(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Blocks;register(Lnet/minecraft/registry/RegistryKey;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;"),
            method = "create(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/block/AbstractBlock$Settings;)Lnet/minecraft/block/Block;")
    private static Block polyposa$redirectBlockFactories(RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, Operation<Block> original) {
        factory = switch (key.getValue().getPath()) {
            case "sequoia_fence" -> (set) -> new PolymerFenceBlock(set, Blocks.MANGROVE_FENCE);
            case "sequoia_fence_gate" -> (set) -> new PolymerFenceGateBlock(SEQUOIA_WOOD_TYPE, set, Blocks.MANGROVE_FENCE_GATE);
            case "potted_sequoia_sapling" -> (set) -> new PottedPolymerPlant(SEQUOIA_SAPLING, set, true);
            case "sequoia_sapling" -> (set) -> new PolymerSaplingBlock(SEQUOIA_SAPLING_GENERATOR, set);
            case "sequoia_button" -> (set) -> new PolymerButtonBlock(SEQUOIA_WOOD_TYPE.setType(), 30, set, Blocks.MANGROVE_BUTTON);
            case "sequoia_slab" -> (set) -> new PolymerSlabBlock(SEQUOIA_PLANKS.getDefaultState(), set);
            case "sequoia_stairs" -> (set) -> new PolymerStairsBlock(SEQUOIA_PLANKS.getDefaultState(), set, Blocks.MANGROVE_STAIRS);
            case "sequoia_door" -> PolymerDoorBlock::new;
            case "sequoia_trapdoor" -> PolymerTrapdoorBlock::new;
            case "sequoia_pressure_plate" -> (set) -> new PolymerPressurePlate(SEQUOIA_WOOD_TYPE.setType(), set, Blocks.MANGROVE_PRESSURE_PLATE);
            case "sequoia_wall_hanging_sign" -> (set) -> new PolymerWallHangingSignBlock(WoodType.OAK, set, Blocks.MANGROVE_WALL_HANGING_SIGN);
            case "sequoia_hanging_sign" -> (set) -> new PolymerHangingSignBlock(WoodType.OAK, set, Blocks.MANGROVE_HANGING_SIGN);
            case "sequoia_wall_sign" -> (set) -> new PolymerWallSignBlock(WoodType.OAK, set, Blocks.MANGROVE_WALL_SIGN);
            case "sequoia_sign" -> (set) -> new PolymerSignBlock(WoodType.OAK, set, Blocks.MANGROVE_SIGN);
            case "sequoia_leaves" -> SequoiaPolymerLeaves::new;
            case "sequoia_log", "stacked_sequoia_logs", "stripped_sequoia_log", "sequoia_wood", "stripped_sequoia_wood" -> PillarPolymerBlock::new;
            default -> TexturedPolymerBlock::new;
        };
        return original.call(key, factory, settings);
    }

    @WrapOperation(at = @At(value = "INVOKE", target = "Ldev/doctor4t/mariposa/index/MariposaItems;create(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;"), method = "createWithItem")
    private static Item polyposa$polymerBlockItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings, Operation<Item> original, @Local Block block) {
        return MariposaItems.create(name, (itemSettings) -> new FactoryBlockItem((Block & PolymerBlock) block, itemSettings, Items.FLINT), settings);
    }
}

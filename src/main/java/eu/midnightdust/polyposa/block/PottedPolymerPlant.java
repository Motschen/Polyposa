package eu.midnightdust.polyposa.block;

import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

public class PottedPolymerPlant extends FlowerPotBlock implements FactoryBlock {
    private final ItemStack MODEL;

    public PottedPolymerPlant(Block content, Settings settings, boolean useExtraModel) {
        super(content, settings);
        Identifier id = Identifier.tryParse(this.getTranslationKey().replace("block.", "").replace(".", ":"));
        MODEL = ItemDisplayElementUtil.getModel(Identifier.of(id.getNamespace(),"block/%s".formatted(useExtraModel ? id.getPath() : id.getPath().replace("potted_", ""))));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return Blocks.FLOWER_POT.getDefaultState();
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return new ItemDisplayPottedPlantModel(pos);
    }

    private class ItemDisplayPottedPlantModel extends BlockModel {

        public ItemDisplayPottedPlantModel(BlockPos pos) {
            ItemDisplayElement main = ItemDisplayElementUtil.createSimple(MODEL);
            main.setScale(new Vector3f(0.98f));
            main.setDisplaySize(1, 1);
            int rotation = pos.hashCode() % 4 * 90;
            main.setRightRotation(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));
            this.addElement(main);
        }
    }
}
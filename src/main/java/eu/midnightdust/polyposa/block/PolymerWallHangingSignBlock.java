package eu.midnightdust.polyposa.block;

import eu.pb4.factorytools.api.block.FactoryBlock;
import eu.pb4.factorytools.api.virtualentity.BlockModel;
import eu.pb4.factorytools.api.virtualentity.ItemDisplayElementUtil;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.attachment.BlockBoundAttachment;
import eu.pb4.polymer.virtualentity.api.attachment.HolderAttachment;
import eu.pb4.polymer.virtualentity.api.elements.ItemDisplayElement;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerWallHangingSignBlock extends WallHangingSignBlock implements FactoryBlock {
    private final Identifier id;
    private final Block template;

    public PolymerWallHangingSignBlock(WoodType woodType, Settings settings, Block template) {
        super(woodType, settings);
        this.template = template;
        id = Identifier.tryParse(this.getTranslationKey().replace("block.", "").replace(".", ":"));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext packetContext) {
        return template.getDefaultState().with(FACING, state.get(FACING)).with(WATERLOGGED, state.get(WATERLOGGED));
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerWorld world, BlockPos pos, BlockState initialBlockState) {
        return new PolymerWallHangingSignBlock.Model(initialBlockState, id);
    }

    public static final class Model extends BlockModel {
        public ItemDisplayElement main;

        public Model(BlockState state, Identifier id) {
            main = ItemDisplayElementUtil.createSimple(ItemDisplayElementUtil.getModel(Identifier.of(id.getNamespace(), "block/"+id.getPath())));
            this.updateItem(state);
            addElement(main);
        }
        private void updateItem(BlockState state) {
            float scale = 1.0075f;
            main.setScale(new Vector3f(scale*2f));
            main.setTranslation(new Vector3f(0, 0, 0));
            main.setYaw(state.get(FACING).getPositiveHorizontalDegrees());
        }

        @Override
        public void notifyUpdate(HolderAttachment.UpdateType updateType) {
            if (updateType == BlockBoundAttachment.BLOCK_STATE_UPDATE) {
                updateItem(this.blockState());
                this.tick();
            }
            super.notifyUpdate(updateType);
        }
    }
}

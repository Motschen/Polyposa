package eu.midnightdust.polyposa.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

import static dev.doctor4t.mariposa.Mariposa.id;

public class PillarPolymerBlock extends PillarBlock implements PolymerTexturedBlock {
    private final BlockState[] model = new BlockState[3];

    public PillarPolymerBlock(Settings settings) {
        super(settings);
        Identifier id = Identifier.tryParse(this.getTranslationKey().replace("block.", "").replace(".", ":"));
        model[0] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(id("block/"+id.getPath()), 90, 90));
        model[1] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(id("block/"+id.getPath()), 0, 0));
        model[2] = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(id("block/"+id.getPath()), 90, 0));
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.OAK_LOG.getDefaultState();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return switch (state.get(PillarBlock.AXIS)) {
            case X -> model[0];
            case Y -> model[1];
            case Z -> model[2];
        };
    }
}

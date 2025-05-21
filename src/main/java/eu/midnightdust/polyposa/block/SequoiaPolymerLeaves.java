package eu.midnightdust.polyposa.block;

import dev.doctor4t.mariposa.block.SequoiaLeavesBlock;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class SequoiaPolymerLeaves extends SequoiaLeavesBlock implements PolymerTexturedBlock {
    private final BlockState MODEL;

    public SequoiaPolymerLeaves(Settings settings) {
        this(settings, BlockModelType.TRANSPARENT_BLOCK);
    }

    public SequoiaPolymerLeaves(Settings settings, BlockModelType blockModelType) {
        super(1, settings);
        Identifier id = Identifier.tryParse(this.getTranslationKey().replace("block.", "").replace(".", ":"));
        MODEL = PolymerBlockResourceUtils.requestBlock(blockModelType, PolymerBlockModel.of(Identifier.of(id.getNamespace(), "block/"+id.getPath())));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return MODEL;
    }
}

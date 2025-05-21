package eu.midnightdust.polyposa.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class TexturedPolymerBlock extends Block implements PolymerTexturedBlock {
    private final BlockState MODEL;

    public TexturedPolymerBlock(Settings settings) {
        this(settings, BlockModelType.FULL_BLOCK);
    }

    public TexturedPolymerBlock(Settings settings, BlockModelType blockModelType) {
        super(settings);
        Identifier id = Identifier.tryParse(this.getTranslationKey().replace("block.", "").replace(".", ":"));
        MODEL = PolymerBlockResourceUtils.requestBlock(blockModelType, PolymerBlockModel.of(Identifier.of(id.getNamespace(), "block/"+id.getPath())));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return MODEL;
    }
}

package eu.midnightdust.polyposa.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerSaplingBlock extends SaplingBlock implements PolymerTexturedBlock {
    private final BlockState model;

    public PolymerSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
        Identifier id = Identifier.tryParse(this.getTranslationKey().replace("block.", "").replace(".", ":"));
        model = PolymerBlockResourceUtils.requestBlock(BlockModelType.PLANT_BLOCK, PolymerBlockModel.of(Identifier.of(id.getNamespace(), "block/"+id.getPath())));
    }

    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return model;
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return model;
    }
}

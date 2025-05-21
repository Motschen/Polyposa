package eu.midnightdust.polyposa.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerSlabBlock extends SlabBlock implements PolymerTexturedBlock {
    private final BlockState TOP_SLAB;
    private final BlockState TOP_SLAB_WATERLOGGED;
    private final BlockState BOTTOM_SLAB;
    private final BlockState BOTTOM_SLAB_WATERLOGGED;
    private final BlockState DOUBLE;

    public PolymerSlabBlock(BlockState base, Settings settings) {
        super(settings);
        DOUBLE = base;
        Identifier id = Identifier.tryParse(this.getTranslationKey().replace("block.", "").replace(".", ":"));
        TOP_SLAB = PolymerBlockResourceUtils.requestBlock(BlockModelType.TOP_SLAB, PolymerBlockModel.of(Identifier.of(id.getNamespace(), "block/" + id.getPath() + "_top")));
        TOP_SLAB_WATERLOGGED = PolymerBlockResourceUtils.requestBlock(BlockModelType.TOP_SLAB_WATERLOGGED, PolymerBlockModel.of(Identifier.of(id.getNamespace(), "block/" + id.getPath() + "_top")));
        BOTTOM_SLAB = PolymerBlockResourceUtils.requestBlock(BlockModelType.BOTTOM_SLAB, PolymerBlockModel.of(Identifier.of(id.getNamespace(), "block/" + id.getPath())));
        BOTTOM_SLAB_WATERLOGGED = PolymerBlockResourceUtils.requestBlock(BlockModelType.BOTTOM_SLAB_WATERLOGGED, PolymerBlockModel.of(Identifier.of(id.getNamespace(), "block/" + id.getPath())));
    }
    @Override
    public BlockState getPolymerBreakEventBlockState(BlockState state, PacketContext context) {
        return Blocks.OAK_SLAB.getDefaultState();
    }
    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return switch (state.get(TYPE)){
            case TOP -> state.get(WATERLOGGED) ? TOP_SLAB_WATERLOGGED : TOP_SLAB;
            case BOTTOM -> state.get(WATERLOGGED) ? BOTTOM_SLAB_WATERLOGGED : BOTTOM_SLAB;
            default -> DOUBLE;
        };
    }
}

package frosta.ancientarch.block.blockentity;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.block.ArchBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArchBlockEntitys {
    public static final BlockEntityType<KilnBlockEntity> KILN_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(AncientArch.MOD_ID, "kiln_block_be"),
                    FabricBlockEntityTypeBuilder.create(KilnBlockEntity::new,
                            ArchBlocks.KILN).build());

    public static void registerBlockEntities() {
        AncientArch.LOGGER.info("Registering Block Entities for " + AncientArch.MOD_ID);

    }
}

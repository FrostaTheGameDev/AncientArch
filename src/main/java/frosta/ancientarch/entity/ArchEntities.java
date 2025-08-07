package frosta.ancientarch.entity;

import frosta.ancientarch.AncientArch;
import frosta.ancientarch.entity.custom.HolyHandGrenadeEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ArchEntities {
    public static final EntityType<HolyHandGrenadeEntity> HOLY_HAND_GRENADE_PR = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(AncientArch.MOD_ID, "holy_hand_grenade_pr"),
            FabricEntityTypeBuilder.<HolyHandGrenadeEntity>create(SpawnGroup.MISC, HolyHandGrenadeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

}

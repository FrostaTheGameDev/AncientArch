package frosta.ancientarch.entity.custom;

import frosta.ancientarch.entity.ArchEntities;
import frosta.ancientarch.item.ArchItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HolyHandGrenadeEntity extends ThrownItemEntity {
    private int fuse = -1;
    private boolean primed = false;

    public HolyHandGrenadeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public HolyHandGrenadeEntity(LivingEntity livingEntity, World world) {
        super(ArchEntities.HOLY_HAND_GRENADE_PR, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ArchItems.HOLY_HAND_GRENADE;
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient() && !primed) {
            primed = true;
            fuse = 100;
            this.setNoGravity(true);
            this.setVelocity(0, 0, 0);

            if (this.getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.playSoundFromEntity(
                        null,
                        this,
                        SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), // ✅ fix here
                        SoundCategory.HOSTILE,
                        1.0f,
                        1.5f
                );
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (primed) {
            if (!this.getWorld().isClient()) {
                if (fuse > 0) {
                    fuse--;
                } else {
                    explode();
                    this.discard();
                }
            } else {
                this.getWorld().addParticle(
                        ParticleTypes.END_ROD,
                        this.getX() + (this.random.nextDouble() - 0.5),
                        this.getY() + 0.5,
                        this.getZ() + (this.random.nextDouble() - 0.5),
                        0, 0.05, 0
                );
            }
        }
    }

    private void explode() {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            BlockPos center = this.getBlockPos();

            int radius = 200;
            int step = 14;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos pos = center.add(x, y, z);
                        if (center.getSquaredDistance(pos) <= radius * radius) {
                            if (serverWorld.getBlockState(pos).isOf(net.minecraft.block.Blocks.WATER)
                                    || serverWorld.getBlockState(pos).isOf(Blocks.KELP)) {
                                serverWorld.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState());
                            }
                        }
                    }
                }
            }

            List<BlockPos> explosionPositions = new ArrayList<>();

            for (int x = -radius; x <= radius; x += step) {
                for (int y = -radius; y <= radius; y += step) {
                    for (int z = -radius; z <= radius; z += step) {
                        BlockPos pos = center.add(x, y, z);
                        if (center.getSquaredDistance(pos) <= radius * radius) {
                            explosionPositions.add(pos);
                        }
                    }
                }
            }

            explosionPositions.sort(Comparator.comparingDouble(pos -> center.getSquaredDistance(pos)));

            for (BlockPos pos : explosionPositions) {
                serverWorld.createExplosion(
                        this,
                        pos.getX() + 0.5,
                        pos.getY(),
                        pos.getZ() + 0.5,
                        25.0f,
                        World.ExplosionSourceType.MOB
                );
            }

            // === Boom sound at center ===
            serverWorld.playSound(
                    null,
                    center,
                    SoundEvents.ENTITY_GENERIC_EXPLODE,
                    SoundCategory.BLOCKS,
                    4.0f,
                    1.0f
            );
        }
    }
}
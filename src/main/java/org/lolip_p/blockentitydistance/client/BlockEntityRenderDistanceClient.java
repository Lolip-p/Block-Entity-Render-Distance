package org.lolip_p.blockentitydistance.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.block.entity.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.block.entity.state.*;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class BlockEntityRenderDistanceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigHolder.loadConfig();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> ConfigCommand.register(dispatcher));

        if (!ConfigHolder.isEnabled) return;

        if (!ConfigHolder.UseGlobalValue) {
            BlockEntityRendererFactories.register(BlockEntityType.CHEST, ChestRenderer::new);
            BlockEntityRendererFactories.register(BlockEntityType.SIGN, SighRenderer::new);
            BlockEntityRendererFactories.register(BlockEntityType.HANGING_SIGN, HangingSighRenderer::new);
            BlockEntityRendererFactories.register(BlockEntityType.BANNER, BannerBlockRenderer::new);
            BlockEntityRendererFactories.register(BlockEntityType.TRAPPED_CHEST, TrappedChestRenderer::new);
            BlockEntityRendererFactories.register(BlockEntityType.SHULKER_BOX, ShulkerBoxRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.CAMPFIRE, CampfireRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.LECTERN, LecternRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.BEACON, BeaconRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.MOB_SPAWNER, MobSpawnerRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.PISTON, PistonRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.ENCHANTING_TABLE, EnchantingTableRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.END_PORTAL, EndPortalRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.ENDER_CHEST, EnderChestRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.SKULL, SkullRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.END_GATEWAY, EndGatewayRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.DECORATED_POT, DecoratedPotRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.BED, BedRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.CONDUIT, ConduitRender::new);
            BlockEntityRendererFactories.register(BlockEntityType.BELL, BellRender::new);
        }
    }

    private static final class ChestRenderer extends ChestBlockEntityRenderer<ChestBlockEntity> {
        public ChestRenderer(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(ChestBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:chest")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class SighRenderer extends SignBlockEntityRenderer {
        public SighRenderer(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(SignBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:sign")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class HangingSighRenderer extends HangingSignBlockEntityRenderer {
        public HangingSighRenderer(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(SignBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:hanging_sign")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class BannerBlockRenderer extends BannerBlockEntityRenderer {
        public BannerBlockRenderer(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(BannerBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:banner")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class TrappedChestRenderer extends ChestBlockEntityRenderer<TrappedChestBlockEntity> {
        public TrappedChestRenderer(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(ChestBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:trapped_chest")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class ShulkerBoxRender extends ShulkerBoxBlockEntityRenderer {
        public ShulkerBoxRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(ShulkerBoxBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:shulker_box")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class CampfireRender extends CampfireBlockEntityRenderer {
        public CampfireRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(CampfireBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:campfire")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class LecternRender extends LecternBlockEntityRenderer {
        public LecternRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(LecternBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:lectern")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class BeaconRender extends BeaconBlockEntityRenderer<BeaconBlockEntity> {
        public BeaconRender(BlockEntityRendererFactory.Context ctx) { super(); }
        @Override
        public void render(BeaconBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:beacon")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class MobSpawnerRender extends MobSpawnerBlockEntityRenderer {
        public MobSpawnerRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(MobSpawnerBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:mob_spawner")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class PistonRender extends PistonBlockEntityRenderer {
        public PistonRender(BlockEntityRendererFactory.Context ctx) { super(); }
        @Override
        public void render(PistonBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:piston")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class EnchantingTableRender extends EnchantingTableBlockEntityRenderer {
        public EnchantingTableRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(EnchantingTableBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:enchanting_table")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class EndPortalRender extends EndPortalBlockEntityRenderer {
        public EndPortalRender(BlockEntityRendererFactory.Context ctx) { super(); }
        @Override
        public void render(EndPortalBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:end_portal")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class EnderChestRender extends ChestBlockEntityRenderer<EnderChestBlockEntity> {
        public EnderChestRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(ChestBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:ender_chest")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class SkullRender extends SkullBlockEntityRenderer {
        public SkullRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(SkullBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:skull")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class EndGatewayRender extends EndGatewayBlockEntityRenderer {
        public EndGatewayRender(BlockEntityRendererFactory.Context ctx) { super(); }
        @Override
        public void render(EndGatewayBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:end_gateway")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class DecoratedPotRender extends DecoratedPotBlockEntityRenderer {
        public DecoratedPotRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(DecoratedPotBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:decorated_pot")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class BedRender extends BedBlockEntityRenderer {
        public BedRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(BedBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:bed")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class ConduitRender extends ConduitBlockEntityRenderer {
        public ConduitRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(ConduitBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:conduit")) {
                super.render(s, ms, q, c);
            }
        }
    }

    private static final class BellRender extends BellBlockEntityRenderer {
        public BellRender(BlockEntityRendererFactory.Context ctx) { super(ctx); }
        @Override
        public void render(BellBlockEntityRenderState s, MatrixStack ms, OrderedRenderCommandQueue q, CameraRenderState c) {
            if (DistanceChecker.shouldRender(s.pos, c.pos, "minecraft:bell")) {
                super.render(s, ms, q, c);
            }
        }
    }
}

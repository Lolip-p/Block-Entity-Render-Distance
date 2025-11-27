package org.lolip_p.blockentitydistance.mixin;

import org.lolip_p.blockentitydistance.client.ConfigHolder;
import org.lolip_p.blockentitydistance.util.GetRenderDistanceInterface;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockEntityRenderer.class)
public interface BlockEntityRenderDistanceMixin extends GetRenderDistanceInterface {
    @Overwrite default int getRenderDistance() {
        if (!ConfigHolder.isEnabled) return 64;

        if(ConfigHolder.UseGlobalValue) {
            return ConfigHolder.GlobalValue;
        }
        return 64;
    }
}

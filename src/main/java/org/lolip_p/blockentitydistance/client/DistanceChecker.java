package org.lolip_p.blockentitydistance.client;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class DistanceChecker {

    // Простая проверка: координаты блока vs координаты камеры vs настройки
    public static boolean shouldRender(BlockPos pos, Vec3d cameraPos, String blockId) {
        if (!ConfigHolder.isEnabled) return true;
        if (pos == null) return true;

        double maxDistance;

        if (ConfigHolder.UseGlobalValue) {
            maxDistance = ConfigHolder.GlobalValue;
        } else {
            // Получаем дистанцию для конкретного ID, или 64.0 по умолчанию
            maxDistance = ConfigHolder.DISTANCE_CONFIG.getOrDefault(blockId, 64.0);
        }

        // Считаем квадрат расстояния (это намного быстрее, чем обычное расстояние с корнем)
        double maxDistanceSq = maxDistance * maxDistance;

        // Центр блока = координаты + 0.5
        double dx = (pos.getX() + 0.5) - cameraPos.x;
        double dy = (pos.getY() + 0.5) - cameraPos.y;
        double dz = (pos.getZ() + 0.5) - cameraPos.z;

        double distSq = (dx * dx) + (dy * dy) + (dz * dz);

        return distSq <= maxDistanceSq;
    }
}
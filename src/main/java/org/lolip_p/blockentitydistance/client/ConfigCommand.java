package org.lolip_p.blockentitydistance.client;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class ConfigCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("BlockEntityRD")
            .then(ClientCommandManager.literal("set")
                .then(ClientCommandManager.literal("isEnabled")
                    .then(ClientCommandManager.argument("value", BoolArgumentType.bool())
                        .executes(context -> setEnabled(context, BoolArgumentType.getBool(context, "value")))))
                .then(ClientCommandManager.literal("UseGlobalValue")
                    .then(ClientCommandManager.argument("value", BoolArgumentType.bool())
                        .executes(context -> setGlobalValueDistance(context, BoolArgumentType.getBool(context, "value")))))
                .then(ClientCommandManager.literal("GlobalValue")
                    .then(ClientCommandManager.argument("value", IntegerArgumentType.integer(1, 64))
                        .executes(context -> setGlobalValue(context, IntegerArgumentType.getInteger(context, "value")))))
                .then(ClientCommandManager.literal("CheckIntervalTicks")
                    .then(ClientCommandManager.argument("value", IntegerArgumentType.integer(1, 200))
                        .executes(context -> setCheckInterval(context, IntegerArgumentType.getInteger(context, "value"))))))
            .then(ClientCommandManager.literal("get")
                .executes(context -> getConfig(context)))
            .then(ClientCommandManager.literal("reload")
                .executes(context -> reloadConfig(context)))
            .executes(context -> {
                context.getSource().sendFeedback(Text.of("§6BlockEntityRenderDistance Config Commands:"));
                context.getSource().sendFeedback(Text.of("§a/BlockEntityRD set UseGlobalValue <true|false>"));
                context.getSource().sendFeedback(Text.of("§a/BlockEntityRD set GlobalValue <value>"));
                context.getSource().sendFeedback(Text.of("§a/BlockEntityRD set CheckIntervalTicks <value>"));
                context.getSource().sendFeedback(Text.of("§a/BlockEntityRD get - Show current config"));
                context.getSource().sendFeedback(Text.of("§a/BlockEntityRD reload - Reload config from file"));
                return Command.SINGLE_SUCCESS;
            })
        );
    }

    private static int setEnabled(CommandContext<FabricClientCommandSource> context, boolean value) {
        ConfigHolder.isEnabled = value;
        context.getSource().sendFeedback(Text.of("§aMod " + (value ? "enabled" : "disabled")));
        ConfigHolder.saveConfig();
        return Command.SINGLE_SUCCESS;
    }

    private static int setGlobalValueDistance(CommandContext<FabricClientCommandSource> context, boolean value) {
        ConfigHolder.UseGlobalValue = value;
        context.getSource().sendFeedback(Text.of("§aSet UseGlobalValue to " + value));
        ConfigHolder.saveConfig();
        return Command.SINGLE_SUCCESS;
    }

    private static int setGlobalValue(CommandContext<FabricClientCommandSource> context, int value) {
        ConfigHolder.GlobalValue = value;
        context.getSource().sendFeedback(Text.of("§aSet GlobalValue to " + value));
        ConfigHolder.saveConfig();
        return Command.SINGLE_SUCCESS;
    }

    private static int setCheckInterval(CommandContext<FabricClientCommandSource> context, int value) {
        ConfigHolder.CheckIntervalTicks = value;
        context.getSource().sendFeedback(Text.of("§aSet CheckIntervalTicks to " + value));
        ConfigHolder.saveConfig();
        return Command.SINGLE_SUCCESS;
    }

    private static int getConfig(CommandContext<FabricClientCommandSource> context) {
        context.getSource().sendFeedback(Text.of("§6Current BlockEntityRenderDistance Config:"));
        context.getSource().sendFeedback(Text.of("§fStatus Mod: " + (ConfigHolder.isEnabled ? "§aEnabled" : "§cDisabled")));
        context.getSource().sendFeedback(Text.of("§fUseGlobalValue: §6" + ConfigHolder.UseGlobalValue));
        context.getSource().sendFeedback(Text.of("§fGlobalValue: §6" + ConfigHolder.GlobalValue));
        context.getSource().sendFeedback(Text.of("§fCheckIntervalTicks: §6" + ConfigHolder.CheckIntervalTicks));
        return Command.SINGLE_SUCCESS;
    }

    private static int reloadConfig(CommandContext<FabricClientCommandSource> context) {
        ConfigHolder.loadConfig();
        context.getSource().sendFeedback(Text.of("§aConfig reloaded from file!"));
        return Command.SINGLE_SUCCESS;
    }
}

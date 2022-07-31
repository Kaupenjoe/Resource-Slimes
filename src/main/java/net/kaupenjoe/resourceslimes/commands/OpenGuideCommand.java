package net.kaupenjoe.resourceslimes.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.kaupenjoe.resourceslimes.networking.ModMessages;
import net.kaupenjoe.resourceslimes.networking.packets.PacketOpenGeneticsGuide;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;

import java.util.Objects;

public class OpenGuideCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralCommandNode<CommandSourceStack> cmd =
                dispatcher.register(Commands.literal("guide").then(Commands.literal("genetics")).executes(context -> {
                    ServerPlayer player = Objects.requireNonNull(context.getSource().getPlayer());
                    ModMessages.sendTo(new PacketOpenGeneticsGuide(), player.connection.connection, NetworkDirection.PLAY_TO_SERVER);
                    context.getSource().sendSuccess(Component.translatable("guide_open.success"), false);
                    return 1;
                }));
        dispatcher.register(Commands.literal("wiki").redirect(cmd));
    }
}

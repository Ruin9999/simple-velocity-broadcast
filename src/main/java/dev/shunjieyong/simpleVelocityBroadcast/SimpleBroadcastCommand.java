package dev.shunjieyong.simpleVelocityBroadcast;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.slf4j.Logger;

public class SimpleBroadcastCommand implements SimpleCommand {
    private final ProxyServer server;
    private final Logger logger;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    // Command permission
    private static final String PERMISSION = "simplevelocitybroadcast.use";

    public SimpleBroadcastCommand(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (args.length == 0) {
            source.sendMessage(Component.text("Usage: /simplevelocitybroadcast <message>"));
            return;
        }

        int playerCount = 0;
        String messageString = String.join(" ", args);
        Component formattedMessage = miniMessage.deserialize("<gold>[Broadcast]</gold> <white> " + messageString + "</white>");
        for (Player player: server.getAllPlayers()) {
            player.sendMessage(formattedMessage);
            playerCount++;
        }

        source.sendMessage(Component.text("Broadcasted message to " + playerCount + " players."));
        logger.info("{}broadcasted: {}", source, messageString);
    }

    @Override
    public boolean hasPermission(final Invocation invocation) {
        return invocation.source().hasPermission(PERMISSION);
    }
}

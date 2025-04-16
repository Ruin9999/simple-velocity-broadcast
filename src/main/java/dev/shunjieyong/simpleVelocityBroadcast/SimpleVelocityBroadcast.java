package dev.shunjieyong.simpleVelocityBroadcast;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
    id = "simple-velocity-broadcast",
    name = "simple-velocity-broadcast",
    version = BuildConstants.VERSION
)
public class SimpleVelocityBroadcast {

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public SimpleVelocityBroadcast(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        logger.info("SimpleVelocityBroadcast has been enabled!");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = server.getCommandManager();
        CommandMeta commandMeta = commandManager.metaBuilder("simplevelocitybroadcast").aliases("broadcast", "vb").build();

        SimpleBroadcastCommand broadcastCommand = new SimpleBroadcastCommand(server, logger);
        commandManager.register(commandMeta, broadcastCommand);
        logger.info("Registered simplevelocitybroadcast.");
    }
}

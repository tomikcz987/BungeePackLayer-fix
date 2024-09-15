package io.th0rgal.packsmanager.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import io.th0rgal.packsmanager.velocity.listeners.ResourcePackRequestListener;
import org.slf4j.Logger;

@Plugin(
        id = "bungeepacklayer",
        name = "Bungee Pack Layer",
        version = "${version}",
        description = "${description}",
        authors = {"th0rgal", "itismaku"}
)
public class BungeePackLayerVelocityPlugin {

    private final ProxyServer proxyServer;

    @Inject
    public BungeePackLayerVelocityPlugin(ProxyServer proxyServer, Logger logger) {
        this.proxyServer = proxyServer;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        EventManager eventManager = proxyServer.getEventManager();
        eventManager.register(this, new ResourcePackRequestListener());
    }

}

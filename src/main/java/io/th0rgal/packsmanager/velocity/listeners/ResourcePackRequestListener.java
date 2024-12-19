package io.th0rgal.packsmanager.velocity.listeners;

import com.google.common.collect.Maps;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.player.ServerResourcePackRemoveEvent;
import com.velocitypowered.api.event.player.ServerResourcePackSendEvent;
import com.velocitypowered.api.proxy.player.ResourcePackInfo;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class ResourcePackRequestListener {

    private final Map<UUID, byte[]> map = Maps.newHashMap();

    @Subscribe(order = PostOrder.FIRST)
    public void packetReceive(ServerResourcePackSendEvent event) {
        ResourcePackInfo resourcePackInfo = event.getProvidedResourcePack();
        UUID uuid = event.getServerConnection().getPlayer().getUniqueId();
        byte[] packHash = resourcePackInfo.getHash();
        if (map.containsKey(uuid) && Arrays.equals(map.get(uuid), packHash)) {
            event.setResult(ResultedEvent.GenericResult.denied());
            return;
        }

        map.put(uuid, packHash);
    }
    //Simple fix for resetting resourcepacks when switching servers
    @Subscribe(order = PostOrder.FIRST)
    public void onReosourcePackRemove(ServerResourcePackRemoveEvent event) {
        event.setResult(ResultedEvent.GenericResult.denied());
    }
    
    @Subscribe(order = PostOrder.FIRST)
    public void onDisconnectEvent(DisconnectEvent event) {
        map.remove(event.getPlayer().getUniqueId());
    }

}

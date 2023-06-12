package pl.pomoku.fastplotplugin.listeners;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerExitThePlotEvent;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.audiences;
import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotBossBarManager;

public class PlayerExitThePlot implements Listener {
    @EventHandler
    public void onPlayerExitThePlot(PlayerExitThePlotEvent event) {
        Player player = event.getPlayer();
        Audience audience = audiences.sender(player);
        audience.hideBossBar(plotBossBarManager.getBossBarFromPlayer(player));
        plotBossBarManager.removePlayer(player);
    }
}

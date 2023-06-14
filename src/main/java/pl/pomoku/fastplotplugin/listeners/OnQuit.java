package pl.pomoku.fastplotplugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.playerOnPlotManager;
import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotBossBarManager;

public class OnQuit implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerOnPlotManager.removePlayer(player);
        plotBossBarManager.removePlayer(player);
    }
}

package pl.pomoku.fastplotplugin.listeners;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerChangePlotEvent;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotBossBarManager;

public class PlayerChangePlot implements Listener {
    @EventHandler
    public void onPlayerEnterPlot(PlayerChangePlotEvent event) {
        Player player = event.getPlayer();
        BossBar oldPlotBossBar = plotBossBarManager.getBossBarFromPlayer(player);
        event.getAudience().hideBossBar(oldPlotBossBar);

        TreePlot newPlot = event.getTo();
        BossBar bossBar = plotBossBarManager.getBossBarFromPlot(newPlot);
        event.getAudience().showBossBar(bossBar);
        plotBossBarManager.addBossBarToPlayer(player, bossBar);
    }
}

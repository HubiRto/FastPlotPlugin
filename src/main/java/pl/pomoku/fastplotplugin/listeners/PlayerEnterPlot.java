package pl.pomoku.fastplotplugin.listeners;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerEntersPlotEvent;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotBossBarManager;

public class PlayerEnterPlot implements Listener {
    @EventHandler
    public void onPlayerEnterPlot(PlayerEntersPlotEvent event) {
        Player player = event.getPlayer();
        TreePlot plot = event.getPlot();
        BossBar bossBar = plotBossBarManager.getBossBarFromPlot(plot);
        event.getAudience().showBossBar(bossBar);
        plotBossBarManager.addBossBarToPlayer(player, bossBar);
    }
}

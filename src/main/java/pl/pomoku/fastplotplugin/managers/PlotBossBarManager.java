package pl.pomoku.fastplotplugin.managers;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.entity.PlotBossBar;
import pl.pomoku.fastplotplugin.entity.TreePlot;

import java.util.HashMap;
import java.util.Map;

import static pl.pomoku.pomokupluginsrepository.text.Text.strToComp;

public class PlotBossBarManager {
    private Map<Player, PlotBossBar> playerPlotBossBar;

    public PlotBossBarManager() {
        this.playerPlotBossBar = new HashMap<>();
    }

    public void addPlayer(Player player, PlotBossBar plotBossBar) {
        this.playerPlotBossBar.put(player, plotBossBar);
    }

    public void removePlayer(Player player) {
        this.playerPlotBossBar.remove(player);
    }

    public void replacePlayer(Player player, PlotBossBar plotBossBar) {
        this.playerPlotBossBar.replace(player, plotBossBar);
    }

    public BossBar getBossBarFromPlot(TreePlot plot) {
        return BossBar.bossBar(
                strToComp("<gray>Znajdujesz się na działce <green>" + plot.getPlotName()
                        + "</green> gracza <aqua>" + plot.getOwnerName()),
                1.0f,
                BossBar.Color.GREEN,
                BossBar.Overlay.PROGRESS
        );
    }

    public BossBar getBossBarFromPlayer(Player player) {
        return this.playerPlotBossBar.get(player).getBossBar();
    }


}

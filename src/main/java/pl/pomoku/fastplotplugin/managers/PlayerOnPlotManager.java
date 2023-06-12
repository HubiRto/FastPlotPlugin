package pl.pomoku.fastplotplugin.managers;

import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.entity.TreePlot;

import java.util.HashMap;
import java.util.Map;

public class PlayerOnPlotManager {
    private final Map<Player, TreePlot> playerOnPlot;

    public PlayerOnPlotManager() {
        this.playerOnPlot = new HashMap<>();
    }

    public void addPlayer(Player player, TreePlot plot) {
        this.playerOnPlot.put(player, plot);
    }

    public void removePlayer(Player player) {
        this.playerOnPlot.remove(player);
    }

    public boolean isPlayerOnPlot(Player player) {
        return this.playerOnPlot.containsKey(player);
    }

    public TreePlot getPlot(Player player) {
        return this.playerOnPlot.get(player);
    }

    public void changePlot(Player player, TreePlot plot) {
        this.playerOnPlot.replace(player, plot);
    }

}

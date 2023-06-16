package pl.pomoku.fastplotplugin.interfaces.plotAction;

import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.interfaces.PlotAction;
import pl.pomoku.fastplotplugin.listeners.OnMove;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerExitThePlotEvent;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.playerOnPlotManager;

public class ExitPlotAction implements PlotAction {
    @Override
    public void execute(Player player, TreePlot currentPlot, Audience audience, OnMove onMove) {
        TreePlot lastPlot = playerOnPlotManager.getPlot(player);
        playerOnPlotManager.removePlayer(player);
        Bukkit.getPluginManager().callEvent(new PlayerExitThePlotEvent(player, lastPlot, audience));
    }
}

package pl.pomoku.fastplotplugin.movement;

import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.entity.Square;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.interfaces.PlotAction;
import pl.pomoku.fastplotplugin.interfaces.plotAction.ChangePlotAction;
import pl.pomoku.fastplotplugin.interfaces.plotAction.EnterPlotAction;
import pl.pomoku.fastplotplugin.interfaces.plotAction.ExitPlotAction;
import pl.pomoku.fastplotplugin.listeners.OnMove;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerChangePlotEvent;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerEntersPlotEvent;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerExitThePlotEvent;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.playerOnPlotManager;

public enum PlotMovement {

    ENTER(new EnterPlotAction()),
    EXIT(new ExitPlotAction()),
    CHANGE(new ChangePlotAction());

    private static final double WALL_DISTANCE = 2.0;
    private final PlotAction plotAction;


    PlotMovement(PlotAction plotAction) {
        this.plotAction = plotAction;
    }

    public void execute(Player player, TreePlot currentPlot, Audience audience, OnMove onMove) {
        this.plotAction.execute(player, currentPlot, audience, onMove);
    }

}

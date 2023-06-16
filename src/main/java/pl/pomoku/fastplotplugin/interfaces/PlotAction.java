package pl.pomoku.fastplotplugin.interfaces;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.listeners.OnMove;

public interface PlotAction {
    void execute(Player player, TreePlot currentPlot, Audience audience, OnMove onMove);
}

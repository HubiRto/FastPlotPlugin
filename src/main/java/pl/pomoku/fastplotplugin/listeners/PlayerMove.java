package pl.pomoku.fastplotplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.pomoku.fastplotplugin.entity.MapPlot;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotManager;
import static pl.pomoku.pomokupluginsrepository.text.Text.strToComp;

public class PlayerMove implements Listener {
    @EventHandler
    public void move(PlayerMoveEvent event) {
        MapPlot plot = plotManager.searchPlot(event.getPlayer());
        if(plot == null) return;
        event.getPlayer().sendMessage(strToComp("<red>Wyszedłeś z działki: " + plot.getPlotName()));
    }
}

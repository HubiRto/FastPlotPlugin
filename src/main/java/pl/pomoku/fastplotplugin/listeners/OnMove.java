package pl.pomoku.fastplotplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerChangePlotEvent;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerEntersPlotEvent;
import pl.pomoku.fastplotplugin.listeners.customEvents.PlayerExitThePlotEvent;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.playerOnPlotManager;
import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotManager;

public class OnMove implements Listener {
    @EventHandler
    public void move(PlayerMoveEvent event){
        TreePlot serachPlot = plotManager.serachPlot(event.getPlayer().getLocation());
        Player player = event.getPlayer();

        if(serachPlot != null){
            //Wszedł na działkę
            if(!playerOnPlotManager.isPlayerOnPlot(event.getPlayer())){
                System.out.println("Wszedł na działkę");
                playerOnPlotManager.addPlayer(event.getPlayer(), serachPlot);
                Bukkit.getPluginManager().callEvent(new PlayerEntersPlotEvent(event.getPlayer(), serachPlot));
            }else {
                //Zmienił działkę
                if(!playerOnPlotManager.getPlot(player).equals(serachPlot)){
                    System.out.println("Zmienił działkę");
                    Bukkit.getPluginManager().callEvent(new PlayerChangePlotEvent(player, playerOnPlotManager.getPlot(player), serachPlot));
                    playerOnPlotManager.changePlot(player, serachPlot);
                }
            }
        }else {
            //Wyszedł z działki
            if(playerOnPlotManager.isPlayerOnPlot(event.getPlayer())){
                System.out.println("Wyszedł z działki");
                playerOnPlotManager.removePlayer(event.getPlayer());
                Bukkit.getPluginManager().callEvent(new PlayerExitThePlotEvent(player, playerOnPlotManager.getPlot(player)));
            }
        }
    }
}

package pl.pomoku.fastplotplugin.listeners;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.pomoku.fastplotplugin.entity.MapPlot;

import java.util.HashMap;
import java.util.Map;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.audiences;
import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotManager;
import static pl.pomoku.pomokupluginsrepository.text.Text.strToComp;

public class PlayerMove implements Listener {
    private final Map<Player, BossBar> bossBars = new HashMap<>();
    @EventHandler
    public void move(PlayerMoveEvent event) {
        MapPlot plot = plotManager.searchPlot(event.getPlayer());
        Audience audience = audiences.sender(event.getPlayer());

        if (plot != null) {
            if (!bossBars.containsKey(event.getPlayer())) {
                BossBar bossBar = BossBar.bossBar(
                        strToComp("<gray>Znajdujesz się na działce <green>" + plot.getPlotName()
                                + "</green> gracza <aqua>" + plot.getOwnerName()),
                        1.0f,
                        BossBar.Color.GREEN,
                        BossBar.Overlay.PROGRESS
                );
                bossBars.put(event.getPlayer(), bossBar);
                audience.showBossBar(bossBar);
            }
        } else {
            if (bossBars.containsKey(event.getPlayer())) {
                audience.hideBossBar(bossBars.get(event.getPlayer()));
                bossBars.remove(event.getPlayer());
            }
        }
    }
}

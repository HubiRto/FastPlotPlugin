package pl.pomoku.fastplotplugin.listeners.customEvents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import pl.pomoku.fastplotplugin.entity.TreePlot;


@AllArgsConstructor
@Getter
public class PlayerEntersPlotEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private TreePlot plot;

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

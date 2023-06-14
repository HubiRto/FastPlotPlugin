package pl.pomoku.fastplotplugin.listeners.customEvents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import pl.pomoku.fastplotplugin.entity.TreePlot;


@AllArgsConstructor
@Getter
public class PlayerExitThePlotEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private TreePlot plot;
    private Audience audience;

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

package pl.pomoku.fastplotplugin.util;

import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class PlotData {
    private static int idCounter = 0;
    private int id;
    private String ownerName;
    private String ownerUUID;
    private String plotName;

    public PlotData(Player owner, String plotName) {
        this.id = idCounter++;
        this.ownerName = owner.getName();
        this.ownerUUID = owner.getUniqueId().toString();
        this.plotName = plotName;
    }
}

package pl.pomoku.fastplotplugin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pl.pomoku.fastplotplugin.util.Square;

@Data
@ToString
@AllArgsConstructor
public class MapPlot {
    private Square boundary;
    private String ownerName;
    private String plotName;
}

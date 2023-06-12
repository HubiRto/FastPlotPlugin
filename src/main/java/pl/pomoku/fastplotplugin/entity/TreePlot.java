package pl.pomoku.fastplotplugin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class TreePlot {
    private Square boundary;
    private String ownerName;
    private String plotName;
}

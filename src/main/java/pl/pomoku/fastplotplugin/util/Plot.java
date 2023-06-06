package pl.pomoku.fastplotplugin.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
public class Plot {
    private Square boundary;
    private String ownerName;
    private String ownerUUID;
    private String plotName;

    public Plot(Square boundary) {
        this.boundary = boundary;
    }
}
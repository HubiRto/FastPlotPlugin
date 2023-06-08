package pl.pomoku.fastplotplugin.util.trees.kdTree;

import lombok.Data;
import pl.pomoku.fastplotplugin.entity.MapPlot;

@Data
public class KDNode {
    private MapPlot plot;
    private KDNode left;
    private KDNode right;
    private int depth;

    public KDNode(MapPlot plot, int depth) {
        this.plot = plot;
        this.depth = depth;
        this.left = null;
        this.right = null;
    }
}

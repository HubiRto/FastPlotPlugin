package pl.pomoku.fastplotplugin.util.trees.kdTree;

import lombok.Data;
import pl.pomoku.fastplotplugin.util.Plot;

@Data
public class KDNode {
    private Plot plot;
    private KDNode left;
    private KDNode right;
    private int depth;

    public KDNode(Plot plot, int depth) {
        this.plot = plot;
        this.depth = depth;
        this.left = null;
        this.right = null;
    }
}

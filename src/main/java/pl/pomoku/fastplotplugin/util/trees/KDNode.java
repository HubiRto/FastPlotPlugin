package pl.pomoku.fastplotplugin.util.trees;

import pl.pomoku.fastplotplugin.entity.Plot;

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

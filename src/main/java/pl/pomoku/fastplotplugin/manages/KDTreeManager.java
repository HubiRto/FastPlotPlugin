package pl.pomoku.fastplotplugin.manages;

import pl.pomoku.fastplotplugin.util.Plot;
import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.trees.kdTree.KDTree;

public class KDTreeManager {
    private final KDTree kdTree;

    public KDTreeManager() {
        kdTree = new KDTree();
    }

    public void addPlot(Plot plot) {
        kdTree.insert(plot);
    }

    public void removePlot(Plot plot) {
        kdTree.remove(plot);
    }

    public Plot searchPlot(Point2D point) {
        return kdTree.search(point);
    }
}

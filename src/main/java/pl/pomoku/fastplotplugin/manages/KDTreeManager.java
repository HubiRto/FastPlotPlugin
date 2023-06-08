package pl.pomoku.fastplotplugin.manages;

import pl.pomoku.fastplotplugin.entity.MapPlot;
import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.trees.kdTree.KDTree;

public class KDTreeManager {
    private final KDTree kdTree;

    public KDTreeManager() {
        kdTree = new KDTree();
    }

    public void addPlot(MapPlot plot) {
        kdTree.insert(plot);
    }

    public void removePlot(MapPlot plot) {
        kdTree.remove(plot);
    }

    public MapPlot searchPlot(Point2D point) {
        return kdTree.search(point);
    }
}

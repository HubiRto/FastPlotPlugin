package pl.pomoku.fastplotplugin.util;

import pl.pomoku.fastplotplugin.enums.DataStorageType;
import pl.pomoku.fastplotplugin.util.trees.kdTree.KDTree;
import pl.pomoku.fastplotplugin.util.trees.quadTree.QuadTree;

public class PlotManager {
    private final KDTree kdTree;
    private final QuadTree quadTree;

    public PlotManager(int mapSize) {
        kdTree = new KDTree();
        quadTree = new QuadTree(new Square(new Point2D(0, 0), mapSize));
    }

    public void addPlot(Plot plot) {
        quadTree.addPlot(plot);
        kdTree.insert(plot);
    }

    public boolean checkPlotOverlap(Plot plot){
        return quadTree.checkPlotOverlap(plot);
    }

    public void removePlot(Plot plot) {
        quadTree.removePlot(plot);
        kdTree.remove(plot);
    }

    public Plot getPlot(Point2D point) {
        return kdTree.search(point);
    }
}

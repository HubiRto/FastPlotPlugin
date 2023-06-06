package pl.pomoku.fastplotplugin.manages;

import pl.pomoku.fastplotplugin.util.Plot;
import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.Square;
import pl.pomoku.fastplotplugin.util.trees.quadTree.QuadTree;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.MAP_SIZE;

public class QuadTreeManager {
    private final QuadTree quadTree;

    public QuadTreeManager() {
        quadTree = new QuadTree(new Square(new Point2D(0,0), MAP_SIZE));
    }

    public void addPlot(Plot plot) {
        quadTree.addPlot(plot.getBoundary());
    }

    public boolean checkPlotOverlap(Plot plot) {
        return quadTree.checkPlotOverlap(plot);
    }

    public void removePlot(Plot plot) {
        quadTree.removePlot(plot.getBoundary());
    }
}

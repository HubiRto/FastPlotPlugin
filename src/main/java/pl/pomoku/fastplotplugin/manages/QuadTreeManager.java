package pl.pomoku.fastplotplugin.manages;

import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.Square;
import pl.pomoku.fastplotplugin.util.trees.quadTree.QuadTree;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.MAP_SIZE;

public class QuadTreeManager {
    private final QuadTree quadTree;

    public QuadTreeManager() {
        quadTree = new QuadTree(new Square(new Point2D(0,0), MAP_SIZE));
    }

    public void addPlot(Square plot) {
        quadTree.addPlot(plot);
    }

    public boolean checkPlotOverlap(Square plot) {
        return quadTree.checkPlotOverlap(plot);
    }

    public void removePlot(Square plot) {
        quadTree.removePlot(plot);
    }
}

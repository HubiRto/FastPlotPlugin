package pl.pomoku.fastplotplugin.managers;

import org.bukkit.Location;
import pl.pomoku.fastplotplugin.entity.Plot;
import pl.pomoku.fastplotplugin.entity.Point2D;
import pl.pomoku.fastplotplugin.entity.Square;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.tree.QuadTree;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotService;

public class PlotManager {
    private static final int MAP_SIZE = 10000;
    private final QuadTree quadTree;

    public PlotManager() {
        this.quadTree = new QuadTree(new Square(0,0, MAP_SIZE));
    }

    public boolean isPlotOverlap(Square plot) {
        return quadTree.checkPlotOverlap(plot);
    }

    public void addPlot(Plot plot){
        quadTree.insert(convertFromPlot(plot));
        plotService.addPlot(plot);
    }

    public void removePlot(Plot plot){
        quadTree.removePlot(convertFromPlot(plot));
        plotService.removePlot(plot);
    }

    public TreePlot serachPlot(Point2D point){
        return quadTree.findPlot(point);
    }

    public TreePlot serachPlot(Location location){
        return serachPlot(new Point2D(location.getBlockX(), location.getBlockZ()));
    }

    public void loadFromDatabase(){
        plotService.findAll().forEach(plot -> quadTree.insert(convertFromPlot(plot)));
    }

    public TreePlot convertFromPlot(Plot plot) {
        Point2D topLeft = new Point2D(plot.getTopLeftX(), plot.getTopLeftZ());
        return new TreePlot(new Square(topLeft, plot.getSize()), plot.getOwnerName(), plot.getPlotName());
    }
}

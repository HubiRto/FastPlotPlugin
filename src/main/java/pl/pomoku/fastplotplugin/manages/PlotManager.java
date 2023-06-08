package pl.pomoku.fastplotplugin.manages;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.entity.MapPlot;
import pl.pomoku.fastplotplugin.entity.Plot;
import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.Square;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotService;

public class PlotManager {
    private final QuadTreeManager quadTreeManager;
    private final KDTreeManager kdTreeManager;

    public PlotManager() {
        quadTreeManager = new QuadTreeManager();
        kdTreeManager = new KDTreeManager();
    }

    public void addPlot(Plot plot) {
        plotService.addPlot(plot);
        MapPlot mapPlot = convertToMapPlot(plot);
        quadTreeManager.addPlot(mapPlot.getBoundary());
        kdTreeManager.addPlot(mapPlot);
    }

    public void removePlot(Plot plot) {
        plotService.removePlot(plot);
        MapPlot mapPlot = convertToMapPlot(plot);
        quadTreeManager.removePlot(mapPlot.getBoundary());
        kdTreeManager.removePlot(mapPlot);
    }

    public boolean checkPlotOverlap(Square plot) {
        return quadTreeManager.checkPlotOverlap(plot);
    }

    public MapPlot searchPlot(Point2D point) {
        return kdTreeManager.searchPlot(point);
    }

    public MapPlot searchPlot(Location location) {
        return searchPlot(new Point2D(location.getBlockX(), location.getBlockZ()));
    }

    public MapPlot searchPlot(Player player) {
        return searchPlot(player.getLocation());
    }


    public MapPlot convertToMapPlot(Plot plot) {
        Square square = new Square(new Point2D(plot.getTopLeftX(), plot.getTopLeftZ()), plot.getSize());
        return new MapPlot(square, plot.getOwnerName(), plot.getPlotName());
    }
}

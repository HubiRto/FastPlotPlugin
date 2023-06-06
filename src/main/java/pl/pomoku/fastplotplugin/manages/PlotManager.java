package pl.pomoku.fastplotplugin.manages;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.util.Plot;
import pl.pomoku.fastplotplugin.util.Point2D;

import java.util.ArrayList;
import java.util.List;

public class PlotManager {
    private final List<Plot> plots;
    private final QuadTreeManager quadTreeManager;
    private final KDTreeManager kdTreeManager;

    public PlotManager() {
        plots = new ArrayList<>();
        quadTreeManager = new QuadTreeManager();
        kdTreeManager = new KDTreeManager();
    }

    public void addPlot(Plot plot) {
        plots.add(plot);
        quadTreeManager.addPlot(plot);
        kdTreeManager.addPlot(plot);
    }

    public void removePlot(Plot plot) {
        plots.remove(plot);
        quadTreeManager.removePlot(plot);
        kdTreeManager.removePlot(plot);
    }

    public boolean checkPlotOverlap(Plot plot) {
        return quadTreeManager.checkPlotOverlap(plot);
    }

    public Plot searchPlot(Point2D point) {
        return kdTreeManager.searchPlot(point);
    }

    public Plot searchPlot(Location location) {
        return searchPlot(new Point2D(location.getBlockX(), location.getBlockZ()));
    }

    public Plot searchPlot(Player player) {
        return searchPlot(player.getLocation());
    }

    public void loadFromBinaryFile() {
        List<Plot> plots = BinaryFileManager.loadPlots("plots.bin");
        if (plots != null) {
            for (Plot plot : plots) {
                addPlot(plot);
            }
        }
    }

    public void saveToBinaryFile() {
        BinaryFileManager.savePlots(plots, "plots.bin");
    }
}

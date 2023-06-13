package pl.pomoku.fastplotplugin.tree;

import pl.pomoku.fastplotplugin.entity.Point2D;
import pl.pomoku.fastplotplugin.entity.Square;
import pl.pomoku.fastplotplugin.entity.TreePlot;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private static final int MAX_CAPACITY = 4;
    private final Square boundary;
    private final List<TreePlot> plots;
    private QuadTree[] children;

    public QuadTree(Square boundary) {
        this.boundary = boundary;
        this.plots = new ArrayList<>();
        this.children = null;
    }

    public void insert(TreePlot plot) {
        if (!isInsideBoundary(plot.getBoundary())) {
            return;
        }

        if (children != null) {
            for (QuadTree child : children) {
                if (child.isInsideBoundary(plot.getBoundary())) {
                    child.insert(plot);
                    return;
                }
            }
        }

        plots.add(plot);

        if (plots.size() > MAX_CAPACITY && children == null) {
            split();
        }
    }

    public void removePlot(TreePlot plot) {

        plots.removeIf(existingPlot -> existingPlot.getBoundary().equals(plot.getBoundary()));
        if (children != null) {
            for (QuadTree child : children) {
                child.removePlot(plot);
            }
        }
    }


    public boolean checkPlotOverlap(Square plot) {
        for (TreePlot existingPlot : plots) {
            if (isPlotOverlap(existingPlot.getBoundary(), plot)) {
                return true;
            }
        }

        if (children != null) {
            for (QuadTree child : children) {
                if (child.checkPlotOverlap(plot)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isPlotOverlap(Square square1, Square square2) {
        return square1.isOverlap(square2);
    }

    public TreePlot findPlot(Point2D point) {
        if (!isInsideBoundary(point)) {
            return null;
        }

        for (TreePlot plot : plots) {
            if (isInsideBoundary(plot.getBoundary(), point)) {
                return plot;
            }
        }

        if (children != null) {
            for (QuadTree child : children) {
                TreePlot foundPlot = child.findPlot(point);
                if (foundPlot != null) {
                    return foundPlot;
                }
            }
        }

        return null;
    }

    private void split() {
        int centerX = boundary.getCenter().getX();
        int centerZ = boundary.getCenter().getZ();
        int childrenSize = boundary.getSize() / 2;

        children = new QuadTree[4];
        children[0] = new QuadTree(new Square(boundary.getTopLeft(), childrenSize));
        children[1] = new QuadTree(new Square(new Point2D(centerX, boundary.getTopLeft().getZ()), childrenSize));
        children[2] = new QuadTree(new Square(new Point2D(boundary.getTopLeft().getX(), centerZ), childrenSize));
        children[3] = new QuadTree(new Square(new Point2D(centerX, centerZ), childrenSize));

        for (TreePlot plot : plots) {
            for (QuadTree child : children) {
                if (child.isInsideBoundary(plot.getBoundary())) {
                    child.insert(plot);
                    break;
                }
            }
        }

        plots.clear();
    }

    private boolean isInsideBoundary(Point2D point) {
        return boundary.contains(point);
    }

    private boolean isInsideBoundary(Square square, Point2D point) {
        return square.contains(point);
    }

    private boolean isInsideBoundary(Square square) {
        return this.boundary.contains(square);
    }
}

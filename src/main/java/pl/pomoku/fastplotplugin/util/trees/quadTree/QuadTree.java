package pl.pomoku.fastplotplugin.util.trees.quadTree;

import pl.pomoku.fastplotplugin.util.Plot;
import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.Square;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private static final int MAX_CAPACITY = 4;
    private final Square boundary;
    private final List<Square> plots;
    private QuadTree[] children;

    public QuadTree(Square boundary) {
        this.boundary = boundary;
        this.plots = new ArrayList<>();
        this.children = null;
    }

    public void addPlot(Square plot) {
        if (!this.boundary.contains(plot)) return;
        if (children != null) {
            for (QuadTree child : children) {
                if (child.isInsideBoundary(plot)) {
                    child.addPlot(plot);
                    return;
                }
            }
        }

        plots.add(plot);

        if (plots.size() > MAX_CAPACITY && children == null) {
            split();
        }
    }

    public void removePlot(Square plot) {
        plots.remove(plot);
        if (children != null) {
            for (QuadTree child : children) {
                child.removePlot(plot);
            }
        }
    }

    public boolean checkPlotOverlap(Plot plot) {
        for (Square existingPlot : plots) {
            if (isPlotOverlap(existingPlot, plot.getBoundary())) {
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
        return square1.intersects(square2);
    }


    private void split() {
        int centerX = boundary.getLeftTop().getX() + (boundary.getRightBottom().getX() - boundary.getLeftTop().getX()) / 2;
        int centerZ = boundary.getLeftTop().getZ() + (boundary.getRightBottom().getZ() - boundary.getLeftTop().getZ()) / 2;

        children = new QuadTree[4];
        children[0] = new QuadTree(new Square(boundary.getLeftTop(), new Point2D(centerX, centerZ)));
        children[1] = new QuadTree(new Square(new Point2D(centerX, boundary.getLeftTop().getZ()), new Point2D(boundary.getRightBottom().getX(), centerZ)));
        children[2] = new QuadTree(new Square(new Point2D(boundary.getLeftTop().getX(), centerZ), boundary.getRightBottom()));
        children[3] = new QuadTree(new Square(new Point2D(centerX, centerZ), boundary.getRightBottom()));

        for (Square plot : plots) {
            for (QuadTree child : children) {
                if (child.isInsideBoundary(plot)) {
                    child.addPlot(plot);
                    break;
                }
            }
        }

        plots.clear();
    }

    private boolean isInsideBoundary(Square square) {
        return boundary.contains(square);
    }
}

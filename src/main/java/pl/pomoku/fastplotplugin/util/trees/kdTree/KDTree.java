package pl.pomoku.fastplotplugin.util.trees.kdTree;

import pl.pomoku.fastplotplugin.entity.MapPlot;
import pl.pomoku.fastplotplugin.util.Point2D;

public class KDTree {
    private static final int DIMENSION = 2;
    private KDNode root;

    public KDTree() {
        root = null;
    }

    public void insert(MapPlot plot) {
        root = insert(root, plot, 0);
    }

    private KDNode insert(KDNode node, MapPlot plot, int depth) {
        if (node == null) return new KDNode(plot, depth);
        int currentDimension = depth % DIMENSION;

        if (compare(plot, node.getPlot(), currentDimension) < 0) {
            node.setLeft(insert(node.getLeft(), plot, depth + 1));
        } else {
            node.setRight(insert(node.getRight(), plot, depth + 1));
        }

        return node;
    }

    public MapPlot search(Point2D point) {
        return search(root, point);
    }

    private MapPlot search(KDNode node, Point2D point) {
        if (node == null) return null;
        if (node.getPlot().getBoundary().contains(point)) return node.getPlot();
        int currentDimension = node.getDepth() % DIMENSION;

        if (compare(point, node.getPlot().getBoundary().getLeftTop(), currentDimension) < 0) {
            return search(node.getLeft(), point);
        } else {
            return search(node.getRight(), point);
        }
    }

    private int compare(MapPlot plotA, MapPlot plotB, int dimension) {
        if (dimension == 0) {
            return Integer.compare(plotA.getBoundary().getLeftTop().getX(), plotB.getBoundary().getLeftTop().getX());
        } else {
            return Integer.compare(plotA.getBoundary().getLeftTop().getZ(), plotB.getBoundary().getLeftTop().getZ());
        }
    }

    private int compare(Point2D pointA, Point2D pointB, int dimension) {
        if (dimension == 0) {
            return Integer.compare(pointA.getX(), pointB.getX());
        } else {
            return Integer.compare(pointA.getZ(), pointB.getZ());
        }
    }

    public void remove(MapPlot plot) {
        root = remove(root, plot, 0);
    }

    private KDNode remove(KDNode node, MapPlot plot, int depth) {
        if (node == null) return null;
        int currentDimension = depth % DIMENSION;

        if (plot.equals(node.getPlot())) {
            if (node.getRight() != null) {
                KDNode successor = findMinimum(node.getRight(), currentDimension, depth + 1);
                node.setPlot(successor.getPlot());
                node.setRight(remove(node.getRight(), successor.getPlot(), depth + 1));
            } else if (node.getLeft() != null) {
                KDNode successor = findMinimum(node.getLeft(), currentDimension, depth + 1);
                node.setPlot(successor.getPlot());
                node.setRight(remove(node.getLeft(), successor.getPlot(), depth + 1));
                node.setLeft(null);
            } else {
                return null;
            }
        } else {
            if (compare(plot, node.getPlot(), currentDimension) < 0) {
                node.setLeft(remove(node.getLeft(), plot, depth + 1));
            } else {
                node.setRight(remove(node.getRight(), plot, depth + 1));
            }
        }

        return node;
    }

    private KDNode findMinimum(KDNode node, int dimension, int depth) {
        if (node == null) {
            return null;
        }

        int currentDimension = depth % DIMENSION;

        if (currentDimension == dimension) {
            if (node.getLeft() == null) {
                return node;
            } else {
                return findMinimum(node.getLeft(), dimension, depth + 1);
            }
        } else {
            KDNode leftMin = findMinimum(node.getLeft(), dimension, depth + 1);
            KDNode rightMin = findMinimum(node.getRight(), dimension, depth + 1);

            if (leftMin == null && rightMin == null) {
                return node;
            } else if (leftMin == null) {
                return rightMin;
            } else if (rightMin == null) {
                return leftMin;
            } else {
                if (compare(leftMin.getPlot(), rightMin.getPlot(), dimension) < 0) {
                    return leftMin;
                } else {
                    return rightMin;
                }
            }
        }
    }
}

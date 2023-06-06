package pl.pomoku.fastplotplugin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Square {
    private Point2D leftTop;
    private Point2D rightBottom;

    public Square(Point2D center, int size) {
        leftTop = new Point2D(center.getX() - size, center.getZ() + size);
        rightBottom = new Point2D(center.getX() + size, center.getZ() - size);
    }

    public boolean contains(Point2D point) {
        return point.getX() >= leftTop.getX() && point.getX() <= rightBottom.getX() &&
                point.getZ() <= leftTop.getZ() && point.getZ() >= rightBottom.getZ();
    }

    public boolean contains(Square other) {
        return contains(other.getLeftTop()) && contains(other.getRightBottom());
    }

    public boolean intersects(Square other) {
        return !(other.getLeftTop().getX() > rightBottom.getX() || other.getRightBottom().getX() < leftTop.getX() ||
                other.getLeftTop().getZ() < rightBottom.getZ() || other.getRightBottom().getZ() > leftTop.getZ());
    }
}

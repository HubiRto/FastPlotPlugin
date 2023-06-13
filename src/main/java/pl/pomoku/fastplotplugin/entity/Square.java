package pl.pomoku.fastplotplugin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Square {
    private Point2D topLeft;
    private int size;

    public Square(int centerX, int centerZ, int size) {
        this.topLeft = new Point2D(centerX - (size / 2), centerZ + (size / 2));
        this.size = size;
    }

    public Point2D getBottomRight() {
        return new Point2D(topLeft.getX() + size, topLeft.getZ() - size);
    }

    public Point2D getCenter() {
        return new Point2D(topLeft.getX() + (size / 2), topLeft.getZ() - (size / 2));
    }

    public Point2D getTopRight() {
        return new Point2D(topLeft.getX() + size, topLeft.getZ());
    }

    public Point2D getBottomLeft() {
        return new Point2D(topLeft.getX(), topLeft.getZ() - size);
    }

    public boolean contains(Point2D point) {
        return point.getX() >= topLeft.getX() && point.getX() <= getBottomRight().getX()
                && point.getZ() <= topLeft.getZ() && point.getZ() >= getBottomRight().getZ();
    }

    public boolean contains(Square square) {
        return contains(square.getTopLeft()) && contains(square.getBottomRight())
                && contains(square.getTopRight()) && contains(square.getBottomLeft());
    }

    public boolean isOverlap(Square square) {
        return contains(square.getTopLeft()) || contains(square.getBottomRight())
                || contains(square.getTopRight()) || contains(square.getBottomLeft());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return size == square.size &&
                Objects.equals(topLeft, square.topLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, size);
    }
}

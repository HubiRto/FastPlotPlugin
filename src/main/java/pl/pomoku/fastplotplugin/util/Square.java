package pl.pomoku.fastplotplugin.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class Square {
    private Point2D leftTop;
    private int size;

    public Square(Point2D center, int size) {
        this.leftTop = new Point2D(center.getX() - size, center.getZ() + size);
        this.size = size;
    }

    public Point2D getRightBottom(){
        return new Point2D(leftTop.getX() + 2*size, leftTop.getZ() - 2*size);
    }

    public boolean contains(Point2D point) {
        return point.getX() >= leftTop.getX() && point.getX() <= getRightBottom().getX() &&
                point.getZ() <= leftTop.getZ() && point.getZ() >= getRightBottom().getZ();
    }

    public boolean contains(Square other) {
        return contains(other.getLeftTop()) && contains(other.getRightBottom());
    }

    public boolean intersects(Square other) {
        return !(other.getLeftTop().getX() > getRightBottom().getX() || other.getRightBottom().getX() < leftTop.getX() ||
                other.getLeftTop().getZ() < getRightBottom().getZ() || other.getRightBottom().getZ() > leftTop.getZ());
    }

    public static Square createByPlayerLocation(Location location){
        return new Square(new Point2D(location.getBlockX(), location.getBlockZ()), 50);
    }
}

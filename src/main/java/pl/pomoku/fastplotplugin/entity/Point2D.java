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
public class Point2D {
    private int x;
    private int z;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return x == point2D.x &&
                z == point2D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}

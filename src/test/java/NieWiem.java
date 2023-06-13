import org.junit.Assert;
import org.junit.Test;
import pl.pomoku.fastplotplugin.entity.Point2D;
import pl.pomoku.fastplotplugin.entity.Square;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.tree.QuadTree;

public class NieWiem {
    @Test
    public void test() {
        QuadTree quadTree = new QuadTree(new Square(0, 0, 10000));

        TreePlot treePlot = new TreePlot(new Square(30, 30, 100), "owner", "name");
        TreePlot treePlot2 = new TreePlot(new Square(30, 30, 100), "owner", "name");

        quadTree.insert(treePlot);
        Assert.assertNotNull(quadTree.findPlot(new Point2D(30, 31)));

        quadTree.removePlot(treePlot2);
        Assert.assertNull(quadTree.findPlot(new Point2D(30, 31)));

    }

    @Test
    public void test2(){
        Square square = new Square(0,0,10);
        Square square1 = new Square(0,0,10);
        Assert.assertTrue(square.equals(square1));
    }
}

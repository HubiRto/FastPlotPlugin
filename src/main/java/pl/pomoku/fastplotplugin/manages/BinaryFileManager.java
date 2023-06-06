package pl.pomoku.fastplotplugin.manages;

import pl.pomoku.fastplotplugin.util.Plot;
import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.Square;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plugin;

public class BinaryFileManager {
    public static void savePlots(List<Plot> plots, String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
            for (Plot plot : plots) {
                Square square = plot.getBoundary();
                Point2D leftTop = square.getLeftTop();
                Point2D rightBottom = square.getRightBottom();

                outputStream.writeInt(leftTop.getX());
                outputStream.writeInt(leftTop.getZ());
                outputStream.writeInt(rightBottom.getX());
                outputStream.writeInt(rightBottom.getZ());

                outputStream.writeUTF(plot.getOwnerName());
                outputStream.writeUTF(plot.getOwnerUUID());
                outputStream.writeUTF(plot.getPlotName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Plot> loadPlots(String fileName) {
        List<Plot> plots = new ArrayList<>();
        File file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Plik: " + fileName + " został utworzony.");
            } catch (IOException e) {
                System.out.println("Błąd podczas tworzenia pliku: " + fileName);
                e.printStackTrace();
            }
        }

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
            while (inputStream.available() > 0) {
                int leftTopX = inputStream.readInt();
                int leftTopZ = inputStream.readInt();
                int rightBottomX = inputStream.readInt();
                int rightBottomZ = inputStream.readInt();

                String ownerName = inputStream.readUTF();
                String ownerUUID = inputStream.readUTF();
                String plotName = inputStream.readUTF();

                Point2D leftTop = new Point2D(leftTopX, leftTopZ);
                Point2D rightBottom = new Point2D(rightBottomX, rightBottomZ);
                Square square = new Square(leftTop, rightBottom);
                Plot plot = new Plot(square, ownerName, ownerUUID, plotName);
                plots.add(plot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return plots;
    }
}

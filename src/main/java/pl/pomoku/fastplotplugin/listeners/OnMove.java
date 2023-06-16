package pl.pomoku.fastplotplugin.listeners;

import net.kyori.adventure.audience.Audience;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.pomoku.fastplotplugin.entity.Square;
import pl.pomoku.fastplotplugin.entity.TreePlot;
import pl.pomoku.fastplotplugin.movement.PlotMovement;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.*;

public class OnMove implements Listener {
    private static final double WALL_DISTANCE = 2.0;
    @EventHandler
    public void move(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Location toLocation = event.getTo();
        if (isIntegerLocation(toLocation)) return;

        TreePlot currentPlot = plotManager.serachPlot(player.getLocation());
        PlotMovement plotMovement = getPlotMovement(currentPlot, player);
        if (plotMovement == null) return;

        Audience audience = audiences.sender(player);
        plotMovement.execute(player, currentPlot, audience, this);

    }

    private boolean isIntegerLocation(Location location) {
        double x = location.getX();
        double z = location.getZ();
        return isInteger(x) || isInteger(z);
    }

    private boolean isInteger(double number) {
        return number == (int) number;
    }

    private PlotMovement getPlotMovement(TreePlot currentPlot, Player player) {
        if (currentPlot != null) {
            if (!playerOnPlotManager.isPlayerOnPlot(player)) {
                return PlotMovement.ENTER;
            } else {
                if (!playerOnPlotManager.getPlot(player).equals(currentPlot)) {
                    return PlotMovement.CHANGE;
                }
            }
        } else {
            if (playerOnPlotManager.isPlayerOnPlot(player)) {
                return PlotMovement.EXIT;
            }
        }
        return null;
    }

    public void spawnPlotBorderParticles(Player player, TreePlot plot, int searchRadius) {
        if (plot == null) {
            return;
        }

        Square plotBounds = plot.getBoundary();
        World world = player.getWorld();
        double playerX = player.getLocation().getX();
        double playerY = player.getLocation().getY();
        double playerZ = player.getLocation().getZ();

        double minX = plotBounds.getTopLeft().getX();
        double minZ = plotBounds.getTopLeft().getZ();
        double maxX = plotBounds.getBottomRight().getX();
        double maxZ = plotBounds.getBottomRight().getZ();

        double step = 0.1; // Krok pomiędzy generowanymi cząsteczkami
        double height = 2.0; // Wysokość generowanych cząsteczek

        // Sprawdzanie granicy działki w obrębie określonego promienia
        for (double x = playerX - searchRadius; x <= playerX + searchRadius; x += step) {
            checkAndSpawnParticle(world, x, playerY + height, minZ, minX, maxX, playerZ, step);
            checkAndSpawnParticle(world, x, playerY + height, maxZ, minX, maxX, playerZ, step);
        }

        for (double z = playerZ - searchRadius; z <= playerZ + searchRadius; z += step) {
            checkAndSpawnParticle(world, minX, playerY + height, z, minZ, maxZ, playerX, step);
            checkAndSpawnParticle(world, maxX, playerY + height, z, minZ, maxZ, playerX, step);
        }
    }

    private void checkAndSpawnParticle(World world, double x, double y, double z, double min, double max, double playerPos, double step) {
        if (playerPos >= min && playerPos <= max) {
            spawnParticle(world, x, y, z, Particle.REDSTONE);
        } else {
            for (double i = min; i <= max; i += step) {
                if (Math.abs(playerPos - i) <= step / 2) {
                    spawnParticle(world, x, y, z, Particle.REDSTONE);
                    break;
                }
            }
        }
    }

    private void spawnParticle(World world, double x, double y, double z, Particle particle) {
        world.spawnParticle(particle, x, y, z, 1, 0, 0, 0, 0);
    }
}

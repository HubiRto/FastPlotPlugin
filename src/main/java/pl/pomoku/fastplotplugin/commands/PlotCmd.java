package pl.pomoku.fastplotplugin.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.util.Plot;
import pl.pomoku.fastplotplugin.util.Point2D;
import pl.pomoku.fastplotplugin.util.Square;
import pl.pomoku.pomokupluginsrepository.commands.CommandInfo;
import pl.pomoku.pomokupluginsrepository.commands.EasyCommand;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotDataService;
import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotManager;
import static pl.pomoku.pomokupluginsrepository.text.Text.strToComp;

@CommandInfo(name = "plot", requiresPlayer = true)
public class PlotCmd extends EasyCommand {
    @Override
    public void execute(Player p, String[] args) {
        if(args.length == 2) {
            if(args[0].equals("create")){
                int id = plotDataService.getMaxId() + 1;
                Plot plot = new Plot(squareFromPoint(p.getLocation()));
                if(plotManager.checkPlotOverlap(plot)){
                    p.sendMessage(strToComp("<red>Nie możesz stworzyć działki. Działka nachodzi na inną działkę."));
                    return;
                }
                plot.setId(id);
                plot.setPlotName(args[1]);
                plot.setOwnerName(p.getName());
                plot.setOwnerUUID(p.getUniqueId().toString());
                plotManager.addPlot(plot);
            }
        }
    }


    private Square squareFromPoint(Location location){
        return new Square(new Point2D((int) location.getX(), (int) location.getZ()), 50);
    }
}

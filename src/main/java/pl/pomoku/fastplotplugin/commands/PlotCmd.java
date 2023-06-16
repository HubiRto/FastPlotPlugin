package pl.pomoku.fastplotplugin.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.pomoku.fastplotplugin.entity.Plot;
import pl.pomoku.fastplotplugin.entity.Square;
import pl.pomoku.pomokupluginsrepository.commands.CommandInfo;
import pl.pomoku.pomokupluginsrepository.commands.EasyCommand;

import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotManager;
import static pl.pomoku.fastplotplugin.FastPlotPlugin.plotService;
import static pl.pomoku.pomokupluginsrepository.text.Text.strToComp;

@CommandInfo(name = "plot", requiresPlayer = true)
public class PlotCmd extends EasyCommand {
    @Override
    public void execute(Player p, String[] args) {
        if (args.length == 2) {
            if (args[0].equals("create")) {

//                if (plotService.existsByOwnerUUID(p.getUniqueId().toString())) {
//                    p.sendMessage(strToComp("<red>Nie możesz stworzyć działki. Posiadasz już działkę."));
//                    return;
//                }

                Location loc = p.getLocation();

                Square plotBoundary = new Square(loc.getBlockX(), loc.getBlockZ(), 50);
                if (plotManager.isPlotOverlap(plotBoundary)) {
                    p.sendMessage(strToComp("<red>Nie możesz stworzyć działki. Działka nachodzi na inną działkę."));
                    return;
                }

                Plot plot = Plot.builder()
                        .plotName(args[1])
                        .ownerName(p.getName())
                        .ownerUUID(p.getUniqueId().toString())
                        .topLeftX(plotBoundary.getTopLeft().getX())
                        .topLeftZ(plotBoundary.getTopLeft().getZ())
                        .size(50)
                        .build();


                plotManager.addPlot(plot);
                p.sendMessage(strToComp("<green>Pomyślnie stworzono działkę."));
            }
        } else if (args.length == 1) {
            if (args[0].equals("remove")) {
                if (!plotService.existsByOwnerUUID(p.getUniqueId().toString())) {
                    p.sendMessage(strToComp("<red>Nie możesz usunąć działki. Nie posiadasz działki."));
                    return;
                }

                Plot plot = plotService.findByOwnerUUID(p.getUniqueId().toString());
                plotManager.removePlot(plot);
                p.sendMessage(strToComp("<green>Pomyślnie usunięto działkę."));
                //TODO: dodać usuwanie bossBarów i graczy na działkach z menadżera
            }
        }
    }
}

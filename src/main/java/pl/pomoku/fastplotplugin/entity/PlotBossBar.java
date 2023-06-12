package pl.pomoku.fastplotplugin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.kyori.adventure.bossbar.BossBar;

@AllArgsConstructor
@Data
public class PlotBossBar {
    private BossBar bossBar;
    private TreePlot plot;
}

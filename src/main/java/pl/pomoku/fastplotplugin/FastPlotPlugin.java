package pl.pomoku.fastplotplugin;

import lombok.Getter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.pomoku.fastplotplugin.manages.PlotManager;
import pl.pomoku.fastplotplugin.services.PlotService;
import pl.pomoku.pomokupluginsrepository.commands.EasyCommand;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@Getter
public final class FastPlotPlugin extends JavaPlugin {
    private static AnnotationConfigApplicationContext applicationContext;
    public static FastPlotPlugin plugin;
    public static final int MAP_SIZE = 10000;
    public static PlotManager plotManager;
    public static PlotService plotService;
    public static BukkitAudiences audiences;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("pl.pomoku.fastplotplugin");
        applicationContext.refresh();

        plotService = (PlotService) applicationContext.getBean("plotService");
        plotManager = new PlotManager();
        audiences = BukkitAudiences.create(this);

        loadListenersAndCommands();
    }

    @Override
    public void onDisable() {
        applicationContext.close();
    }

    private void loadListenersAndCommands() {
        String packageName = getClass().getPackage().getName();
        loadListeners(packageName, ".listeners");
        loadCommands(packageName, ".commands");
    }

    private void loadListeners(String packageName, String listenersPackage) {
        for (Class<?> clazz : new Reflections(packageName + listenersPackage)
                .getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadCommands(String packageName, String commandsPackage) {
        for (Class<? extends EasyCommand> clazz : new Reflections(packageName + commandsPackage)
                .getSubTypesOf(EasyCommand.class)) {
            try {
                EasyCommand pluginCommand = clazz.getDeclaredConstructor().newInstance();
                Objects.requireNonNull(getCommand(pluginCommand.getCommandInfo().name())).setExecutor(pluginCommand);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}

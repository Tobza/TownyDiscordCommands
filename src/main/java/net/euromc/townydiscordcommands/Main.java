package net.euromc.townydiscordcommands;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfiguration();
        BotMain.main();
    }

    @Override
    public void onDisable() {
    }

    public void loadConfiguration(){
        instance.getConfig().options().copyDefaults(false);
        instance.saveDefaultConfig();
    }
}

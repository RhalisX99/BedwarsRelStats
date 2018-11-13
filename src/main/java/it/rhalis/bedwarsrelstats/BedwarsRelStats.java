package it.rhalis.bedwarsrelstats;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import it.rhalis.bedwarsrelstats.command.MainCommand;
import it.rhalis.bedwarsrelstats.hook.PAPIHook;
import it.rhalis.bedwarsrelstats.sql.SQLManager;
import it.rhalis.bedwarsrelstats.listener.PluginListener;
import it.rhalis.bedwarsrelstats.listener.PlayerListener;

import java.util.logging.Logger;

public final class BedwarsRelStats extends JavaPlugin {

    private static BedwarsRelStats plugin;

    private String ip;
    private String port;
    private String databaseName;
    private String user;
    private String password;

    private SQLManager sql;

    @Override
    public void onEnable() {

        plugin = this;
        saveDefaultConfig();

        setupListeners();
        setupCommands();

        if (getConfig().getBoolean("MySQL.enabled")) {
            this.ip = getConfig().getString("MySQL.connection.ip");
            this.port = getConfig().getString("MySQL.connection.port");
            this.databaseName = getConfig().getString("MySQL.connection.database");
            this.user = getConfig().getString("MySQL.connection.user");
            this.password = getConfig().getString("MySQL.connection.password");

            sql = new SQLManager("jdbc:mysql://" + ip + ":" + port + "/" + databaseName + "?autoReconnect=true&user=" + user + "&password=" + password);
        } else {
            Logger logger = getLogger();

            logger.info("MySQL is not enabled. Searching BedwarsRel plugin...");
            if (getServer().getPluginManager().isPluginEnabled("BedwarsRel")) {
                logger.info("BedwarsRel found, BedwarsRelStats will get player stats from it.");
            } else {
                logger.info("BedwarsRel not found. Enable the MySQL feature or add BedwarsRel plugin in your plugins folder!");
            }
        }


        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PAPIHook(this).register();
        }
    }

    private void setupListeners(){
        new PlayerListener(this);
        new PluginListener(this);
    }

    private void setupCommands(){
        new MainCommand(this, "bedwarsrelstats");
    }

    public static BedwarsRelStats getInstance() {
        return plugin;
    }

    public SQLManager getSQLManager() {
        return sql;
    }

    public String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
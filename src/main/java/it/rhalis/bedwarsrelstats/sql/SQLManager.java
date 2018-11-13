package it.rhalis.bedwarsrelstats.sql;

import org.bukkit.entity.Player;
import it.rhalis.bedwarsrelstats.BedwarsRelStats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public final class SQLManager {

    private Connection conn;

    private String url;
    private boolean isConnected;

    private HashMap<Player, PlayerStats> stats;
    private SQLStatsAPI api;

    public SQLManager(String url) {
        this.url = url;
        this.isConnected = false;
        this.api = new SQLStatsAPI(this);
        connect();
    }

    private void connect() {
        BedwarsRelStats plugin = BedwarsRelStats.getInstance();
        try {
            conn = DriverManager.getConnection(url);
            plugin.getLogger().info("Database connection done successfully.");

            stats = new HashMap<>();
            isConnected = true;
        } catch (SQLException ex) {
            this.isConnected = false;
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    protected void setConnected(boolean b){
        this.isConnected = b;
    }

    public void addPlayerStats(PlayerStats ps){
        stats.put(ps.getPlayer(), ps);
    }

    public PlayerStats getPlayerStats(Player p){
        return stats.get(p);
    }

    public SQLStatsAPI getApi(){
        return this.api;
    }

}

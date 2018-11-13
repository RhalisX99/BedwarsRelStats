package it.rhalis.bedwarsrelstats.sql;

import org.bukkit.entity.Player;
import it.rhalis.bedwarsrelstats.BedwarsRelStats;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class SQLStatsAPI {

    private SQLManager manager;
    private String tablePrefix;

    public SQLStatsAPI(SQLManager manager){
        this.manager = manager;
        this.tablePrefix = BedwarsRelStats.getInstance().getConfig().getString("MySQL.connection.table-prefix");
    }
    
    public int getWins(Player p) {
        int i = 0;
        try {
            Statement s = manager.getConnection().createStatement();
            String query = "SELECT wins FROM " + tablePrefix + "stats_players WHERE uuid = '" + p.getUniqueId() + "';";
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                i = r.getInt("wins");
            }
        } catch (SQLException ex) {
            manager.setConnected(false);
            ex.printStackTrace();
        }
        return i;
    }
    
    public int getLoses(Player p) {
        int i = 0;
        try {
            Statement s = manager.getConnection().createStatement();
            String query = "SELECT loses FROM " + tablePrefix + "stats_players WHERE uuid = '" + p.getUniqueId() + "';";
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                i = r.getInt("loses");
            }
            
        } catch (SQLException ex) {
            manager.setConnected(false);
            ex.printStackTrace();
        }
        return i;
    }
    
    public int getDestroyedBeds(Player p) {
        int i = 0;
        try {
            Statement s = manager.getConnection().createStatement();
            String query = "SELECT destroyedBeds FROM " + tablePrefix + "stats_players WHERE uuid = '" + p.getUniqueId() + "';";
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                i = r.getInt("destroyedBeds");
            }
        } catch (SQLException ex) {
            manager.setConnected(false);
            ex.printStackTrace();
        }
        return i;
    }
    
    public int getScore(Player p) {
        int i = 0;
        try {
            Statement s = manager.getConnection().createStatement();
            String query = "SELECT score FROM " + tablePrefix + "stats_players WHERE uuid = '" + p.getUniqueId() + "';";
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                i = r.getInt("score");
            }
        } catch (SQLException ex) {
            manager.setConnected(false);
            ex.printStackTrace();
        }
        return i;
    }
    
    public int getKills(Player p) {
        int i = 0;
        try {
            Statement s = manager.getConnection().createStatement();
            String query = "SELECT kills FROM " + tablePrefix + "stats_players WHERE uuid = '" + p.getUniqueId() + "';";
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                i = r.getInt("kills");
            }
        } catch (SQLException ex) {
            manager.setConnected(false);
            ex.printStackTrace();
        }
        return i;
    }

    public int getDeaths(Player p) {
        int i = 0;
        try {
            Statement s = manager.getConnection().createStatement();
            String query = "SELECT deaths FROM " + tablePrefix + "stats_players WHERE uuid = '" + p.getUniqueId() + "';";
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                i = r.getInt("deaths");
            }
        } catch (SQLException ex) {
            manager.setConnected(false);
            ex.printStackTrace();
        }
        return i;
    }
}

package it.rhalis.bedwarsrelstats.hook;

import io.github.bedwarsrel.BedwarsRel;
import it.rhalis.bedwarsrelstats.BedwarsRelStats;
import it.rhalis.bedwarsrelstats.sql.SQLManager;
import java.text.DecimalFormat;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.entity.Player;

public class PAPIHook extends PlaceholderExpansion {

    private BedwarsRelStats plugin;

    public PAPIHook(BedwarsRelStats p) {
        this.plugin = p;
        plugin.getLogger().info("PlaceholderAPI found. Placeholders integration enabled!");
    }

    @Override
    public String getIdentifier() {
        return "BedwarsRelStats";
    }

    @Override
    public String getPlugin() {
        return "BedwarsRelStats";
    }

    @Override
    public String getAuthor() {
        return "RhalisX99";
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        if (p == null) {
            return "";
        }

        SQLManager sql = BedwarsRelStats.getInstance().getSQLManager();
        boolean isUsingMySQL = sql != null;

        boolean isBedwarsRel = plugin.getServer().getPluginManager().isPluginEnabled("BedwarsRel");

        if (identifier.equalsIgnoreCase("player_name")) {
            return p.getName();
        }

        if (identifier.equalsIgnoreCase("player_wins")) {
            String wins;

            if(isUsingMySQL){
                wins = sql.isConnected() ? String.valueOf(sql.getPlayerStats(p).getWins()) : null;
            } else {
                wins = isBedwarsRel ? String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getWins()) : null;
            }
            return wins;
        }

        if (identifier.equalsIgnoreCase("player_loses")) {
            String loses;

            if(isUsingMySQL){
                loses = sql.isConnected() ? String.valueOf(sql.getPlayerStats(p).getLoses()) : null;
            } else {
                loses = isBedwarsRel ? String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getLoses()) : null;
            }
            return loses;
        }

        if (identifier.equalsIgnoreCase("player_total")) {
            String total;

            if (isUsingMySQL) {
                total = sql.isConnected() ? String.valueOf(sql.getPlayerStats(p).getWins() + sql.getPlayerStats(p).getLoses()) : null;
            } else {
                total = isBedwarsRel ? String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getWins()
                                                    + BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getLoses()) : null;
            }
            return total;
        }

        if (identifier.equalsIgnoreCase("player_kills")) {
            String kills;

            if (isUsingMySQL) {
                kills = sql.isConnected() ? String.valueOf(sql.getPlayerStats(p).getKills()) : null;
            } else {
                kills = isBedwarsRel ? String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getKills()) : null;
            }
            return kills;
        }

        if (identifier.equalsIgnoreCase("player_deaths")) {
            String deaths;

            if (isUsingMySQL) {
                deaths = sql.isConnected() ? String.valueOf(sql.getPlayerStats(p).getDeaths()) : null;
            } else {
                deaths = isBedwarsRel ? String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getDeaths()) : null;
            }
            return deaths;
        }

        if (identifier.equalsIgnoreCase("player_destroyedbeds")) {
            String destroyed;

            if (isUsingMySQL) {
                destroyed = sql.isConnected() ? String.valueOf(sql.getPlayerStats(p).getDestroyedBeds()) : null;
            } else {
                destroyed = isBedwarsRel ? String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getDestroyedBeds()) : null;
            }
            return destroyed;
        }

        if (identifier.equalsIgnoreCase("player_score")) {
            String score;

            if (isUsingMySQL) {
                score = sql.isConnected() ? String.valueOf(sql.getPlayerStats(p).getScore()) : null;
            } else {
                score = isBedwarsRel ? String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getScore()) : null;
            }
            return score;
        }

        if (identifier.equalsIgnoreCase("player_kd")) {

            DecimalFormat format = new DecimalFormat("0.00");
            String kd;

            if (isUsingMySQL) {
                try{
                    kd = sql.isConnected() ? format.format((double) (sql.getPlayerStats(p).getKills()/sql.getPlayerStats(p).getDeaths())) : null;
                } catch (ArithmeticException e){
                    kd = String.valueOf(sql.getPlayerStats(p).getKills());
                }
            } else {
                try{
                    kd = isBedwarsRel ? format.format((double) BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getKills()
                            / BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getDeaths()) : null;
                } catch(ArithmeticException e){
                    kd = String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getKills());
                }
            }
            return kd;
        }

        if (identifier.equalsIgnoreCase("player_wl")) {

            DecimalFormat format = new DecimalFormat("0.00");
            String wl;

            if (isUsingMySQL) {
                try{
                    wl = sql.isConnected() ? format.format((double) (sql.getPlayerStats(p).getWins()/sql.getPlayerStats(p).getLoses())) : null;
                } catch (ArithmeticException e){
                    wl = String.valueOf(sql.getPlayerStats(p).getWins());
                }
            } else {
                try{
                    wl = isBedwarsRel ? format.format((double) BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getWins()
                            / BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getLoses()) : null;
                } catch(ArithmeticException e){
                    wl = String.valueOf(BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getWins());
                }
            }
            return wl;
        }
        return null;
    }
}

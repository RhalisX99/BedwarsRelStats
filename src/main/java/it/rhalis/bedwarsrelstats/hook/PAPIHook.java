package it.rhalis.bedwarsrelstats.hook;

import io.github.bedwarsrel.BedwarsRel;
import it.rhalis.bedwarsrelstats.BedwarsRelStats;
import it.rhalis.bedwarsrelstats.sql.SQLManager;
import java.text.DecimalFormat;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;

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

            int wins;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    wins = sql.getPlayerStats(p).getWins();
                    return String.valueOf(wins);

                }
            } else {
                if (isBedwarsRel) {

                    wins = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getWins();
                    return String.valueOf(wins);

                }
            }
        }

        if (identifier.equalsIgnoreCase("player_loses")) {

            int loses;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    loses = sql.getPlayerStats(p).getLoses();
                    return String.valueOf(loses);

                }
            } else {
                if (isBedwarsRel) {

                    loses = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getLoses();
                    return String.valueOf(loses);
                }
            }
        }

        if (identifier.equalsIgnoreCase("player_total")) {

            int total;

            int wins;
            int loses;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    wins = sql.getPlayerStats(p).getKills();
                    loses = sql.getPlayerStats(p).getDeaths();

                    total = wins + loses;

                    return String.valueOf(total);

                }
            } else {
                if (isBedwarsRel) {

                    wins = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getWins();
                    loses = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getLoses();

                    total = wins + loses;

                    return String.valueOf(total);
                }
            }
        }

        if (identifier.equalsIgnoreCase("player_kills")) {

            int kills;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    kills = sql.getPlayerStats(p).getKills();
                    return String.valueOf(kills);

                }
            } else {
                if (isBedwarsRel) {

                    kills = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getKills();
                    return String.valueOf(kills);
                }
            }
        }

        if (identifier.equalsIgnoreCase("player_deaths")) {

            int deaths;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    deaths = sql.getPlayerStats(p).getDeaths();
                    return String.valueOf(deaths);

                }
            } else {
                if (isBedwarsRel) {

                    deaths = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getDeaths();
                    return String.valueOf(deaths);
                }
            }
        }

        if (identifier.equalsIgnoreCase("player_destroyedbeds")) {

            int destroyed;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    destroyed = sql.getPlayerStats(p).getDestroyedBeds();
                    return String.valueOf(destroyed);

                }
            } else {
                if (isBedwarsRel) {

                    destroyed = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getDestroyedBeds();
                    return String.valueOf(destroyed);
                }
            }
        }

        if (identifier.equalsIgnoreCase("player_score")) {

            int score;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    score = sql.getPlayerStats(p).getScore();
                    return String.valueOf(score);

                }
            } else {
                if (isBedwarsRel) {

                    score = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getScore();
                    return String.valueOf(score);
                }
            }
        }

        if (identifier.equalsIgnoreCase("player_kd")) {

            DecimalFormat format = new DecimalFormat("0.00");
            double kd;

            int kills;
            int deaths;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    kills = sql.getPlayerStats(p).getKills();
                    deaths = sql.getPlayerStats(p).getDeaths();

                    try {
                        kd = (double)kills / deaths;
                    } catch (ArithmeticException e) {
                        kd = kills;
                    }

                    return format.format(kd);

                }
            } else {
                if (isBedwarsRel) {

                    kills = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getKills();
                    deaths = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getDeaths();

                    try {
                        kd = (double)kills / deaths;
                    } catch (ArithmeticException e) {
                        kd = kills;
                    }

                    return format.format(kd);
                }
            }
        }

        if (identifier.equalsIgnoreCase("player_wl")) {

            DecimalFormat format = new DecimalFormat("0.00");
            double wl;

            int wins;
            int loses;

            if (isUsingMySQL) {
                if (sql.isConnected()) {

                    wins = sql.getPlayerStats(p).getKills();
                    loses = sql.getPlayerStats(p).getDeaths();

                    try {
                        wl = (double)wins / loses;
                    } catch (ArithmeticException e) {
                        wl = wins;
                    }

                    return format.format(wl);

                }
            } else {
                if (isBedwarsRel) {

                    wins = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getWins();
                    loses = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p).getLoses();

                    try {
                        wl = (double)wins / loses;
                    } catch (ArithmeticException e) {
                        wl = wins;
                    }

                    return format.format(wl);
                }
            }
        }

        return null;
    }
}

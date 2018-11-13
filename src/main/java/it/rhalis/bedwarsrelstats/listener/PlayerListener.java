package it.rhalis.bedwarsrelstats.listener;

import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.statistics.PlayerStatistic;

import it.rhalis.bedwarsrelstats.BedwarsRelStats;
import it.rhalis.bedwarsrelstats.sql.SQLManager;
import it.rhalis.bedwarsrelstats.sql.SQLStatsAPI;
import it.rhalis.bedwarsrelstats.sql.PlayerStats;

import java.text.DecimalFormat;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public final class PlayerListener implements Listener {

    private BedwarsRelStats plugin;

    public PlayerListener(BedwarsRelStats p) {
        this.plugin = p;
        p.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        SQLManager sql = plugin.getSQLManager();

        if (sql != null) {
            if (sql.isConnected()) {

                PlayerStats stats = new PlayerStats(p);
                SQLStatsAPI api = plugin.getSQLManager().getApi();

                stats.setWins(api.getWins(p));
                stats.setLoses(api.getLoses(p));
                stats.setKills(api.getKills(p));
                stats.setDeaths(api.getDeaths(p));
                stats.setDestroyedBeds(api.getDestroyedBeds(p));
                stats.setScore(api.getScore(p));

                sql.addPlayerStats(stats);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equals("/" + plugin.getConfig().getString("Command.custom-command"))) {
            e.setCancelled(true);
            sendStats(e.getPlayer());
        }
    }

    private void sendStats(Player p) {

        int wins;
        int loses;
        int kills;
        int deaths;
        int destroyed;
        int score;

        int total;

        double kd;
        double wl;

        SQLManager sql = plugin.getSQLManager();
        boolean isUsingMySQL = sql != null;

        if (isUsingMySQL) {
            if (sql.isConnected()) {
                PlayerStats ps = sql.getPlayerStats(p);

                wins = ps.getWins();
                loses = ps.getLoses();
                kills = ps.getKills();
                deaths = ps.getDeaths();
                destroyed = ps.getDestroyedBeds();
                score = ps.getScore();

            } else {
                p.sendMessage(plugin.translate(plugin.getConfig().getString("Messages.main-command.not-set-correctly")));
                return;
            }
        } else {
            if (BedwarsRelStats.getInstance().getServer().getPluginManager().isPluginEnabled("BedwarsRel")) {

                PlayerStatistic ps = BedwarsRel.getInstance().getPlayerStatisticManager().getStatistic(p);

                wins = ps.getWins();
                loses = ps.getLoses();
                kills = ps.getKills();
                deaths = ps.getDeaths();
                destroyed = ps.getDestroyedBeds();
                score = ps.getScore();

            } else {
                p.sendMessage(plugin.translate(plugin.getConfig().getString("Messages.custom-command.not-set-correctly")));
                return;
            }
        }

        total = wins + loses;

        try {
            kd = (double)kills / deaths;
            wl = (double)wins / loses;
        } catch (ArithmeticException ex) { //Prevent division by 0
            kd = kills;
            wl = wins;
        }

        DecimalFormat format = new DecimalFormat("0.00");

        List<String> rows = BedwarsRelStats.getInstance().getConfig().getStringList("Command.output");
        for (String row : rows) {
            
            String result = row
                    .replaceAll("\\{name}", p.getName())
                    .replaceAll("\\{displayname}", p.getDisplayName())
                    .replaceAll("\\{wins}", String.valueOf(wins))
                    .replaceAll("\\{loses}", String.valueOf(loses))
                    .replaceAll("\\{total}", String.valueOf(total))
                    .replaceAll("\\{kills}", String.valueOf(kills))
                    .replaceAll("\\{deaths}", String.valueOf(deaths))
                    .replaceAll("\\{destroyed}", String.valueOf(destroyed))
                    .replaceAll("\\{score}", String.valueOf(score))
                    .replaceAll("\\{kd}", format.format(kd))
                    .replaceAll("\\{wl}", format.format(wl));
            p.sendMessage(plugin.translate(result));
        }
    }
}

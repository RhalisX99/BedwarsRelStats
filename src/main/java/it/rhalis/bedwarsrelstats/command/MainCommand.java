package it.rhalis.bedwarsrelstats.command;

import it.rhalis.bedwarsrelstats.BedwarsRelStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private BedwarsRelStats plugin;

    public MainCommand(BedwarsRelStats p, String command) {
        this.plugin = p;
        p.getCommand(command).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bedwarsrelstats.admin")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(plugin.translate(plugin.getConfig().getString("Messages.main-command.reload-successful")));
                } else {
                    sender.sendMessage(plugin.translate(plugin.getConfig().getString("Messages.main-command.correct-usage")));
                }
            } else {
                sender.sendMessage(plugin.translate(plugin.getConfig().getString("Messages.main-command.correct-usage")));
            }
        } else {
            sender.sendMessage(plugin.translate(plugin.getConfig().getString("Messages.main-command.no-permission")));
        }
        return true;
    }
}

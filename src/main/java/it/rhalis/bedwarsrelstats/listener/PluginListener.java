package it.rhalis.bedwarsrelstats.listener;

import it.rhalis.bedwarsrelstats.BedwarsRelStats;
import it.rhalis.bedwarsrelstats.hook.PAPIHook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.clip.placeholderapi.events.ExpansionUnregisterEvent;

import org.bukkit.event.server.PluginEnableEvent;

public final class PluginListener implements Listener{
    
    private BedwarsRelStats plugin;
    
    public PluginListener(BedwarsRelStats p){
        this.plugin = p;
        p.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onBedwarsEnable(PluginEnableEvent e){
        if (e.getPlugin().getName().equals("BedwarsRel")){
            if (plugin.getSQLManager() != null){
                plugin.getLogger().info("BedwarsRelStats will now get stats from BedwarsRel plugin.");
            }
        }
    }


    /*
        Used to prevent a bug of /papi reload, which
        unregisters the integrated BedwarsRelStats expansion
    */
    @EventHandler
    public void onUnregister(ExpansionUnregisterEvent e){
        if (e.getExpansion() instanceof PAPIHook){
            plugin.getLogger().info("Successfully registered BedwarsRelStats expansion");
            PAPIHook p = (PAPIHook) e.getExpansion();
            p.register();
        }
    }
}

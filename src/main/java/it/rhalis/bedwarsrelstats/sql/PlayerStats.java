package it.rhalis.bedwarsrelstats.sql;

import org.bukkit.entity.Player;

//Used to store temporary player stats with MySQL
public final class PlayerStats {
    
    private Player p;
    
    private int wins = 0;
    private int loses = 0;
    private int kills = 0;
    private int deaths = 0;
    private int destroyed = 0;
    private int score = 0;
    
    public PlayerStats(Player p){
        this.p = p;
    }
    
    public void setWins(int w){
        this.wins = w;
    }
    
    public void setLoses(int l){
        this.loses = l;
    }
    
    public void setKills(int k){
        this.kills = k;
    }
    
    public void setDeaths(int d){
        this.deaths = d;
    }
    
    public void setDestroyedBeds(int db){
        this.destroyed = db;
    }
    
    public void setScore(int s){
        this.score = s;
    }
    
    public int getWins(){
        return this.wins;
    }
    
    public int getLoses(){
        return this.loses;
    }
    
    public int getKills(){
        return this.kills;
    }
    
    public int getDeaths(){
        return this.deaths;
    }
    
    public int getDestroyedBeds(){
        return this.destroyed;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public Player getPlayer(){
        return this.p;
    }
}

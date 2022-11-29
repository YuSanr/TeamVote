package kawayi.hanhanqaq.teamvote.FileBase;

import kawayi.hanhanqaq.teamvote.TeamVote;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class FileConfigManager{
    static Plugin plugin = TeamVote.getPlugin(TeamVote.class);
    public static File votefile =  new File(plugin.getDataFolder(),"votedata.yml");
    public static FileConfiguration votedata = YamlConfiguration.loadConfiguration(votefile);
}

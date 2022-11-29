package kawayi.hanhanqaq.teamvote.Command;

import kawayi.hanhanqaq.teamvote.FileBase.FileConfigManager;
import kawayi.hanhanqaq.teamvote.TeamVote;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class GiveBackStakedPoints implements CommandExecutor {
    Set<String> keys = FileConfigManager.votedata.getKeys(true);
    HashMap<Player,Integer> map = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            for (String key : keys) {
                String str = key.replace(".",",");
                String[] strings = str.split(",");
                if (strings.length == 2 && Objects.equals(strings[1], "staked")) {
                    map.put(Bukkit.getPlayer(strings[0]), FileConfigManager.votedata.getInt(strings[0] + ".staked"));
                }
            }
            for (Player player:Bukkit.getOnlinePlayers()) {
                int points = TeamVote.ppAPI.look(player.getUniqueId());
                int returnPoints = points +
            }
        }
        return false;
    }
    public int theBeiShu(String winTeamName) {
        HashMap<String,Integer> map1 = new HashMap<>();
        map1.put("staked",0);
        map1.put("teamstaked",0);
        for (String key : keys) {
            String str = key.replace(".",",");
            String[] strings = str.split(",");
            if (strings.length == 2) {
                if (Objects.equals(strings[1], "staked")) {
                    int stakedpoints = FileConfigManager.votedata.getInt(strings[0] + "." + strings[1]);
                    map1.put("staked",map1.get("staked") + stakedpoints);
                }
            }
        }
        double stakedPoints = map1.get("staked");
        for (String key : keys) {
            String str = key.replace(".",",");
            String[] strings = str.split(",");
            if (strings.length == 2) {
                if (Objects.equals(FileConfigManager.votedata.getString(strings[0] + ".select"), winTeamName))  {
                    if (Objects.equals(strings[1], "staked")) {
                        int stakedpoints = FileConfigManager.votedata.getInt(strings[0] + "." + strings[1]);
                        map1.put("teamstaked",map1.get("teamstaked") + stakedpoints);
                    }
                }
            }
        }
        double winTeamPoints = map1.get("teamstaked");
    }
}

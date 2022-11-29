package kawayi.hanhanqaq.teamvote.Listener;

import kawayi.hanhanqaq.teamvote.FileBase.FileConfigManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PlayerJoinListener implements Listener {
    public static ArrayList<String> namelist = new ArrayList<>();
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent pje) throws IOException, InvalidConfigurationException {
        Player player = pje.getPlayer();
        for (String key : FileConfigManager.votedata.getKeys(true)) {
            String str = key.replace(".",",");
            String[] strings = str.split(",");
            if (strings.length == 1) {
                namelist.add(strings[0]);
            }
        }
        if (!isInData(player.getName())) {
            FileConfigManager.votedata.set(player.getName() + ".select" , "null");
            FileConfigManager.votedata.set(player.getName() + ".staked" , 0);
            FileConfigManager.votedata.save(FileConfigManager.votefile);
            FileConfigManager.votedata.load(FileConfigManager.votefile);
        }
    }
    public boolean isInData(String name) {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("yes",false);
        for (String pname: namelist) {
            if (Objects.equals(pname, name)) {
                map.put("yes",true);
                break;
            }
        }
        return map.get("yes");
    }
}

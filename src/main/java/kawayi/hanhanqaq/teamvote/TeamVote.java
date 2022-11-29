package kawayi.hanhanqaq.teamvote;

import kawayi.hanhanqaq.teamvote.FileBase.FileConfigManager;
import kawayi.hanhanqaq.teamvote.Gui.VoteGUI;
import kawayi.hanhanqaq.teamvote.Listener.PlayerClickListener;
import kawayi.hanhanqaq.teamvote.Listener.PlayerJoinListener;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class TeamVote extends JavaPlugin {

    public static PlayerPointsAPI ppAPI;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            this.ppAPI = PlayerPoints.getInstance().getAPI();
            getLogger().info("[TeamVoteGUI]已Hook到PlayerPoints插件，插件可用！");
        }
        saveDefaultConfig();
        if (!FileConfigManager.votefile.exists()) {
            try {
                FileConfigManager.votefile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        getServer().getPluginManager().registerEvents(new PlayerClickListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this);
        getCommand("votemenu").setExecutor(new VoteGUI());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

package kawayi.hanhanqaq.teamvote.Listener;

import kawayi.hanhanqaq.teamvote.FileBase.FileConfigManager;
import kawayi.hanhanqaq.teamvote.Gui.VoteGUI;
import kawayi.hanhanqaq.teamvote.TeamVote;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class PlayerClickListener implements Listener {
    HashMap<Player,String> selectmap = new HashMap<>();
    @EventHandler
    public void PlayerClickEvent(InventoryClickEvent ice) throws IOException, InvalidConfigurationException {
        Player player = (Player) ice.getWhoClicked();
        UUID playerUuid = player.getUniqueId();
        InventoryView inv = player.getOpenInventory();
        if (inv.getTitle().equals("§7你确定吗")) {
            ice.setCancelled(true);
            switch (ice.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§a确定":
                    openPayMenu(player);
                    break;
                case "§c取消":
                    VoteGUI.openMenu(player);
                    break;
            }
        }
        if (inv.getTitle().equals("§7错误")) {
            ice.setCancelled(true);
            if ("§c你的押注已锁定！".equals(ice.getCurrentItem().getItemMeta().getDisplayName())) {
                VoteGUI.openMenu(player);
            }
        }
        if (inv.getTitle().equals("§7选择下注金额")) {
            ice.setCancelled(true);
            switch (ice.getCurrentItem().getItemMeta().getDisplayName()) {
                //"§e下注 : §b20 点券" "§e下注 : §b50 点券" "§e下注 : §b100 点券" "§e下注 : §b200 点券"
                case "§e下注 : §b20 点券":
                    if (TeamVote.ppAPI.look(playerUuid) < 20) {
                        player.sendMessage("§c下注失败！原因：您没有足够的点券，请尝试选择更低的数额或者充值点券。");
                    } else {
                        TeamVote.ppAPI.set(playerUuid,TeamVote.ppAPI.look(playerUuid) - 20);
                        FileConfigManager.votedata.set(player.getName() + ".staked" , FileConfigManager.votedata.getInt(player.getName() + ".staked") + 20 );
                        FileConfigManager.votedata.set(player.getName() + ".select",selectmap.get(player));
                        FileConfigManager.votedata.save(FileConfigManager.votefile);
                        FileConfigManager.votedata.load(FileConfigManager.votefile);
                        player.sendMessage("§a下注成功！您为队伍：§b" + FileConfigManager.votedata.getString(player.getName() + ".select") + "§a增长了人气值！");
                    VoteGUI.openMenu(player);
                    }
                    break;
                case "§e下注 : §b50 点券":
                    if (TeamVote.ppAPI.look(playerUuid) < 50) {
                        player.sendMessage("§c下注失败！原因：您没有足够的点券，请尝试选择更低的数额或者充值点券。");
                    } else {
                        TeamVote.ppAPI.set(playerUuid,TeamVote.ppAPI.look(playerUuid) - 50);
                        FileConfigManager.votedata.set(player.getName() + ".staked" , FileConfigManager.votedata.getInt(player.getName() + ".staked") + 50 );
                        FileConfigManager.votedata.set(player.getName() + ".select",selectmap.get(player));
                        FileConfigManager.votedata.save(FileConfigManager.votefile);
                        FileConfigManager.votedata.load(FileConfigManager.votefile);
                        player.sendMessage("§a下注成功！您为队伍：§b" + FileConfigManager.votedata.getString(player.getName() + ".select") + "§a增长了人气值！");
                        VoteGUI.openMenu(player);
                    }
                    break;
                case "§e下注 : §b100 点券":
                    if (TeamVote.ppAPI.look(playerUuid) < 100) {
                        player.sendMessage("§c下注失败！原因：您没有足够的点券，请尝试选择更低的数额或者充值点券。");
                    } else {
                        TeamVote.ppAPI.set(playerUuid,TeamVote.ppAPI.look(playerUuid) - 100);
                        FileConfigManager.votedata.set(player.getName() + ".staked" , FileConfigManager.votedata.getInt(player.getName() + ".staked") + 100 );
                        FileConfigManager.votedata.set(player.getName() + ".select",selectmap.get(player));
                        FileConfigManager.votedata.save(FileConfigManager.votefile);
                        FileConfigManager.votedata.load(FileConfigManager.votefile);
                        player.sendMessage("§a下注成功！您为队伍：§b" + FileConfigManager.votedata.getString(player.getName() + ".select") + "§a增长了人气值！");
                        VoteGUI.openMenu(player);
                    }
                    break;
                case "§e下注 : §b200 点券":
                    if (TeamVote.ppAPI.look(playerUuid) < 200) {
                        player.sendMessage("§c下注失败！原因：您没有足够的点券，请尝试选择更低的数额或者充值点券。");
                    } else {
                        TeamVote.ppAPI.set(playerUuid,TeamVote.ppAPI.look(playerUuid) - 200);
                        FileConfigManager.votedata.set(player.getName() + ".staked" , FileConfigManager.votedata.getInt(player.getName() + ".staked") + 200 );
                        FileConfigManager.votedata.set(player.getName() + ".select",selectmap.get(player));
                        FileConfigManager.votedata.save(FileConfigManager.votefile);
                        FileConfigManager.votedata.load(FileConfigManager.votefile);
                        player.sendMessage("§a下注成功！您为队伍：§b" + FileConfigManager.votedata.getString(player.getName() + ".select") + "§a增长了人气值！");
                        VoteGUI.openMenu(player);
                    }
                    break;

            }
        }
        if (inv.getTitle().equals("§7比赛队伍")) {
            Plugin plugin = TeamVote.getPlugin(TeamVote.class);
            ice.setCancelled(true);
            switch (ice.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§a技术组 §6§l已押注":
                case "§a建筑组 §6§l已押注":
                case "§a策划组 §6§l已押注":
                case "§a客服组 §6§l已押注":
                    player.sendMessage("§c你已经为这个队伍押注了！");
                    break;
                case "§c技术组":
                case "§c建筑组":
                case "§c策划组":
                case "§c客服组":
                    openLockMenu(player);
                    break;
                case "§e技术组":
                    if (!plugin.getConfig().getBoolean("MatchStarted",false)) {
                        selectmap.put(player,"技术组");
                        openQRMenu(player);
                    } else {
                        player.sendMessage("§c比赛已经结束了！你不能押注了！");
                    }
                    break;
                case "§e建筑组":
                    if (!plugin.getConfig().getBoolean("MatchStarted",false)) {
                        selectmap.put(player,"建筑组");
                        openQRMenu(player);
                    } else {
                        player.sendMessage("§c比赛已经结束了！你不能押注了！");
                    }
                    break;
                case "§e策划组":
                    if (!plugin.getConfig().getBoolean("MatchStarted",false)) {
                        selectmap.put(player,"策划组");
                        openQRMenu(player);
                    } else {
                        player.sendMessage("§c比赛已经结束了！你不能押注了！");
                    }
                    break;
                case "§e客服组":
                    if (!plugin.getConfig().getBoolean("MatchStarted",false)) {
                        selectmap.put(player,"客服组");
                        openQRMenu(player);
                    } else {
                        player.sendMessage("§c比赛已经结束了！你不能押注了！");
                    }
                    break;
            }
        }
    }

    public void openPayMenu(Player player) {
        Inventory inv = Bukkit.createInventory(Bukkit.getPlayer("sb"),6 * 9,"§7选择下注金额");
        ItemStack item20 = new ItemStack(Material.IRON_INGOT);
        ItemMeta itemMeta20 = item20.getItemMeta();
        itemMeta20.setDisplayName("§e下注 : §b20 点券");
        item20.setItemMeta(itemMeta20);
        inv.setItem(19,item20);

        ItemStack item50 = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta50 = item50.getItemMeta();
        itemMeta50.setDisplayName("§e下注 : §b50 点券");
        item50.setItemMeta(itemMeta50);
        inv.setItem(21,item50);

        ItemStack item100 = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta100 = item100.getItemMeta();
        itemMeta100.setDisplayName("§e下注 : §b100 点券");
        item100.setItemMeta(itemMeta100);
        inv.setItem(23,item100);

        ItemStack item200 = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta200 = item200.getItemMeta();
        itemMeta200.setDisplayName("§e下注 : §b200 点券");
        item200.setItemMeta(itemMeta200);
        inv.setItem(25,item200);
        Bukkit.getPlayer(player.getName()).openInventory(inv);
    }
    public void openLockMenu(Player player) {
        Inventory inv = Bukkit.createInventory(Bukkit.getPlayer("sba"),6 * 9,"§7错误");
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§c你的押注已锁定！");
        itemMeta.setLore(Arrays.asList("§7请等待下一场比赛！","","§e点击返回押注页面！"));
        item.setItemMeta(itemMeta);
        inv.setItem(22,item);
        Bukkit.getPlayer(player.getName()).openInventory(inv);
    }
    public void openQRMenu(Player player) {
        Inventory inv = Bukkit.createInventory(Bukkit.getPlayer("sba"),6 * 9,"§7你确定吗");
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§a确定");
        itemMeta.setLore(Arrays.asList("§7单次比赛中 您仅有一次押注机会！","","§e点击进入支付界面！"));
        item.setItemMeta(itemMeta);
        inv.setItem(20,item);
        ItemStack item1 = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta itemMeta1 = item.getItemMeta();
        itemMeta1.setDisplayName("§c取消");
        itemMeta1.setLore(Arrays.asList("","§e点击返回队伍界面！"));
        item1.setItemMeta(itemMeta1);
        inv.setItem(24,item1);
        Bukkit.getPlayer(player.getName()).openInventory(inv);
    }
}

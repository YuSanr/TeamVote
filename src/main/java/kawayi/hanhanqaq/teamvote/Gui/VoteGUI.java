package kawayi.hanhanqaq.teamvote.Gui;

import kawayi.hanhanqaq.teamvote.FileBase.FileConfigManager;
import kawayi.hanhanqaq.teamvote.TeamVote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class VoteGUI implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            openMenu(player);
        }
        return false;
    }
    public static void openMenu(Player player) {
        Plugin plugin = TeamVote.getPlugin(TeamVote.class);
        Inventory inv = Bukkit.createInventory(Bukkit.getPlayer("sb"), 6 * 9, "§7比赛队伍");
        if (plugin.getConfig().getBoolean("MatchStarted",false)) {
            setSeledItem(inv,Material.STORAGE_MINECART,plugin.getConfig().getString("Item.name").replace("&","§"),31,plugin.getConfig().getString("Item.lore1").replace("&","§"),plugin.getConfig().getString("Item.lore2").replace("&","§"));
        } else {
            setSeledItem(inv,Material.MINECART,plugin.getConfig().getString("Item.name").replace("&","§"),31,plugin.getConfig().getString("Item.lore1").replace("&","§"),plugin.getConfig().getString("Item.lore2").replace("&","§"));
        }

        if (hasSelect(player)) {
            switch (FileConfigManager.votedata.getString(player.getName() + ".select")) {
                case "技术组":
                    setSeledItem(inv,Material.REDSTONE_COMPARATOR,"§a技术组 §6§l已押注",10,"§7相信这个队伍？","§7为技术组下注","","§b该组目前的投票占比：",thePopularProgress("技术组"),"","§c你已经押注了这个队伍！");
                    setUnSelItem(inv,Material.WOOD_AXE,"§c建筑组",12,"§7相信这个队伍？","§7为建筑组下注","","§b该组目前的投票占比：",thePopularProgress("建筑组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setUnSelItem(inv,Material.BOOK_AND_QUILL,"§c策划组",14,"§7相信这个队伍？","§7为策划组下注","","§b该组目前的投票占比：",thePopularProgress("策划组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setUnSelItem(inv,Material.PAPER,"§c客服组",16,"§7相信这个队伍？","§7为客服组下注","","§b该组目前的投票占比：",thePopularProgress("客服组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    Bukkit.getPlayer(player.getName()).openInventory(inv);
                    break;
                case "建筑组":
                    setUnSelItem(inv,Material.REDSTONE_COMPARATOR,"§c技术组",10,"§7相信这个队伍？","§7为技术组下注","","§b该组目前的投票占比：",thePopularProgress("技术组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setSeledItem(inv,Material.WOOD_AXE,"§a建筑组 §6§l已押注",12,"§7相信这个队伍？","§7为建筑组下注","","§b该组目前的投票占比：",thePopularProgress("建筑组"),"","§c你已经押注了这个队伍！");
                    setUnSelItem(inv,Material.BOOK_AND_QUILL,"§c策划组",14,"§7相信这个队伍？","§7为策划组下注","","§b该组目前的投票占比：",thePopularProgress("策划组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setUnSelItem(inv,Material.PAPER,"§c客服组",16,"§7相信这个队伍？","§7为客服组下注","","§b该组目前的投票占比：",thePopularProgress("客服组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    Bukkit.getPlayer(player.getName()).openInventory(inv);
                    break;
                case "策划组":
                    setUnSelItem(inv,Material.REDSTONE_COMPARATOR,"§c技术组",10,"§7相信这个队伍？","§7为技术组下注","","§b该组目前的投票占比：",thePopularProgress("技术组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setUnSelItem(inv,Material.WOOD_AXE,"§c建筑组",12,"§7相信这个队伍？","§7为建筑组下注","","§b该组目前的投票占比：",thePopularProgress("建筑组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setSeledItem(inv,Material.BOOK_AND_QUILL,"§a策划组 §6§l已押注",14,"§7相信这个队伍？","§7为策划组下注","","§b该组目前的投票占比：",thePopularProgress("策划组"),"","§c你已经押注了这个队伍！");
                    setUnSelItem(inv,Material.PAPER,"§c客服组",16,"§7相信这个队伍？","§7为客服组下注","","§b该组目前的投票占比：",thePopularProgress("客服组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    Bukkit.getPlayer(player.getName()).openInventory(inv);
                    break;
                case "客服组":
                    setUnSelItem(inv,Material.REDSTONE_COMPARATOR,"§c技术组",10,"§7相信这个队伍？","§7为技术组下注","","§b该组目前的投票占比：",thePopularProgress("技术组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setUnSelItem(inv,Material.WOOD_AXE,"§c建筑组",12,"§7相信这个队伍？","§7为建筑组下注","","§b该组目前的投票占比：",thePopularProgress("建筑组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setUnSelItem(inv,Material.BOOK_AND_QUILL,"§c策划组",14,"§7相信这个队伍？","§7为策划组下注","","§b该组目前的投票占比：",thePopularProgress("策划组"),"","§e点击对这个队伍押注！","§c注意：你只能同时对一个队伍进行押注");
                    setSeledItem(inv,Material.PAPER,"§a客服组 §6§l已押注",16,"§7相信这个队伍？","§7为客服组下注","","§b该组目前的投票占比：",thePopularProgress("客服组"),"","§c你已经押注了这个队伍！");
                    Bukkit.getPlayer(player.getName()).openInventory(inv);
                    break;
            }
        } else {
            setUnSelItem(inv,Material.REDSTONE_COMPARATOR,"§e技术组",10,"§7相信这个队伍？","§7为技术组下注","","§b该组目前的投票占比：",thePopularProgress("技术组"),"","§e点击对这个队伍押注！");
            setUnSelItem(inv,Material.WOOD_AXE,"§e建筑组",12,"§7相信这个队伍？","§7为建筑组下注","","§b该组目前的投票占比：",thePopularProgress("建筑组"),"","§e点击对这个队伍押注！");
            setUnSelItem(inv,Material.BOOK_AND_QUILL,"§e策划组",14,"§7相信这个队伍？","§7为策划组下注","","§b该组目前的投票占比：",thePopularProgress("策划组"),"","§e点击对这个队伍押注！");
            setUnSelItem(inv,Material.PAPER,"§e客服组",16,"§7相信这个队伍？","§7为客服组下注","","§b该组目前的投票占比：",thePopularProgress("客服组"),"","§e点击对这个队伍押注！");
            Bukkit.getPlayer(player.getName()).openInventory(inv);
        }
    }
    public static String thePopularProgress(String teamName) {
        Set<String> keys = FileConfigManager.votedata.getKeys(true);
        HashMap<String,Integer> map = new HashMap<>();
        map.put("staked",0);
        map.put("teamstaked",0);
        HashMap<Integer,String> colorMap = new HashMap<>();
        for (String key : keys) {
            String str = key.replace(".",",");
            String[] strings = str.split(",");
            if (strings.length == 2) {
                if (Objects.equals(strings[1], "staked")) {
                    int stakedpoints = FileConfigManager.votedata.getInt(strings[0] + "." + strings[1]);
                    map.put("staked",map.get("staked") + stakedpoints);
                }
            }
        }
        double stakedPoints = map.get("staked");
        for (String key : keys) {
            String str = key.replace(".",",");
            String[] strings = str.split(",");
            if (strings.length == 2) {
                if (Objects.equals(FileConfigManager.votedata.getString(strings[0] + ".select"), teamName))  {
                    if (Objects.equals(strings[1], "staked")) {
                        int stakedpoints = FileConfigManager.votedata.getInt(strings[0] + "." + strings[1]);
                        map.put("teamstaked",map.get("teamstaked") + stakedpoints);
                    }
                }
            }
        }
        double teamStakedPoints = map.get("teamstaked");
        double precent = teamStakedPoints/stakedPoints * 100;
        colorMap.put(1,"§7");
        colorMap.put(2,"§7");
        colorMap.put(3,"§7");
        colorMap.put(4,"§7");
        colorMap.put(5,"§7");
        colorMap.put(6,"§7");
        colorMap.put(7,"§7");
        colorMap.put(8,"§7");
        colorMap.put(9,"§7");
        colorMap.put(10,"§7");
        switch (finalPercent((int) precent)) {
            case 0:
                colorMap.put(0,"§b");
                break;
            case 10:
                colorMap.put(1,"§b");
                break;
            case 20:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                break;
            case 30:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                break;
            case 40:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                colorMap.put(4,"§b");
                break;
            case 50:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                colorMap.put(4,"§b");
                colorMap.put(5,"§b");
                break;
            case 60:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                colorMap.put(4,"§b");
                colorMap.put(5,"§b");
                colorMap.put(6,"§b");
                break;
            case 70:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                colorMap.put(4,"§b");
                colorMap.put(5,"§b");
                colorMap.put(6,"§b");
                colorMap.put(7,"§b");
                break;
            case 80:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                colorMap.put(4,"§b");
                colorMap.put(5,"§b");
                colorMap.put(6,"§b");
                colorMap.put(7,"§b");
                colorMap.put(8,"§b");
                break;
            case 90:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                colorMap.put(4,"§b");
                colorMap.put(5,"§b");
                colorMap.put(6,"§b");
                colorMap.put(7,"§b");
                colorMap.put(8,"§b");
                colorMap.put(9,"§b");
                break;
            case 100:
                colorMap.put(1,"§b");
                colorMap.put(2,"§b");
                colorMap.put(3,"§b");
                colorMap.put(4,"§b");
                colorMap.put(5,"§b");
                colorMap.put(6,"§b");
                colorMap.put(7,"§b");
                colorMap.put(8,"§b");
                colorMap.put(9,"§b");
                colorMap.put(10,"§b");
                break;

        }
        return "§7[" + colorMap.get(1) + "▇" + colorMap.get(2) + "▇" + colorMap.get(3) + "▇" + colorMap.get(4) + "▇" + colorMap.get(5) + "▇" + colorMap.get(6) + "▇" + colorMap.get(7) + "▇" + colorMap.get(8) + "▇" + colorMap.get(9) + "▇" + colorMap.get(10) + "▇" + "§7]";
    }
    public static int finalPercent(int percent) {
        if (percent < 5) {
            return 0;
        }
        if (percent < 15) {
            return 10;
        }
        if (percent < 25) {
            return 20;
        }
        if (percent < 35) {
            return 30;
        }
        if (percent < 45) {
            return 40;
        }
        if (percent < 55) {
            return 50;
        }
        if (percent < 65) {
            return 60;
        }
        if (percent < 75) {
            return 70;
        }
        if (percent < 85) {
            return 80;
        }
        if (percent < 95) {
            return 90;
        }
        if (percent == 100) {
            return 100;
        }
        return percent;
    }
    public static boolean hasSelect(Player player) {
        Set<String> keys = FileConfigManager.votedata.getKeys(true);
        for (String key : keys) {
            String str = key.replace(".",",");
            String[] strings = str.split(",");
            if (strings.length == 2) {
                if (FileConfigManager.votedata.getString(player.getName() + ".select").equals("null")) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void setSeledItem(Inventory inv, Material material, String displayName, int tier, String... strings) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.asList(strings));
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        item.setItemMeta(itemMeta);
        inv.setItem(tier,item);
    }
    public static void setUnSelItem(Inventory inv, Material material, String displayName, int tier, String... strings) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.asList(strings));
        item.setItemMeta(itemMeta);
        inv.setItem(tier,item);
    }
    static class PlayerData {
        private final int points;
        PlayerData(int points) {
            this.points = points;
        }
    }
}

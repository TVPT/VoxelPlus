package com.thevoxelbox.VoxelPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author plusnine
 */
public class VoxelPlusCore extends JavaPlugin {

	private PlusListener plistener;
	public List<Player> sprintPlayers = new ArrayList<Player>();

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!");
	}

	@Override
	public void onEnable() {
		plistener = new PlusListener(this);
		Bukkit.getPluginManager().registerEvents(plistener, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
	}

	// test command, to be removed later
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		String commandName = command.getName().toLowerCase();
		if (commandName.equals("plusnine") && sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage("plusnine's first plugin works! ...kinda.");
			return true;
		}
		// get drunk command (self) by plusnine
		if (commandName.equals("getmedrunk") && sender instanceof Player) {
			Player drunkplayer = (Player) sender;
			PlusDrunk drunktank = new PlusDrunk(drunkplayer, 600, (byte) 9, (byte) 0, true);
			drunktank.settaskid(Bukkit.getScheduler().scheduleSyncRepeatingTask(this, drunktank, 5, 200));
			return true;
		}

		// get drunk command (other) by deamon5550
		if (commandName.equals("getotherdrunk") && sender instanceof Player) {
			if (args[0] != null) {
				Player player = (Player) sender;
				for (Player p : player.getServer().getOnlinePlayers()) {
					if (p.getName().equals(args[0].toString())) {
						PlusDrunk drunktank = new PlusDrunk(p, 600, (byte) 9, (byte) 0, true);
						drunktank.settaskid(Bukkit.getScheduler().scheduleSyncRepeatingTask(this, drunktank, 5, 200));
						player.sendMessage(ChatColor.GOLD + p.getName() + " sure did enjoy their VoxelLager");
						return true;
					}
				}
				player.sendMessage(ChatColor.GOLD + "Unknown player");

				return true;
			}
		}

		// jmck95's vhelm code
		if (commandName.equalsIgnoreCase("vhelm")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length >= 2) {
					player.sendMessage("Too many arguements!");
				} else {
					int vhelmid = Integer.parseInt(args[0]);
					ItemStack vhelmstack = new ItemStack(vhelmid);
					player.getInventory().setHelmet(vhelmstack);
					player.sendMessage("You have something on your head. LOL!");
					return true;
				}
				return true;
			}
			return true;
		}

		// Jmck95's vsit code
		if (commandName.equalsIgnoreCase("vsit")) {
			if (sender instanceof Player && sender.hasPermission("voxelplus.vsit")) {
				Player player = (Player) sender;
				Player sittingPlayer = null;

				if (args.length > 0) {
					sittingPlayer = Bukkit.getServer().getPlayer(args[0]);
				}

				if (args.length >= 2) {
					player.sendMessage("Too Many Arguements!");
				} else {
					if (sittingPlayer == null) {
						player.sendMessage(ChatColor.GOLD + "Player was not found!");
					} else if (sittingPlayer.getPassenger() == sittingPlayer) {
						sittingPlayer.eject();
						sittingPlayer.sendMessage(ChatColor.GOLD + "You are no longer sitting!");
					} else if (sittingPlayer.getPassenger() == null) {
						sittingPlayer.setPassenger(sittingPlayer);
						player.sendMessage(ChatColor.GOLD + args[0] + " is now sitting!");
						sittingPlayer.sendMessage(ChatColor.GOLD + "You are now sitting!");
						return true;
					}
					return true;
				}
				return true;
			}
			return true;
		}

		// deamon's coffee code ---Not working fully
		if (commandName.equals("getmecoffee") && sender instanceof Player) {
			if (args[0] != null) {
				if (Integer.parseInt(args[0]) <= 15 && Integer.parseInt(args[0]) >= 0) {
					Player drunkplayer = (Player) sender;
					// faster movement
					PlusDrunk drunktank = new PlusDrunk(drunkplayer, 600, (byte) 1, (byte) Integer.parseInt(args[0]), true);
					// faster digging
					PlusDrunk drunktank2 = new PlusDrunk(drunkplayer, 600, (byte) 3, (byte) Integer.parseInt(args[0]), false);
					drunktank.settaskid(Bukkit.getScheduler().scheduleSyncRepeatingTask(this, drunktank, 5, 200));
					drunktank2.settaskid(Bukkit.getScheduler().scheduleSyncRepeatingTask(this, drunktank2, 5, 200));
					return true;
				} else {
					Player player = (Player) sender;
					player.sendMessage(ChatColor.GOLD + "Please enter a strength (0-15");
					return true;
				}
			} else {
				Player player = (Player) sender;
				player.sendMessage(ChatColor.GOLD + "Please enter a strength (0-15");
				return true;
			}
		}
		return false;
	}

	public void sprint(Player p) {
		p.sendMessage(ChatColor.GOLD + "You feel energized from the coffee.");
		sprintPlayers.add(p);
		final Player p2 = p;
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				sprintPlayers.remove(p2);
				p2.sendMessage(ChatColor.GOLD + "Your caffeine boost is wearing off.");
			}
		}, 400L);
	}
}
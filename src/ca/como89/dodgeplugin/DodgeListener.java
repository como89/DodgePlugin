package ca.como89.dodgeplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class DodgeListener implements Listener {

	
	private DodgePlugin plugin;
	
	public DodgeListener(DodgePlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent e){
		Player player = e.getPlayer();
		if(!DodgeData.hasPlayerWait(player.getName()) && player.getFoodLevel() >= DodgePlugin.dodgeprice){
			if(e.isSneaking()){
				DodgeData.addPlayerSneak(player.getName());
				Bukkit.getScheduler().runTaskLater(plugin, new DodgeTask(player,-1), 20L * DodgePlugin.timeDodging);
			}
			else {
				DodgeData.removePlayerSneak(player.getName());
			}
		} else if(e.isSneaking()) {
			player.sendMessage(ChatColor.RED + "Vous ne pouvez pas esquiver.");
		}
	}
	
	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e){
		Entity entity = e.getEntity();
		if(entity.getType() == EntityType.PLAYER){
			Player player = (Player) entity;
			if(DodgeData.hasPlayerSneak(player.getName())){
				DodgeData.removePlayerSneak(player.getName());
				e.setDamage(DodgeData.getDamageReduce(e.getDamage()));
				if(DodgePlugin.activateSoundParticule){
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 0.6f, 0.4f);
				}
				if(DodgePlugin.showDodgeRemaining){
					player.setFoodLevel(player.getFoodLevel() - DodgePlugin.dodgeprice);
				}
					DodgeData.addPlayerWait(player.getName());
					DodgeData.putPlayerTask(player.getName(),Bukkit.getScheduler().runTaskTimer(plugin, new DodgeTask(player,DodgePlugin.timeInSecond), 1L, 20L).getTaskId());
			}
		}
	}
}

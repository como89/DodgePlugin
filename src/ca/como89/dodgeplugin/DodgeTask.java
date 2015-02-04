package ca.como89.dodgeplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DodgeTask implements Runnable{
	
	private Player player;
	private int time = -1;
	private int compteur = 0;
	
	DodgeTask(Player player,int time){
		this.player = player;
		this.time = time;
	}

	@Override
	public void run() {
		if(time == -1){
			if(DodgeData.hasPlayerSneak(player.getName())){
				DodgeData.removePlayerSneak(player.getName());
			}
		} else {
			if(time == 0 && compteur >= 5){
				player.sendMessage(ChatColor.GRAY + "Esquive prêt!");
				DodgeData.removePlayerWait(player.getName());
				int taskId = DodgeData.getTaskId(player.getName());
				DodgeData.removePlayerTask(player.getName());
				Bukkit.getScheduler().cancelTask(taskId);
			}
			else {
				if(compteur == 5){
					if(DodgePlugin.showTimeRemaining){
						player.sendMessage(ChatColor.GRAY + "Il reste " + ChatColor.GOLD + time + ChatColor.GRAY+ " secondes pour esquiver.");
					}
				compteur = 0;
				time = time - 5;
				} else {
					compteur ++;
				}
			}
		}
	}

}

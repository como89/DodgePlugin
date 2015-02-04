package ca.como89.dodgeplugin;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DodgePlugin extends JavaPlugin {

	static int dodgeprice = 3;
	static boolean showDodgeRemaining = true;
	static boolean showTimeRemaining = false;
	static int timeInSecond = 10;
	static int timeDodging = 2;
	static boolean activateSoundParticule = true;
	private static PluginDescriptionFile pdf;
	
	@Override
	public void onEnable(){
		DodgeData.eraseAll();
		loadConfig();
		pdf = this.getDescription();
		this.getServer().getPluginManager().registerEvents(new DodgeListener(this), this);
		this.getLogger().info(" " + pdf.getName() + " by " + pdf.getAuthors() + " Version : " + pdf.getVersion());
	}
	
	
	private void loadConfig(){
		File file = new File("plugins/DodgePlugin/config.yml");
		if(!file.exists()){
		this.getConfig().addDefault("Dodge.DodgePrice.showDodgeRemaining", true);
		this.getConfig().addDefault("Dodge.DodgePrice.price", 3);
		this.getConfig().addDefault("Dodge.DodgeTime.showTimeRemaining", false);
		this.getConfig().addDefault("Dodge.DodgeTime.timeInSecond", 10);
		this.getConfig().addDefault("Dodge.DodgeTime.timeDodging", 2);
		this.getConfig().addDefault("Dodge.DodgeParticule.activateSoundParticule", true);
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.getLogger().info("Default config create!");
		} else {
			showDodgeRemaining = this.getConfig().getBoolean("Dodge.DodgePrice.showDodgeRemaining");
			dodgeprice = this.getConfig().getInt("Dodge.DodgePrice.price");
			showTimeRemaining = this.getConfig().getBoolean("Dodge.DodgeTime.showTimeRemaining");
			timeInSecond = this.getConfig().getInt("Dodge.DodgeTime.timeInSecond");
			timeDodging = this.getConfig().getInt("Dodge.DodgeTime.timeDodging");
			activateSoundParticule = this.getConfig().getBoolean("Dodge.DodgeParticule.activateSoundParticule");
			this.getLogger().info("Config load!");
		}
	}
}

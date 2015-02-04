package ca.como89.dodgeplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DodgeData {

	private static ArrayList<String> listPlayerSneak = new ArrayList<String>();
	private static ArrayList<String> listWaitPlayer = new ArrayList<String>();
	private static HashMap<String,Integer> listPlayerTask = new HashMap<String,Integer>();
	private static Random random = new Random();
	
	
	public static boolean addPlayerSneak(String playerName){
		return listPlayerSneak.add(playerName);
	}
	
	public static boolean removePlayerSneak(String playerName){
		return listPlayerSneak.remove(playerName);
	}
	
	public static boolean hasPlayerSneak(String playerName){
		return listPlayerSneak.contains(playerName);
	}
	
	public static boolean addPlayerWait(String playerName){
		return listWaitPlayer.add(playerName);
	}
	
	public static boolean removePlayerWait(String playerName){
		return listWaitPlayer.remove(playerName);
	}
	
	public static boolean hasPlayerWait(String playerName){
		return listWaitPlayer.contains(playerName);
	}
	
	public static void putPlayerTask(String playerName,int taskID){
		listPlayerTask.put(playerName, taskID);
	}
	
	public static void removePlayerTask(String playerName){
		listPlayerTask.remove(playerName);
	}
	
	public static int getTaskId(String playerName){
		return listPlayerTask.get(playerName);
	}
	
	public static int getDamageReduce(double damage){
		return random.nextInt((int)damage);
	}
	
	static void eraseAll(){
		listPlayerSneak.clear();
	}
}

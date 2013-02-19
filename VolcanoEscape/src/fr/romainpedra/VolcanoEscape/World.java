package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;

public class World {

	public ArrayList<Rock> rocks; //liste des rochers present sur le terrain
	public Player player; //Objet Joueur
	public float speedScroll; //Vitesse de defilement du terrain
	
	private ArrayList<Wall> wallRight; //paroies de droite du terrain
	private ArrayList<Wall> wallLeft; //paroies de gauche du terrain
	
	private int height;
	private int width;
	
	
	public World(int speedScroll, Player player)
	{
		this.speedScroll =  speedScroll;
		this.rocks = new ArrayList<Rock>();
		this.player = player;
	}
	
	public void UpdateWorld()
	{
		
	}
}

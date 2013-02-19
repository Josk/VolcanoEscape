package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class World {

	public ArrayList<Rock> rocks; //liste des rochers present sur le terrain
	public Player player; //Objet Joueur
	public float speedScroll; //Vitesse de defilement du terrain
	
	private ArrayList<Wall> wallRight; //paroies de droite du terrain
	private ArrayList<Wall> wallLeft; //paroies de gauche du terrain
	
	private int height;
	private int width;
	
	
	public World(Stage stage, int speedScroll, Player player)
	{
		this.speedScroll =  speedScroll;
		this.rocks = new ArrayList<Rock>();
		this.player = player;
		
		int countWall = (int) (stage.getHeight() / Wall.HEIGHT);
		for(int i = 0; i < countWall; i++)
		{
			stage.addActor(new Wall(stage, 0, (int)(i*Wall.HEIGHT), 32f));
			stage.addActor(new Wall(stage, (int)(stage.getWidth()- Wall.WIDTH), (int)(i*Wall.HEIGHT), 32f));
		}	
	}
	
	public void Update()
	{
		
	}
}

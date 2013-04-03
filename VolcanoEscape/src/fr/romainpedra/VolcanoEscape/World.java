package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.Position;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class World {

	public ArrayList<Rock> rocks; //liste des rochers present sur le terrain
	public float speedScroll; //Vitesse de defilement du terrain
	private ArrayList<Wall> wallRight; //paroies de droite du terrain
	private ArrayList<Wall> wallLeft; //paroies de gauche du terrain
	private ArrayList<BackGround> backgrounds;
	private Stage scene;
	private Player player;
	private int countWall;
	private int countBackGround;
//	private int posY;
	private Lava lava;
	private LavaOverlay lavaOverlay;
	
	private float spawnRockRateStart=2f;//5
	private float spawnRockRate=2f; 
	private float timeNextSpawn=2f;
	
	private float changeSpawnRate=2f;//5
	private float spawnModif=0.1f;
	private float timeNextChangeSpawnRate=5f;
	
	// temps ecoule depuis que le dernier rock a pop
	float elapsedTime = 0.0f;
	
	public World(Stage stage, float speedScroll, Player player)
	{
		this.player = player;
		this.scene = stage;
		this.wallRight = new ArrayList<Wall>();
		this.wallLeft = new ArrayList<Wall>();
		this.backgrounds = new ArrayList<BackGround>();
		
		this.speedScroll =  speedScroll;
		this.rocks = new ArrayList<Rock>();
		
		this.lava = new Lava(stage, 0, 0,stage.getWidth() , 110);
		this.lava.toFront();
		
		this.lavaOverlay = new LavaOverlay(stage, 0, 0, (int)stage.getWidth(), (int)stage.getHeight());
		this.lavaOverlay.toFront();
		
		this.countBackGround = 8;
		for(int i = 0; i < this.countBackGround; i++)
		{
			BackGround bgTmp;
			if(i == 0)
			{
				bgTmp = new BackGround(stage, 0, (int) (i*BackGround.HEIGHT), MathUtils.random(1, 3));
			}
			//
			else
			{
				bgTmp = new BackGround(stage, 0,(int)(i*this.backgrounds.get(i -1).GetSizeY())  , MathUtils.random(1, 3));
			}
			
			stage.addActor(bgTmp);
			this.backgrounds.add(bgTmp);
			bgTmp.toBack();
		}
		
		this.countWall = (int) (stage.getHeight() / Wall.HEIGHT) + 3 ;
		for(int i = 0; i < countWall; i++)
		{
			Wall wallTmp = new Wall(stage, 0, (int)(i*Wall.HEIGHT), Assets.get().wallLeft);
			stage.addActor(wallTmp);
			this.wallLeft.add(wallTmp);
			
			wallTmp = new Wall(stage, (int)(stage.getWidth()- Wall.WIDTH), (int)(i*Wall.HEIGHT), Assets.get().wallRight);
			stage.addActor(wallTmp);
			this.wallRight.add(wallTmp);
		}
		stage.addActor(this.lavaOverlay);
		this.lavaOverlay.toFront();
		stage.addActor(this.lava);
		this.lava.toFront();
		//Assets.get().music1.play();
		//Assets.get().music1.setLooping(true);
		
		
	}
	
	public void UpdateWorld(float delta, Stage stage)
	{		
		//scroll sur le player
//		if(this.player.dirY  == 0)
//		{
		this.player.update(delta);
		this.player.setPosition(this.player.getX(), this.player.getY() - speedScroll* delta);
		this.player.hook.update(speedScroll, delta);
//		}
		
		
		for(int i  = 0; i<backgrounds.size(); i++)
		{
			BackGround bgTmp = backgrounds.get(i);
			
			
			//Defilement 
			bgTmp.setPosition(bgTmp.getX(), bgTmp.getY() - (speedScroll *delta)/5);
			
			//Si un mur sort de l'ecran
			if(bgTmp.getY()< -1 * bgTmp.GetSizeY())
			{
				bgTmp.remove();
				backgrounds.remove(i);
				i -= 1;
				if(i < 0)
				{
					i = 0;
				}
			}
			
			//si le nombre de mur est inferieur au nombre de mur a afficher
			if(backgrounds.size() < this.countBackGround)
			{
				int count = this.countBackGround - backgrounds.size();
				for(int y = 0; y<count; y++)
				{
					int y1 =  (int) backgrounds.get(backgrounds.size()-1).getY();
					y1 +=(int) backgrounds.get(backgrounds.size()-1).GetSizeY() * 2;
					
					BackGround bgTmp1 = new BackGround(stage, 0,y1,MathUtils.random(1, 3));
					
					stage.addActor(bgTmp1);
					bgTmp1.toBack();
					this.backgrounds.add(bgTmp1);
					//wallTmp1.setZIndex(5);
					//this.lavaOverlay.toFront();
					//this.lava.toFront();
					
				}
			}
		}
		
		
		//Deflilement du mur de Gauche
		for(int i  = 0; i<wallLeft.size(); i++)
		{
			Wall wallTmp = wallLeft.get(i);
			
			
			//Defilement 
			wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - speedScroll * delta);
			
			//Si un mur sort de l'ecran
			if(wallTmp.getY()< -1 * Wall.HEIGHT)
			{
				wallTmp.remove();
				wallLeft.remove(i);
				i -= 1;
				if(i < 0)
				{
					i = 0;
				}
			}
			
			//si le nombre de mur est inferieur au nombre de mur a afficher
			if(wallLeft.size() < this.countWall)
			{
				int count = this.countWall - wallLeft.size();
				for(int y = 0; y<count; y++)
				{
					int y1 =  (int) wallLeft.get(wallLeft.size()-1).getY();
					y1 += Wall.HEIGHT ;
					Wall wallTmp1 = new Wall(stage, 0,y1, Assets.get().wallLeft);
					//wallTmp1.toBack();
					stage.addActor(wallTmp1);
					this.wallLeft.add(wallTmp1);
					//wallTmp1.setZIndex(5);
					this.lavaOverlay.toFront();
					this.lava.toFront();
					
				}
			}
		}
		
		//Defilement du mur de droite
		for(int i  = 0; i<wallRight.size(); i++)
		{	
			Wall wallTmp = wallRight.get(i);
			
			//Defilement 
			wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - speedScroll* delta);
							
			//Si un mur sort de l'ecran
			if(wallTmp.getY()<  -1 * Wall.HEIGHT)
			{
				wallTmp.remove();
				wallRight.remove(i);
				i -= 1;
				if(i < 0)
				{
					i = 0;
				}
			}
			
			//si le nombre de mur est inferieur au nombre de mur a afficher
			if(wallRight.size() < this.countWall)
			{
				int count = this.countWall - wallRight.size();
				for(int y = 0; y<count; y++)
				{
					int y1 =  (int) wallRight.get(wallRight.size()-1).getY();
					y1 += Wall.HEIGHT ;
					Wall wallTmp1 = new Wall(stage, (int)(stage.getWidth()- Wall.WIDTH),y1, Assets.get().wallRight);
					//wallTmp1.toBack();
					stage.addActor(wallTmp1);
					this.wallRight.add(wallTmp1);
					//wallTmp1.setZIndex(5);
					this.lavaOverlay.toFront();
					this.lava.toFront();
					
				}
			}
		}
		
		for(int i=0; i<this.rocks.size();i++){
			Rock rock = this.rocks.get(i);
			rock.update(delta);
//			System.out.println(rock.getX()+" "+rock.getY()+" "+player.getX()+" "+player.getY());
//			System.out.println(Math.sqrt(Math.pow(rock.getX()-player.getX(),2)+Math.pow(rock.getY()-player.getY(),2))<100);
			if(rock.collide()){				
				this.rocks.remove(i);
				rock.remove();
				--i;
				gameOver();return;
			}
		}
		
		if (this.player.getY() <= this.lava.getY()+30 )
		{
			gameOver();
		}
		
		elapsedTime+=delta;
		if(elapsedTime>timeNextSpawn){
			spawnRock();
			timeNextSpawn=elapsedTime+spawnRockRate;
		}
		
		if(elapsedTime>timeNextChangeSpawnRate){
			
			spawnRockRate-=spawnModif;
//			System.out.println(spawnRockRate);
			timeNextChangeSpawnRate=elapsedTime+changeSpawnRate;
		}
	}	
	
	public void spawnRock() {
		
		//////////////////////////////////////////////////////////////
		//if(world.rocks.size()==0){
		// creer un nouvel alien
		Rock rock = new Rock(scene, 600f, 400f, 60, 60,this.player);
		rocks.add(rock);
		// l'ajouter a la scene (il est deja anime--cf. son constructeur)
		scene.addActor(rock);
		//}
	}
	
	void gameOver(){
		for(int i=0; i<this.rocks.size();i++){
			this.rocks.get(i).remove();
		}
		
		this.rocks.clear();
	
		spawnRockRate=spawnRockRateStart;
		this.player.init();
	}
	
}

package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.Position;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class World {

	public ArrayList<Rock> rocks; //liste des rochers present sur le terrain
	public float speedScroll; //Vitesse de defilement du terrain
	private ArrayList<Wall> wallRight; //paroies de droite du terrain
	private ArrayList<Wall> wallLeft; //paroies de gauche du terrain
	private ArrayList<BackGround> backgrounds;
	public ArrayList<Particle> particules;
	private Stage scene;
	private Player player;
	private int countWall;
	private int countBackGround;
//	private int posY;
	private Lava lava;
	private LavaOverlay lavaOverlay;
	
	private float spawnRockRateStart=2f;//5
	private float spawnRockRateMin=0.5f;
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
		this.particules = new ArrayList<Particle>();
		
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
				CreateBackgound(stage, 0, (int) (i*BackGround.HEIGHT));
			}
			else
			{
				CreateBackgound(stage, 0, (int)(i*this.backgrounds.get(i -1).GetSizeY()));
			}
		}
		
		this.countWall = (int) (stage.getHeight() / Wall.HEIGHT) + 3 ;
		for(int i = 0; i < countWall; i++)
		{
			CreateWallLeft(stage,0, (int)(i*Wall.HEIGHT));
			CreateWallRight(stage, (int)(stage.getWidth()- Wall.WIDTH), (int)(i*Wall.HEIGHT));
		}
		
		stage.addActor(this.lavaOverlay);
		this.lavaOverlay.toFront();
		stage.addActor(this.lava);
		this.lava.toFront();
		//Assets.get().music1.play();
		//Assets.get().music1.setLooping(true);
		
		
	}
	
	public void CreateBackgound(Stage stage, int x, int y)
	{
		BackGround bgTmp1 = new BackGround(stage, x,y,MathUtils.random(1, 3));				
		stage.addActor(bgTmp1);
		this.backgrounds.add(bgTmp1);	
		bgTmp1.toBack();
	}
	
	public void CreateWallLeft(Stage stage, int x, int y)
	{
		Wall wallTmp = new Wall(stage, x,y, Assets.get().wallLeft);
		stage.addActor(wallTmp);
		this.wallLeft.add(wallTmp);
	}
	
	public void CreateWallRight(Stage stage, int x, int y)
	{
		Wall wallTmp = new Wall(stage, x, y, Assets.get().wallRight);
		stage.addActor(wallTmp);
		this.wallRight.add(wallTmp);
	}
	
	public void UpdateWall(Stage stage, float delta)
	{
		for(int i  = 0; i<wallLeft.size(); i++)
		{
			Wall wallTmpLeft = wallLeft.get(i);
			Wall wallTmpRight = wallRight.get(i);	
			
			//Defilement 
			wallTmpLeft.setPosition(wallTmpLeft.getX(), wallTmpLeft.getY() - speedScroll * delta);
			wallTmpRight.setPosition(wallTmpRight.getX(), wallTmpRight.getY() - speedScroll* delta);
			
			//Si un mur sort de l'ecran
			if(wallTmpLeft.getY()< -1 * Wall.HEIGHT)
			{			
				wallTmpLeft.remove();
				wallLeft.remove(i);
				
				wallTmpRight.remove();
				wallRight.remove(i);
				
				i -= 1;
				if(i < 0)
				{
					i = 0;
				}
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
				
				CreateWallLeft(stage, 0, y1);	
				CreateWallRight(stage, (int)(stage.getWidth()- Wall.WIDTH), y1);
				
				this.lavaOverlay.toFront();
				this.lava.toFront();					
			}
		}
	}
	
	public void CheckRockCollision(float delta)
	{
		for(int i=0; i<this.rocks.size();i++){
			Rock rock = this.rocks.get(i);
			rock.update(delta);
			if(rock.collide()){				
				this.rocks.remove(i);
				rock.remove();
				--i;
				gameOver();return;
			}
		}
	}
	
	public void UpdateBackground(float delta, Stage stage)
	{
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
		}
		
		if(backgrounds.size() < this.countBackGround)
		{
			int count = this.countBackGround - backgrounds.size();
			for(int y = 0; y<count; y++)
			{
				int y1 =  (int) backgrounds.get(backgrounds.size()-1).getY();
				y1 +=(int) backgrounds.get(backgrounds.size()-1).GetSizeY() * 2;
				CreateBackgound(stage, 0, y1);				
			}
		}
	}
	
	public void CheckParticleState()
	{
		for(int i=0; i<this.particules.size(); i++)
		{
			ParticleEffect pTmp = this.particules.get(i).m_particle;
			if(pTmp.isComplete())
			{
				pTmp.dispose();
				this.particules.remove(i);
				i --;
				if(i < 0)
				{
					i = 0;
				}
			}
		}
	}
	
	
	public void CheckPositionRocks(Stage stage)
	{	
		for(int i=0; i<this.rocks.size(); i++)
		{	
			Rock rTmp = this.rocks.get(i);
			//System.out.println("X: "+ this.rocks.get(i).getX());
			if(rTmp.getY() <= this.lava.getY() + 60)
			{
				ParticleEffect particule = new ParticleEffect();
				particule.load(Gdx.files.internal("data/Particles/flame2.p"), 
			    Gdx.files.internal("data"));
				Particle pTmp = new Particle(stage, rTmp.getX()+rTmp.getWidth()/2, rTmp.getY(), particule, 1);
				stage.addActor(pTmp);
				this.lavaOverlay.toFront();
				this.lava.toFront();
				
				
				//System.out.println("X: "+ rTmp.getX());
				//System.out.println("Y: "+ rTmp.getY());
				//particule.
				/*particule.setPosition(rTmp.getX()+rTmp.getWidth()/2, rTmp.getY());
				particule.setDuration(1);
				particule.start();*/
				particules.add(pTmp);
				
				rTmp.Destroy();
				this.rocks.remove(i);
				i --;
				if(i < 0)
				{
					i = 0;
				}
			}
		}
	}
	
	public void UpdateWorld(float delta, Stage stage)
	{		
		this.player.update(delta);
		if(this.player.dirX ==0)
		{
			this.player.setPosition(this.player.getX(), this.player.getY() - speedScroll* delta);
		}
		this.player.hook.update(speedScroll, delta);
		
		UpdateBackground(delta, stage);	
		UpdateWall(stage, delta);	
		CheckPositionRocks(stage);
		CheckParticleState();
		CheckRockCollision(delta);
		
		if (this.player.getY() <= this.lava.getY()+30 )
		{
			gameOver();
		}
		
		elapsedTime+=delta;
		if(elapsedTime>timeNextSpawn){
			spawnRock();
			timeNextSpawn=elapsedTime+spawnRockRate;
		}
		
		if(spawnRockRate>spawnRockRateMin&&elapsedTime>timeNextChangeSpawnRate){
			
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
		Preferences prefs = Gdx.app.getPreferences( "VolcanoEscape" );
		
		if(prefs.getInteger("score")<(int)player.score ){
			prefs.putInteger( "score", (int)player.score );
			prefs.flush();
		}
		
		spawnRockRate=spawnRockRateStart;
		this.player.init();
	}
	
}

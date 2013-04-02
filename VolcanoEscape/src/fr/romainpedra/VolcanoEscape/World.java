package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;

import javax.swing.text.Position;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class World {

	public ArrayList<Rock> rocks; //liste des rochers present sur le terrain
	public float speedScroll; //Vitesse de defilement du terrain
	private ArrayList<Wall> wallRight; //paroies de droite du terrain
	private ArrayList<Wall> wallLeft; //paroies de gauche du terrain
	private Stage scene;
	private Player player;
	private int countWall;
	private int posY;
	private Lava lava;
	
	public World(Stage stage, float speedScroll, Player player)
	{
		this.player = player;
		this.scene = stage;
		this.wallRight = new ArrayList<Wall>();
		this.wallLeft = new ArrayList<Wall>();
		
		this.speedScroll =  speedScroll;
		this.rocks = new ArrayList<Rock>();
		
		this.lava = new Lava(stage, 0, 0,stage.getWidth() , 110);
		this.lava.toFront();
		
		
		this.countWall = (int) (stage.getHeight() / Wall.HEIGHT) + 3 ;
		for(int i = 0; i < countWall; i++)
		{
			Wall wallTmp = new Wall(stage, 0, (int)(i*Wall.HEIGHT));
			//wallTmp.toBack();
			stage.addActor(wallTmp);
			this.wallLeft.add(wallTmp);
			
			wallTmp = new Wall(stage, (int)(stage.getWidth()- Wall.WIDTH), (int)(i*Wall.HEIGHT));
			//wallTmp.toBack();
			stage.addActor(wallTmp);
			this.wallRight.add(wallTmp);
			//wallTmp.setZIndex(5);
		}
		stage.addActor(this.lava);
		this.lava.toFront();
		Assets.get().music1.play();
		Assets.get().music1.setLooping(true);
		
		
	}
	
	public void UpdateWorld(float Delta, Stage stage)
	{		
		//scroll sur le player
//		if(this.player.dirY  == 0)
//		{
			this.player.setPosition(this.player.getX(), this.player.getY() - speedScroll* Delta);
			this.player.hook.update(speedScroll, Delta);
//		}
		
		
		
		//Deflilement du mur de Gauche
		for(int i  = 0; i<wallLeft.size(); i++)
		{
			Wall wallTmp = wallLeft.get(i);
			
			
			//Defilement 
			wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - speedScroll * Delta);
			
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
					Wall wallTmp1 = new Wall(stage, 0,y1);
					//wallTmp1.toBack();
					stage.addActor(wallTmp1);
					this.wallLeft.add(wallTmp1);
					//wallTmp1.setZIndex(5);
					this.lava.toFront();
					
				}
			}
		}
		
		//Defilement du mur de droite
		for(int i  = 0; i<wallRight.size(); i++)
		{	
			Wall wallTmp = wallRight.get(i);
			
			//Defilement 
			wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - speedScroll* Delta);
							
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
					Wall wallTmp1 = new Wall(stage, (int)(stage.getWidth()- Wall.WIDTH),y1);
					//wallTmp1.toBack();
					stage.addActor(wallTmp1);
					this.wallRight.add(wallTmp1);
					//wallTmp1.setZIndex(5);
					//this.lava.to
					
				}
			}
		}
		
		for(int i=0; i<this.rocks.size();i++){
			Rock rock = this.rocks.get(i);
			rock.update(Delta);
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
		
	}	
	
	
	void gameOver(){
		for(int i=0; i<this.rocks.size();i++){
			this.rocks.get(i).remove();
		}
		
		this.rocks.clear();
	
		this.player.init();
	}
	
}

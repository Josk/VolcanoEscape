package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class World {

	public ArrayList<Rock> rocks; //liste des rochers present sur le terrain
	public float speedScroll; //Vitesse de defilement du terrain
	private ArrayList<Wall> wallRight; //paroies de droite du terrain
	private ArrayList<Wall> wallLeft; //paroies de gauche du terrain
	private Stage scene;
	private Player player;
	
	public World(Stage stage, float speedScroll, Player player)
	{
		this.player = player;
		this.scene = stage;
		this.wallRight = new ArrayList<Wall>();
		this.wallLeft = new ArrayList<Wall>();
		
		this.speedScroll =  speedScroll;
		this.rocks = new ArrayList<Rock>();
		
		int countWall = (int) (stage.getHeight() / Wall.HEIGHT) + 1 ;
		for(int i = 0; i < countWall; i++)
		{
			Wall wallTmp = new Wall(stage, 0, (int)(i*Wall.HEIGHT));
			stage.addActor(wallTmp);
			this.wallLeft.add(wallTmp); 
			
			wallTmp = new Wall(stage, (int)(stage.getWidth()- Wall.WIDTH), (int)(i*Wall.HEIGHT));
			stage.addActor(wallTmp);
			this.wallRight.add(wallTmp);
		}	
	}
	
	public void UpdateWorld(float Delta)
	{
		if(this.player.dirY > 0) 
		{
			for(int i  = 0; i<wallLeft.size(); i++)
			{
				Wall wallTmp = wallLeft.get(i);
				wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - (this.player.dirY * Delta));
				
				if(wallTmp.getY()< -200)
				{
					wallTmp.remove();
				}
				
				wallTmp = wallRight.get(i);
				wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - (this.player.dirY * Delta));
				
				if(wallTmp.getY()< -200)
				{
					wallTmp.remove();
				}
			}
		}
		
		for(int i=0; i<this.rocks.size();i++){
			Rock rock = this.rocks.get(i);
//			System.out.println(rock.getX()+" "+rock.getY()+" "+player.getX()+" "+player.getY());
//			System.out.println(Math.sqrt(Math.pow(rock.getX()-player.getX(),2)+Math.pow(rock.getY()-player.getY(),2))<100);
			if(rock.collide(this.player)){				
				this.rocks.remove(i);
				rock.remove();
				--i;
			}
		}
	}
	
	
}

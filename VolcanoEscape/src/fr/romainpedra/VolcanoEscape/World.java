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
	private int countWall;
	
	public World(Stage stage, float speedScroll, Player player)
	{
		this.player = player;
		this.scene = stage;
		this.wallRight = new ArrayList<Wall>();
		this.wallLeft = new ArrayList<Wall>();
		
		this.speedScroll =  speedScroll;
		this.rocks = new ArrayList<Rock>();
		
		this.countWall = (int) (stage.getHeight() / Wall.HEIGHT) + 3 ;
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
	
	public void UpdateWorld(float Delta, Stage stage)
	{
		if(this.player.dirY > 0) 
		{
			for(int i  = 0; i<wallLeft.size(); i++)
			{
				Wall wallTmp = wallLeft.get(i);
				wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - (this.player.dirY * Delta));
				
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
				
				if(wallLeft.size() < this.countWall)
				{
					int count = this.countWall - wallLeft.size();
					for(int y = 0; y<count; y++)
					{
						int y1 =  (int) wallLeft.get(wallLeft.size()-1).getY();
						y1 += Wall.HEIGHT ;
						Wall wallTmp1 = new Wall(stage, 0,y1);
						stage.addActor(wallTmp1);
						this.wallLeft.add(wallTmp1); 
					}
				}
			}
			
			for(int i  = 0; i<wallRight.size(); i++)
			{	
				Wall wallTmp = wallRight.get(i);
				wallTmp.setPosition(wallTmp.getX(), wallTmp.getY() - (this.player.dirY * Delta));
				
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
				
				int count = this.countWall - wallRight.size();
				for(int y = 0; y<count; y++)
				{
					int y1 =  (int) wallRight.get(wallRight.size()-1).getY();
					y1 += Wall.HEIGHT ;
					Wall wallTmp1 = new Wall(stage, (int)(stage.getWidth()- Wall.WIDTH),y1);
					stage.addActor(wallTmp1);
					this.wallRight.add(wallTmp1); 
				}
			}
		}
	}	
}

package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Wall extends Actor {
	
	public int x;
	public int y;
	public int sizeX;
	public int sizeY;
	
	public static final float HEIGHT = 200;
	public static final float WIDTH = 40;
		
	TextureRegion rgn = new TextureRegion();
	public Wall(Stage stage,int x, int y, Texture texture){
		//rgn = new TextureRegion(Assets.get().wall);
		rgn = new TextureRegion(texture);
		setSize(Wall.WIDTH,Wall.HEIGHT);
		setOrigin(0,0);
		setPosition(x,y);
		this.setZIndex(5);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		//this.setZIndex(5);
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());		
	}
}

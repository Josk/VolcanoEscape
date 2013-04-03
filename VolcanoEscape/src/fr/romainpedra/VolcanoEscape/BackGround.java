package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BackGround extends Actor {
	
	public int x;
	public int y;
	public int sizeX;
	public int sizeY;
	
	public static final float HEIGHT = 160;
	public static final float WIDTH = 800;
		
	TextureRegion rgn = new TextureRegion();
	public BackGround (Stage stage,int x, int y){
		//rgn = new TextureRegion(Assets.get().wall);
		rgn = new TextureRegion(Assets.get().background);
		setSize(BackGround.WIDTH,BackGround.HEIGHT);
		setOrigin(0,0);
		setPosition(x,y);
		this.toBack();
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		//this.setZIndex(5);
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());		
	}
}
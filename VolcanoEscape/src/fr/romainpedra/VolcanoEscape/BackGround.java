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
	public BackGround (Stage stage,int x, int y, int indexTexture){
		//rgn = new TextureRegion(Assets.get().wall);
		
		switch (indexTexture)
		{
			case 1:
				rgn = new TextureRegion(Assets.get().background1);
				sizeX = 576;
				sizeY = 448;
				setOrigin(576/2,448/2);
			break;
			case 2:
				rgn = new TextureRegion(Assets.get().background2);
				sizeX = 608;
				sizeY = 672;
				setOrigin(608/2, 672/2);
			break;
			case 3:
				rgn = new TextureRegion(Assets.get().background3);
				sizeX = 192;
				sizeY = 288;
				setOrigin(192/2,288/2);
			break;
		}
		setSize(sizeX,sizeY);
		
		setPosition(x,y);
		this.toBack();
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		//this.setZIndex(5);
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());		
	}
	
	public int GetSizeX()
	{
		return sizeX;
	}
	
	public int GetSizeY()
	{
		return sizeY;
	}
}
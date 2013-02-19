package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Lava extends Actor{
	TextureRegion rgn = new TextureRegion();
	
	float x;
	float y;
	float width;
	float height;
	
	
	public Lava(Stage stage,float x, float y, float width, float height){
		rgn = new TextureRegion(Assets.get().lave);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setSize(this.width,this.height);
		setOrigin(this.width/2,this.height/2);
		
		setPosition(this.x,this.y);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
	}
}

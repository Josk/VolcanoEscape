package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Lava extends Actor{
	TextureRegion rgnLeft = new TextureRegion();
	TextureRegion rgnRight = new TextureRegion();
	
	public Lava(Stage stage){
		rgnLeft = new TextureRegion(Assets.get().lave);
		rgnRight = new TextureRegion(Assets.get().lave);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.draw(rgnLeft,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
		batch.draw(rgnRight,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
	}
}

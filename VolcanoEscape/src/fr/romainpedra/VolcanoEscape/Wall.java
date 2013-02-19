package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;

public class Wall extends Actor {
	
	public int x;
	public int y;
	public int sizeX;
	public int sizeY;
	
	TextureRegion rgn = new TextureRegion();
	public Wall(Stage stage,int x, int y, float SpeedScroll){
		rgn = new TextureRegion(Assets.get().wall);
		setSize(64f,64f);
		setOrigin(32f,32f);
		setPosition(x,y);
		
		addAction(sequence(
				moveTo(x, stage.getHeight()+SpeedScroll, 3.0f)
				));
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());		
	}
}

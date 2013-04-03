package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;

public class LavaOverlay extends Actor {
	
	public int x;
	public int y;
	public int sizeX;
	public int sizeY;
	
	//public static final float HEIGHT = 200;
	//public static final float WIDTH = 40;
		
	TextureRegion rgn = new TextureRegion();
	public LavaOverlay(Stage stage,int x, int y, int sizeX, int sizeY){
		rgn = new TextureRegion(Assets.get().laveOverlaid);
		setSize(sizeX,sizeY);
		setOrigin(0,0);
		setPosition(x,y);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());		
	}
}

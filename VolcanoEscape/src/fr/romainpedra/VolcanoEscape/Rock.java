package fr.romainpedra.VolcanoEscape;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;

public class Rock extends Actor {

	TextureRegion rgn = new TextureRegion();
	Random random = new Random();
	public Rock(Stage stage){
		rgn = new TextureRegion(Assets.get().rocher);
		setSize(64f,64f);
		setOrigin(32f,32f);
		
		
		float x = (random.nextFloat() *stage.getWidth());
		setPosition(x,0f);
		
		addAction(sequence(
				moveTo(x, stage.getHeight()+32f, 3.0f)
				));
		addAction(forever(rotateBy(360, 2.0f)));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
		
	}
}

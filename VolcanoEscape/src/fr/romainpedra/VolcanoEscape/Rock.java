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
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

public class Rock extends Actor {

	TextureRegion rgn = new TextureRegion();
	Random random = new Random();
	float fallSpeed;
	float rotationSpeed;
	public Rock(Stage stage,float fallSpeed,float rotationSpeed){
		this.fallSpeed = fallSpeed;
		this.rotationSpeed = rotationSpeed;
		this.rgn = new TextureRegion(Assets.get().rocher);
		setSize(64f,64f);
		setOrigin(32f,32f);
		
		
		float x = (random.nextFloat() *stage.getWidth());
		setPosition(x,0f);
		
		addAction(sequence(
				moveTo(x, stage.getHeight()+32f, this.fallSpeed),
				run(new Runnable() {
					@Override
					public void run() {
						Rock.this.remove();
					}})
				));
		addAction(forever(rotateBy(360, this.rotationSpeed)));
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
	}
}

package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player extends Actor {

	
TextureRegion rgn = new TextureRegion();
	
	public Player(Stage stage) {
		rgn = new TextureRegion(Assets.get().perso);
		
		setSize(64.0f, 64.0f);
		setOrigin(32.0f, 32.0f);
		setPosition(32.0f, stage.getHeight()*0.5f-32.0f);
		
		
//		addListener(new ClickListener() {
//			
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				boolean rv = super.touchDown(event, x, y, pointer, button);
//				//Alien.this.remove();
//				
//				Vector2 coords = localToStageCoordinates(new Vector2(x, y));
//				GameScreen.get().alienDestroyed(coords.x , coords.y);
//				
//				return rv;
//			}
//		});
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(this.getColor());
		batch.draw(rgn, 
				getX(), getY(), 
				getOriginX(), getOriginY(), 
				getWidth(), getHeight(), 
				getScaleX(), getScaleY(), 
				getRotation());
	}
}

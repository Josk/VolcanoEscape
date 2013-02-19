package fr.romainpedra.VolcanoEscape;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Player extends Actor {

	TextureRegion rgn = new TextureRegion();

//	public float x = 50, y = 50;
	private float gravity = 0;
	public float gravityForce = 0.1f;
	public float gravityMax = 1;
	public float width, height;
	public float dirX, dirY;

	public Player(Stage stage) {
		rgn = new TextureRegion(Assets.get().perso);

		this.width = stage.getWidth();
		this.height = stage.getHeight();

		setSize(64.0f, 64.0f);
		setOrigin(32.0f, 32.0f);
		setPosition(32.0f, stage.getHeight() * 0.5f - 32.0f);

//		addListener(new ClickListener() {
//			
//			@Override
//			public boolean touchDown(InputEvent event, float x, float y,
//					int pointer, int button) {
//				boolean rv = super.touchDown(event, x, y, pointer, button);
//				Player.this.hook( x, y);
//				
//				return rv;
//			}
//		});

	}
	
	
	public void hook(float x,float y){
		
		//Vector2 coords = localToStageCoordinates(new Vector2(x, y));
		this.dirX=(x-Player.this.getX()+Player.this.getWidth()/2)/5;
		this.dirY=(y-Player.this.getY()+Player.this.getHeight()/2)/5;
	}
	
	public void update(float delta) {
		this.gravity += this.gravityForce;
		if (this.gravity > this.gravityMax)
			this.gravity = this.gravityMax;
		this.dirY += gravity;
		this.dirY *= 0.95f;
		if (!ScreenOut(this.getX(), this.getY() + this.dirY * delta)) {
			this.setY(this.getY() + this.dirY * delta);
		} else {
			// this.dirY *= -0.5*delta;
			this.gravity = 0;
			this.dirX = 0;
		}

		// this.dirX *= 0.9f*delta;

		if (!ScreenOut(this.getX() + this.dirX, this.getY())) {
			this.setX(this.getX() + this.dirX * delta);
		} else {
			this.dirX *= -1;
			// this.dirX=0;
		}

	}

	boolean ScreenOut(float newX, float newY) {
		if (newX > 0 && newX < this.width && newY > 0 && newY < this.height)
			return false;
		return true;

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(this.getColor());
		batch.draw(rgn, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
				getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}

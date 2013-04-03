package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;

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
	
	ArrayList<TextureRegion> overlayTextures;
	public int x;
	public int y;
	public int sizeX;
	public int sizeY;
	float speedSprite;
	private int indexSprite;
	private float timeTmp;
	private boolean invert;
	
	public LavaOverlay(Stage stage,int x, int y, int sizeX, int sizeY){
		setSize(sizeX,sizeY);
		setOrigin(0,0);
		setPosition(x,y);
		this.speedSprite =2f;
		this.timeTmp = 0;
		this.invert = false;	
		this.indexSprite = 0;
		
		overlayTextures = new ArrayList<TextureRegion>();
		
		overlayTextures.add(new TextureRegion(Assets.get().laveOverlaid1));
		overlayTextures.add(new TextureRegion(Assets.get().laveOverlaid2));
		overlayTextures.add(new TextureRegion(Assets.get().laveOverlaid3));
		overlayTextures.add(new TextureRegion(Assets.get().laveOverlaid4));
		overlayTextures.add(new TextureRegion(Assets.get().laveOverlaid5));
		overlayTextures.add(new TextureRegion(Assets.get().laveOverlaid6));
		this.toFront();	
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		this.timeTmp += 0.1f;
		if(this.timeTmp >= this.speedSprite)
		{
			this.timeTmp = 0;
			if(invert)
			{
				this.indexSprite --;
				if(indexSprite < 0)
				{
					this.indexSprite = 1;
					invert = false;
				}
			}
			else
			{
				this.indexSprite ++;
				if(indexSprite == this.overlayTextures.size())
				{
					this.indexSprite = this.overlayTextures.size() -2;
					invert = true;
				}
			}
			
		}
		//this.setZIndex(10);
		batch.draw(overlayTextures.get(this.indexSprite),getX() ,getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
	}
}

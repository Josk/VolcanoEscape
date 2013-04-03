package fr.romainpedra.VolcanoEscape;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.tablelayout.BaseTableLayout.Debug;

public class Lava extends Actor{
	ArrayList<TextureRegion> lavaTexture;
	float x;
	float y;
	float width;
	float height;
	float speedSprite;
	private int indexSprite;
	private float timeTmp;
	private boolean invert;
	
	
	public Lava(Stage stage,float x, float y, float width, float height){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speedSprite =0.8f;
		this.timeTmp = 0;
		setSize(this.width,this.height);
		setOrigin(this.width/2,this.height/2);
		setPosition(this.x,this.y);
		this.invert = false;
		
		this.indexSprite = 0;
		
		lavaTexture = new ArrayList<TextureRegion>();
		
		lavaTexture.add(new TextureRegion(Assets.get().lave1));
		lavaTexture.add(new TextureRegion(Assets.get().lave2));
		lavaTexture.add(new TextureRegion(Assets.get().lave3));
		lavaTexture.add(new TextureRegion(Assets.get().lave4));
		lavaTexture.add(new TextureRegion(Assets.get().lave5));
		lavaTexture.add(new TextureRegion(Assets.get().lave6));
		this.toFront();
		
	}
	
	@Override
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
				if(indexSprite == this.lavaTexture.size())
				{
					this.indexSprite = this.lavaTexture.size() -2;
					invert = true;
				}
			}
			
		}
		//this.setZIndex(10);
		batch.draw(lavaTexture.get(this.indexSprite),getX() ,getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
	}
}

package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Lava extends Actor{
	TextureRegion rgn = new TextureRegion();
	TextureRegion rgn2 = new TextureRegion();
	TextureRegion rgn3 = new TextureRegion();
	float x;
	float y;
	float width;
	float height;
	private float drawX;
	private float drawY;
	private float speedX;
	private float speedY;
	private float rgn1X;
	private float rgn2X;
	private float rgn1Y;
	private float rgn2Y;
	private boolean invertX;
	private boolean invertY;
	
	public Lava(Stage stage,float x, float y, float width, float height){
		rgn = new TextureRegion(Assets.get().lave);
		rgn2 = new TextureRegion(Assets.get().lave);
		rgn3 = new TextureRegion(Assets.get().lave);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setSize(this.width,this.height);
		setOrigin(this.width/2,this.height/2);
		this.drawX = 30f;
		this.drawY = 50f;
		this.rgn1X = 0;
		this.rgn2X = this.drawX;
		this.rgn1Y = 0;
		this.rgn2Y = this.drawY;
		this.invertX = false;
		this.invertY = false;
		this.speedX = 0.1f;
		this.speedY = 0.20f;
		
		setPosition(this.x,this.y);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		
		if(this.invertX)
		{
			this.rgn1X -= this.speedX;
			this.rgn2X += this.speedX;
			if(this.rgn1X <= 0)
			{
				this.invertX = false;
			}
		}
		else
		{
			this.rgn1X += this.speedX;
			this.rgn2X -= this.speedX;
			if(this.rgn1X >= this.drawX)
			{
				this.invertX = true;
			}
		}
		
		if(this.invertY)
		{
			this.rgn1Y -= this.speedY;
			this.rgn2Y += this.speedY;
			if(this.rgn1Y <= 0)
			{
				this.invertY = false;
			}
		}
		else
		{
			this.rgn1Y += this.speedY;
			this.rgn2Y -= this.speedY;
			if(this.rgn1Y >= this.drawY)
			{
				this.invertY = true;
			}
		}
		
		batch.draw(rgn2,getX() + this.rgn2X ,getY() + this.rgn2Y,getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
		batch.draw(rgn3,getX() + this.rgn1X ,getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
		batch.draw(rgn,getX() ,getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
	}
}

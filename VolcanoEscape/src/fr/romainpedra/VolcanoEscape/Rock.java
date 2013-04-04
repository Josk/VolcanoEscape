package fr.romainpedra.VolcanoEscape;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Rock extends Actor {

	TextureRegion rgn = new TextureRegion();
	TextureRegion rgnDebug = new TextureRegion();
	Random random = new Random();
	float fallSpeed;
	float rotationSpeed;
	float width;
	float height;
	Player player;
	
	public Rock(Stage stage,float fallSpeed,float rotationSpeed, float width, float height, Player player){
		this.fallSpeed = fallSpeed;
		this.rotationSpeed = rotationSpeed;
		this.width = width;
		this.height = height;
		this.rgn = new TextureRegion(Assets.get().rock);
		rgnDebug = new TextureRegion(Assets.get().wall);
		setSize(this.width,this.height);
		setOrigin(this.width/2,this.height/2);
		
		this.player = player;
		
		float x = (100+random.nextFloat() *(stage.getWidth()-width-100));
		
		////////////////////////////
//		setPosition(stage.getWidth()/2,this.height);
//		/*
		setPosition(x,stage.getHeight());
//		*/
	}
	
	boolean collide() {
		if (this.player.getHitBoxX()+getWidth()/2-width/2+this.player.width> this.getX() && this.player.getHitBoxX() < this.getX()+this.width
				&& this.player.getHitBoxY()+this.player.height> this.getY() && this.player.getHitBoxY() < this.getY()+this.height
				)
			return true;
		return false;

	}
	
	public void update(float delta){
		
		this.setY(this.getY() - this.fallSpeed * delta);		
		this.setRotation(this.getRotation() + this.rotationSpeed * delta);
	}
	
	public void Destroy(){
		this.remove();
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha){
		//this.setZIndex(5);
		batch.draw(rgn,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
//		batch.draw(rgnDebug, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
//				getHeight(), width/getWidth(),height/getHeight()/*getScaleX(), getScaleY()*/, 0);
	}
}

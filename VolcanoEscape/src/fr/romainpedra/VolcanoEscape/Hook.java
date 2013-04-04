package fr.romainpedra.VolcanoEscape;

import sun.org.mozilla.javascript.internal.ast.WithStatement;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Hook extends Actor {
	TextureRegion hookL = new TextureRegion();
	TextureRegion hookR = new TextureRegion();
	TextureRegion hook;
	
	TextureRegion chain = new TextureRegion();
	TextureRegion rgnDebug = new TextureRegion();
	
	public float width=120, height=120;
	public float widthChain=60, heightChain=60;
	
	public int chainSpace=10;
	
	public Player player;
	
	public Hook(Stage stage,Player player) {
		
		rgnDebug = new TextureRegion(Assets.get().lave1);
		
		hookL = new TextureRegion(Assets.get().hookL);
		hookR = new TextureRegion(Assets.get().hookR);
		hook=hookL;
		chain = new TextureRegion(Assets.get().chain);
		setSize(width, height);
//		setOrigin(width/2, height/2);
		stage.addActor(this);
		
		this.player=player;
	}
	
	public void update(float speedScroll, float delta){
		setPosition(this.getX(), this.getY() - speedScroll* delta);
//		this.setRotation(this.getRotation() + 10 * delta);
	}
	
	public void Hooked(float x, float y, int wall){
		setPosition(x, y);
		if(wall==1){
			hook=hookL;
		}else{
			hook=hookR;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
////////////////////////
//this.toFront();
		batch.draw(hook, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
				getHeight(), getScaleX(), getScaleY(), getRotation());
		
//		batch.draw(rgnDebug,getX()+getOriginX(), getY()+getOriginY(), getOriginX(), getOriginY(), getWidth(),
//				getHeight(), getScaleX()/10, getScaleY()/10, 0);
		


		float xP=player.getX()+player.widthAsset/2;
		float yP=player.getY()+player.heightAsset/2;
		float xH=getX()+width/2;
		float yH=getY()+height/2;
		
		float a=(yH-yP)/(xH-xP);
		float b=yP-a*xP;
		
		float startPosX, endPosX;
		
		if(xP-xH<0){
			startPosX=xP;
			endPosX=xH;
		}else{
			startPosX=xH;
			endPosX=xP;
			
		}
		
		int nbChain=getNbChain(xP,yP,xH,yH);
//		System.out.println(nbChain);
		for(int i=0;i<nbChain;++i){
			float x=lerp(((float)i/(float)nbChain),startPosX,endPosX);
//			System.out.println(i/nbChain+" "+startPos+" "+EndPos+" "+x);
			batch.draw(chain, x-widthChain/2, a*x+b-heightChain/2, getOriginX(), getOriginY(), widthChain,
					heightChain, 1, 1, getRotation());
			
		}
	}
	
	int getNbChain(float xP, float yP,float xH, float yH){
//		System.out.println((int)Math.sqrt(Math.pow((double)(xP-xH),2)+Math.pow((double)(yP-yH),2)));
		return (int)Math.sqrt(Math.pow((double)(xP-xH),2)+Math.pow((double)(yP-yH),2))/chainSpace;
	}
	
	public float lerp(float t,float start,float end){
		return (end-start)*t+start;
	}
	
}

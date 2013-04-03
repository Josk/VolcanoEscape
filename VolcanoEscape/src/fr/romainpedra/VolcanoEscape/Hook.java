package fr.romainpedra.VolcanoEscape;

import sun.org.mozilla.javascript.internal.ast.WithStatement;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Hook extends Actor {
	TextureRegion hook = new TextureRegion();
	TextureRegion chain = new TextureRegion();
	public float width=120, height=120;
	public float widthChain=60, heightChain=60;
	
	public int chainSpace=10;
	
	public Player player;
	
	public Hook(Stage stage,Player player) {
		
		
		hook = new TextureRegion(Assets.get().hook);
		chain = new TextureRegion(Assets.get().chain);
		setSize(width, height);
		setOrigin(width/2, height/2);
		stage.addActor(this);
		
		this.player=player;
	}
	
	public void update(float speedScroll, float Delta){
		setPosition(this.getX(), this.getY() - speedScroll* Delta);
	}
	
	public void Hooked(float x, float y, float degrees){
		setPosition(x, y);
		//setRotation(degrees);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
////////////////////////
this.toFront();
		batch.draw(hook, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
				getHeight(), getScaleX(), getScaleY(), getRotation());
		
//		float xP=100;//player.getX();
//		float yP=100;//player.getY();
//		float xH=400;//getX();
//		float yH=400;//getY();

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

package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Hook extends Actor {
	TextureRegion rgn = new TextureRegion();
	public float width=60, height=60;
	public float widthChain=30, heightChain=30;
	
	public int chainSpace=20;
	
	public Player player;
	
	public Hook(Stage stage,Player player) {
		
		
		rgn = new TextureRegion(Assets.get().rock);
		
		setSize(width, height);
		stage.addActor(this);
		
		this.player=player;
	}
	
	public void update(float speedScroll, float Delta){
		setPosition(this.getX(), this.getY() - speedScroll* Delta);
	}
	
	public void Hooked(float x, float y){
		setPosition(x, y);
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
////////////////////////
//this.toFront();
		batch.draw(rgn, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
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
			batch.draw(rgn, x-widthChain/2, a*x+b-heightChain/2, getOriginX(), getOriginY(), widthChain,
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

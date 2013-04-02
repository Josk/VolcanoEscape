package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class Hook extends Actor {
	TextureRegion rgn = new TextureRegion();
	public float width=60, height=60;
	
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
		batch.draw(rgn, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
				getHeight(), getScaleX(), getScaleY(), getRotation());
		
//		float xP=100;//player.getX();
//		float yP=100;//player.getY();
//		float xH=400;//getX();
//		float yH=400;//getY();

		float xP=player.getX()+player.widthAsset/2;
		float yP=player.getY()+player.heightAsset/2;
		float xH=getX();
		float yH=getY();
		
		float a=(yH-yP)/(xH-xP);
		float b=yP-a*xP;
		
		float startPos, endPos;
		
		if(xP-xH<0){
			startPos=xP;
			endPos=xH;
		}else{
			startPos=xH;
			endPos=xP;
			
		}
		
		int nbChain=getNbChain(startPos, endPos);
		System.out.println(nbChain);
		for(int i=0;i<nbChain;++i){
			float x=lerp(((float)i/(float)nbChain),startPos,endPos);
//			System.out.println(i/nbChain+" "+startPos+" "+EndPos+" "+x);
			batch.draw(rgn, x, a*x+b, getOriginX(), getOriginY(), getWidth(),
					getHeight(), 0.5f, 0.5f, getRotation());
			
		}
	}
	
	int getNbChain(float startPos, float endPos){
		return (int)Math.sqrt(Math.pow((double)(startPos-endPos),2))/20;
	}
	
	public float lerp(float t,float start,float end){
		return (end-start)*t+start;
	}
	
}

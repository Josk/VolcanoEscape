package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player extends Actor {

	TextureRegion rgn = new TextureRegion();
	
	TextureRegion rgnDebug = new TextureRegion();
//	public float x = 50, y = 50;
	private float gravity = 0;
	public float gravityForce = 50f;
	public float gravityMax = 500;
	public float widthStage, heightStage;
	public float widthAsset=128, heightAsset=128;
	public float width=60, height=60;
	public float dirX, dirY;

	TextureRegion rgnHook = new TextureRegion();
	public float hookPosX, hookPosY;
	
	public int hookedWall=1;
	
	public float wallSize=50;
	
	public int score = 0;
	
	public Player(Stage stage) {
		rgn = new TextureRegion(Assets.get().perso);
		rgnDebug = new TextureRegion(Assets.get().wall);
		rgnHook= new TextureRegion(Assets.get().rock);
		this.widthStage = stage.getWidth();
		this.heightStage = stage.getHeight();

		init();


	}
	
	public void init(){
		setSize(widthAsset, heightAsset);
//		setOrigin(width/2, height/2);
		setPosition(wallSize-width/2, heightStage/2);
		 this.dirX =0;
		 this.dirY =0;
		 this.score=0;
		 this.hookedWall=1;
	}
	
	public void hook(float x,float y){
		if(x<this.wallSize||x>this.widthStage-this.wallSize){
			
//			System.out.println(this.hookedWall);
			switch (this.hookedWall) {
			case 1:
				if(x<this.widthStage/2){
//					System.out.println("L No");
					return;
				}else{
					this.hookedWall=2;
//					System.out.println("L ok");
				}
				break;
				
			case 2:
				if(x>this.widthStage/2){
//					System.out.println("R no");
					return;
				}else{
					this.hookedWall=1;
//					System.out.println("R ok");
				}
				break;
				
			default:
//				System.out.println("WTF?!");
				break;
			}
						
			//Vector2 coords = localToStageCoordinates(new Vector2(x, y));
			this.dirX=(x-(Player.this.getX()+Player.this.getWidth()/2))*2;
			this.dirY=(y-(Player.this.getY()+Player.this.getHeight()/2))*2;
			hookPosX=x;
			hookPosY=y;
		}
	}
	
	public void update(float delta) {
		

		if (!ScreenOut(this.getX(), this.getY() + this.dirY  *delta)) {
			this.setY(this.getY() + this.dirY  * delta);
		}

/*		
		this.gravity += this.gravityForce;
		if (this.gravity > this.gravityMax)
			this.gravity = this.gravityMax;
		this.dirY -= gravity *delta;
	*/	


		if (!ScreenOut(this.getX() + this.dirX*delta, this.getY())) {
			this.setX(this.getX() + this.dirX  * delta);
		}
		else {
			this.dirX = 0;
			this.dirY = 0;
//			this.dirX *= -1;
			// this.dirX=0;
		}
		
		if(this.getY()>this.score)
			this.score=(int)this.getY();
	}

	boolean ScreenOut(float newX, float newY) {
		if (newX > 0 && newX+getWidth() < this.widthStage && newY > 0 && newY+getHeight() < this.heightStage)
			return false;
		return true;

	}

	public float getHitBoxX(){
		return getX()+getWidth()/2-width/2;
	}
	
	public float getHitBoxY(){
		return getY()+getHeight()/2-height/2;
	}
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
//		batch.setColor(this.getColor());
		batch.draw(rgn, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
				getHeight(), getScaleX(), getScaleY(), getRotation());
//		batch.draw(rgnDebug, getHitBoxX(), getHitBoxY(), getOriginX(), getOriginY(), getWidth(),
//				getHeight(), width/getWidth(),height/getHeight()/*getScaleX(), getScaleY()*/, getRotation());
		batch.draw(rgnHook, hookPosX, hookPosY, getOriginX(), getOriginY(), getWidth(),
				getHeight(), width/getWidth(),height/getHeight()/*getScaleX(), getScaleY()*/, getRotation());
		
	}
}

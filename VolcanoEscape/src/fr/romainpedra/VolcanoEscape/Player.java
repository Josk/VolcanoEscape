package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player extends Actor {

	TextureRegion persoJumpL = new TextureRegion();
	TextureRegion persoJumpR = new TextureRegion();
	
	TextureRegion persoWaitL = new TextureRegion();
	TextureRegion persoWaitR = new TextureRegion();
	
	TextureRegion rgnDebug = new TextureRegion();
//	public float x = 50, y = 50;
//	private float gravity = 0;
//	public float gravityForce = 50f;
//	public float gravityMax = 500;
	
	public float hookSpeed=4f;
	public float widthStage, heightStage;
	public float widthAsset=150, heightAsset=150;//128
	public float width=60, height=60;
	public float dirX, dirY;
	
	public int hookedWall=1;
	
	public float wallSize=50;
	
	public float score = 0;
	
	public Hook hook;
	
	public Player(Stage stage) {
		
		
		
		persoJumpL = new TextureRegion(Assets.get().persoJumpL);
		
		persoWaitL = new TextureRegion(Assets.get().persoWaitL);
		
		persoJumpR = new TextureRegion(Assets.get().persoJumpR);
		
		persoWaitR = new TextureRegion(Assets.get().persoWaitR);
		
		rgnDebug = new TextureRegion(Assets.get().wall);
		
		this.widthStage = stage.getWidth();
		this.heightStage = stage.getHeight();
		
		hook=new Hook(stage, this);
		
		init();


	}
	
	public void init(){
		setSize(widthAsset, heightAsset);
//		setOrigin(width/2, height/2);
		setPosition(wallSize-widthAsset/2, heightStage/2);
		
		hook.Hooked(wallSize-width/2-10, heightStage/2+50);
		
		 this.dirX =0;
		 this.dirY =0;
		 this.score=0;
		 this.hookedWall=1;
	}
	
	public void hook(float x,float y){
		if(x<this.wallSize*4||x>this.widthStage-this.wallSize*4){
			
//			System.out.println(this.hookedWall);
			switch (this.hookedWall) {
			case 1:
				if(x<this.widthStage/2){
//					System.out.println("L No");
					return;
				}else{
					this.hookedWall=2;
					x=this.widthStage-hook.width/2-wallSize;
//					System.out.println("L ok");
				}
				break;
				
			case 2:
				if(x>this.widthStage/2){
//					System.out.println("R no");
					return;
				}else{
					this.hookedWall=1;
					x=wallSize-hook.width/2;
//					System.out.println("R ok");
				}
				break;
				
			default:
//				System.out.println("WTF?!");
				break;
			}
						
			//Vector2 coords = localToStageCoordinates(new Vector2(x, y));
			this.dirX=(x-(Player.this.getX()+Player.this.getWidth()/2))*hookSpeed;
			this.dirY=(y-(Player.this.getY()+Player.this.getHeight()/2))*hookSpeed;
//			hookPosX=x;
//			hookPosY=y;
			hook.Hooked(x, y);
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
			if(this.hookedWall==1){
				this.setX(wallSize-widthAsset/2);
			}else{
				this.setX(this.widthStage-this.widthAsset/2-wallSize);
			}
			
			
//			this.dirX *= -1;
			// this.dirX=0;
		}
		
//		if(this.getY()>this.score)
//			this.score=(int)this.getY();
		this.score+=delta;
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
////////////////////////
//this.toFront();
//		batch.setColor(this.getColor());
		
		if(dirX==0&&dirY==0){
			if(hookedWall==1){
				batch.draw(persoWaitL, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
						getHeight(), getScaleX(), getScaleY(), getRotation());
			}else{
				batch.draw(persoWaitR, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
						getHeight(), getScaleX(), getScaleY(), getRotation());
			}
			
		}else{
			if(hookedWall==1){
				batch.draw(persoJumpL, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
						getHeight(), getScaleX(), getScaleY(), getRotation());
			}else{
				batch.draw(persoJumpR, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
						getHeight(), getScaleX(), getScaleY(), getRotation());
			}
		}
		
//		batch.draw(rgnDebug, getHitBoxX(), getHitBoxY(), getOriginX(), getOriginY(), getWidth(),
//				getHeight(), width/getWidth(),height/getHeight()/*getScaleX(), getScaleY()*/, getRotation());
		
	}
}

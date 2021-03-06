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
	
	public boolean started=false;
	
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
		started=false;
		setSize(widthAsset, heightAsset);
//		setOrigin(width/2, height/2);
		setPosition(wallSize-widthAsset/2, heightStage/2);
		
		hook.Hooked(-wallSize/2, heightStage/2+50,1);
		
		 this.dirX =0;
		 this.dirY =0;
		 this.score=0;
		 this.hookedWall=1;
		 Assets.get().music1.play();
		 Assets.get().music1.setLooping(true);
	}
	
	public void hook(float x,float y){
		started=true;
		if(x<this.wallSize*4||x>this.widthStage-this.wallSize*4){
//			System.out.println(this.hookedWall);
			switch (this.hookedWall) {
			case 1://R
				if(x<this.widthStage/2){
					return;
				}else{
					this.hookedWall=2;
					x=this.widthStage-wallSize*12/8;
//					x=this.widthStage/2;
				}
				break;
				
			case 2://L
				if(x>this.widthStage/2){
					return;
				}else{
					this.hookedWall=1;
					x=-wallSize/2;
				}
				break;
				
			default:
//				System.out.println("WTF?!");
				break;
			}
			Assets.get().jump.play(0.5f);	
			//Vector2 coords = localToStageCoordinates(new Vector2(x, y));
			this.dirX=(x-(Player.this.getX()+Player.this.getWidth()/2))*hookSpeed;
			this.dirY=(y-(Player.this.getY()+Player.this.getHeight()/2))*hookSpeed;
//			hookPosX=x;
//			hookPosY=y;
			hook.Hooked(x, y,this.hookedWall);
		}
	}
	
	public void update(float delta) {
		

		if (!ScreenOut(this.getX(), this.getY() + this.dirY  *delta)) {
			this.setY(this.getY() + this.dirY  * delta);
		}



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
			
		}

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

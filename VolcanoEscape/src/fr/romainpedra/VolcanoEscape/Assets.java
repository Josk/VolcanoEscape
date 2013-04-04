package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.romainpedra.VolcanoEscape.Assets;

public class Assets {
	
	public Texture persoJumpL;
	public Texture persoWaitL;
	
	public Texture hookL;
	public Texture hookR;
	public Texture chain;

	public Texture persoJumpR;
	public Texture persoWaitR;
	
	public Texture wall, wallLeft, wallRight;
	public Texture rock;
	public Texture lave1, lave2, lave3, lave4, lave5, lave6;
	public Texture laveOverlaid1, laveOverlaid2, laveOverlaid3, laveOverlaid4, laveOverlaid5, laveOverlaid6;
	public Texture background1, background2, background3;
	public Music music1;
	public FileHandle font;
	public FileHandle fontImg;
	public Sound death;
	public Sound jump;
	public Sound lavanoise;
	
	public void load(){
		persoJumpL = new Texture(Gdx.files.internal("data/heroJumpL.png"));
		persoJumpL.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		persoWaitL = new Texture(Gdx.files.internal("data/heroWaitL.png"));
		persoWaitL.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		persoJumpR = new Texture(Gdx.files.internal("data/heroJumpR.png"));
		persoJumpR.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		persoWaitR = new Texture(Gdx.files.internal("data/heroWaitR.png"));
		persoWaitR.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		hookL = new Texture(Gdx.files.internal("data/hookL.png"));
		hookL.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		hookR = new Texture(Gdx.files.internal("data/hookR.png"));
		hookR.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		chain = new Texture(Gdx.files.internal("data/chain.png"));
		chain.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		lave1 = new Texture(Gdx.files.internal("data/lava1.png"));
		lave1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		lave2 = new Texture(Gdx.files.internal("data/lava2.png"));
		lave2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		lave3 = new Texture(Gdx.files.internal("data/lava3.png"));
		lave3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		lave4 = new Texture(Gdx.files.internal("data/lava4.png"));
		lave4.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		lave5 = new Texture(Gdx.files.internal("data/lava5.png"));
		lave5.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		lave6 = new Texture(Gdx.files.internal("data/lava6.png"));
		lave6.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		wall = new Texture(Gdx.files.internal("data/wall.png"));
		wall.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		wallLeft = new Texture(Gdx.files.internal("data/LeftWall.png"));
		wallLeft.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		wallRight = new Texture(Gdx.files.internal("data/RightWall.png"));
		wallRight.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rock = new Texture(Gdx.files.internal("data/rock.png"));
		rock.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		music1 = Gdx.audio.newMusic(Gdx.files.internal("data/Sounds/music1.mp3"));
		
		death = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/death.wav"));
		
		jump = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/jump.wav"));
		
		lavanoise = Gdx.audio.newSound(Gdx.files.internal("data/Sounds/lavanoise.wav"));
		
		laveOverlaid1 = new Texture(Gdx.files.internal("data/overlay1.png"));
		laveOverlaid1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		laveOverlaid2 = new Texture(Gdx.files.internal("data/overlay2.png"));
		laveOverlaid2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		laveOverlaid3 = new Texture(Gdx.files.internal("data/overlay3.png"));
		laveOverlaid3.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		laveOverlaid4 = new Texture(Gdx.files.internal("data/overlay4.png"));
		laveOverlaid4.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		laveOverlaid5 = new Texture(Gdx.files.internal("data/overlay5.png"));
		laveOverlaid5.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		laveOverlaid6 = new Texture(Gdx.files.internal("data/overlay6.png"));
		laveOverlaid6.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		font = Gdx.files.internal("data/fonts/font.fnt");

		fontImg = Gdx.files.internal("data/fonts/font.png");
		
		background1 = new Texture(Gdx.files.internal("data/background1.png"));
		background1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		background2 = new Texture(Gdx.files.internal("data/background2.png"));
		background2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		background3 = new Texture(Gdx.files.internal("data/background3.png"));
		background3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	}
	
	public void dispose(){
		
		if(persoJumpL != null){
			persoJumpL.dispose();
			persoJumpL = null;
		}
		if(wallLeft != null){
			wallLeft.dispose();
			wallLeft = null;
		}
		if(wallRight != null){
			wallRight.dispose();
			wallRight = null;
		}
		if(rock != null){
			rock.dispose();
			rock = null;
		}
		if(lave1 != null){
			lave1.dispose();
			lave1 = null;
		}
		if(lave2 != null){
			lave2.dispose();
			lave2 = null;
		}
		if(lave3 != null){
			lave3.dispose();
			lave3 = null;
		}
		if(lave4 != null){
			lave4.dispose();
			lave4 = null;
		}
		if(lave5 != null){
			lave5.dispose();
			lave5 = null;
		}
		if(lave6 != null){
			lave6.dispose();
			lave6 = null;
		}
		if(laveOverlaid1 != null){
			laveOverlaid1.dispose();
			laveOverlaid1 = null;
		}
		if(laveOverlaid2 != null){
			laveOverlaid2.dispose();
			laveOverlaid2 = null;
		}
		if(laveOverlaid3 != null){
			laveOverlaid3.dispose();
			laveOverlaid3 = null;
		}
		if(laveOverlaid4 != null){
			laveOverlaid4.dispose();
			laveOverlaid4 = null;
		}
		if(laveOverlaid5 != null){
			laveOverlaid5.dispose();
			laveOverlaid5 = null;
		}
		if(laveOverlaid6 != null){
			laveOverlaid6.dispose();
			laveOverlaid6 = null;
		}
		if(background1 != null){
			background1.dispose();
			background1 = null;
		}	
		if(background2 != null){
			background2.dispose();
			background2 = null;
		}		
		if(background3 != null){
			background3.dispose();
			background3 = null;
		}		
		if(music1 != null){
			music1.dispose();
			music1 = null;
		}
		if(death != null){
			death.dispose();
			death = null;
		}
		if(jump != null){
			jump.dispose();
			jump = null;
		}
		if(lavanoise != null){
			lavanoise.dispose();
			lavanoise = null;
		}
		if(font != null) {
			font = null;
		}
		if(fontImg != null){
			fontImg = null;
		}
	}
	
	private static Assets sInstance;
	
	//Asset.get().perso
	public static Assets get(){
		if(sInstance == null){
			sInstance = new Assets();
		}
		return sInstance;
	}

}

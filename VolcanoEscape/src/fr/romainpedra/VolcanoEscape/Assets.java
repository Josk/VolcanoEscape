package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import fr.romainpedra.VolcanoEscape.Assets;

public class Assets {
	
	public Texture perso;
	public Texture wall;
	public Texture rock;
	public Texture lave;
	public Texture background;
	public Music music1;
	
	public void load(){
		perso = new Texture(Gdx.files.internal("data/enemi.png"));
		perso.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		lave = new Texture(Gdx.files.internal("data/lava.png"));
		lave.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		wall = new Texture(Gdx.files.internal("data/wall.png"));
		wall.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		rock = new Texture(Gdx.files.internal("data/rock.png"));
		rock.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		music1 = Gdx.audio.newMusic(Gdx.files.internal("data/Sounds/music1.mp3"));
		
		
		
	}
	
	public void dispose(){
		
		if(perso != null){
			perso.dispose();
			perso = null;
		}
		if(wall != null){
			wall.dispose();
			wall = null;
		}
		if(rock != null){
			rock.dispose();
			rock = null;
		}
		if(lave != null){
			lave.dispose();
			lave = null;
		}
		if(background != null){
			background.dispose();
			background = null;
		}
		if(music1 != null){
			music1.dispose();
			music1 = null;
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

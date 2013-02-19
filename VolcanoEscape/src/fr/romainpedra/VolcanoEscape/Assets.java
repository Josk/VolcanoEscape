package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import fr.romainpedra.VolcanoEscape.Assets;

public class Assets {
	
	public Texture perso;
	public Texture wall;
	public Texture rocher;
	public Texture lave;
	public Texture background;
	
	
	public void load(){
		perso = new Texture(Gdx.files.internal("data/enemi.png"));
		perso.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		wall = new Texture(Gdx.files.internal("data/enemi.png"));
		wall.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
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
		if(rocher != null){
			rocher.dispose();
			rocher = null;
		}
		if(lave != null){
			lave.dispose();
			lave = null;
		}
		if(background != null){
			background.dispose();
			background = null;
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

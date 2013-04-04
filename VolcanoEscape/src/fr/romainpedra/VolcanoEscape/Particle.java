package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Particle extends Actor {
	
	public int x;
	public int y;
	public int sizeX;
	public int sizeY;
	public Stage stage;
	
	public ParticleEffect m_particle;
		
	TextureRegion rgn = new TextureRegion();
	public Particle(Stage stage,float x, float y, ParticleEffect particle, int duration){
		
		this.m_particle = particle;
		this.m_particle.setPosition(x, y);
		this.m_particle.setDuration(duration);
		this.m_particle.start();
		this.stage = stage;
		
		//setSize(Wall.WIDTH,Wall.HEIGHT);
		setOrigin(0,0);
		setPosition(x,y);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha){
		if(! this.m_particle.isComplete())
		{
			//batch.begin();
			
			this.m_particle.draw(batch, Gdx.graphics.getDeltaTime());
			//this.m_particle.draw(batch, parentAlpha);
			//batch.end();
		}
	}
}
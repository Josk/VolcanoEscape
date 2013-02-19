package fr.romainpedra.VolcanoEscape;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class VolcanoEscape extends Game{
	
	private static VolcanoEscape current;
	public static VolcanoEscape get() {
		return current;
	}
	
	@Override
	public void create() {
		current = this;
		setScreen(new GameScreen(Gdx.graphics.getWidth(), Gdx.graphics.getHeight() ));
	}

}

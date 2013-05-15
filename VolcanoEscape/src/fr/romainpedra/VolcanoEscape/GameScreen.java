package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

public class GameScreen implements Screen {
	//private Sprite marineSprite;
	private Stage scene;
	private Player player;
	private World world;
	
	// temps ecoule depuis que le dernier rock a pop
	float elapsedTime = 0.0f;
	
//	private int lives = 3;
	private BitmapFont font;
	private SpriteBatch fontBatch;
	private SpriteBatch particuleBatch;
	private Label scoreLabel;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 480;
	
	private static GameScreen current;
	
	private boolean paused=false;
	
	public static GameScreen get() {
		return current;
	}
	
	public GameScreen() {
		current = this;
		scene = new Stage(WIDTH, HEIGHT, true);
		
		
		/*LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = font;
		labelStyle.fontColor = Color.WHITE;
		scoreLabel = new Label("TA MAMAN", new LabelStyle());
		scoreLabel.setPosition(50, 50);*/

		Assets.get().load();
		
		font = new BitmapFont(Assets.get().font,Assets.get().fontImg,false);
		font.setScale(0.5f,0.5f);
		fontBatch = new SpriteBatch();
		particuleBatch = new SpriteBatch();
		player = new Player(scene);
		world = new World(scene, 400f, player);
		scene.getCamera().update();
		fontBatch.setProjectionMatrix(scene.getCamera().combined);
		particuleBatch.setProjectionMatrix(scene.getCamera().combined);
		/*player.addAction(
				forever(rotateBy(360, 2.0f))
		);*/
		
		scene.addActor(player);
		
		// assigner la scene comme gestionnaire de touch
		Gdx.input.setInputProcessor(scene);
		scene.addListener(new ClickListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				boolean rv = super.touchDown(event, x, y, pointer, button);
				GameScreen.this.player.hook( x, y);
				
				return rv;
			}
		});

	}


	
	@Override
	public void dispose() {
		Assets.get().dispose();
		font.dispose();
		fontBatch.dispose();
	}
	
	
	public void update(float delta){
		
		world.UpdateWorld(delta, scene);
		
		
	}

	@Override
	public void render(float delta) {	
		if(paused)
			delta=0;
		update(delta);
		
		Gdx.gl.glClearColor(16f/255f, 3f/255f, 28f/255f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		// incrementer le temps ecoule
		elapsedTime += Gdx.graphics.getDeltaTime();
		

		// update / faire bouger les elements de la scene / animer les actions
		scene.act();
		
		// rendu des elements ajoutes a la scene
		scene.draw();
		
		
		fontBatch.begin();
			Preferences prefs = Gdx.app.getPreferences( "VolcanoEscape" );
		
			if(world.player.started){
				font.draw(fontBatch, "Record: "+prefs.getInteger("playerScore"), 100 , scene.getHeight() - 0);
				font.draw(fontBatch, "Score: "+(int)player.score, scene.getWidth() - 350 , scene.getHeight() - 0);
			}else{
				font.draw(fontBatch, "Touch the screen to start", 50 , scene.getHeight()/2);
			}

		fontBatch.end();
	}
	


	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		paused=true;
	}

	@Override
	public void resume() {
		paused=false;
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}
}

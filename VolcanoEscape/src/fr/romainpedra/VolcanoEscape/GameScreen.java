package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

public class GameScreen implements Screen {
	//private Sprite marineSprite;
	private Stage scene;
	private Player player;
	private World world;
	
	private float spawnRockRate=5f; 
	
//	private int lives = 3;
	private BitmapFont font;
	private SpriteBatch fontBatch;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 480;
	
	private static GameScreen current;
	public static GameScreen get() {
		return current;
	}
	
	public GameScreen() {
		current = this;
		scene = new Stage(WIDTH, HEIGHT, true);
		
		font = new BitmapFont();
		fontBatch = new SpriteBatch();
		
		Assets.get().load();
		
		player = new Player(scene);
		world = new World(scene, 200f, player);
		
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

	
	// temps ecoule depuis que le dernier rock a pop
	float elapsedTime = 0.0f;
	
	
	public void update(float delta){
		this.player.update(delta);
		world.UpdateWorld(delta, scene);
		
		elapsedTime+=delta;
		if(elapsedTime>spawnRockRate){
			spawnRock();
			elapsedTime=0f;
		}
	}
	
	public void spawnRock() {
		
//////////////////////////////////////////////////////////////
//		if(world.rocks.size()==0){
			// creer un nouvel alien
			Rock rock = new Rock(scene, 400f, 400f, 60, 60,this.player);
			world.rocks.add(rock);
			// l'ajouter a la scene (il est deja anime--cf. son constructeur)
			scene.addActor(rock);
//		}
	}
	
	@Override
	public void render(float delta) {	
		update(delta);
		
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// incrementer le temps ecoule
		elapsedTime += Gdx.graphics.getDeltaTime();
		
//		// si le temps ecoule est superieur a 1 seconde
//		if(elapsedTime > 1.0f) {
//			// ajouter un alien
//			spawnAlien();
//			// remise a zero pour le prochain alien
//			elapsedTime = 0.0f;
//		}
//		
		// update / faire bouger les elements de la scene / animer les actions
		scene.act();
		
		// rendu des elements ajoutes a la scene
		scene.draw();
		
		fontBatch.setProjectionMatrix(scene.getCamera().combined);
		fontBatch.begin();
			font.draw(fontBatch, "Score : "+player.score, 10, 25);
		fontBatch.end();
	}
	


	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}
}

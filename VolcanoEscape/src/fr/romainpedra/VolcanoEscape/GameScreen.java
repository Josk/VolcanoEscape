package fr.romainpedra.VolcanoEscape;

import com.badlogic.gdx.Gdx;
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
		update(delta);
		
		Gdx.gl.glClearColor(16f/255f, 3f/255f, 28f/255f, 1.0f);
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
		
	
		for(int i =0; i< this.world.particules.size(); i++)
		{
			ParticleEffect pTmp = this.world.particules.get(i);
			if(! pTmp.isComplete())
			{
				particuleBatch.begin();
				pTmp.draw(particuleBatch, delta);
				particuleBatch.end();
			}
			else
			{
				//System.out.println("LOLOLOLOLOLO");
				pTmp.dispose();
				this.world.particules.remove(i);
				i --;
				if(i < 0)
				{
					i = 0;
				}
			}
		}
		
		/*fontBatch.begin();
		scoreLabel.draw(fontBatch, 100);
		fontBatch.end();*/
		
		fontBatch.setProjectionMatrix(scene.getCamera().combined);
		
		fontBatch.begin();
			font.draw(fontBatch, "Record: "+(int)player.score, 100 , scene.getHeight() - 0);
			font.draw(fontBatch, "Score: "+(int)player.score, scene.getWidth() - 350 , scene.getHeight() - 0);
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

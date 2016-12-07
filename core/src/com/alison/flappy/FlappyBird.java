package com.alison.flappy;

import com.alison.flappy.states.GameStateManager;
import com.alison.flappy.states.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Flappy Bird";

    private GameStateManager gsm;
	private SpriteBatch batch; //one per game (heavy files)
//	Texture img;

    private Music music; //music is streamed from disk (I/O device)
	
	@Override
	public void create () {
	    gsm = new GameStateManager();
		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f); //10% volume
        music.play();
        Gdx.gl.glClearColor(0, 1, 1, 1);
        gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //wipes screen
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
        super.dispose();
		batch.dispose();
//		img.dispose();
        music.dispose();
        System.out.println("sb and music disposed");
	}
}

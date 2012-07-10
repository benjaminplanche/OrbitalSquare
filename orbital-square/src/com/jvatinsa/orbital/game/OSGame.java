package com.jvatinsa.orbital.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvatinsa.orbital.ui.screens.GameScreen;
import com.jvatinsa.orbital.ui.screens.HomeScreen;
import com.jvatinsa.orbital.utils.FontsManager;
import com.jvatinsa.orbital.utils.SpritesManager;

public class OSGame extends Game {
	
	// Constants
	public static final int WIDTH = 860;
	public static final int HEIGHT = 860;
	
	// Screens
	public static HomeScreen homeScreen;
	public static GameScreen gameScreen;
	
	// Batch : draw textures
	private SpriteBatch batch;
	
	// Camera
	private OrthographicCamera camera;	
	
	@Override
	public void create() {
		
		// Create Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		
		// Create SpriteBatch
		batch = new SpriteBatch();
		
		// Loading resources
		SpritesManager.getI();
		FontsManager.getI();
		
		// Create Screens
		homeScreen = new HomeScreen(this);
		gameScreen = new GameScreen(this);
		
		// Load first Screen
		setScreen(homeScreen);
		
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}

	@Override
	public void dispose() {
		batch.dispose();
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
}

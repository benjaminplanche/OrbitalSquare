package com.jvatinsa.orbital.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.jvatinsa.orbital.characters.Spaceman;
import com.jvatinsa.orbital.game.OSGame;
import com.jvatinsa.orbital.maps.OrbitalMap;
import com.jvatinsa.orbital.maps.OrbitalMapFactory;

public class GameScreen extends OSScreen{

	private final int DEFAULT_ANGLE = 15; // degrees
	
	private OrbitalMap currentMap;
	
	public GameScreen(OSGame game) {
		this.game = game;
		this.batch = game.getSpriteBatch();
		this.camera = game.getCamera();
		
		currentMap = OrbitalMapFactory.getI().randomMap();
		
		Spaceman.getI().setPosition(OSGame.WIDTH/2, OSGame.HEIGHT/2);
		
	}

	@Override
	public void render(float delta) {
		
		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.Q)) {
			Spaceman.getI().moveLeft();
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			Spaceman.getI().moveRight();
		} 
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		currentMap.update(delta);
		currentMap.draw(batch);
		
		Spaceman.getI().update(delta);
		Spaceman.getI().draw(batch);
		
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

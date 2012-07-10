package com.jvatinsa.orbital.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jvatinsa.orbital.game.OSGame;
import com.jvatinsa.orbital.utils.FontsManager;
import com.jvatinsa.orbital.utils.StringsManager;

public class HomeScreen extends OSScreen {

	private BitmapFont font;
	
	public HomeScreen(OSGame game) {
		this.game = game;
		this.batch = game.getSpriteBatch();
		this.camera = game.getCamera();
		this.font = FontsManager.getI().getFonts().get("calibri32");
		
	}
	
	@Override
	public void render(float delta) {
		
		if (Gdx.input.isKeyPressed(Keys.ENTER)) {
			game.setScreen(OSGame.gameScreen);
		}
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		font.draw(batch, 
				StringsManager.TITLE_GAME, 
				OSGame.WIDTH/2-font.getBounds(StringsManager.TITLE_GAME).width/2, 
				OSGame.HEIGHT/2-font.getBounds(StringsManager.TITLE_GAME).height/2);
		
		font.draw(batch, 
				StringsManager.PLAY_MSG, 
				OSGame.WIDTH/2-font.getBounds(StringsManager.TITLE_GAME).width/2, 
				OSGame.HEIGHT/2-font.getBounds(StringsManager.TITLE_GAME).height/2-100);
		
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

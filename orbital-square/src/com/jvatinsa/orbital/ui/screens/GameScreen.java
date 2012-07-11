package com.jvatinsa.orbital.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.jvatinsa.orbital.characters.Spaceman;
import com.jvatinsa.orbital.game.OSGame;
import com.jvatinsa.orbital.game.OSWorld;
import com.jvatinsa.orbital.maps.OrbitalMap;
import com.jvatinsa.orbital.maps.OrbitalMapFactory;

public class GameScreen extends OSScreen{

	private final int DEFAULT_ANGLE = 15; // degrees
	
	private OrbitalMap currentMap;
	
	
	// Box2D stuff
	/** the immediate mode renderer to output our debug drawings **/
	private ShapeRenderer renderer;

	/** box2d debug renderer **/
	private Box2DDebugRenderer debugRenderer;
	
	
	public GameScreen(OSGame game) {
		this.game = game;
		this.batch = game.getSpriteBatch();
		this.camera = game.getCamera();
		
		// map creation with bodies
		currentMap = OrbitalMapFactory.getI().randomMap();
		Spaceman.getI().setPosition(OSGame.WIDTH/2, OSGame.HEIGHT/2);
	
		// next we setup the immediate mode renderer
		renderer = new ShapeRenderer();

		// next we create the box2d debug renderer
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render(float delta) {
		
		// update world
		OSWorld.getI().world.step(Gdx.graphics.getDeltaTime(), 8, 3);
		
		// handle inputs
		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.Q)) {
			Spaceman.getI().moveLeft();
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			Spaceman.getI().moveRight();
		} 
		
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			Spaceman.getI().jump();
		} 
		
		// render graphics
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		currentMap.update(delta);
		currentMap.draw(batch);
		
		Spaceman.getI().update(delta);
		Spaceman.getI().draw(batch);
		
		
		// DEBUG
		debugRenderer.render(OSWorld.getI().world, camera.combined);

		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Point);
		renderer.setColor(0, 1, 0, 1);
		for (int i = 0; i < OSWorld.getI().world.getContactCount(); i++) {
			Contact contact = OSWorld.getI().world.getContactList().get(i);
			if (contact.isTouching()) {
				WorldManifold manifold = contact.getWorldManifold();
				int numContactPoints = manifold.getNumberOfContactPoints();
				for (int j = 0; j < numContactPoints; j++) {
					Vector2 point = manifold.getPoints()[j];
					renderer.point(point.x, point.y, 0);
				}
			}
		}
		renderer.end();
		// END DEBUG
		
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

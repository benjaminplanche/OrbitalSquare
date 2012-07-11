package com.jvatinsa.orbital.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.jvatinsa.orbital.characters.Spaceman;
import com.jvatinsa.orbital.game.OSGame;
import com.jvatinsa.orbital.maps.Cell;
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
	
	public static World world;
	
	private void createPhysicsWorld () {
		world = new World(new Vector2(0, -90), true);
		
		for (int i = 0; i <currentMap.width; i++) {
			for (int j = 0; j <currentMap.width; j++) {
				if (currentMap.map[i][j] != null) {
					
					PolygonShape cellPoly = new PolygonShape();
					cellPoly.setAsBox(Cell.SIZE/2, Cell.SIZE/2);
					
					BodyDef cellBodyDef = new BodyDef();
					cellBodyDef.type = BodyType.StaticBody;
					cellBodyDef.position.x = currentMap.map[i][j].x+Cell.SIZE/2;
					cellBodyDef.position.y = currentMap.map[i][j].y+Cell.SIZE/2;
					
					FixtureDef fixtureDef = new FixtureDef();
					fixtureDef.shape = cellPoly;
					fixtureDef.filter.groupIndex = 0;
					currentMap.map[i][j].body = world.createBody(cellBodyDef);
					currentMap.map[i][j].body.createFixture(fixtureDef);
					
					cellPoly.dispose();
					
				}
			}
		}
		
		createSpacemanBody();
		
	}
	
	private void createSpacemanBody() {
		
		PolygonShape boxPoly = new PolygonShape();
		boxPoly.setAsBox(17, 24);
		
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.DynamicBody;
		boxBodyDef.position.x = Spaceman.getI().x;
		boxBodyDef.position.y = Spaceman.getI().y;
		Spaceman.getI().body = world.createBody(boxBodyDef);
		Spaceman.getI().body.createFixture(boxPoly, 1);
		
		boxPoly.dispose();
	}
	
	
	public GameScreen(OSGame game) {
		this.game = game;
		this.batch = game.getSpriteBatch();
		this.camera = game.getCamera();
		
		currentMap = OrbitalMapFactory.getI().randomMap();
		
		Spaceman.getI().setPosition(OSGame.WIDTH/2, OSGame.HEIGHT/2);
	
		
		// Box2D stuff
		
		// next we setup the immediate mode renderer
		renderer = new ShapeRenderer();

		// next we create the box2d debug renderer
		debugRenderer = new Box2DDebugRenderer();
		createPhysicsWorld();
		
	}

	@Override
	public void render(float delta) {
		
		// update world
		world.step(Gdx.graphics.getDeltaTime(), 8, 3);
		
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
		
		
		// next we use the debug renderer. Note that we
		// simply apply the camera again and then call
		// the renderer. the camera.apply() call is actually
		// not needed as the opengl matrices are already set
		// by the spritebatch which in turn uses the camera matrices :)
		debugRenderer.render(world, camera.combined);

		// finally we render all contact points
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Point);
		renderer.setColor(0, 1, 0, 1);
		for (int i = 0; i < world.getContactCount(); i++) {
			Contact contact = world.getContactList().get(i);
			// we only render the contact if it actually touches
			if (contact.isTouching()) {
				// get the world manifold from which we get the
				// contact points. A manifold can have 0, 1 or 2
				// contact points.
				WorldManifold manifold = contact.getWorldManifold();
				int numContactPoints = manifold.getNumberOfContactPoints();
				for (int j = 0; j < numContactPoints; j++) {
					Vector2 point = manifold.getPoints()[j];
					renderer.point(point.x, point.y, 0);
				}
			}
		}
		renderer.end();
		
		
		
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

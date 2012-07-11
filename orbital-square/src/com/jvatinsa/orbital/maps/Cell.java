package com.jvatinsa.orbital.maps;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.jvatinsa.orbital.characters.GameObject;
import com.jvatinsa.orbital.utils.SpritesManager;

public class Cell extends GameObject {

	public static final int SIZE = 20;
	
	protected Sprite sprite;
	
	// Box2D stuff
	public Body body;
	
	public Cell(float x, float y) {
		super();
		this.x = x;
		this.y = y;
		angle = 0;
		sprite = SpritesManager.getI().sprites.get("tile");
	}
	
	public Cell(float x, float y, final Sprite sprite) {
		super();
		this.x = x;
		this.y = y;
		angle = 0;
		this.sprite = SpritesManager.getI().sprites.get("tile");
	}
	
	public void rotate(float angle, final float offsetX, final float offsetY) {
		this.angle += angle;
		float xd = body.getPosition().x - offsetX;
		float yd = body.getPosition().y - offsetY;
		
		angle = (float) Math.toRadians(angle);
		x = (float) (xd*Math.cos(angle) - yd*Math.sin(angle)) + offsetX;
		y = (float) (xd*Math.sin(angle) + yd*Math.cos(angle)) + offsetY;
		
		body.setTransform(
				(float) (xd*Math.cos(angle) - yd*Math.sin(angle)) + offsetX, 
				(float) (xd*Math.sin(angle) + yd*Math.cos(angle)) + offsetY, 
				this.angle*MathUtils.degreesToRadians);
		
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setX(x-sprite.getWidth()/2);
		sprite.setY(y-sprite.getHeight()/2);
		sprite.setRotation(angle);
		sprite.draw(batch);
		
		
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
	}
	
	
	
}

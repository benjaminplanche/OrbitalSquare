package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.jvatinsa.orbital.commons.Displayable;

public class GameObject implements Displayable{

	// Position
	protected float x;
	protected float y;
	protected float angle;
	
	// Animation
	protected float stateTime;
	protected Sprite sprite;
	
	// Box2D stuff
	protected Body body;
	
	public GameObject() {
		x = 0;
		y = 0;
		angle = 0;
		stateTime = 0;
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(float deltaTime) {
		stateTime+=deltaTime;
		
		
		x = body.getPosition().x;
		y = body.getPosition().y;
		angle = body.getAngle()*MathUtils.radiansToDegrees;
		
	}

	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	
}

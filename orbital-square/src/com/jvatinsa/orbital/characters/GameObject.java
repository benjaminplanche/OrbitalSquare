package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.jvatinsa.orbital.commons.Displayable;

public class GameObject implements Displayable{

	// Position
	public float x;
	public float y;
	protected float angle;
	
	// Animation
	protected float stateTime;
	
	// Box2D stuff
	public Body body;
	
	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.stateTime = 0;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		
		
		
	}

	@Override
	public void update(float deltaTime) {
		this.stateTime+=deltaTime;
		
		this.x = body.getPosition().x;
		this.y = body.getPosition().y;
		this.angle = body.getAngle()*MathUtils.radiansToDegrees;
		
	}

	
	
}

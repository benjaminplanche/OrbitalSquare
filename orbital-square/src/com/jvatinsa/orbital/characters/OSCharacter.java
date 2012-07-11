package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OSCharacter extends GameObject {

	// Defaults
	public static final float DEFAULT_ANIM_SPEED = 0.2f; 
	
	public static final int SPEED = 2000;
	public static final int HEALTH = 100; 
	public static final int MASS = 100; // kg
	
	// Properties
	protected int health;
	protected int speed;
	protected int mass;
	
	// Animations
	protected boolean turnLeft;
	protected Animation walkLeft;
	protected Animation walkRight;
	
	public OSCharacter() {
		super();
		health = HEALTH;
		speed = SPEED;
		mass = MASS;
		this.turnLeft = true;
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
	}
	
	
	
	
}

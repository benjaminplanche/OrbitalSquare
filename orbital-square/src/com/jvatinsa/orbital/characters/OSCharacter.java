package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OSCharacter extends GameObject {

	// Defaults
	public static final int SPEED = 2000;
	public static final float DEFAULT_ANIM_SPEED = 0.2f; 
	
	// Properties
	protected int health;
	protected int speed;
	
	// Animations
	protected boolean turnLeft;
	protected Animation walkLeft;
	protected Animation walkRight;
	
	public OSCharacter() {
		super();
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

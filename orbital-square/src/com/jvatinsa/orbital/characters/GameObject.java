package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvatinsa.orbital.commons.Displayable;

public class GameObject implements Displayable{

	// Position
	protected float x;
	protected float y;
	
	// Animation
	protected float stateTime;
	
	
	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.stateTime = 0;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		
		
		
	}

	@Override
	public void update(float deltaTime) {
		this.stateTime+=deltaTime;
		
	}

	
	
}

package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jvatinsa.orbital.commons.Displayable;
import com.jvatinsa.orbital.utils.SpritesManager;

public class Spaceman extends OSCharacter implements Displayable{
	
	private static Spaceman spaceman = null;
	
	public static Spaceman getI() {
		if(spaceman == null) {
			spaceman = new Spaceman();
		}
		return spaceman;
	}
	
	
	protected float animWalkSpeed = DEFAULT_ANIM_SPEED;
	
	private Spaceman() {
		super();

		walkLeft = new Animation(animWalkSpeed, SpritesManager.getI().animations.get("smWalkLeft"));
		walkRight = new Animation(animWalkSpeed, SpritesManager.getI().animations.get("smWalkRight"));

		speed = SPEED;
	}

	@Override
	public void draw(SpriteBatch batch) {
		
		TextureRegion frame = null;
		
		if (turnLeft) {
			frame = walkLeft.getKeyFrame(stateTime, true);
		} else {
			frame = walkRight.getKeyFrame(stateTime, true);
		}
		
		batch.draw(frame, x, y);
		
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void moveLeft() {
		turnLeft = true;
		this.x -= speed;
	}
	
	public void moveRight() {
		turnLeft = false;
		this.x += speed;
	}
	
	public void jump() {
		// TODO
	}
	
	
}

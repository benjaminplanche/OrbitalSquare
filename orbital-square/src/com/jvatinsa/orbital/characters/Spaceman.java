package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
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
		
		float angle = MathUtils.radiansToDegrees * body.getAngle();
		
		batch.draw(frame, x, y, // the bottom left corner of the box, unrotated
				1f, 1f, // the rotation center relative to the bottom left corner of the box
				frame.getRegionWidth(), frame.getRegionHeight(), // the width and height of the box
				1, 1, // the scale on the x- and y-axis
				angle); // the rotation angle
		
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void moveLeft() {
		turnLeft = true;
		this.body.applyLinearImpulse(-speed*10000, 0, body.getPosition().x, body.getPosition().y);
		//this.x -= speed;
	}
	
	public void moveRight() {
		turnLeft = false;
		this.body.applyLinearImpulse(speed*10000, 0, body.getPosition().x, body.getPosition().y);
		//this.x += speed;
	}
	
	public void jump() {
		this.body.applyLinearImpulse(0, speed*10000, body.getPosition().x, body.getPosition().y);
		//this.body.applyForceToCenter(new Vector2(0, speed*10));
	}
	
	
}

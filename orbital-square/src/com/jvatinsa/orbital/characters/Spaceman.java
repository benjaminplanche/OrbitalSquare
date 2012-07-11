package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.jvatinsa.orbital.commons.Displayable;
import com.jvatinsa.orbital.game.OSWorld;
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
		
		PolygonShape boxPoly = new PolygonShape();
		boxPoly.setAsBox(17, 24);
		
		BodyDef boxBodyDef = new BodyDef();
		boxBodyDef.type = BodyType.DynamicBody;
		boxBodyDef.position.x = 0;
		boxBodyDef.position.y = 0;
		body = OSWorld.getI().world.createBody(boxBodyDef);
		body.createFixture(boxPoly, 1);

		boxPoly.dispose();

		walkLeft = new Animation(animWalkSpeed, SpritesManager.getI().animations.get("smWalkLeft"));
		walkRight = new Animation(animWalkSpeed, SpritesManager.getI().animations.get("smWalkRight"));

	}

	@Override
	public void draw(SpriteBatch batch) {
		
		TextureRegion frame = null;
		
		if (turnLeft) {
			frame = walkLeft.getKeyFrame(stateTime, true);
		} else {
			frame = walkRight.getKeyFrame(stateTime, true);
		}
		
		
		batch.draw(frame, 
				x- frame.getRegionWidth()/2, 
				y - frame.getRegionHeight()/2, // the bottom left corner of the box, unrotated
				0, 0, // the rotation center relative to the bottom left corner of the box
				frame.getRegionWidth(), frame.getRegionHeight(), // the width and height of the box
				1, 1, // the scale on the x- and y-axis
				(float)MathUtils.radiansToDegrees * body.getAngle()); // the rotation angle
		
	}

	public void setPosition(float x, float y) {
		this.body.setTransform(x, y, 0);
	}
	
	
	public void moveLeft() {
		turnLeft = true;
		this.body.applyLinearImpulse(-speed*10000, 0, body.getPosition().x, body.getPosition().y);
	}
	
	public void moveRight() {
		turnLeft = false;
		this.body.applyLinearImpulse(speed*10000, 0, body.getPosition().x, body.getPosition().y);
	}
	
	public void jump() {
		this.body.applyLinearImpulse(0, speed*10000, body.getPosition().x, body.getPosition().y);
	}
	
	
}

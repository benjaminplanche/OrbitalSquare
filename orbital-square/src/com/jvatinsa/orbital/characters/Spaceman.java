package com.jvatinsa.orbital.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.jvatinsa.orbital.commons.Displayable;
import com.jvatinsa.orbital.game.OSWorld;
import com.jvatinsa.orbital.managers.SpritesManager;

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

		// test
		body.setGravityScale(10);
		
		// Animations
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
		
		// TODO : use Pool !
		sprite = new Sprite(frame); // GC calls !
		
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setX(x-sprite.getWidth()/2);
		sprite.setY(y-sprite.getHeight()/2);
		sprite.setRotation(angle);
		sprite.draw(batch);
		
		/*
		batch.draw(frame, 
				x - frame.getRegionWidth()/2, 
				y - frame.getRegionHeight()/2, 
				0, 0, 
				frame.getRegionWidth(), 
				frame.getRegionHeight(), 
				1.f, 1.f, angle);
		*/
		
	}

	public void setPosition(float x, float y) {
		this.body.setTransform(x, y, 0);
	}
	
	
	// TODO : use "reals" values
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

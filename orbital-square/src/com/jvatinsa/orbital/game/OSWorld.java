package com.jvatinsa.orbital.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


public class OSWorld {

private static OSWorld osWorld = null;
	
	public static OSWorld getI() {
		if(osWorld == null) {
			osWorld = new OSWorld();
		}
		return osWorld;
	}
	
	public World world;
	public static final int GRAVITY = -10;
	
	private OSWorld() {
		world = new World(new Vector2(0, GRAVITY), true);
		
	}
	
	
}

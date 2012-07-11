package com.jvatinsa.orbital.maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jvatinsa.orbital.characters.Spaceman;
import com.jvatinsa.orbital.commons.Displayable;
import com.jvatinsa.orbital.game.OSGame;

public class OrbitalMap implements Displayable{

	public int width;
	public int height;
	public Cell[][] map;
	
	public OrbitalMap(final int width, final int height, final Cell[][] map) {
		this.width = width;
		this.height = height;
		this.map = map;
		
		
	}

	public void rotate(float angle) {
		for (int i = 0; i< width; i++) {
			for (int j = 0; j < height; j++) {
				if (map[i][j] != null) {
					map[i][j].rotate(angle, 
							OSGame.WIDTH/2,
							OSGame.HEIGHT/2
							);
				}
			}
		}
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		for (int i = 0; i< width; i++) {
			for (int j = 0; j < height; j++) {
				if (map[i][j] != null) {
					map[i][j].draw(batch);
				}
			}
		}
		
	}

	@Override
	public void update(float deltaTime) {
		rotate(0.1f); // test
		Spaceman.getI().update(deltaTime);
		
	}
	
}

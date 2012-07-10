package com.jvatinsa.orbital;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jvatinsa.orbital.game.OSGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "orbital-square";
		cfg.useGL20 = false;
		cfg.width = OSGame.WIDTH;
		cfg.height = OSGame.HEIGHT;
		
		new LwjglApplication(new OSGame(), cfg);
	}
}

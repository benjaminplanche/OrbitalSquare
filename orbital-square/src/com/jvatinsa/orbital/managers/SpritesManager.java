package com.jvatinsa.orbital.managers;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class SpritesManager {

private static SpritesManager spritesManager = null;
	
	private TextureAtlas atlas;
	
	public HashMap<String, Sprite > sprites;
	public HashMap<String, List<AtlasRegion> > animations;
	
	public static SpritesManager getI() {
		if(spritesManager == null) {
			spritesManager = new SpritesManager();
		}
		return spritesManager;
	}

	private SpritesManager() {
		sprites = new HashMap<String, Sprite>();
		animations = new HashMap<String, List<AtlasRegion> >();
		
		atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.SPRITES_PACK));
		loadSprites();
		loadAnimations();
		
	}

	private void loadSprites(){
		sprites.put("tile", atlas.createSprite("tile"));
		
	}
	
	private void loadAnimations(){
		animations.put("smWalkLeft", atlas.findRegions("smWalkLeft"));
		animations.put("smWalkRight", atlas.findRegions("smWalkRight"));
		
	}
	
	
	public void disposeSprites() {
		atlas.dispose();
	}
	
}

package com.jvatinsa.orbital.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontsManager {

	private static FontsManager fontManager = null;
	
	public static FontsManager getI() {
		if(fontManager == null) {
			fontManager = new FontsManager();
		}
		return fontManager;
	}
	
	private HashMap<String, BitmapFont> hashFonts;
	
	public HashMap<String, BitmapFont> getFonts() {
		return hashFonts;
	}
	
	private FontsManager() {
		hashFonts = new HashMap<String, BitmapFont>();
		loadFonts();
	}
	
	public void disposeFonts(){
		for (String font : hashFonts.keySet()) {
			hashFonts.get(font).dispose();
		}
	}
	
	public void loadFonts() {
		// Testing
		// TODO : generate another font using hiero
		BitmapFont font = new BitmapFont(Gdx.files.internal(AssetsManager.FONT_DEFAULT),false);
		font.setColor(0, 0, 0, 1);
		hashFonts.put("calibri32", font);
		
	}
	
}

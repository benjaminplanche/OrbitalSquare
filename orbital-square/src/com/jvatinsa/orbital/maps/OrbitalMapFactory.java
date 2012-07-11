package com.jvatinsa.orbital.maps;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.JsonReader;
import com.jvatinsa.orbital.game.OSGame;
import com.jvatinsa.orbital.game.OSWorld;

public class OrbitalMapFactory {

	private JsonReader jsonReader;

	private static OrbitalMapFactory orbitalMapFactory = null;
		
		public static OrbitalMapFactory getI() {
			if(orbitalMapFactory == null) {
				orbitalMapFactory = new OrbitalMapFactory();
			}
			return orbitalMapFactory;
		}
	
	private OrbitalMapFactory() {
		jsonReader = new JsonReader();
		
	}
	
	public OrbitalMap loadMap(final String fileName) {
		return null;
		/*
		int width = 0, height = 0;
		TileType[][] map = null;
		
		Json json = new Json();
		// dirty method with casts ...
		OrderedMap<String, Object> root = (OrderedMap<String, Object>) jsonReader.parse(Gdx.files.internal(fileName));
		System.out.println(root.toString());
		width = Integer.parseInt((String) root.get("width"));
		height = Integer.parseInt((String) root.get("height"));
		
		map = new TileType[width][height];
		Array<Array<Float>> mapJSON = (Array<Array<Float>>) root.get("tiles");
		for (int i = 0; i < mapJSON.size; i++) {
			Array<Float> p = mapJSON.get(i);
			switch ((p.get(2)) {
				case 1: 
					map[p.get(0)][p.get(1)] = TileType.SOLID;
				break;
				default:
				
				break;
			}
			
			
		}
		
		return new OrbitalMap(width, height, map);
		*/
	}
	
	public OrbitalMap randomMap() {
		int width = 30, height = 30;
		Cell[][] map = null;
		map = new Cell[width][height];
		
		// Define offsets depending on the size of the map
		float offsetX = OSGame.WIDTH/2-(width/2)*Cell.SIZE;
		float offsetY = OSGame.HEIGHT/2-(height/2)*Cell.SIZE;
		
		// manual build
		for (int j = 0; j < width; j++) {
			map[0][j] = new Brick(0+offsetX, j*Cell.SIZE+offsetY);
			map[height-1][j] = new Brick((height-1)*Cell.SIZE+offsetX, j*Cell.SIZE+offsetY);
		}
		
		for (int i = 0; i < height; i++) {
			map[i][0] = new Brick(i*Cell.SIZE+offsetX, 0+offsetY);
			map[i][width-1] = new Brick(i*Cell.SIZE+offsetX, (width-1)*Cell.SIZE+offsetY);
		}
		
		for (int i = 10; i < 15; i++) {
			map[i][17] = new Brick(i*Cell.SIZE+offsetX, 17*Cell.SIZE+offsetY);
		}
		for (int i = 6; i < 17; i++) {
			map[i][11] = new Brick(i*Cell.SIZE+offsetX, 11*Cell.SIZE+offsetY);
		}
		
		for (int j = 8; j < 15; j++) {
			map[24][j] = new Brick(24*Cell.SIZE+offsetX, j*Cell.SIZE+offsetY);
		}
		
		for (int j = 12; j < 17; j++) {
			map[18][j] = new Brick(18*Cell.SIZE+offsetX, j*Cell.SIZE+offsetY);
		}
		
		
		// 
		for (int i = 0; i <width; i++) {
			for (int j = 0; j <width; j++) {
				if (map[i][j] != null) {
					
					PolygonShape cellPoly = new PolygonShape();
					cellPoly.setAsBox(Cell.SIZE/2, Cell.SIZE/2);
					
					BodyDef cellBodyDef = new BodyDef();
					cellBodyDef.type = BodyType.StaticBody;
					cellBodyDef.position.x = map[i][j].getX()+Cell.SIZE/2;
					cellBodyDef.position.y = map[i][j].getY()+Cell.SIZE/2;
					
					FixtureDef fixtureDef = new FixtureDef();
					fixtureDef.shape = cellPoly;
					fixtureDef.filter.groupIndex = 0;
					map[i][j].body = OSWorld.getI().world.createBody(cellBodyDef);
					map[i][j].body.createFixture(fixtureDef);
					
					cellPoly.dispose();
				}
			}
		}
		
		
		return new OrbitalMap(width, height, map);
	}
	
}

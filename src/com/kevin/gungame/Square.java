package com.kevin.gungame;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.RectF;

public class Square extends Actor{
	
	public Square(){
		
		super(World.WIDTH/6,(World.WIDTH/6)*2);
		collision=true;
		drawable=true;
		World.actors3d.add(this);
		
	}

}

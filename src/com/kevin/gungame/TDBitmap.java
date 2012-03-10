package com.kevin.gungame;

import javax.microedition.khronos.opengles.GL10;

public class TDBitmap extends Actor {
	public TDBitmap(float width,float height){
		super(width,height);
		World.actors2d.add(this);
	}
	public void draw(GL10 gl){
		super.draw(gl);
		gl.glLoadIdentity();
	}
}

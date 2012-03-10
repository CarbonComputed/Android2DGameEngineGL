package com.kevin.gungame;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.PointF;
import android.util.Log;

public class Bullet extends Actor {
	PointF hitzone;
	Gun gun;
	public int length;
	public float color;
	public float velocity;
	public float energy;
	public float size;
	public PointF oldPos;
	public PointF Pos;
	
	public Bullet(Gun g){
		super(1f,.1f);
		gun=g;
		velocity=gun.velocity;
		World.actors3d.add(this);
		
	}
	
	public void initShape(){
		
		
	}
	public void move(float px,float py,float pdirection){
		drawable=true;
		x=px;
		y=py;
		direction=pdirection+90;
		drawable=true;
		collision=true;
		Log.d("test2", ""+direction);
		update();
		//Log.d("test","AAAAAAAAA");
	}
	public void update(){
		x+=(Math.cos(Math.toRadians(direction-180))/10);
		y+=Math.sin(Math.toRadians(direction-180))/10;
		
	}
	public void draw(GL10 gl){
		gl.glColor4f(1f, 1f, 1f, .7f);
		super.draw(gl);
		gl.glColor4f(1f,1f,1f,1f);
		//gl.glcl
	}
	
	
}

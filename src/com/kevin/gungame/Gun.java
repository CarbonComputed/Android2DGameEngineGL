package com.kevin.gungame;

import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.util.Log;

public class Gun extends Actor {
	public int damage;
	public Bullet Ammo[];
	public int accuracy;
	public float velocity;
	public int resource;
	private int ammocount;
	private int capacity;
	private int rof;
	private boolean shot=false;
	private long time;
	public Gun(int dmg,int capacity,float v){
		damage=dmg;
		velocity=v;
		ammocount=capacity;
		this.capacity=capacity;
		Ammo=new Bullet[capacity];
		for(int c=0;c<capacity;c++){
			Ammo[c]=new Bullet(this);
		}
		
	}
	public void shoot(float px, float py,float pdirection){
	//	Log.d("test2",""+(System.currentTimeMillis()-time)*.001f);
		if(shot==false){
			time=System.currentTimeMillis();
			shot=true;
		}
		
		if(shot==true&&(System.currentTimeMillis()-time)*.001f>0.15){
			time=System.currentTimeMillis();
		if(ammocount>0){
			Ammo[ammocount-1].move(px,py,pdirection);
			ammocount--;
		}
		if(ammocount<=0){
			ammocount=capacity;
		}
		}
	}
	@Override
	public void loadGLTexture(GL10 gl,Context context,int Resource){
		super.loadGLTexture(gl, context, Resource);
		
	}
}

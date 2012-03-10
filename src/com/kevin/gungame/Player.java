package com.kevin.gungame;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Message;
import android.util.Log;

public class Player extends Actor {
	Circle boundary;
	private float prevx;
	private float prevy;
	private float camprevx;
	private float camprevy;
	private float dy;
	private float dx;
	public Gun gun;
	public Player(){
		super(World.WIDTH/10,World.WIDTH/10);
		direction=180;
		
		//boundary=new RectF(0f,(float)World.WIDTH/9f , 0f, (float)World.WIDTH/9f);
		//boundary=new RectF(x-width/2,y+height/2,x+width/2,y-height/2);
		boundary=new Circle(new PointF(x,y),width/2);
		gun=new Gun(10,30,0.5f);
		drawable=true;
		World.actors3d.add(this);
		
	}
	
	public void shoot(){
		gun.shoot(x, y, direction);
	}
	
	public void move(GameControls gc){
		dy=(gc._touchingPoint.y-gc.inity)/1000*.3f*-1;
		dx=(gc._touchingPoint.x-gc.initx)/1000*.3f;
		//boundary.set(boundary.left+dx, boundary.top+dy, boundary.right+dx, boundary.bottom+dy);
		boundary.center.x+=dx;
		boundary.center.y+=dy;
		if(gc._touchingPoint.y!=gc.inity&&gc._pointerPosition.x!=gc.initx)
		direction = (float) (Math.atan2(gc._touchingPoint.y - gc.inity,gc._touchingPoint.x - gc.initx)/(Math.PI/180))*-1+90;
		//float dy=(float)(z*Math.cos(direction))/1000;
		//float dx=(float)(z*Math.sin(direction))/1000;
		
		//Log.d("test2", ""+direction);
		if(inBoundary(gc)){
			boolean test=Collision.intersectsRectangle(boundary,World.test);
			Message m=new Message();
			m.obj=""+test;
		//	World.handler.sendMessage(m);
				if(test==true){
				float ex=((dx/Math.abs(dx))*5)/1000;
				float ey=((dy/Math.abs(dy))*5)/1000;
				
				//ey=ey*-1;
			//	World.camx+=ex*0.6;
				//World.camy+=ey*0.6;
				//x-=ex;
				//y-=ey;
			//	Message m2=new Message();
				//m2.obj=x+" "+y;
			//	World.handler.sendMessage(m2);
				
				boundary.center.x-=dx;
				boundary.center.y-=dy;
				if(Float.isNaN(ex)==false && Float.isNaN(ey)== false){
				boundary.center.x-=ex;
				boundary.center.y-=ey;
				x-=ex;
				y-=ey;
				World.camx+=ex*0.6;
				World.camy+=ey*0.6;
				}
				//
				//World.camx+=dx+.001f;
				//dy=.001f;
			//	World.camx+=ey*0.6;
				//World.camy+=ex*0.6;
				//}
				//else{
					//y-=.0001f;
					//x-=.0001f;
					//World.camx+=.1;
					//World.camy+=.1;
				//}   
				
			
			}
				else{		
		y+=dy;
		x+=dx;
		World.camx-=dx*0.6;
		World.camy-=dy*0.6;
				}	
		}
		
		
		//x+=.1;
		//y+=.1;
		
		//aLog.d("angle", ""+(gc._touchingPoint.y-gc.inity)+" "+direction);
	}
	public boolean inBoundary(GameControls gc){
		return true;
	}
	
}

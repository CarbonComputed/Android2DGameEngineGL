package com.kevin.gungame;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Message;
import android.util.Log;

public class Collision {
	public static float dx;
	public static float dy;
	public static boolean intersectsRectangle(Circle a,Actor b){
		float cdx = Math.abs(a.center.x-b.x)-b.width/2;
		float cdy=Math.abs(a.center.y-b.y)-b.width/2;
		Collision.dx=cdx;
		Collision.dy=cdy;
		
		Message M =new Message();
		M.obj=""+cdx+"\n"+cdy+"\n"+b.width;;
		//World.handler.sendMessage(M);
		if(cdx > (b.width/2-a.radius)){return false;}
		if(cdy > (b.height/2-a.radius)){return false;}
		Message m=new Message();
		float t=(float) (a.center.x+a.radius);
		m.obj=""+cdx+" "+t;
		World.handler.sendMessage(m);
		if(cdx<=(b.width/2)){return true;}
		if(cdy<=(b.width/2)){return true;}
		float cornerdistance=(float) (Math.pow(cdx-b.width/2, 2)+Math.pow(cdy- b.height/2,2));
		return (cornerdistance<=Math.pow(a.radius, 2));
	}
	
	public static boolean lineinCircleIntersection(Point a,Point b, Circle c){
		float dx= Math.abs(b.x-a.x);
		float dy=Math.abs(b.y-a.y);
		//float dr=Math.pow(dx, 2)+dy;
		return false;
	}
	/*
	 	float cdx=Math.abs(a.x-b.x-b.width/2);
		float cdy=Math.abs(a.y-b.y-b.width/2);
		if(cdx > (b.width/2+a.width/2)){return false;}
		if(cdy > (b.height/2+a.width/2)){return false;}
		
		if(cdx<=(b.width/2)){return true;}
		if(cdx<=(b.height/2)){return true;}
		float cornerdistance=(float) (Math.pow(cdx-b.width/2, 2)+Math.pow(cdy- b.height/2,2));
		return (cornerdistance<=Math.pow(a.width/2, 2));
	 */
	public static boolean pointinCircle(PointF a,Circle b){
		if(Math.pow((a.x-b.center.x), 2)+Math.pow((a.y-b.center.y), 2)<Math.pow(b.radius, 2)){
			Message m=new Message();
			m.obj="collision";
			World.handler.sendMessage(m);
			return true;
		}
		Message m=new Message();
		m.obj="no collision";
		World.handler.sendMessage(m);
		return false;
	}
	public boolean contains(Actor a,Actor b){
		return true;
	}
	
}
class Circle{
	PointF center;
	double radius;
	public Circle(PointF c,double r){
		center=c;
		radius=r;
	}
}
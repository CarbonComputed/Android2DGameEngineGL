package com.kevin.gungame;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

	private SurfaceHolder mSurfaceHolder;
	private Handler mHandler;
	private Context mContext;
	private Paint mLinePaint;
	private Paint blackPaint;

	//for consistent rendering
	private long sleepTime;
	//amount of time to sleep for (in milliseconds)
	private long delay=70;
	//state of game (Running or Paused).
	int state = 1;
	public final static int RUNNING = 1;
	public final static int PAUSED = 2;
	public final static int STOPED = 3;

	

	public GameThread(Context context, Handler handler){

		//data about the screen
		//mSurfaceHolder = surfaceHolder;
		mHandler = handler;
		mContext = context;
		init();
		//gEngine=gEngineS;
		//gEngine.setRenderer(new customR(context));
	}

	//This is the most important part of the code. It is invoked when the call to start() is
	//made from the SurfaceView class. It loops continuously until the game is finished or
	//the application is suspended.
	private long beforeTime;
	@Override
	public void run() {
		
		//UPDATE
		while (state==RUNNING) {
			//Log.d("State","Thread is running");
			//time before update
			beforeTime = System.nanoTime();
			
			
			update();


			//SLEEP
			//Sleep time. Time required to sleep to keep game consistent
			//This starts with the specified delay time (in milliseconds) then subtracts from that the
			//actual time it took to update and render the game. This allows our game to render smoothly.
			this.sleepTime = delay-((System.nanoTime()-beforeTime)/1000000L);

			try {
				//actual sleep code
				if(sleepTime>0){
					this.sleep(5);
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
			}
			
			while (state==PAUSED){
				Log.d("State","Thread is pausing");
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void init(){
		
		World.player=new Player();
	}
	public void update(){
		updateInput();
		updateAI();
		updatePhysics();
		
	}
	public void updateInput(){
		World.gc.update(null);
	}
	public void updateAI(){
		
	}
	public void updatePhysics(){
		if(World.gc.pressed==true){
		//	World.player.move(World.gc);
		World.gc.pressed=false;
		}
		for(Bullet b:World.player.gun.Ammo){
		//	if(b.drawable){
			b.update();
			
		}
	}

	}

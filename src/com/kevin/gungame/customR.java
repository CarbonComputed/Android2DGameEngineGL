package com.kevin.gungame;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;



public class customR implements Renderer {
	

	private Background background;	
	
	TDBitmap joystick;
	TDBitmap joystickbg;
	float m;
	Actor c;
	private Context context;
	/**
	 * Instance the Cube object and set 
	 * the Activity Context handed over
	 */
	public customR(Context context) {
		this.context = context;
		background = new Background();
		joystick = new TDBitmap(66f,66f);
		joystickbg= new TDBitmap(106f,106f);
		c=new Square();
		c.x=1f;
		c.y=1f;
		
		World.test=c;
	}

	public void initTexture(GL10 gl){
		background.loadGLTexture(gl, context,R.drawable.ground);
		World.player.loadGLTexture(gl, context, R.drawable.player);
		joystick.loadGLTexture(gl, context, R.drawable.joystick);
		joystickbg.loadGLTexture(gl, context, R.drawable.joystick_bg);
		World.test.loadGLTexture(gl, context, R.drawable.square);
		for(Bullet b:World.player.gun.Ammo){
			b.loadGLTexture(gl, context, R.drawable.particle_laser);
		}
	}
	/**
	 * The Surface is created/init()
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {		
		//Load the texture for the cube once during Surface creation
		if(World.gameThread==null){
			World.gameThread=new GameThread(context,World.worldview.getHandler());
			World.gameThread.start();
								   }
		initTexture(gl);
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 
		//GLES20 gl2=(GLES20)gl;
		//gl2.gl
	}

	public void onSurfaceOrtho( GL10 gl ) {
	    gl.glMatrixMode( GL10.GL_PROJECTION );
	    gl.glLoadIdentity();
	    //gl.glOrthof(0f, 533f, 320f, 0f, -0.1f, 0.1f );
	    
	    gl.glOrthof( 0.0f, (float)World.swidth, (float)World.sheight, 0.0f, -0.1f, 0.1f );
	    gl.glMatrixMode( GL10.GL_MODELVIEW );
	    gl.glLoadIdentity();
	    
	}

	public void onSurfacePerspective( GL10 gl ) {
	    gl.glMatrixMode( GL10.GL_PROJECTION );
	    gl.glLoadIdentity();
	    GLU.gluPerspective( gl, 45.0f, (float)World.swidth/(float)World.sheight, 0.1f, 100.0f );
	    gl.glMatrixMode( GL10.GL_MODELVIEW );
	    gl.glLoadIdentity();
	}
	
	/**
	 * Here we do our drawing
	 */
	public void onDrawFrame(GL10 gl) {
					//Reset The Current Modelview Matrix
			//Move 5 units into the screen
	//	Log.d("actor", ""+World.actors3d.size());
		   World.gc.update(null);
		  
		    gl.glClear( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
		    gl.glLoadIdentity();
		    onSurfacePerspective( gl );
		    gl.glTranslatef(World.camx, World.camy, -5.0f);	
		    
		   // m+=-1;
		  //  gl.glRotatef(m, 1.0f, 1.0f, 1.0f);
		    //gl.glTranslatef(0f, 0f, -5f);
		    
		    background.draw(gl);
		    for(Actor a:World.actors3d){
		    	if(a.equals(World.player)){
		    		Player p=(Player)a;
		    		Message m=new Message();
		    		m.obj=""+p.x+" , "+p.y;
		    		//World.handler.sendMessage(m);
		    		
		    		
		    		p.draw(gl);
		    		
		    		
		    	}
		    	if(a.equals(World.test)){
		    		Square s=(Square)a;
		    		
		    		
		    		
		    		s.x=1f;
		    		s.y=1f;
		    		gl.glLoadIdentity();
		    		 gl.glTranslatef(World.camx, World.camy, -5.0f);	
		    		//gl.glTranslatef(0f, 0f, -5f);
		    		s.draw(gl);
		    		
		    	}
		    	if(a instanceof Bullet){
		    		if(a.drawable){
		    		   Bullet bullet=(Bullet)a;
		    		   
		    		   bullet.draw(gl);
		    		}
		    	}
		    	
		    
		    }
		   
		    onSurfaceOrtho( gl );
		    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		    gl.glEnable(GL10.GL_BLEND);
		    drawJoystick(gl);
		    for(TDBitmap td:World.actors2d){
		    	if(td.equals(joystick)==false && td.equals(joystickbg)==false){
		    	
		    	td.draw(gl);
		    	}
		    }
		    
		   // b2.x=100;
		   // b2.y=100;
		    //b2.draw(gl);
		   
		//Log.d("TEST","TEST");
	}
	
	public void drawJoystick(GL10 gl){
		joystick.x=World.gc._touchingPoint.x;
		joystick.y=World.gc._touchingPoint.y;
		joystickbg.x=70;
		joystickbg.y=260;
		joystickbg.draw(gl);
		joystick.draw(gl);
		
		
	}
	/**
	 * If the surface changes, reset the view
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}
		World.swidth=width;
		World.sheight=height;
		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix
		World.gc.inity=World.sheight-60;

		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 	//Reset The Modelview Matrix
	//	Log.d("Gun", Integer.toString(width));
	}
}
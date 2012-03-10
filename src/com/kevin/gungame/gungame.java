package com.kevin.gungame;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.GLWrapper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;





import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * The initial Android Activity, setting and initiating
 * the OpenGL ES Renderer Class @see Lesson06.java
 * 
 * @author Savas Ziplies (nea/INsanityDesign)
 */
public class gungame extends Activity {

	/** The OpenGL View */
	private GLSurfaceView worldview;
	private customR render;
	private TextView tv;
	/**
	 * Initiate the OpenGL View and set our own
	 * Renderer (@see Lesson06.java)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		//Create an Instance with this Activity
		worldview =new GLSurfaceView(this);
		World.worldview=worldview;
		render=new customR(this);
		//Set the GLSurface as View to this Activity
		worldview.setRenderer(render);
		initWorld();
		initInput();
		setContentView(worldview);
		//Set our own Renderer and hand the renderer this Activity Context
		
		
		
	//	Button visibleButton = (Button) findViewById(R.id.vis);
	//	Label label=new Label(this);
		tv=new TextView(this);
		tv.setText("TEST");
		tv.setTextColor(Color.WHITE);
		tv.setBackgroundColor(0);
		addContentView((View)tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
	//	addContentView((View)label, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

		World.handler=handler;
		World.boundary=new RectF(-3.5f,-6.0f,3.5f,6.0f);
		
	}
	final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            tv.setText(""+msg.obj);
            
        }
    };
	public void initInput(){
		 World.gc = new GameControls();
		World.worldview.setOnTouchListener(World.gc);
		//render.setInput(_controls);
	}
	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {
		super.onResume();
		worldview.onResume();
		//World.gameThread.state=1;
	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {
		super.onPause();
		worldview.onPause();
		//World.gameThread.state=2;
	}
	


	
	public void initWorld(){
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//worldview.setBackgroundResource(R.drawable.terrain1);
		//Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.triangle);

	
	          
		
		
	}
}

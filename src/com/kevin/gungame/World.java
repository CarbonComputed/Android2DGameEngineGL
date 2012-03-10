package com.kevin.gungame;

import java.util.ArrayList;

import android.graphics.RectF;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.widget.TextView;

public class World {
public static final float WIDTH=3.5f;
public static final float HEIGHT=2.1f;
public static int swidth=0;
public static int sheight=0;
public static float camx=0f;
public static float camy=0f;
public static Player player;
public static ArrayList<TDBitmap> actors2d = new ArrayList<TDBitmap>();
public static ArrayList<Actor> actors3d = new ArrayList<Actor>();
public static GameThread gameThread;
public static GLSurfaceView worldview;
public static GameControls gc;
public static Handler handler;
public static RectF boundary;
public static Actor test;

}

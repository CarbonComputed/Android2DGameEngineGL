package com.kevin.gungame;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Config;
import android.util.Log;

public class Actor {
	protected FloatBuffer vertexBuffer;
	protected FloatBuffer textureBuffer;
	public float x;
	public float y;
	public float z;
	public boolean collision=false;
	public float width;
	public float height;
	public float direction;
	public boolean drawable=false;
	int r;
	
	public float vertices[] = { 
			-1.0f, -1.0f, 0.0f, 	//Bottom Left
			1.0f, -1.0f, 0.0f, 		//Bottom Right
			-1.0f, 1.0f, 0.0f,	 	//Top Left
			1.0f, 1.0f, 0.0f 		//Top Right
							};
	protected int[] textures = new int[1];
	
	private float texture[] = {
			0.0f, 0.0f, 
			1.0f, 0.0f, 
			0.0f, 1.0f, 
			1.0f, 1.0f,
					};
	public Actor() {
		initTexture();
		x=0.0f;
		y=0.0f;
		z=0.0f;
		
	}
	public Actor(float width,float height) {
		
		x=0.0f;
		y=0.0f;
		z=0.0f;
		this.width=width;
		this.height=height;
		float vert[] = { 
				-width/2, -height/2, 0.0f, 	//Bottom Left
				width/2, -height/2, 0.0f, 		//Bottom Right
				-width/2, height/2, 0.0f,	 	//Top Left
				width/2, height/2, 0.0f 		//Top Right
								};
		vertices=vert;
		
		initTexture();
	//	World.actors3d.add(this);
	}
	protected void initTexture(){
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuf.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
	}
	public void move(){
		
	}
public void draw(GL10 gl) {		
		//gl.glPushMatrix();
		//Log.d("Gun", Integer.toString(World.WIDTH));
		//gl.glLoadIdentity();
		
		gl.glTranslatef(x, y, z);
		gl.glRotatef(direction, 0, 0, 1);
		//GLES20 g=(GLES20)gl;
		//g.glVertexAttrib2f(0, x ,y);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		//Point to our buffers
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
		
		//Draw the vertices as triangle strip
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		
		//Disable the client state before leaving
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glLoadIdentity();
		gl.glTranslatef(World.camx, World.camy, -5f);
		//gl.glPopMatrix();
		
	}

	
	public void loadGLTexture(GL10 gl, Context context,int resource) {
		//Get the texture from the Android resource directory
		r=resource;
		InputStream is = context.getResources().openRawResource(resource);
		Bitmap bitmap = null;
		
		try {
			//BitmapFactory is an Android graphics utility for images
			bitmap = BitmapFactory.decodeStream(is);

		} finally {
			//Always clear and close
			try {
				is.close();
				is = null;
			} catch (IOException e) {
			}
		}
		//bitmap=Bitmap.createBitmap(bitmap, 0, 0, 128, 128);
		//Log.d("Debug", ""+bitmap.getConfig());
		
		bitmap=Bitmap.createScaledBitmap(bitmap, 512, 512, true );
		
		//bitmap=Bitmap.createBitmap(256, 256, bitmap.getConfig());
		
		
		//bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.triangle);
		//Generate there texture pointer
		gl.glGenTextures(1, textures, 0);

		//Create Linear Filtered Texture and bind it to texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		//Clean up
		bitmap.recycle();
		
	}
	
}

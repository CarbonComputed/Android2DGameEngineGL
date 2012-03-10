package com.kevin.gungame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Label extends View {
	private String text;
	private int color;
	public int x;
	public int y;
	public Label(Context context) {
		super(context);
		setBackgroundColor(0);
		// TODO Auto-generated constructor stub
	}
	public Label(Context context,String s,int x,int y) {
		super(context);
		setBackgroundColor(0);
		text=s;
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(Canvas canvas){
		
		Paint paint=new Paint();
		paint.setColor(color);
		paint.setTextSize(10);
		canvas.drawText(text, 0, text.length(), x, y, paint);
	}
	public void setText(String s){
		text=s;
	}
	public void setColor(int c){
		color=c;
	}

}

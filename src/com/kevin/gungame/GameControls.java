package com.kevin.gungame;

import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameControls implements OnTouchListener{

	public float initx = 70;
	public float inity = World.sheight-60;
	public double down=0;
	public Point _touchingPoint = new Point(70,World.sheight-60);
	public Point _pointerPosition = new Point(220,150);
	private Boolean _draggingF1 = false;
	private Boolean twofingers = false;
	public Boolean pressed=false;
	public Boolean shooting=false;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
			

				
			
		update(event);
		return true;
	}

	private MotionEvent lastEvent;
	public void update(MotionEvent event){
		
		if (event == null && lastEvent == null)
		{
			return;
		}else if(event == null && lastEvent != null){
			event = lastEvent;
		}else{
			lastEvent = event;
		}
		//drag drop 
		
		int tempx=(int)event.getX();
		int tempy=(int)event.getY();
		int tempx2=(int)event.getX(1);
		int tempy2=(int)event.getY(1);
	//	Log.d("test",""+tempx+" , "+tempx2);
		
		
		if ( event.getAction()==MotionEvent.ACTION_DOWN){
			_draggingF1 = true;
		}else if ( event.getAction() == MotionEvent.ACTION_UP){
			_draggingF1 = false;
		}
		if(event.getPointerCount()>=2){
			twofingers=true;
		}else if(event.getPointerCount()<2){
			twofingers=false;
		}
		Log.d("test2", ""+tempx+"+"+tempx2+"+"+twofingers+"+"+_draggingF1+"+"+event.getAction());
		if ( tempx>=150&&tempx2<150&&_draggingF1==true&&twofingers==true){
			// get the pos
			
			_touchingPoint.x = tempx2;
			_touchingPoint.y = tempy2;
			if( _touchingPoint.x < 45){
				_touchingPoint.x = 45;
			}
			if ( _touchingPoint.x > 95){
				_touchingPoint.x = 95;
			}
			if (_touchingPoint.y < (World.sheight-60)-25){
				_touchingPoint.y = (World.sheight-60)-25;
			}
			if ( _touchingPoint.y > (World.sheight-60)+25){
				_touchingPoint.y = (World.sheight-60)+25;
			}
			World.player.move(this);
			World.player.shoot();
		}else if (tempx<150&&twofingers==false&&_draggingF1==true&&event.getAction()!=6&&event.getAction()!=262)
		{
			// Snap back to center when the joystick is released
			_touchingPoint.x = tempx;
			_touchingPoint.y = tempy;
			if( _touchingPoint.x < 45){
				_touchingPoint.x = 45;
			}
			if ( _touchingPoint.x > 95){
				_touchingPoint.x = 95;
			}
			if (_touchingPoint.y < (World.sheight-60)-25){
				_touchingPoint.y = (World.sheight-60)-25;
			}
			if ( _touchingPoint.y > (World.sheight-60)+25){
				_touchingPoint.y = (World.sheight-60)+25;
			}
			World.player.move(this);
			//_touchingPoint.x = (int) initx;
			//_touchingPoint.y = (int) inity;
			//shaft.alpha = 0;
		}
		else if(tempx>=150&&twofingers==false&&_draggingF1==true){
			_touchingPoint.x = (int) initx;
			_touchingPoint.y = (int) inity;
			World.player.move();
			World.player.shoot();
		}else if (tempx<150&&tempx2>=150&&twofingers==true&&_draggingF1==true&&event.getAction()!=6&&event.getAction()!=262)
		{
			// Snap back to center when the joystick is released
			_touchingPoint.x = tempx;
			_touchingPoint.y = tempy;
			if( _touchingPoint.x < 45){
				_touchingPoint.x = 45;
			}
			if ( _touchingPoint.x > 95){
				_touchingPoint.x = 95;
			}
			if (_touchingPoint.y < (World.sheight-60)-25){
				_touchingPoint.y = (World.sheight-60)-25;
			}
			if ( _touchingPoint.y > (World.sheight-60)+25){
				_touchingPoint.y = (World.sheight-60)+25;
			}
			World.player.move(this);
			World.player.shoot();
			//shaft.alpha = 0;
		}
		else if(event.getAction()==6||event.getAction()==262){
			_touchingPoint.x = (int) initx;
			_touchingPoint.y = (int) inity;
			World.player.move(this);
			World.player.shoot();
		}
		else{
			_touchingPoint.x = (int) initx;
			_touchingPoint.y = (int) inity;
			World.player.move(this);
		}
		
	try {
		World.gameThread.sleep(0);
		Thread.sleep(0);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}

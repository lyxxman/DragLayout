package com.ly.baseproject.draglayout.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class CustScrollView extends ScrollView {
	private static final int TOUCH_IDLE = 0;
	private static final int TOUCH_INNER_CONSIME = 1; // touch事件由ScrollView内部消费
	private static final int TOUCH_DRAG_LAYOUT = 2; // touch事件由上层的DragLayout去消费

	boolean isAtBottom; // 按下的时候是否在底部
	private int mTouchSlop = 4; // 判定为滑动的阈值，单位是像素
	private int scrollMode;
	private float downY;
	private float downX;
	public CustScrollView(Context arg0) {
		this(arg0, null);
	}

	public CustScrollView(Context arg0, AttributeSet arg1) {
		this(arg0, arg1, 0);
	}

	public CustScrollView(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
		ViewConfiguration configuration = ViewConfiguration.get(getContext());
		mTouchSlop = configuration.getScaledTouchSlop();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			downY = ev.getRawY();
			downX = ev.getX();
			isAtBottom = isAtBottom();
			scrollMode = TOUCH_IDLE;
			getParent().requestDisallowInterceptTouchEvent(true);
		} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			if (scrollMode == TOUCH_IDLE) {
				float yOffset = downY - ev.getRawY(); //移动偏移量
				float xOffset = downX -ev.getRawX();
				float yDistance = Math.abs(yOffset); //
				float xDistance = Math.abs(xOffset);
//				Log.i("test:","xDistance="+xDistance+" yDistance="+yDistance);
				if(xDistance>yDistance){ //横向偏移量大于竖向偏移量
					scrollMode = TOUCH_DRAG_LAYOUT;
					getParent().requestDisallowInterceptTouchEvent(false);
					return true;
				}else if(xDistance<=yDistance&&!isAtBottom){ //竖向滑动且在scrollview的底部
					scrollMode = TOUCH_INNER_CONSIME;
					getParent().requestDisallowInterceptTouchEvent(true);
				}else if(xDistance<=yDistance&&yOffset<0&&isAtBottom) { //竖线滑动，在底部，且是向上滑动
					scrollMode = TOUCH_INNER_CONSIME;
					getParent().requestDisallowInterceptTouchEvent(true);
				}else { //其它滑动给父控件处理
					scrollMode = TOUCH_DRAG_LAYOUT;
					getParent().requestDisallowInterceptTouchEvent(false);
					return true;
				}
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (scrollMode == TOUCH_DRAG_LAYOUT) {
			return false;
		}
		return super.onTouchEvent(ev);
	}

	private boolean isAtBottom() {
		return getScrollY() + getMeasuredHeight() >= computeVerticalScrollRange() - 2;
	}

}
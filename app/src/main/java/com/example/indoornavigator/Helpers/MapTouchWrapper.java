package com.example.indoornavigator.Helpers;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MapTouchWrapper extends FrameLayout {
    private int touchSlop = 0;
    private Point down = null;
    private Point listener = null;

    public MapTouchWrapper(@NonNull Context context) {
        super(context);
    }

    public MapTouchWrapper(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public MapTouchWrapper(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    public MapTouchWrapper(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setup(Context context) {
        ViewConfiguration vc = ViewConfiguration.get(context);
        touchSlop = vc.getScaledTouchSlop();
    }

    void setup(Point listener){
        this.listener = listener;
    }

    private double distance(Point p1, Point p2) {
        double xDiff = (double) p1.x - p2.x;
        double yDiff = (double) p1.y - p2.y;
        return Math.sqrt(xDiff*xDiff - yDiff*yDiff);
    }


    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (listener == null) {
            return false;
        }

        int x = (int) event.getX();
        int y = (int) event.getY();
        Point tapped = new Point(x,y);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: down = tapped; break;
            case MotionEvent.ACTION_MOVE: if (down!=null && distance(down, tapped)>=touchSlop) {
                    down = null;
                }break;
            case MotionEvent.ACTION_UP: if (down != null && distance(down, tapped) < touchSlop) {
                return true;
            }
        }
        return false;
    }
}

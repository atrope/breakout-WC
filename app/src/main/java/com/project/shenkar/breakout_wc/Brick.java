package com.project.shenkar.breakout_wc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Brick extends VisibleGameObject {
    private boolean isVisible;
    private int color;

    public Brick(){
        color = Color.WHITE;
        isVisible = true;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public void setColor(int newColor){
        color = newColor;
    }

    public boolean getVisibility(){
        return isVisible;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(color);
        if(this.isVisible) super.draw(canvas, paint);
    }

    @Override
    public void reset(){
        isVisible = true;
    }
}

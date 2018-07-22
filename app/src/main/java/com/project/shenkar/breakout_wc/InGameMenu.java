package com.project.shenkar.breakout_wc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.Vector;

public class InGameMenu {
    private int screenX, screenY;
    public enum InMenuResult { Exit, Resume, Restart }

    public class MenuItem{
        public Rect rect;
        public InMenuResult action;
        public String text;
    }
    private Vector<MenuItem> _inMenuItems;

    InGameMenu(){
        screenX = BreakoutGame.BreakoutView.screenX;
        screenY = BreakoutGame.BreakoutView.screenY;
        _inMenuItems = new Vector<>();

        MenuItem resumeButton = new MenuItem();
        resumeButton.rect = new Rect(screenY/2,screenX / 10 ,screenY/2 + (screenY / 8)*4,screenX/10+120);
        resumeButton.text = "Resume";
        resumeButton.action = InMenuResult.Resume;
        MenuItem restartButton = new MenuItem();
        restartButton.rect = new Rect(screenY/2,screenX / 6+60,screenY/2 + (screenY / 8)*4,screenX/6+180);
        restartButton.text = "Restart";
        restartButton.action = InMenuResult.Restart;
        MenuItem exitButton = new MenuItem();
        exitButton.rect = new Rect(screenY/2,screenX / 6+240,screenY/2 + (screenY / 8)*4,screenX/6+360);
        exitButton.text = "Exit";
        exitButton.action = InMenuResult.Exit;
        _inMenuItems.addElement(resumeButton);
        _inMenuItems.addElement(restartButton);
        _inMenuItems.addElement(exitButton);
    }

    public Vector<MenuItem> getMenuItems(){
        return _inMenuItems;
    }

    public void show(SurfaceHolder holder, Canvas canvas, Paint paint){
        if(holder.getSurface().isValid()){
            Vector<MenuItem> items = getMenuItems();
            paint.setColor(Color.WHITE);//FONT
            paint.setTextSize(screenY / 8);
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLUE);//BG
            for (MenuItem item : items) canvas.drawText(item.text, item.rect.left, item.rect.bottom, paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }
}

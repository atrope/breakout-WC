package com.project.shenkar.breakout_wc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Paddle extends VisibleGameObject {

    public enum MovementState{Right, Left, Stopped}

    private float velocity, initialVelocity;
    private MovementState movementState;
    private int screenX, screenY;

    Paddle(){
        initialVelocity = 680.0f;
        velocity = initialVelocity;
        setSize(130, 20);
        movementState = MovementState.Stopped;
        screenX = BreakoutGame.BreakoutView.screenX;
        screenY = BreakoutGame.BreakoutView.screenY;
    }

    @Override
    public void update(long fps, long elapsedTime){
        PointF loc = getPosition();
        // collision with left and right walls
        if(loc.x < 0.0f) velocity = (movementState == MovementState.Left)? 0 : initialVelocity;
        if(loc. x + getWidth() > screenX) velocity = (movementState == MovementState.Right)? 0 : initialVelocity;

        if(movementState == MovementState.Right) loc.x = loc.x + velocity / fps;
        else if(movementState == MovementState.Left) loc.x = loc.x - velocity / fps;
        setPosition(loc.x, loc.y);
    }

    public void setMovementState(MovementState state){
        movementState = state;
    }

    public MovementState getMovementState(){
        return movementState;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.argb(255, 255, 255, 255));
        super.draw(canvas, paint);
    }

    public void reset(){
        super.reset();
        velocity = initialVelocity;
        movementState = MovementState.Stopped;
    }
}
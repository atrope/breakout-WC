package com.project.shenkar.breakout_wc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class Ball extends VisibleGameObject{
    private float _velocity, initialVelocity;
    private float _angle;
    private int screenX, screenY;
    private long elapsedTimeSinceStart;
    private final long minCollisionInterval;
    private long collisionTime;
    private Bitmap bitmap;
    Ball(Context context){
        setSize(30, 30);
        initialVelocity = 300;
        _velocity = initialVelocity;
        Random generator = new Random();
        _angle = generator.nextInt(360);
        if(_angle >= 70.0f && _angle <= 110.0f) _angle += 40;
        if(_angle >= 250.0f && _angle <= 290.0f) _angle -= 40;
        screenX = BreakoutGame.BreakoutView.screenX;
        screenY = BreakoutGame.BreakoutView.screenY;
        elapsedTimeSinceStart = 0;
        minCollisionInterval = 200;
        collisionTime = 0;
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.raw.ball);

    }

    /*
    /*Update ball location, check for collisions and deal with them
     */
    @Override
    public void update(long fps, long elapsedTime){
        elapsedTimeSinceStart += elapsedTime;
        if(elapsedTimeSinceStart < 3000) return;
        float xVelocity = _velocity * linearVelocityX(_angle);
        float yVelocity = _velocity * linearVelocityY(_angle);
        RectF rect = getBoundingRect();
        float left = rect.left + (xVelocity / fps);
        float top = rect.top + (yVelocity / fps);
        float right = rect.right + (xVelocity / fps);
        float bottom = rect.bottom + (yVelocity / fps);
        // collision with right and left wall
        if(left <= 0 || right >= screenX){
            _angle = 360.0f - _angle;
            xVelocity = -xVelocity;
            if(_angle >= 70.0f && _angle <= 110.0f) _angle += 40;
            if(_angle >= 250.0f && _angle <= 290.0f) _angle -= 40;
        }
        // collision with top
        if(top <= 0){
            yVelocity = -yVelocity;
            _angle = 180.0f - _angle;
            //Fixing angles
            if(_angle < 0.0f) _angle += 360.0f;
            if(_angle > 360.0f) _angle = _angle - 360.0f;
            //TODO: play sound;
        }
        // collision with bottom
        if(bottom >= screenY){
            BreakoutGame.BreakoutView.getScoreBoard().decrementLife();
            if(BreakoutGame.BreakoutView.checkVictory()) return;
            //TODO: play sound;
            reset();
        }

        // collision with bricks
        int numBricks = BreakoutGame.BreakoutView.getNumberOfBricks();
        for(int i=0; i<numBricks; i++){
            String name = "Brick"+ Integer.toString(i);
            Brick brick = (Brick) BreakoutGame.BreakoutView.getObjectManager().get(name);
            if(brick.getVisibility()){
                if(RectF.intersects(brick.getBoundingRect(), getBoundingRect())){
                    brick.setInvisible();
                    yVelocity = -yVelocity;
                    _angle = 180.0f - _angle;
                    //Fixing angles
                    if(_angle < 0.0f)  _angle += 360.0f;
                    if(_angle > 360.0f)_angle = _angle - 360.0f;
                    BreakoutGame.BreakoutView.getScoreBoard().incrementScore();
                    if(BreakoutGame.BreakoutView.checkVictory()) return;
                    //TODO: play sound;
                }
            }
        }
        // collision with paddle
        Paddle paddle = (Paddle) BreakoutGame.BreakoutView.getObjectManager().get("Paddle");

        collisionTime += elapsedTime;
        if(paddle != null){
            RectF paddleRect = paddle.getBoundingRect();
            if(RectF.intersects(paddleRect, getBoundingRect()) &&
                    collisionTime > minCollisionInterval){
                yVelocity = -yVelocity;
                // collision at edge
                if(paddleRect.top + paddle.getHeight()/2 < getBoundingRect().bottom) _angle = 360.0f - _angle;
                    // collision at top
                else _angle = 180.0f - _angle;
                //Fixing angles
                if(_angle < 0.0f) _angle += 360.0f;
                if(_angle >= 360.0f) _angle = _angle - 360.0f;
                clearObstacleY(paddle.getBoundingRect().top - 2);
                // adding spin to ball
                if(paddle.getMovementState() == Paddle.MovementState.Left){
                    _angle -= 30.0f;
                    if(_angle < 0.0f) _angle += 360.0f;
                }
                else if(paddle.getMovementState() == Paddle.MovementState.Right){
                    _angle += 30.0f;
                    if(_angle > 360.0f) _angle = _angle - 360.0f;
                }
                //TODO: play sound
                _velocity += 20.0f;
                collisionTime = 0;
            }
        }

        rect.left = rect.left + (xVelocity / fps);
        rect.top = rect.top + (yVelocity / fps);
        rect.right = rect.left + getWidth();
        rect.bottom = rect.top + getHeight();

        setPosition(rect.left, rect.top);
    }

    /*
    /*Get Velocity  for Axis X
     */
    private float linearVelocityX(float angle){
        angle -= 90;
        if(angle < 0) angle += 360;
        return (float)Math.cos(angle * 3.14159 / 180.0);
    }
    /*
    /*Get Velocity  for Axis Y
     */
    private float linearVelocityY(float angle){
        angle -= 90;
        if(angle < 0) angle += 360;
        return (float)Math.sin(angle * 3.14159 / 180.0);
    }

    public void reset(){
        super.reset();
        _velocity = initialVelocity;
        elapsedTimeSinceStart = 0;
        Random generator = new Random();
        _angle = generator.nextInt(360);
        if(_angle >= 70.0f && _angle <= 110.0f)_angle += 40;
        if(_angle >= 250.0f && _angle <= 290.0f) _angle -= 40;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawBitmap(bitmap, getPosition().x, getPosition().y, paint);
    }

    public void clearObstacleY(float y){
        RectF rect = getBoundingRect();
        rect.bottom = y;
        rect.top = y - getHeight();
        setBoundingRect(rect);
    }
}
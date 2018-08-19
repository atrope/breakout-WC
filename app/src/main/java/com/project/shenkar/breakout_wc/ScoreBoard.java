package com.project.shenkar.breakout_wc;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class ScoreBoard {
    private int score;
    private int lives;
    private int level;
    private int maxLevel;
    private int levelScore;
    private int maxScore;
    private int screenY, screenX;
    enum GameResult{Win, Lose, Playing,LevelComplete}
    private GameResult result;

    ScoreBoard(int numBricks){
        score = 0;
        levelScore=0;
        lives = 3;
        level = 1;
        maxLevel = Countries.CountriesEnum.values().length;
        maxScore = numBricks * 10;
        screenX = BreakoutGame.BreakoutView.screenX;
        screenY = BreakoutGame.BreakoutView.screenY;
        result = GameResult.Playing;
    }

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.argb(255, 255, 255, 255));
        paint.setTextSize(25);
        String text = "Score: " + score;
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        canvas.drawText("Score: " + score, 10, bounds.height() + 3, paint);
        canvas.drawText("Level: " + Countries.CountriesEnum.values()[getLevel()].name(), 220, bounds.height() + 3, paint);

        text = "Lives: " + lives;
        paint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, screenX - bounds.width() - 15, bounds.height() + 3, paint);

        if(result == GameResult.Win){
            paint.setTextSize(screenY / 6);
            text = "YOU HAVE WON!";
            paint.getTextBounds(text, 0, text.length(), bounds);

            float height = paint.descent() - paint.ascent();
            float offset = (height / 2) - paint.descent();
            float xPos = (canvas.getWidth() / 2) - (bounds.width() / 2);
            float yPos = (canvas.getHeight() / 2) + offset;
            canvas.drawText(text, xPos, yPos, paint);


            paint.setTextSize(screenY / 11);
            text = "Press any key to continue";
            paint.getTextBounds(text, 0, text.length(), bounds);

            xPos = (canvas.getWidth() / 2) - (bounds.width() / 2);
            yPos = (canvas.getHeight() * 3 / 4) + offset;
            canvas.drawText(text, xPos, yPos, paint);

        }

        if(result == GameResult.Lose){
            paint.setTextSize(screenY / 6);
            text = "YOU HAVE LOST!";
            paint.getTextBounds(text, 0, text.length(), bounds);

            float height = paint.descent() - paint.ascent();
            float offset = (height / 2) - paint.descent();
            float xPos = (canvas.getWidth() / 2) - (bounds.width() / 2);
            float yPos = (canvas.getHeight() / 2) + offset;
            canvas.drawText(text, xPos, yPos, paint);
            paint.setTextSize(screenY / 11);
            text = "Press any key to continue";
            paint.getTextBounds(text, 0, text.length(), bounds);

            xPos = (canvas.getWidth() / 2) - (bounds.width() / 2);
            yPos = (canvas.getHeight() * 3 / 4) + offset;
            canvas.drawText(text, xPos, yPos, paint);
        }
    }

    public GameResult getGameResult(){
        return result;
    }
    public int getLevel(){
        return level-1;
    }

    public void incrementScore(){
        score += 10;
        levelScore+=10;
        //    if(levelScore>0){ // DEBUG one hit change level
         if(levelScore % maxScore == 0 && levelScore>0){
                if (level == maxLevel) result = GameResult.Win;
            else {
                level++;
                levelScore =0;
                result = GameResult.LevelComplete;
            }
                //TODO play sound
        }
    }

    public void decrementLife(){
        if(--lives <= 0){
            result = GameResult.Lose;
            //TODO play sound
        }
    }

    public void resetScore(){
        score = 0;
        levelScore = 0;
        lives = 3;
        level = 1;
        result = GameResult.Playing;
    }
}


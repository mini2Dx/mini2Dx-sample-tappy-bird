package org.mini2dx.Tappybird;

import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.Tappybird.TappyBirdGame.FLYING_SPEED;

public class Background extends Hazards {

    BackgroundTexture backgroundTexture;

    private static float BACKGROUND_SPEED = 0.3f;
    private float width;

    public Background(BackgroundTexture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
        this.width = backgroundTexture.background.getWidth();
    }

    @Override
    void update() {
        point.preUpdate();
        point.set(point.getX() - FLYING_SPEED * BACKGROUND_SPEED, point.getY());

        //This resets the backgrounds position to it's original once it has left the screen.
        if (point.getX() < -width + 5) {
            point.setX(width - 5);
        }
    }

    void render(Graphics g) {
        g.drawTexture(backgroundTexture.background, point.getX(), point.getY() + 15f);
    }
}


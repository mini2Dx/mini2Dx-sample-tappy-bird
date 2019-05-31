package org.mini2dx.Tappybird;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.Tappybird.TappyBirdGame.flyingspeed;

public class Background extends Hazards {

    BackgroundModel backgroundModel;

    private static float BACKGROUND_SPEED = 0.3f;
    private float width;

    public Background(BackgroundModel backgroundModel) {
        this.backgroundModel = backgroundModel;
        this.width = backgroundModel.background.getWidth();
    }

    @Override
    void update() {
        point.preUpdate();
        point.set(point.getX() - flyingspeed * BACKGROUND_SPEED, point.getY());

        //This resets the backgrounds position to it's original once it has left the screen.
        if (point.getX() < -width + 5) {
            point.setX(width - 5);
        }
    }

    void render(Graphics g) {
        g.drawTexture(backgroundModel.background, point.getX(), point.getY() + 15f);
    }
}


package org.mini2dx.Tappybird;

import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.Tappybird.TappyBirdGame.GAME_HEIGHT;
import static org.mini2dx.Tappybird.TappyBirdGame.GAME_WIDTH;

public class TopBottomEdge extends Hazards {

    TopBottomEdgeTexture topBottomEdgeTexture;
    private float groundTextureHeight;

    public TopBottomEdge(TopBottomEdgeTexture topBottomEdgeModel) {
        this.topBottomEdgeTexture = topBottomEdgeModel;
        groundTextureHeight = topBottomEdgeModel.groundTexture.getHeight();
    }

    @Override
    void update() {
        super.update();
        if (point.getX() < -GAME_WIDTH-1) {
            point.setX(GAME_WIDTH-1);
        }
    }

    void render(Graphics g) {
        g.drawTexture(topBottomEdgeTexture.groundTexture, point.getX(),
                GAME_HEIGHT - topBottomEdgeTexture.groundTexture.getHeight());
        g.drawTexture(topBottomEdgeTexture.ceilingTexture, point.getX(), 0f);
    }


    float getGroundTextureHeight() {
        return groundTextureHeight;
    }
}

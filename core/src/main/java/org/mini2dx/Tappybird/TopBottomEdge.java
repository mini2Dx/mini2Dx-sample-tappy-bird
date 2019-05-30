package org.mini2dx.Tappybird;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.Tappybird.TappyBirdGame.gameHeight;

public class TopBottomEdge extends Hazards {

    /*
    private static final String groundTexturePath = "groundDirt.png";
    private static final String ceilingTexturePath = "ceilingDirt.png";

    Texture groundTexture = new Texture(groundTexturePath);
    Texture ceilingTexture = new Texture(ceilingTexturePath);

     */

    TopBottomEdgeModel topBottomEdgeModel;
    private float groundTextureHeight;

    public TopBottomEdge(TopBottomEdgeModel topBottomEdgeModel) {
        this.topBottomEdgeModel = topBottomEdgeModel;
        groundTextureHeight = topBottomEdgeModel.groundTexture.getHeight();
    }

    @Override
    void update() {
        super.update();
        if (point.getX() < -799f) {
            point.setX(799f);
        }
    }

    void render(Graphics g) {
        g.drawTexture(topBottomEdgeModel.groundTexture, point.getX(), gameHeight - topBottomEdgeModel.groundTexture.getHeight());
        g.drawTexture(topBottomEdgeModel.ceilingTexture, point.getX(), 0f);
    }


    float getGroundTextureHeight() {
        return groundTextureHeight;
    }
}

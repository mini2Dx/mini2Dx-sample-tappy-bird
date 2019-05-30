package org.mini2dx.Tappybird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;


public class Pillars extends Hazards {

    float hazardYGap;
    float pillarHeight;
    float pillarWidth;
    float halfPillarWidth;

    private float collisionRectHeight;
    private float collisionRectWidth = 10f;

    CollisionBox collisionRectTop, collisionRectBottom;

    PillarModel pillarModel;

    public Pillars(PillarModel pillarModel) {
        this.pillarModel = pillarModel;
        pillarHeight = pillarModel.pillarUp.getHeight();
        pillarWidth = pillarModel.pillarUp.getWidth();
        halfPillarWidth = pillarWidth / 2.0f;
        collisionRectHeight = pillarHeight;
    }

    void generateHazardAtPos(float xPos, float yPos, float gap) {
        super.generateHazardAtPos(xPos, yPos);
        hazardYGap = gap;
        collisionRectTop = generatePillarCollisionRectAt(xPos + halfPillarWidth, yPos);
        collisionRectBottom = generatePillarCollisionRectAt(xPos + halfPillarWidth, yPos + gap + pillarHeight);
    }

    CollisionBox generatePillarCollisionRectAt(float xPos, float yPos) {
        //TODO this is bad, refactor once working.
        return new CollisionBox(xPos, yPos, collisionRectWidth, collisionRectHeight);
    }

    //Test function
    void DrawPillarCollisionBoxes(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(collisionRectTop.getX(), collisionRectTop.getY(),
                collisionRectTop.getWidth(), collisionRectTop.getHeight());
        g.fillRect(collisionRectBottom.getX(), collisionRectBottom.getY(),
                collisionRectBottom.getWidth(), collisionRectBottom.getHeight());
    }

    void update() {
        super.update();
        collisionRectTop.setX(point.getX() + halfPillarWidth);
        collisionRectBottom.setX(point.getX() + halfPillarWidth);
    }


    void render(Graphics g) {
        g.drawTexture(pillarModel.pillarDown, point.getX(), point.getY());
        g.drawTexture(pillarModel.pillarUp, point.getX(), point.getY() + pillarHeight + hazardYGap);
        //DrawPillarCollisionBoxes(g);
    }
}

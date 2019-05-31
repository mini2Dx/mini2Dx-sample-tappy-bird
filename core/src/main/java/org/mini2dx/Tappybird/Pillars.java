package org.mini2dx.Tappybird;

import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.Tappybird.Player.PLAYER_X;


public class Pillars extends Hazards {

    private boolean isRotating;

    float hazardYGap;
    float pillarHeight;
    float pillarWidth;
    float halfPillarWidth;

    private float collisionRectHeight;
    private float collisionRectWidth = 10f;

    private float[] topCollisionVertices, bottomCollisionVertices;

    CollisionBox collisionRectTop, collisionRectBottom;

    PillarTexture pillarTexture;

    public Pillars(PillarTexture pillarTexture, boolean isRotating) {
        this.pillarTexture = pillarTexture;
        pillarHeight = pillarTexture.pillarUp.getHeight();
        pillarWidth = pillarTexture.pillarUp.getWidth();
        halfPillarWidth = pillarWidth / 2.0f;
        collisionRectHeight = pillarHeight;
        this.isRotating = isRotating;
    }

    void generateHazardAtPos(float xPos, float yPos, float gap) {
        super.generateHazardAtPos(xPos, yPos);
        hazardYGap = gap;
        if (isRotating) {
            collisionRectTop = generatePillarCollisionRectAt(xPos + halfPillarWidth - pillarWidth / 8, yPos);
            collisionRectTop.setRotationAround(collisionRectTop.getCenterX(), collisionRectTop.getCenterY(), -10);
            collisionRectBottom = generatePillarCollisionRectAt(xPos + halfPillarWidth - pillarWidth / 8, yPos + gap + pillarHeight);
            collisionRectBottom.setRotationAround(collisionRectBottom.getCenterX(), collisionRectBottom.getCenterY(), 10);
        } else {
            collisionRectTop = generatePillarCollisionRectAt(xPos + halfPillarWidth + collisionRectWidth/2, yPos);
            collisionRectBottom = generatePillarCollisionRectAt(xPos + halfPillarWidth + collisionRectWidth/2, yPos + gap + pillarHeight);
        }
    }

    CollisionBox generatePillarCollisionRectAt(float xPos, float yPos) {
        //TODO this is bad, refactor once working.
        return new CollisionBox(xPos, yPos, collisionRectWidth, collisionRectHeight);
    }

    //Test function
    void DrawPillarCollisionBoxes(Graphics g) {
        g.setColor(Color.RED);

        topCollisionVertices = collisionRectTop.getVertices();
        bottomCollisionVertices = collisionRectBottom.getVertices();

        for(int i=0; i<7; i=i+2){
            g.drawLineSegment(topCollisionVertices[i],topCollisionVertices[i+1],
                    topCollisionVertices[(i+2) % 8],topCollisionVertices[(i+3) % 8]);
            g.drawLineSegment(bottomCollisionVertices[i],bottomCollisionVertices[i+1],
                    bottomCollisionVertices[(i+2) % 8],bottomCollisionVertices[(i+3) % 8]);
        }
    }

    private boolean hasBeenCalled = false;
    private boolean counted = false;

    void update(float speed) {
        super.update();
        collisionRectTop.preUpdate();
        collisionRectBottom.preUpdate();

        if(!hasBeenCalled) {
            collisionRectTop.moveTowards(-100, collisionRectTop.getY(), speed);
            collisionRectBottom.moveTowards(-100, collisionRectBottom.getY(), speed);

            /* This code does the same as move but sets the location each frame

            collisionRectTop.setX(point.getX() + halfPillarWidth - pillarWidth/4);
            collisionRectTop.setRotationAround(collisionRectTop.getCenterX(),collisionRectTop.getCenterY(),-20);
            collisionRectBottom.setX(point.getX() + halfPillarWidth);
            collisionRectBottom.setRotationAround(collisionRectBottom.getCenterX(),collisionRectBottom.getCenterY(),20);

             */
        }

        if(!counted && getHazardXPos()<PLAYER_X){
            TappyBirdGame.setScore(TappyBirdGame.getScore()+1);
            counted = true;
        }
    }


    void render(Graphics g, boolean isTesting) {
        g.drawTexture(pillarTexture.pillarDown, point.getX(), point.getY());
        g.drawTexture(pillarTexture.pillarUp, point.getX(), point.getY() + pillarHeight + hazardYGap);
        if(isTesting) {
            DrawPillarCollisionBoxes(g);
        }
    }
}

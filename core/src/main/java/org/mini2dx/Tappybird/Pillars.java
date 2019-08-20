/*******************************************************************************
 * Copyright 2019 Viridian Software Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.mini2dx.Tappybird;

import org.mini2Dx.core.Graphics;
import org.mini2Dx.core.collision.CollisionBox;
import org.mini2Dx.core.graphics.Colors;

import static org.mini2dx.Tappybird.Player.PLAYER_X;


public class Pillars extends Hazards {

    private boolean isRotating;
    private boolean hasBeenCalled = false;
    private boolean counted = false;

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
        g.setColor(Colors.RED());

        topCollisionVertices = collisionRectTop.getVertices();
        bottomCollisionVertices = collisionRectBottom.getVertices();

        for(int i=0; i<7; i=i+2){
            g.drawLineSegment(topCollisionVertices[i],topCollisionVertices[i+1],
                    topCollisionVertices[(i+2) % 8],topCollisionVertices[(i+3) % 8]);
            g.drawLineSegment(bottomCollisionVertices[i],bottomCollisionVertices[i+1],
                    bottomCollisionVertices[(i+2) % 8],bottomCollisionVertices[(i+3) % 8]);
        }
    }

    void update(float speed) {
        super.update();
        collisionRectTop.preUpdate();
        collisionRectBottom.preUpdate();

        if(!hasBeenCalled) {
            collisionRectTop.moveTowards(-100, collisionRectTop.getY(), speed);
            collisionRectBottom.moveTowards(-100, collisionRectBottom.getY(), speed);
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

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

import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import static org.mini2dx.Tappybird.TappyBirdGame.GRAVITY;

public class Player {

    public static float PLAYER_X = 100;
    private static float JUMP_ACCEL = -12.0f;

    private float playerTextureHeight, playerTextureWidth;
    private boolean isRotating, isTesting;

    private float playerY;
    private float playerYAccel = 0.0f;
    private float playerRotation;
    private float[] playerCollisionVertices;

    CollisionBox playerCollisionBox;

    private PlayerTexture playerTexture;


    public Player(PlayerTexture playerTexture, boolean isRotating, boolean isTesting) {
        this.playerTexture = playerTexture;
        playerTextureHeight = playerTexture.playerSpriteSheet.getSprite(0).getHeight();
        playerTextureWidth = playerTexture.playerSpriteSheet.getSprite(0).getWidth();
        playerCollisionBox = generateCollisionRectAt(PLAYER_X, playerY);
        playerY = 250-playerTextureHeight/2;
        this.isRotating = isRotating;
        this.isTesting = isTesting;
        playerTexture.playerAnimation.setLooping(true);
    }

    void settingPlayerJumping() {
        playerYAccel = JUMP_ACCEL;
    }

    void calcPlayerYPos() {
        playerY += playerYAccel;
        playerCollisionBox.setY(playerY);
    }

    CollisionBox generateCollisionRectAt(float xPos, float yPos) {
        int tempX = (int) xPos;
        int tempY = (int) yPos;
        return new CollisionBox(tempX, tempY, playerTextureWidth, playerTextureHeight);
    }

    void DrawPlayerCollisionBox(Graphics g) {
        g.setColor(Color.RED);
        playerCollisionVertices = playerCollisionBox.getVertices();

        for(int i=0; i<7; i=i+2){
            g.drawLineSegment(playerCollisionVertices[i],playerCollisionVertices[i+1],
                    playerCollisionVertices[(i+2) % 8],playerCollisionVertices[(i+3) % 8]);
        }
    }


    void update(boolean isJumping, float delta) {

        playerCollisionBox.preUpdate();

        playerTexture.playerAnimation.update(delta);


        if (isJumping) {
            settingPlayerJumping();
        } else {
            playerYAccel += GRAVITY;
        }

        if(isRotating) {
            if (playerYAccel > 0) {
                playerRotation = Math.min(playerYAccel, 45f);
                playerCollisionBox.setRotationAround(PLAYER_X, playerY + playerTextureWidth, playerRotation);
            } else if (playerYAccel < 0) {
                playerRotation = Math.max(playerYAccel, -45f);
                playerCollisionBox.setRotationAround(PLAYER_X, playerY + playerTextureWidth, playerRotation);
            } else {
                playerRotation = 0;
                playerCollisionBox.setRotationAround(PLAYER_X, playerY + playerTextureWidth, playerRotation);
            }
        }

        calcPlayerYPos();
    }

    void render(Graphics g) {
        //TODO Give plane tilt when flying up or down.
        if (getPlayerYAccel() > 0) {

        } else if (getPlayerYAccel() > 0) {

        }

        playerTexture.playerAnimation.setRotation(playerRotation);
        playerTexture.playerAnimation.draw(g,PLAYER_X,getPlayerY());

        if(isTesting){
            DrawPlayerCollisionBox(g);
        }

    }

    float getPlayerY() {
        return playerY;
    }

    float getPlayerYAccel() {
        return playerYAccel;
    }

    float getPlayerTextureHeight() {
        return playerTextureHeight;
    }

}

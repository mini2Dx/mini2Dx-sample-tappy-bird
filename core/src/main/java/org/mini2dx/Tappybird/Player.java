package org.mini2dx.Tappybird;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

import static org.mini2dx.Tappybird.TappyBirdGame.GRAVITY;

public class Player {

    private static float playerX = 100;
    private static float jumpAccel = -10.0f;

    private float playerTextureHeight;

    private float playerY = 250;
    private float playerYAccel = 0.0f;

    CollisionBox playerCollisionBox;

    private PlayerModel playerModel;

    public Player(PlayerModel playerModel) {
        this.playerModel = playerModel;
        playerTextureHeight = playerModel.playerTexture.getHeight();
        playerCollisionBox = generateCollisionRectAt(playerX, playerY);
    }

    void settingPlayerJumping() {
        playerYAccel = jumpAccel;
    }

    void calcPlayerYPos() {
        playerY += playerYAccel;
        playerCollisionBox.setY(playerY);
    }

    CollisionBox generateCollisionRectAt(float xPos, float yPos) {
        int tempX = (int) xPos;
        int tempY = (int) yPos;
        return new CollisionBox(tempX, tempY, playerModel.playerTexture.getWidth(), playerTextureHeight);
    }

    void DrawPlayerCollisionBox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(playerCollisionBox.getX(), playerCollisionBox.getY(),
                playerCollisionBox.getWidth(), playerCollisionBox.getHeight());
    }


    void update(boolean isJumping) {

        if (isJumping) {
            settingPlayerJumping();
        } else {
            playerYAccel += GRAVITY;
        }

        calcPlayerYPos();
    }

    void render(Graphics g) {
        //TODO Give plane tilt when flying up or down.
        if (getPlayerYAccel() < 0) {
            //g.rotate(10.0f, 0f, 0f);
        } else if (getPlayerYAccel() > 0) {

        }

        g.drawTexture(playerModel.playerTexture, playerX, getPlayerY());
        //DrawPlayerCollisionBox(g);
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

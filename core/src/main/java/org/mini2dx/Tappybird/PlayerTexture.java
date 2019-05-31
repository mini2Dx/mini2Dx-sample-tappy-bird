package org.mini2dx.Tappybird;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Animation;
import org.mini2Dx.core.graphics.SpriteSheet;

public class PlayerTexture {

    private static final String PLAYER_SPRITE_SHEET_LOCATION = "Player/planes.png";

    private float frameDuration = 0.025f;

    Texture spriteTexture = new Texture(PLAYER_SPRITE_SHEET_LOCATION);
    SpriteSheet playerSpriteSheet = new SpriteSheet(spriteTexture,88,73);
    Animation playerAnimation = new Animation();


    public PlayerTexture(){
        playerAnimation.addFrame(playerSpriteSheet.getSprite(1),frameDuration);
        playerAnimation.addFrame(playerSpriteSheet.getSprite(9),frameDuration);
        playerAnimation.addFrame(playerSpriteSheet.getSprite(12),frameDuration);
        playerAnimation.addFrame(playerSpriteSheet.getSprite(9),frameDuration);
    }

}

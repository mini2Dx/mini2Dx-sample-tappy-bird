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

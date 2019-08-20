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

import static org.mini2dx.Tappybird.TappyBirdGame.FLYING_SPEED;

public class Background extends Hazards {

    BackgroundTexture backgroundTexture;

    private static float BACKGROUND_SPEED = 0.3f;
    private float width;

    public Background(BackgroundTexture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
        this.width = backgroundTexture.background.getWidth();
    }

    @Override
    void update() {
        point.preUpdate();
        point.set(point.getX() - FLYING_SPEED * BACKGROUND_SPEED, point.getY());

        //This resets the backgrounds position to it's original once it has left the screen.
        if (point.getX() < -width + 5) {
            point.setX(width - 5);
        }
    }

    void render(Graphics g) {
        g.drawTexture(backgroundTexture.background, point.getX(), point.getY() + 15f);
    }
}


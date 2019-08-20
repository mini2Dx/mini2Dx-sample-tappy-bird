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

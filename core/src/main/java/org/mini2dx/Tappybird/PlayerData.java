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

import org.mini2Dx.core.Mdx;
import org.mini2Dx.core.exception.PlayerDataException;
import org.mini2Dx.core.serialization.annotation.Field;


public class PlayerData {
    @Field
    private int highScore;

    private static final String DATA_LOCATION = "playerdata.json";

    public int getHighScore(){
        return highScore;
    }

    public void setHighScore(int highScore){
        this.highScore = highScore;
    }

    public void savePlayerData(int highScore){
        try{
            PlayerData data = new PlayerData();
            data.setHighScore(highScore);
            Mdx.playerData.writeJson(data, DATA_LOCATION);
        } catch (PlayerDataException e) {
            System.out.println("Unable to save data.");
            e.printStackTrace();
        }
    }

    public void loadPlayerData(){
        try{
            PlayerData data = Mdx.playerData.readJson(PlayerData.class, DATA_LOCATION);
            setHighScore(data.getHighScore());
        } catch (PlayerDataException e) {
            setHighScore(0);
        } catch (Exception e) {
            setHighScore(0);
        }
    }
}

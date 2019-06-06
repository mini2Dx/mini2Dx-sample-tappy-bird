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
import org.mini2Dx.core.graphics.Graphics;
import java.util.LinkedList;
import java.util.List;
import static org.mini2dx.Tappybird.TappyBirdGame.GAME_HEIGHT;
import static org.mini2dx.Tappybird.TappyBirdGame.GAME_WIDTH;

public class UserInterface {

    Texture[] numberTextures = new Texture[10];

    Texture getReadyText, gameOverText, highscoreText;

    List<Integer> scoreDigits = new LinkedList<Integer>();

    Integer[] digits;

    private int numberWidth = 53;
    private int highscoreY = 75;
    private int messageY = (int)(GAME_HEIGHT - 100);

    public UserInterface(UserInterfaceTexture userInterfaceTexture){

        numberTextures[0] = userInterfaceTexture.numberZeroTexture;
        numberTextures[1] = userInterfaceTexture.numberOneTexture;
        numberTextures[2] = userInterfaceTexture.numberTwoTexture;
        numberTextures[3] = userInterfaceTexture.numberThreeTexture;
        numberTextures[4] = userInterfaceTexture.numberFourTexture;
        numberTextures[5] = userInterfaceTexture.numberFiveTexture;
        numberTextures[6] = userInterfaceTexture.numberSixTexture;
        numberTextures[7] = userInterfaceTexture.numberSevenTexture;
        numberTextures[8] = userInterfaceTexture.numberEightTexture;
        numberTextures[9] = userInterfaceTexture.numberNineTexture;

        getReadyText = userInterfaceTexture.getReadyTexture;
        gameOverText = userInterfaceTexture.gameOverTexture;
        highscoreText = userInterfaceTexture.highscoreTexture;

    }

    private Integer[] intToIntArrayByDigits(int integer){
        scoreDigits.clear();

        while (integer>0){
            int d = integer % 10;
            scoreDigits.add(0,d);
            integer /= 10;
        }

        return scoreDigits.toArray(new Integer[0]);
    }

    void displayScore(Graphics g, int tempScore){

        digits = intToIntArrayByDigits(tempScore);

        for(int i = 0; i<digits.length; i++){
            g.drawTexture(numberTextures[digits[i]],i*numberWidth,0);
        }
    }

    void displayHighscore (Graphics g, int highscore){

        digits = intToIntArrayByDigits(highscore);

        g.drawTexture(highscoreText, GAME_WIDTH/2 - highscoreText.getWidth()/2, highscoreY);

        if(digits.length > 0) {
            for (int i = 0; i < digits.length; i++) {
                g.drawTexture(numberTextures[digits[i]],
                        (GAME_WIDTH / 2) - (digits.length * numberWidth / 2) + (i * numberWidth),
                        highscoreY + 60);
            }
        } else {
            g.drawTexture(numberTextures[0],GAME_WIDTH/2 - numberWidth/2, highscoreY + 60);
        }
    }

    void displayGetReadyMessage(Graphics g){
        g.drawTexture(getReadyText, GAME_WIDTH/2 - getReadyText.getWidth()/2 , messageY);
    }

    void displayGameOverMessage(Graphics g){
        g.drawTexture(gameOverText, GAME_WIDTH/2 - gameOverText.getWidth()/2 , messageY);
    }

}

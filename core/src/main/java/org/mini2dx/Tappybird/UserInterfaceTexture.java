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

public class UserInterfaceTexture {

    private static final String NUMBER_0_LOCATION = "UserInterface/Numbers/number0.png";
    private static final String NUMBER_1_LOCATION = "UserInterface/Numbers/number1.png";
    private static final String NUMBER_2_LOCATION = "UserInterface/Numbers/number2.png";
    private static final String NUMBER_3_LOCATION = "UserInterface/Numbers/number3.png";
    private static final String NUMBER_4_LOCATION = "UserInterface/Numbers/number4.png";
    private static final String NUMBER_5_LOCATION = "UserInterface/Numbers/number5.png";
    private static final String NUMBER_6_LOCATION = "UserInterface/Numbers/number6.png";
    private static final String NUMBER_7_LOCATION = "UserInterface/Numbers/number7.png";
    private static final String NUMBER_8_LOCATION = "UserInterface/Numbers/number8.png";
    private static final String NUMBER_9_LOCATION = "UserInterface/Numbers/number9.png";

    private static final String GAME_OVER_TEXT_LOCATION = "UserInterface/textGameOver.png";
    private static final String PRESS_SPACE_TEXT_LOCATION = "UserInterface/textPressSpace.png";
    private static final String HIGHSCORE_TEXT_LOCATION = "UserInterface/textHighscore.png";

    Texture numberZeroTexture = new Texture(NUMBER_0_LOCATION);
    Texture numberOneTexture = new Texture(NUMBER_1_LOCATION);
    Texture numberTwoTexture = new Texture(NUMBER_2_LOCATION);
    Texture numberThreeTexture = new Texture(NUMBER_3_LOCATION);
    Texture numberFourTexture = new Texture(NUMBER_4_LOCATION);
    Texture numberFiveTexture = new Texture(NUMBER_5_LOCATION);
    Texture numberSixTexture = new Texture(NUMBER_6_LOCATION);
    Texture numberSevenTexture = new Texture(NUMBER_7_LOCATION);
    Texture numberEightTexture = new Texture(NUMBER_8_LOCATION);
    Texture numberNineTexture = new Texture(NUMBER_9_LOCATION);

    Texture gameOverTexture = new Texture(GAME_OVER_TEXT_LOCATION);
    Texture getReadyTexture = new Texture(PRESS_SPACE_TEXT_LOCATION);
    Texture highscoreTexture = new Texture(HIGHSCORE_TEXT_LOCATION);
}
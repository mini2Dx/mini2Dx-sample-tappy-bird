package org.mini2dx.Tappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputHandler {

    //TODO Maybe add an abstraction here to the player input.
    boolean spacePressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    boolean escPressed() {
        return Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);
    }


}

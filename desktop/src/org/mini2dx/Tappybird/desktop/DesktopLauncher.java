package org.mini2dx.Tappybird.desktop;


import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import org.mini2Dx.libgdx.desktop.DesktopMini2DxConfig;
import org.mini2dx.Tappybird.TappyBirdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(TappyBirdGame.GAME_IDENTIFIER);
		config.vSyncEnabled = true;
		new DesktopMini2DxGame(new TappyBirdGame(), config);
	}
}

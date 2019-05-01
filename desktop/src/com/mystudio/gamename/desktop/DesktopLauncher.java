package com.mystudio.gamename.desktop;

import org.mini2Dx.desktop.DesktopMini2DxConfig;

import com.badlogic.gdx.backends.lwjgl.DesktopMini2DxGame;

import com.mystudio.gamename.MyMini2DxGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DesktopMini2DxConfig config = new DesktopMini2DxConfig(MyMini2DxGame.GAME_IDENTIFIER);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		config.vSyncEnabled = true;
		config.width = screenSize.width;
		config.height = screenSize.height;
		DesktopMini2DxGame myGame = new DesktopMini2DxGame(new MyMini2DxGame(), config);
	}
}

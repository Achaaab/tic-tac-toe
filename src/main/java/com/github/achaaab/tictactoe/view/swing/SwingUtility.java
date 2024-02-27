package com.github.achaaab.tictactoe.view.swing;

import java.awt.Toolkit;

import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.Math.round;

/**
 * Swing utility functions.
 *
 * @author Jonathan Guéhenneux
 * @since 0.0.0
 */
public class SwingUtility {

	private static final Toolkit TOOLKIT = getDefaultToolkit();
	private static final float BASE_RESOLUTION = 72.0f;
	private static final int RESOLUTION = TOOLKIT.getScreenResolution();

	/**
	 * @param size normalized size for 72 DPI resolution
	 * @return scaled and rounded size
	 * @since 0.0.0
	 */
	public static int scale(float size) {
		return round(scaleFloat(size));
	}

	/**
	 * @param size normalized size for 72 DPI resolution
	 * @return scaled size
	 * @since 0.0.0
	 */
	public static float scaleFloat(float size) {
		return size * RESOLUTION / BASE_RESOLUTION;
	}
}

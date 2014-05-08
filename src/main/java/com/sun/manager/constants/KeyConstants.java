package com.sun.manager.constants;

import javafx.scene.input.KeyCode;

import java.util.Arrays;
import java.util.List;

/**
 * User: iason
 * Date: 08.05.14
 */
public class KeyConstants {
    private static List<KeyCode> codes = Arrays.asList(
            KeyCode.A,
            KeyCode.E,
            KeyCode.F,
            KeyCode.I,
            KeyCode.J,
            KeyCode.L,
            KeyCode.N,
            KeyCode.P,
            KeyCode.T,
            KeyCode.U,
            KeyCode.V,
            KeyCode.W,
            KeyCode.X,
            KeyCode.Y,
            KeyCode.Z,

            KeyCode.DIGIT0,
            KeyCode.DIGIT1,
            KeyCode.DIGIT2,
            KeyCode.DIGIT3,
            KeyCode.DIGIT4,
            KeyCode.DIGIT5,
            KeyCode.DIGIT6,
            KeyCode.DIGIT7,
            KeyCode.DIGIT8,
            KeyCode.DIGIT9
    );

    public static List<KeyCode> getInvalidCodes() {
        return codes;
    }
}

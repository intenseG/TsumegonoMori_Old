package com.example.oubeika.tsumegonomori;

import java.io.Serializable;

public class GameConst {

    public static final String ProblemsPATH = "problems"; // Firebaseに質問を保存するバス
    public static final String AnswersPATH = "answers"; // Firebaseに解答を保存するパス

    public static final int SIZE = 15;
    public static final int LAST_MARK = 5;
    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int BLACK_PASS = -1;
    public static final int WHITE_PASS = -2;
}

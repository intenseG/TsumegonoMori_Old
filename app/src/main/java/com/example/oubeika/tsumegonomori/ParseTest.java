package com.example.oubeika.tsumegonomori;

import java.util.ArrayList;
import java.util.List;

import static com.example.oubeika.tsumegonomori.Const.BLACK;
import static com.example.oubeika.tsumegonomori.Const.BOARD_SIZE;
import static com.example.oubeika.tsumegonomori.Const.WHITE;

public class ParseTest {

    private List<String> sgfList;
    private List<String> variationList;

    private int[][] zahyoP = new int[BOARD_SIZE][BOARD_SIZE];
    private int[][] zahyoA = new int[BOARD_SIZE][BOARD_SIZE];

    private GoData goData = new GoData();

    private String pred_stones = "";
    private int start = 0;
    private int end = 0;

    ParseTest(ArrayList<String> sgfList) {
        this.sgfList = new ArrayList<>();
        variationList = new ArrayList<>();

        this.sgfList = sgfList;
    }

    public void splitGoData() {
        boolean sgf_reading_flag = false;
        boolean zahyo_reading_flag = false;

        for (int i = 0; i < sgfList.size(); i++) {
            // 不要な改行などを除去
            String data = sgfList.get(i).replaceAll("\\n\\r\\t", "");

            // ここからパース処理を書いていく

            //String[] split = (problemData.split(",", 0));

/*            for (int p = 0; p < data.length(); p++) {
                char present_char = data.charAt(p);
                if (!sgf_reading_flag) {
                    switch (present_char) {
                        case '\r':
                        case '\n':
                        case '\t':
                        case ' ':
                        case '(':
                        case ';':
                            if (data.startsWith("AB", p + 1) || data.startsWith("AW", p + 1)) {
                                end = data.indexOf(";", p + 1);
                                pred_stones = data.substring(p + 1, end);
                                variationList.add(pred_stones);
                            }

                            if (data.startsWith("B", p + 1) || data.startsWith("W", p + 1)) {
                                end = data.indexOf(")", p + 1);
                                pred_stones = data.substring(p + 1, end);
                                variationList.add(pred_stones);
                            }
                        case '[':
                            sgf_reading_flag = true;
                            break;
                    }
                } else {
                    switch (present_char) {
                        case ']':
                    }
                }*/

                /*if (data.startsWith("AB::")) {
                    start = data.indexOf("AB::");
                    if (start != -1) {
                        allGoData = data.substring(start + 4);
                        String[] divisionData = allGoData.split(";", 0);
                        splitZahyoP(divisionData);
                        for (String s : divisionData) {
                           problemZahyoToInt(s, BLACK);
                        }
                    }
                }
                if (data.startsWith("B;")) {
                    start = data.indexOf("B;");
                    if (start != -1) {
                        allGoData = data.substring(start + 2);
                        String[] divisionData = allGoData.split(";", 0);
                        for (String s : divisionData) {
                            answerZahyoToInt(s, BLACK);
                        }
                    }
                }
                if (data.startsWith("AW::")) {
                    start = data.indexOf("AW::");
                    if (start != -1) {
                        allGoData = data.substring(start + 4);
                        String[] divisionData = allGoData.split(";", 0);
                        for (String s : divisionData) {
                            problemZahyoToInt(s, WHITE);
                        }
                    }
                }
                if (data.startsWith("W;")) {
                    start = data.indexOf("W;");
                    if (start != -1) {
                        allGoData = data.substring(start + 2);
                        String[] divisionData = allGoData.split(";", 0);
                        for (String s : divisionData) {
                            answerZahyoToInt(s, WHITE);
                        }
                    }
                }*/
            if (data.startsWith("QN::")) {
                start = data.indexOf("QN::");
                if (start != -1) {
                    String qNum = data.substring(start + 4);
                    goData.setNumber(qNum);
                }
            }
            if (data.startsWith("LV::")) {
                start = data.indexOf("LV::");
                if (start != -1) {
                    String level = data.substring(start + 4);
                    goData.setLevel(level);
                }
            }
        }
    }

/*    public String pickingZahyo(String zahyo) {

    }*/

    public void splitZahyoP(String[] zahyoDataP) {
        String allZahyo;

        for (String data : zahyoDataP) {

            if (data.startsWith("AB::")) {
                start = data.indexOf("AB::");
                if (start != -1) {
                    allZahyo = data.substring(start + 4);
                    String[] divisionData = allZahyo.split(";", 0);
                    for (String s : divisionData) {
                        problemZahyoToInt(s, BLACK);
                    }
                }
            }
            if (data.startsWith("B;")) {
                start = data.indexOf("B;");
                if (start != -1) {
                    allZahyo = data.substring(start + 2);
                    String[] divisionData = allZahyo.split(";", 0);
                    for (String s : divisionData) {
                        answerZahyoToInt(s, BLACK);
                    }
                }
            }
            if (data.startsWith("AW::")) {
                start = data.indexOf("AW::");
                if (start != -1) {
                    allZahyo = data.substring(start + 4);
                    String[] divisionData = allZahyo.split(";", 0);
                    for (String s : divisionData) {
                        problemZahyoToInt(s, WHITE);
                    }
                }
            }
            if (data.startsWith("W;")) {
                start = data.indexOf("W;");
                if (start != -1) {
                    allZahyo = data.substring(start + 2);
                    String[] divisionData = allZahyo.split(";", 0);
                    for (String s : divisionData) {
                        answerZahyoToInt(s, WHITE);
                    }
                }
            }
        }
    }


    private void problemZahyoToInt(String zahyo, int stoneColor) {

        System.out.println(zahyo);
        int colP = intChanger(zahyo.charAt(0));
        int rowP = intChanger(zahyo.charAt(1));
        zahyoP[colP][rowP] = stoneColor;

        this.setZahyoP(zahyoP);
    }

    private int[][] answerZahyoToInt(String zahyo, int stoneColor) {

        int colA = intChanger(zahyo.charAt(0));
        int rowA = intChanger(zahyo.charAt(1));
        zahyoA[colA][rowA] = stoneColor;

        return zahyoA;
    }

    private int intChanger(char c) {
        int i;
        i = (int) c - (int) 'a';
        System.out.println(i);
        return i;
    }

    public void setZahyoP(int[][] zahyoP) {
        this.zahyoP = zahyoP;
    }

    public int[][] getZahyoP() {
        return zahyoP;
    }

    public void setZahyoA(int[][] zahyoA) {
        this.zahyoA = zahyoA;
    }

    public int[][] getZahyoA() {
        return zahyoA;
    }
}

package com.example.oubeika.tsumegonomori;

public class SgfChange {

        private int Col;
        private int Row;
        private int StoneColor;

        public void SgfFirstStone(String str, int stoneColor) {

            Col = hantenSub(str.charAt(0));
            Row = hantenSub(str.charAt(1));
            StoneColor = stoneColor;
        }

        public int getStoneColor(){

            return this.StoneColor;
        }

        private int hantenSub(char ch) {
            int i;
            switch (ch) {
                case 'a':
                    i = 0;
                    break;
                case 'b':
                    i = 1;
                    break;
                case 'c':
                    i = 2;
                    break;
                case 'd':
                    i = 3;
                    break;
                case 'e':
                    i = 4;
                    break;
                case 'f':
                    i = 5;
                    break;
                case 'g':
                    i = 6;
                    break;
                case 'h':
                    i = 7;
                    break;
                case 'i':
                    i = 8;
                    break;
                case 'j':
                    i = 9;
                    break;
                case 'k':
                    i = 10;
                    break;
                case 'l':
                    i = 11;
                    break;
                case 'm':
                    i = 12;
                    break;
                case 'n':
                    i = 13;
                    break;
                case 'o':
                    i = 14;
                    break;
                case 'p':
                    i = 15;
                    break;
                case 'q':
                    i = 16;
                    break;
                case 'r':
                    i = 17;
                    break;
                case 's':
                    i = 18;
                    break;
                case 't':
                    i = -1;
                    break;
                default:
                    i = -1;
                    break;
            }
            return i;
        }
}

package lesson3;

import java.util.Random;
import java.util.Scanner;

public class Lesson3 {
    private static final char HUMAN_DOT = 'X';
    private static final char AI_DOT = 'O';
    private static final char EMPTY_DOT = '_';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static int fieldSizeY;
    private static int fieldSizeX;
    private static char[][] field;

    private static void initField() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = EMPTY_DOT;
            }
        }
    }
    private static void printField() {
        System.out.println("_y_y_y_y_y_");
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print("x");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }
    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.print("Please enter coordinates X and Y (1 to 3) separated by space");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isEmptyCell(x, y) || !isValidCell(x, y));
        field[y][x] = HUMAN_DOT;
    }
    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }
    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }
    private static boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y))
                    return false;
            }
        }
        return true;
    }
    /* Старый код
    private static boolean checkWin(char c) {
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;

        return false;
    }
    */
// Новый код для проверки выигрывшего по 4 фишкам
    private static boolean checkWin2(char c) {
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (checkDiagonal(c) || checkLanes(c)) return true;
            }
        }
        return false;
    }

// проверка диагоналей
   public static boolean checkDiagonal(char c) {
        boolean toright, toleft;
        toright = true;
        toleft = true;
        for (int i=0; i<4; i++) {
            toright &= (field[i][i] == c);
            toleft &= (field[4-i-1][i] == c);
        }

        if (toright || toleft) return true;

        return false;
    }
// проверка линий
    public static boolean checkLanes(char c) {
        boolean cols, rows;
        for (int col = 0; col < 4; col++) {
            cols = true;
            rows = true;
            for (int row = 0; row < 4; row++) {
                cols &= (field[col][row] == c);
                rows &= (field[row][col] == c);
            }
            if (cols || rows) return true;
        }
        return false;
    }


        public static void main(String[] args) {
        initField();
        printField();
//        while (true) {
        while (true) {
            humanTurn();
            printField();
            if (checkWin2(HUMAN_DOT)) {
                System.out.println("Player wins!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw!");
                break;
            }
            aiTurn();
            printField();
            if (checkWin2(AI_DOT)) {
                System.out.println("Computer wins!");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw!");
                break;
            }
        }
        //play again?
        // if !y break;
    }
//    }

}
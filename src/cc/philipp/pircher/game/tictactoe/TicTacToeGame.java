package cc.philipp.pircher.game.tictactoe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    private static char[][] fields = new char[3][3];

    public static void main(String[] args) {
        startNewGame();
    }

    private static void initFields() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                fields[i][j] = ' ';
            }
        }
    }

    private static void drawFields() {
        System.out.println(fields[0][0] + "|" + fields[0][1] + "|" + fields[0][2]);
        System.out.println("-----");
        System.out.println(fields[1][0] + "|" + fields[1][1] + "|" + fields[1][2]);
        System.out.println("-----");
        System.out.println(fields[2][0] + "|" + fields[2][1] + "|" + fields[2][2] + "\n");
    }

    private static void startNewGame() {
        boolean exit = false;
        boolean playerOnTurn = true;
        System.out.println("Willkommen bei Tic-Tac-Toe");
        initFields();
        do {
            drawFields();

            if (playerOnTurn) {
                playerPlaceSign();
                playerOnTurn = false;
            }
            else {
                computerPlaceSign();
                playerOnTurn = true;
            }
        } while (!exit);
    }

    private static void playerPlaceSign() {
        int row = 0;
        int column = 0;
        final char SIGNPLAYER = 'X';
        final String NAMEPLAYER = "Spieler";
        boolean setSignToFields = false;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(NAMEPLAYER + " ist dran");
                System.out.print("Gib Zeile ein (0 bis 2): ");
                row = sc.nextInt();
                System.out.print("Gib Spalte ein (0 bis 2): ");
                column = sc.nextInt();

                if ((row < 0 || row > 2) || (column < 0 || column > 2)) {
                    System.out.println("Wert liegt außerhalb 0 bis 2! Bitte nur Werte zwischen 0 bis 2 eingeben\n");
                }
                else {
                    setSignToFields = checkPlaceAndSet(row, column, SIGNPLAYER, NAMEPLAYER);
                }
            } catch (InputMismatchException e) {
                System.err.println("Ungültiger Buchstabenwert! Bitte nur Werte zwischen 0 bis 2 eingeben\n");
            }
        }while(!setSignToFields);
    }

    private static void computerPlaceSign() {
        final char SIGNCOMPUTER = 'O';
        final String NAMECOMPUTER = "Computer";
        computerPlaceRandomSign(SIGNCOMPUTER, NAMECOMPUTER);
        //checkOneComputerSignInLine(SIGNCOMPUTER, NAMECOMPUTER);
    }

    private static void computerPlaceRandomSign(char sign, String name){
        int row, column;
        Random rand = new Random();
        System.out.println(name + " ist dran");
        do {
            row = rand.nextInt(3);
            column = rand.nextInt(3);
        } while (!checkPlaceAndSet(row, column, sign, name));
    }

    private static boolean checkPlaceAndSet(int row, int column, char sign, String name) {
        boolean isSignSet = false;
        if ((fields[row][column] == ' ')){
            fields[row][column] = sign;
            checkThreeInLine(sign, name);
            isSignSet = true;
        } else {
            if (name.equals("Spieler"))
                System.out.println("Feld [" + row + "][" + column + "] ist bereits belegt\n");
            isSignSet = false;
        }
        checkDraw();
        return isSignSet;
    }

    private static void checkThreeInLine(Character sign, String name) {
        boolean isWon = false;

        if ((fields[0][0] == sign) && (fields[0][1] == sign) && (fields[0][2] == sign))    // XXX First Line Hor
            isWon = true;
        if ((fields[1][0] == sign) && (fields[1][1] == sign) && (fields[1][2] == sign))    // XXX Second Line Horizontal
            isWon = true;
        if ((fields[2][0] == sign) && (fields[2][1] == sign) && (fields[2][2] == sign))    // XXX Third Line Horizontal
            isWon = true;
        if ((fields[0][0] == sign) && (fields[1][0] == sign) && (fields[2][0] == sign))    // XXX First Line Vertical
            isWon = true;
        if ((fields[0][1] == sign) && (fields[1][1] == sign) && (fields[2][1] == sign))    // XXX Second Line Vertical
            isWon = true;
        if ((fields[0][2] == sign) && (fields[1][2] == sign) && (fields[2][2] == sign))    // XXX Second Line Vertical
            isWon = true;
        if ((fields[0][0] == sign) && (fields[1][1] == sign) && (fields[2][2] == sign))    // XXX Diagonal from top left to bottom right
            isWon = true;
        if ((fields[0][2] == sign) && (fields[1][1] == sign) && (fields[2][0] == sign))    // XXX Diagonal from top right to bottom left
            isWon = true;

        if (isWon) {
            drawFields();
            System.out.println("Wir haben einen Gewinner, " + name + " gewinnt! Spiel Neustart\n");
            startNewGame();
        }
    }

    private static void checkDraw() {
        if ((fields[0][0] != ' ') && (fields[0][1] != ' ') && (fields[0][2] != ' ') &&
                (fields[1][0] != ' ') && (fields[1][1] != ' ') && (fields[1][2] != ' ') &&
                (fields[2][0] != ' ') && (fields[2][1] != ' ') && (fields[2][2] != ' ')) {
            drawFields();
            System.out.println("Unentschieden!");
            startNewGame();
        }
    }
}




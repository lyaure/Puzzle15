package com.lyaurese_samuelna.ex1;

import java.util.Random;

public class GameBoard {
    private int[][] board;
    private int moves;
    private int iZero, jZero;

    public GameBoard() {
        this.board = new int[4][4];
        newBoard();
    }

    public void newBoard(){
        int tmp = 1;
        for(int i=0; i<4; i++) {
            for (int j=0; j < 4; j++){
                this.board[i][j] = tmp;
                tmp++;
            }
        }

        this.iZero = 3;
        this.jZero = 3;
        this.board[3][3] = 0;

        shuffle();
        this.moves = 0;
    }

    public String returnString(int i, int j){
        if(this.board[i][j] != 0)
            return ""+this.board[i][j];
        else
            return "";
    }

    private void shuffle(){
        int i = 3, j = 3;
        int tmp = 500;
        Random rn = new Random();

        while (tmp>0){
            int rand = rn.nextInt(4)+1;

            switch (rand){
                case 1:
                    if(i+1 < 4) {
                        iZero = i;
                        jZero = j;
                        swap(i, j, i+1, j);
                        i++;
                        tmp--;
                    }
                    break;
                case 2:
                    if(i-1 >= 0) {
                        swap(i, j, i-1, j);
                        i--;
                        tmp--;
                    }
                    break;
                case 3:
                    if(j+1 < 4) {
                        swap(i, j, i , j+1);
                        j++;
                        tmp--;
                    }
                    break;
                case 4:
                    if(j-1 >= 0) {
                        swap(i, j, i , j-1);
                        j--;
                        tmp--;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public boolean move(int i, int j){
        if(isMoveable(i, j)) {
            swap(i, j, iZero, jZero);
            this.moves ++;
            return true;
        }
        return false;
    }

    private void swap(int a, int b, int c, int d) {
        int tmp = this.board[a][b];
        this.board[a][b] = this.board[c][d];
        this.board[c][d] = tmp;
    }

    public boolean isMoveable(int i, int j) {
        if(i+1 < 4)
            if(this.board[i+1][j] == 0) {
                iZero = i+1;
                jZero = j;
                return true;
            }

        if(i-1 >= 0)
            if(this.board[i-1][j] == 0) {
                iZero = i - 1;
                jZero = j;
                return true;
            }

        if(j+1 < 4)
            if(this.board[i][j+1] == 0) {
                iZero = i;
                jZero = j+1;
                return true;
            }

        if(j-1 >= 0)
            if(this.board[i][j-1] == 0) {
                iZero = i;
                jZero = j-1;
                return true;
            }
        return false;
    }

    public String getMoves(){
        int tmp = this.moves;
        String formatted = String.format("%04d", tmp);
        return formatted;
    }

    public boolean isGameOver(){
        int tmp = 1;
        for(int i=0; i<4; i++) {
            for (int j=0; j < 4; j++){
                if(this.board[i][j] != tmp)
                    return false;
                tmp++;
                if(tmp == 16)
                    tmp = 0;
            }
        }
        return true;
    }
}

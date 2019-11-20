package com.example.lyaure.puzzle15;

import java.util.Random;

public class GameBoard {
    private int[][] board;
    private int moves;
    private boolean isFinish;

    public GameBoard() {
        this.board = new int[4][4];
        int tmp = 1;
        for(int i=0; i<4; i++) {
            for (int j=0; j < 4; j++){
                this.board[i][j] = tmp;
                tmp++;
            }
        }
        this.board[3][3] = 0;
        shuffle();
        this.moves = 0;
        this.isFinish = false;

    }

    public String returnString(int i, int j){
        if(this.board[i][j] != 0)
            return ""+this.board[i][j];
        else
            return "";
    }

    private void shuffle(){
        int i = 3, j = 3;
        int tmp = 1000;
        Random rn = new Random();

        while (tmp>0){
            int rand = rn.nextInt(4)+1;

            switch (rand){
                case 1:
                    if(i+1 < 4) {
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

    public void move(int i, int j){
        int iZero = -1;
        int jZero = -1;
        if(isMoveable(i, j, iZero, jZero)) {
            swap(i, j, iZero, jZero);
            this.moves ++;
            // UPDATE UI BOARD
            // UPDATE UI MOVES COUNT
        }
        if(isFinish) {
            // TODO
        }
    }

    private void swap(int a, int b, int c, int d) {
        int tmp = this.board[a][b];
        this.board[a][b] = this.board[c][d];
        this.board[c][d] = tmp;
    }

    public boolean isMoveable(int i, int j, int iZero, int jZero) {
        if(i+1 < 4)
            if(this.board[i+1][j] == 0) {
                iZero = i+1;
                jZero = j;
                return true;
            }

        if(i-1 >= 0)
            if(this.board[i+1][j] == 0) {
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


    public void findZero(int iZero, int jZero) {
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                if(this.board[i][j] == 0) {
                    iZero = i;
                    jZero = j;
                }
    }

    private void checkBoard() {
        int tmp = 1;
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++)
                if (this.board[i][j] != tmp) {
                    this.isFinish = false;
                    return;
                }
        }
        this.isFinish = true;
    }

}

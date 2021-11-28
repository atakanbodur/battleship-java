package edu.ozyegin;

import java.util.ArrayList;

public class Board {
    private int[][] board;
    private ArrayList<Ship> myShips;
    private ArrayList<Integer> registeredShips;
    private boolean allBoatsAreCreated;
    private boolean playerLostGame;
    private String playerUsername;
    private Ship carrier;
    private Ship battleship;
    private Ship submarine;
    private Ship destroyer;

    Board(String playerUsername){
        this.playerUsername=playerUsername;
        this.allBoatsAreCreated=false;
        this.playerLostGame=false;
        this.board = initBoard(10);
        this.carrier = new Ship("C",5,105);
        this.battleship = new Ship("B",4,104);
        this.submarine = new Ship("S",3,103);
        this.destroyer = new Ship("D",2,102);
        this.myShips = new ArrayList<>();
        this.myShips.add(carrier);
        this.myShips.add(battleship);
        this.myShips.add(submarine);
        this.myShips.add(destroyer);
        this.registeredShips = new ArrayList<>();
    }

    private int[][] initBoard(int size){
        int[][] clone = new int[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                clone[i][j]=0;
            }
        }
        return clone;
    }
    public void printBoard(int[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                System.out.print(board[i][j]+"");
            }
            System.out.println();
        }
    }
    public int[][] getBoard() {
        return board;
    }
    public boolean isAllBoatsAreCreated() {
        return allBoatsAreCreated;
    }
    public boolean isPlayerLostGame() {
        return playerLostGame;
    }
    public String getPlayerUsername() {
        return playerUsername;
    }
    public ArrayList<Ship> getMyShips() {
        return myShips;
    }
    public ArrayList<Integer> getRegisteredShips() {
        return registeredShips;
    }
    public void setAllBoatsAreCreated(boolean allBoatsAreCreated) {
        this.allBoatsAreCreated = allBoatsAreCreated;
    }
}

package edu.ozyegin;

import java.util.ArrayList;

public class Board {
    private int[][] board;
    private ArrayList<Integer> registeredShips;
    private boolean allBoatsAreCreated;
    private boolean playerLostGame;
    private String playerUsername;

    Board(String playerUsername){
        this.playerUsername=playerUsername;
        this.allBoatsAreCreated=false;
        this.playerLostGame=false;
        this.board = initBoard(10);
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
    public ArrayList<Integer> getRegisteredShips() {
        return registeredShips;
    }
    public void setAllBoatsAreCreated(boolean allBoatsAreCreated) {
        this.allBoatsAreCreated = allBoatsAreCreated;
    }


    public boolean placeShip(Ship ship,int[] coordinateArr,boolean isHorizontal){
        int xCoordinate = coordinateArr[0]-1;
        int yCoordinate = coordinateArr[1]-1;

        if (!checkIfSuitable(xCoordinate,yCoordinate,ship.getSize(),isHorizontal)) return false;
        else {
            if (isHorizontal){
                    for (int i=0;i<ship.getSize();i++){
                        this.board[yCoordinate][xCoordinate]=ship.getShipNumber();
                        xCoordinate++;
                    }
            }
            else {
                for (int i=0;i<ship.getSize();i++){
                    this.board[yCoordinate][xCoordinate]=ship.getShipNumber();
                    yCoordinate++;
                }
            }
            return true;
        }
    }
    public boolean checkIfSuitable(int x, int y, int size, boolean isHorizontal){
        int suitableSector = 0;
        boolean key=true;
        int loopCounter=0;
        while (key){
            if (isHorizontal){
                if (loopCounter>=size){
                    key = false;
                }
                else {
                    for (int i=0; i<size;i++){
                        if (this.board[y][x]==0){
                            x++;
                            suitableSector++;
                            loopCounter++;
                        }
                        else return false;
                    }
                }
            }
            else if (!isHorizontal){
                if (loopCounter>=size){
                    key = false;
                }
                else {
                    for (int i=0; i<size;i++){
                        if (this.board[y][x]==0){
                            y++;
                            suitableSector++;
                            loopCounter++;
                        }
                        else return false;
                    }
                }
            }
        }
        if (suitableSector==size) return true;
        else return false;
    }

    public void setPlayerLostGame(boolean playerLostGame) {
        this.playerLostGame = playerLostGame;
    }

}

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
    public boolean placeShip(int[][] board, Ship ship, int[] startCoordinates, boolean isOnYaxis){
        int yCoordinate = startCoordinates[0];
        int xCoordinate = startCoordinates[1];
        //as they'll enter coordinates as B1 for example, computer must understand user is trying to say 2nd row first row,
        //so we must -1 them so that it becomes compatible with array language-->[1][0]
        yCoordinate--;
        xCoordinate--;
        boolean key=true;
        try {
            if (checkIfSuitable(board,yCoordinate,xCoordinate,ship.getSize())){
                if (isOnYaxis){
                    System.out.println("isOnYaxis");
                    fillBoard(board,ship.getSize(),yCoordinate,xCoordinate,ship.getShipNumber());
                }
                else {
                    System.out.println("!isOnYaxis");
                    fillBoard(board,ship.getSize(),xCoordinate,yCoordinate,ship.getShipNumber());
                }
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            System.out.println("Something went wrong!");
        }
        this.board=board;
        return true;
    }

    private boolean checkIfSuitable(int[][] board, int y, int x, int size){
        if (board[y][x]!=0){
            System.out.println("board["+y+"]["+x+"] is not equal to 0");
            return false;
        }
        return true;
    }
    private int[][] fillBoard(int[][] board, int size, int y, int x, int shipNumber){
        boolean key = true;
        int loopCounter = 0;
        while (key){
            if (loopCounter>=size){
                key=false;
            }
            else {
                board[y][x] = shipNumber;
                loopCounter++;
                x++;
            }
        }
        return board;
    }
}

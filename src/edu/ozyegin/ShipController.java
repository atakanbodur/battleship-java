package edu.ozyegin;

public class ShipController {
    public ShipController() {
    }

    public int[][] placeShip(int[][] board, Ship ship, int[] startCoordinates, boolean isOnYaxis){
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
                return null;
            }
        }
        catch (Exception e){
            System.out.println("Something went wrong!");
        }

        return board;
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

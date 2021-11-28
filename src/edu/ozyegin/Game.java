package edu.ozyegin;

public class Game {
    private String player1;
    private String player2;
    private Board myBoard;
    private Board opponentBoard;
    private GameController gameController;

    public Game(String player1) {
        this.player1 = player1;
        this.player2 = "opponent";
        this.myBoard = new Board(player1);
        this.opponentBoard = new Board(player2);
        this.gameController = new GameController();
    }



    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
    public String receiveHit(String coordinate){
        //was your hit successful? we'll see. I'll also change the value of the section that the opponent shot
        //to 1 regardless.
        int[] coordinates =  gameController.convertInputToArr(coordinate);
        int xCoordinate=coordinates[0]-1;
        int yCoordinate=coordinates[1]-1;
        if (myBoard.getBoard()[yCoordinate][xCoordinate]!=0){
            myBoard.getBoard()[yCoordinate][xCoordinate]=1;
            return "true";
        }
        else {
            myBoard.getBoard()[yCoordinate][xCoordinate]=1;
            return "false";
        }

    }
    public String hit(String coordinate){
        //I'm taking your coordinates and just checking if you shot the same place or not.
        //I'll return false if you have, if not, I'll just send the coordinate back to you.
        int[] coordinates =  gameController.convertInputToArr(coordinate);
        int xCoordinate=coordinates[0]-1;
        int yCoordinate=coordinates[1]-1;
        if (opponentBoard.getBoard()[yCoordinate][xCoordinate]!=1){
            return coordinate;
        }
        else {
            opponentBoard.getBoard()[yCoordinate][xCoordinate]=1;
            return "false";
        }
    }


    public String getPlayer1() {
        return player1;
    }
    public Board getMyBoard() {
        return myBoard;
    }
    public Board getOpponentBoard() {
        return opponentBoard;
    }
    public GameController getGameController() {
        return gameController;
    }
}

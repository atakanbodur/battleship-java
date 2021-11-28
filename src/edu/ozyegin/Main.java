package edu.ozyegin;

import javax.xml.stream.FactoryConfigurationError;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game;
        String ip;
        int port;
        ServerSocket serverSocket;
        Socket socket;
        String sentence;
        BufferedReader reader;
        DataOutputStream outputStream;
        InputStreamReader inputStreamReader;
        String opponent;
        int activePlayer;


        reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("----------------------------------");
        System.out.println("Would you please enter your name? Please...");
        String username = reader.readLine();

        System.out.println("What would you like to do?");
        System.out.println("Write; '1' for to host a game. '2' for to join a game");
        System.out.println("------------------------------------\n");
        sentence = reader.readLine();


        switch (sentence) {
            case "1": //host
                System.out.println("Great! So just give me a port number so I can initialize the socket.");
                ip = "localhost";
                port = Integer.parseInt(reader.readLine());
                serverSocket = new ServerSocket(port);
                System.out.println("\nA server created on " + ip + " and your port # is " + port);
                socket = serverSocket.accept();
                if (socket.isConnected()) {
                    System.out.println("SOMEONE CONNECTED.\n");

                    BufferedReader inFromOpponent = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    DataOutputStream outToOpponent = new DataOutputStream(socket.getOutputStream());
                    outToOpponent.writeBytes(username + "\n");
                    outToOpponent.flush();
                    sentence = inFromOpponent.readLine();
                    opponent = sentence;
                    game = new Game(username);
                    game.setPlayer2(opponent);
                    System.out.println("Your opponent is " + opponent + "\n");


                    while (!game.getMyBoard().isAllBoatsAreCreated()) {
                        game.getMyBoard().printBoard(game.getMyBoard().getBoard());
                        System.out.println("You have to place your ships to begin.");
                        System.out.println("Your ships are: a Carrier, a Battleship, a Submarine and a Destroyer. They are denoted by:");
                        System.out.println("C,B,S and D respectively. Please enter a symbol among these to place that ship on your board.\n");
                        sentence = reader.readLine();
                        switch (sentence.toUpperCase()) {
                            case "C":
                                if (game.getMyBoard().getRegisteredShips().contains(105)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship carrier = new Ship("C", 5, 105);
                                    System.out.println("You have selected carrier. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine().toUpperCase();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(carrier, game.getGameController().convertInputToArr(input), isHorizontal);
                                        game.getMyBoard().getRegisteredShips().add(105);
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "B":
                                if (game.getMyBoard().getRegisteredShips().contains(104)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship battleship = new Ship("B", 4, 104);
                                    System.out.println("You have selected battleship. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        boolean a =game.getMyBoard().placeShip(battleship, game.getGameController().convertInputToArr(input), isHorizontal);
                                        if (a){
                                            game.getMyBoard().getRegisteredShips().add(104);
                                        }
                                        else {
                                            System.out.println("Please try again");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "S":
                                if (game.getMyBoard().getRegisteredShips().contains(103)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship submarine = new Ship("S", 3, 103);
                                    System.out.println("You have selected submarine. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false\n");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(submarine, game.getGameController().convertInputToArr(input), isHorizontal);
                                        game.getMyBoard().getRegisteredShips().add(103);
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "D":
                                if (game.getMyBoard().getRegisteredShips().contains(102)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship destroyer = new Ship("D", 2, 102);
                                    System.out.println("You have selected destroyer. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false\n");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(destroyer, game.getGameController().convertInputToArr(input), isHorizontal);
                                        game.getMyBoard().getRegisteredShips().add(102);
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                        }
                        game.getMyBoard().setAllBoatsAreCreated(true);
                        /*
                        if (game.getMyBoard().getRegisteredShips().contains(105) && game.getMyBoard().getRegisteredShips().contains(104)
                                && game.getMyBoard().getRegisteredShips().contains(103) && game.getMyBoard().getRegisteredShips().contains(102)) {
                            game.getMyBoard().setAllBoatsAreCreated(true);
                        }

                         */
                    }
                    outToOpponent.writeBytes("done\n");
                    outToOpponent.flush();

                    System.out.println("You have created your board. Take a look at it!\n");
                    game.getMyBoard().printBoard(game.getMyBoard().getBoard());

                    System.out.println("We are waiting for your opponent to setup her board.\n");
                    if (inFromOpponent.readLine().equals("done")) {
                        System.out.println("Game can BEGIN!!!");
                        boolean key = true;
                        int hits=0;
                        while (!game.getMyBoard().isPlayerLostGame()) {
                            while (key) {
                                if (hits==2){
                                    System.out.println("You won the game!");
                                    game.getMyBoard().setPlayerLostGame(true);
                                    sentence = "lost";
                                    outToOpponent.writeBytes(sentence+"\n");
                                    outToOpponent.flush();
                                    key=false;
                                    break;
                                }
                                else {
                                    System.out.println("Game is still going on...\n");
                                    sentence = "cont";
                                    outToOpponent.writeBytes(sentence+"\n");
                                    outToOpponent.flush();
                                }
                                System.out.println("It's your turn to make a move. Please enter the coordinate of the place you'd like to hit.\n");
                                sentence = game.hit(reader.readLine());
                                if (!sentence.equals("false")) {
                                    outToOpponent.writeBytes(sentence + "\n");
                                    outToOpponent.flush();
                                    System.out.println("Waiting for opponent to tell us if the shot was successful or not.\n");
                                    sentence = inFromOpponent.readLine();
                                    key = false;
                                    if (sentence.equals("true")) {
                                        hits++;
                                        System.out.println("Shot successful!");
                                    } else System.out.println("You couldn't hit it. Sorry mate.");
                                } else {
                                    System.out.println("You are trying to hit the same coordinates. Try again.");
                                }
                            }
                            while (!key) {
                                if (inFromOpponent.readLine().equals("lost")){
                                    System.out.println("You lost the game.");
                                    game.getMyBoard().setPlayerLostGame(true);
                                    key=true;
                                    break;
                                }
                                System.out.println("Opponent is taking the shot...\n");
                                sentence = inFromOpponent.readLine();
                                if (game.receiveHit(sentence).equals("true")) {
                                    outToOpponent.writeBytes("true\n");
                                    outToOpponent.flush();
                                } else {
                                    outToOpponent.writeBytes("false\n");
                                    outToOpponent.flush();
                                }
                                key = true;
                            }
                        }
                    }
                }
                break;
            case "2": //join a game
                System.out.println("OK. Who will we connect to? I'm sure we'll win the game.");
                ip = reader.readLine();
                System.out.println("OK. Which port they're on?");
                port = Integer.parseInt(reader.readLine());
                socket = new Socket(ip, port);
                if (socket.isConnected()) {
                    System.out.println("We are connected. GREAT!\n");

                    BufferedReader inFromOpponent = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    DataOutputStream outToOpponent = new DataOutputStream(socket.getOutputStream());
                    outToOpponent.writeBytes(username + "\n");
                    outToOpponent.flush();
                    sentence = inFromOpponent.readLine();
                    opponent = sentence;
                    game = new Game(username);
                    game.setPlayer2(opponent);
                    System.out.println("Your opponent is " + opponent + "\n");

                    while (!game.getMyBoard().isAllBoatsAreCreated()) {
                        game.getMyBoard().printBoard(game.getMyBoard().getBoard());
                        System.out.println("You have to place your ships to begin.");
                        System.out.println("Your ships are: a Carrier, a Battleship, a Submarine and a Destroyer. They are denoted by:");
                        System.out.println("C,B,S and D respectively. Please enter a symbol among these to place that ship on your board.\n");
                        sentence = reader.readLine();
                        switch (sentence.toUpperCase()) {
                            case "C":
                                if (game.getMyBoard().getRegisteredShips().contains(105)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship carrier = new Ship("C", 5, 105);
                                    System.out.println("You have selected carrier. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine().toUpperCase();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(carrier, game.getGameController().convertInputToArr(input), isHorizontal);
                                        game.getMyBoard().getRegisteredShips().add(105);
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "B":
                                if (game.getMyBoard().getRegisteredShips().contains(104)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship battleship = new Ship("B", 4, 104);
                                    System.out.println("You have selected battleship. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        boolean a =game.getMyBoard().placeShip(battleship, game.getGameController().convertInputToArr(input), isHorizontal);
                                        if (a){
                                            game.getMyBoard().getRegisteredShips().add(104);
                                        }
                                        else {
                                            System.out.println("Please try again");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "S":
                                if (game.getMyBoard().getRegisteredShips().contains(103)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship submarine = new Ship("S", 3, 103);
                                    System.out.println("You have selected submarine. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false\n");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(submarine, game.getGameController().convertInputToArr(input), isHorizontal);
                                        game.getMyBoard().getRegisteredShips().add(103);
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case "D":
                                if (game.getMyBoard().getRegisteredShips().contains(102)) {
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                } else {
                                    Ship destroyer = new Ship("D", 2, 102);
                                    System.out.println("You have selected destroyer. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input = reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false\n");
                                    boolean isHorizontal = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(destroyer, game.getGameController().convertInputToArr(input), isHorizontal);
                                        game.getMyBoard().getRegisteredShips().add(102);
                                    } catch (Exception e) {
                                        System.out.println("Something went wrong");
                                        e.printStackTrace();
                                    }
                                }
                                break;
                        }
                        game.getMyBoard().setAllBoatsAreCreated(true);
                      /*  if (game.getMyBoard().getRegisteredShips().contains(105) && game.getMyBoard().getRegisteredShips().contains(104)
                                && game.getMyBoard().getRegisteredShips().contains(103) && game.getMyBoard().getRegisteredShips().contains(102)) {
                            game.getMyBoard().setAllBoatsAreCreated(true);
                        }*/
                    }

                    outToOpponent.writeBytes("done\n");
                    outToOpponent.flush();

                    System.out.println("You have created your board. Take a look at it!\n");
                    game.getMyBoard().printBoard(game.getMyBoard().getBoard());

                    System.out.println("We are waiting for your opponent to setup her board.\n");
                    boolean key = false;
                    if (inFromOpponent.readLine().equals("done")) {
                        System.out.println("Game can BEGIN!!!");
                        int hits=0;
                        while (!game.getMyBoard().isPlayerLostGame()) {
                            while (key) {
                                if (hits==2){
                                    System.out.println("You won the game!");
                                    game.getMyBoard().setPlayerLostGame(true);
                                    sentence = "lost";
                                    outToOpponent.writeBytes(sentence+"\n");
                                    outToOpponent.flush();
                                    key=false;
                                    break;
                                }
                                else {
                                    System.out.println("Game is still going on...\n");
                                    sentence = "cont";
                                    outToOpponent.writeBytes(sentence+"\n");
                                    outToOpponent.flush();
                                }
                                System.out.println("It's your turn to make a move. Please enter the coordinate of the place you'd like to hit.\n");
                                sentence = game.hit(reader.readLine());
                                if (!sentence.equals("false")) {
                                    outToOpponent.writeBytes(sentence + "\n");
                                    outToOpponent.flush();
                                    System.out.println("Waiting for opponent to tell us if the shot was successful or not.\n");
                                    sentence = inFromOpponent.readLine();
                                    key = false;
                                    if (sentence.equals("true")) {
                                        hits++;
                                        System.out.println("Shot successful!");
                                    } else System.out.println("You couldn't hit it. Sorry mate.");
                                } else {
                                    System.out.println("You are trying to hit the same coordinates. Try again.");
                                }
                            }
                            while (!key) {
                                if (inFromOpponent.readLine().equals("lost")){
                                    System.out.println("You lost the game.");
                                    game.getMyBoard().setPlayerLostGame(true);
                                    key=true;
                                    break;
                                }
                                System.out.println("Opponent is taking the shot...\n");
                                sentence = inFromOpponent.readLine();
                                if (game.receiveHit(sentence).equals("true")) {
                                    outToOpponent.writeBytes("true\n");
                                    outToOpponent.flush();
                                } else {
                                    outToOpponent.writeBytes("false\n");
                                    outToOpponent.flush();
                                }
                                key = true;
                            }
                        }
                    }
                }
        }
    }

}




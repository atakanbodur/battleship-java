package edu.ozyegin;

import javax.xml.stream.FactoryConfigurationError;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game;
        String ip ;
        int port;
        ServerSocket serverSocket;
        Socket socket;
        String sentence;
        BufferedReader reader;
        DataOutputStream outputStream;
        InputStreamReader inputStreamReader;
        String opponent;


        reader = new BufferedReader(new InputStreamReader(System.in));



        System.out.println("----------------------------------");
        System.out.println("Would you please enter your name? Please...");
        String username = reader.readLine();

        System.out.println("What would you like to do?");
        System.out.println("Write; '1' for to host a game. '2' for to join a game");
        System.out.println("------------------------------------\n");

        sentence=reader.readLine();
        switch (sentence){
            case "1": //host
                System.out.println("Great! So just give me a port number so I can initialize the socket.");
                ip="localhost";
                port = Integer.parseInt(reader.readLine());
                serverSocket = new ServerSocket(port);
                System.out.println("\nA server created on "+ip+" and your port # is "+port);
                socket = serverSocket.accept();
                if (socket.isConnected()){
                    System.out.println("SOMEONE CONNECTED.\n");

                    BufferedReader inFromOpponent = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    DataOutputStream outToOpponent = new DataOutputStream(socket.getOutputStream());
                    outToOpponent.writeBytes(username+"\n");
                    outToOpponent.flush();
                    sentence=inFromOpponent.readLine();
                    opponent = sentence;
                    game = new Game(username);
                    game.setPlayer2(opponent);
                    System.out.println("Your opponent is "+opponent+"\n");


                    while (!game.getMyBoard().isAllBoatsAreCreated()){
                        System.out.println("You have to place your ships to begin.");
                        System.out.println("Your ships are: a Carrier, a Battleship, a Submarine and a Destroyer. They are denoted by:");
                        System.out.println("C,B,S and D respectively. Please enter a symbol among these to place that ship on your board.\n");
                        sentence= reader.readLine();
                            switch (sentence.toUpperCase()){
                                case "C":
                                    if (game.getMyBoard().getRegisteredShips().contains(105)){
                                        System.out.println("Already placed. Sorry :(");
                                        break;
                                    }
                                    else {
                                        Ship carrier = new Ship("C",5,105);
                                        System.out.println("You have selected carrier. Please enter the coordinates that you want your ship to be placed.\n");
                                        String input=reader.readLine().toUpperCase();
                                        System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                        boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                        try {
                                            game.getMyBoard().placeShip(game.getMyBoard().getBoard(), carrier,game.getGameController().convertInputToArr(input), isOnYaxis);
                                            game.getMyBoard().getRegisteredShips().add(105);
                                        }
                                        catch (Exception e){
                                            System.out.println("Something went wrong");
                                        }
                                    }
                                    break;
                                case "B":
                                    if (game.getMyBoard().getRegisteredShips().contains(104)){
                                        System.out.println("Already placed. Sorry :(");
                                        break;
                                    }
                                    else {
                                        Ship battleship = new Ship("B",4,104);
                                        System.out.println("You have selected battleship. Please enter the coordinates that you want your ship to be placed.\n");
                                        String input=reader.readLine();
                                        System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                        boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                        try {
                                            game.getMyBoard().placeShip(game.getMyBoard().getBoard(), battleship,game.getGameController().convertInputToArr(input), isOnYaxis);
                                            game.getMyBoard().getRegisteredShips().add(104);
                                        }
                                        catch (Exception e){
                                            System.out.println("Something went wrong");
                                        }
                                    }
                                        break;
                                case "S":
                                    if (game.getMyBoard().getRegisteredShips().contains(103)){
                                        System.out.println("Already placed. Sorry :(");
                                        break;
                                    }
                                    else {
                                        Ship submarine = new Ship("S",3,103);
                                        System.out.println("You have selected submarine. Please enter the coordinates that you want your ship to be placed.\n");
                                        String input=reader.readLine();
                                        System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                        boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                        try {
                                            game.getMyBoard().placeShip(game.getMyBoard().getBoard(), submarine,game.getGameController().convertInputToArr(input), isOnYaxis);
                                            game.getMyBoard().getRegisteredShips().add(103);
                                        }
                                        catch (Exception e){
                                            System.out.println("Something went wrong");
                                        }
                                    }
                                    break;
                                case "D":
                                    if (game.getMyBoard().getRegisteredShips().contains(102)){
                                        System.out.println("Already placed. Sorry :(");
                                        break;
                                    }
                                    else {
                                        Ship destroyer = new Ship("D",2, 102);
                                        System.out.println("You have selected destroyer. Please enter the coordinates that you want your ship to be placed.\n");
                                        String input=reader.readLine();
                                        System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                        boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                        try {
                                            game.getMyBoard().placeShip(game.getMyBoard().getBoard(), destroyer,game.getGameController().convertInputToArr(input), isOnYaxis);
                                            game.getMyBoard().getRegisteredShips().add(102);
                                        }
                                        catch (Exception e){
                                            System.out.println("Something went wrong");
                                        }
                                    }
                                    break;
                            }
                            if (game.getMyBoard().getRegisteredShips().contains(105)&&game.getMyBoard().getRegisteredShips().contains(104)
                            &&game.getMyBoard().getRegisteredShips().contains(103)&&game.getMyBoard().getRegisteredShips().contains(102)){
                                game.getMyBoard().setAllBoatsAreCreated(true);
                            }
                    }
                    outToOpponent.writeBytes("done\n");
                    outToOpponent.flush();

                    System.out.println("You have created your board. Take a look at it!\n");
                    game.getMyBoard().printBoard(game.getMyBoard().getBoard());

                    System.out.println("We are waiting for your opponent to setup her board.\n");
                    if (inFromOpponent.readLine().equals("done")){
                        System.out.println("Game can BEGIN!!!");
                    }
                }
                break;
            case "2": //join a game
                System.out.println("OK. Who will we connect to? I'm sure we'll win the game.");
                ip=reader.readLine();
                System.out.println("OK. Which port they're on?");
                port = Integer.parseInt(reader.readLine());
                socket = new Socket(ip,port);
                if (socket.isConnected()){
                    System.out.println("We are connected. GREAT!\n");

                    BufferedReader inFromOpponent = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    DataOutputStream outToOpponent = new DataOutputStream(socket.getOutputStream());
                    outToOpponent.writeBytes(username+"\n");
                    outToOpponent.flush();
                    sentence=inFromOpponent.readLine();
                    opponent = sentence;
                    game = new Game(username);
                    game.setPlayer2(opponent);
                    System.out.println("Your opponent is "+opponent+"\n");

                    while (!game.getMyBoard().isAllBoatsAreCreated()){
                        System.out.println("You have to place your ships to begin.");
                        System.out.println("Your ships are: a Carrier, a Battleship, a Submarine and a Destroyer. They are denoted by:");
                        System.out.println("C,B,S and D respectively. Please enter a symbol among these to place that ship on your board.\n");
                        sentence= reader.readLine();
                        switch (sentence.toUpperCase()){
                            case "C":
                                if (game.getMyBoard().getRegisteredShips().contains(105)){
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                }
                                else {
                                    Ship carrier = new Ship("C",5,105);
                                    System.out.println("You have selected carrier. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input=reader.readLine().toUpperCase();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(game.getMyBoard().getBoard(), carrier,game.getGameController().convertInputToArr(input), isOnYaxis);
                                        game.getMyBoard().getRegisteredShips().add(105);
                                    }
                                    catch (Exception e){
                                        System.out.println("Something went wrong");
                                    }
                                }
                                break;
                            case "B":
                                if (game.getMyBoard().getRegisteredShips().contains(104)){
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                }
                                else {
                                    Ship battleship = new Ship("B",4,104);
                                    System.out.println("You have selected battleship. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input=reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(game.getMyBoard().getBoard(), battleship,game.getGameController().convertInputToArr(input), isOnYaxis);
                                        game.getMyBoard().getRegisteredShips().add(104);
                                    }
                                    catch (Exception e){
                                        System.out.println("Something went wrong");
                                    }
                                }
                                break;
                            case "S":
                                if (game.getMyBoard().getRegisteredShips().contains(103)){
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                }
                                else {
                                    Ship submarine = new Ship("S",3,103);
                                    System.out.println("You have selected submarine. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input=reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(game.getMyBoard().getBoard(), submarine,game.getGameController().convertInputToArr(input), isOnYaxis);
                                        game.getMyBoard().getRegisteredShips().add(103);
                                    }
                                    catch (Exception e){
                                        System.out.println("Something went wrong");
                                    }
                                }
                                break;
                            case "D":
                                if (game.getMyBoard().getRegisteredShips().contains(102)){
                                    System.out.println("Already placed. Sorry :(");
                                    break;
                                }
                                else {
                                    Ship destroyer = new Ship("D",2, 102);
                                    System.out.println("You have selected destroyer. Please enter the coordinates that you want your ship to be placed.\n");
                                    String input=reader.readLine();
                                    System.out.println("Is your ship will be placed on horizontally? Answer with true or false");
                                    boolean isOnYaxis = Boolean.parseBoolean(reader.readLine());
                                    try {
                                        game.getMyBoard().placeShip(game.getMyBoard().getBoard(), destroyer,game.getGameController().convertInputToArr(input), isOnYaxis);
                                        game.getMyBoard().getRegisteredShips().add(102);
                                    }
                                    catch (Exception e){
                                        System.out.println("Something went wrong");
                                    }
                                }
                                break;
                        }
                        if (game.getMyBoard().getRegisteredShips().contains(105)&&game.getMyBoard().getRegisteredShips().contains(104)
                                &&game.getMyBoard().getRegisteredShips().contains(103)&&game.getMyBoard().getRegisteredShips().contains(102)){
                            game.getMyBoard().setAllBoatsAreCreated(true);
                            game.getMyBoard().printBoard(game.getMyBoard().getBoard());
                        }
                    }

                    outToOpponent.writeBytes("done\n");
                    outToOpponent.flush();

                    System.out.println("You have created your board. Take a look at it!\n");
                    game.getMyBoard().printBoard(game.getMyBoard().getBoard());

                    System.out.println("We are waiting for your opponent to setup her board.\n");
                    if (inFromOpponent.readLine().equals("done")){
                        System.out.println("Game can BEGIN!!!");

                    }
                }
                break;
            default:
                break;
        }
    }
    //
    // Something's messy with placeShip method, first call is correct but then it gives an exception. Maybe smth wrong with the board we're sending??(done)
    //TODO: Out of bond exception is coming when you enter g;8 horizontal for submarine. It shouldn't.


}

package edu.ozyegin;

import javax.xml.stream.FactoryConfigurationError;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        String ip ;
        int port;
        ServerSocket serverSocket;
        Socket socket;
        String sentence;
        BufferedReader reader;
        DataOutputStream outputStream;
        InputStreamReader inputStreamReader;



        reader = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("LEGEND:");
        System.out.println("----------------------------------");
        System.out.println("Write X if you'd like to quit.\n");

        System.out.println("Would you please enter your name? Please...");
        String username = reader.readLine();

        System.out.println("What would you like to do?");
        System.out.println("Write; '1' for to host a game. '2' for to join a game");
        System.out.println("------------------------------------\n");

        sentence=reader.readLine();
        switch (sentence){
            case "1":
                System.out.println("Great! So just give me a port number so I can initialize the socket.");
                ip="localhost";
                port = Integer.parseInt(reader.readLine());
                serverSocket = new ServerSocket(port);
                System.out.println("\nA server created on "+ip+" and your port # is "+port);
                socket = serverSocket.accept();
                if (socket.isConnected()){
                    System.out.println("SOMEONE CONNECTED. But no one is here?!");
                }
                break;
            case "2":
                System.out.println("OK. Who will we connect to? I'm sure we'll win the game.");
                ip=reader.readLine();
                System.out.println("OK. Which port they're on?");
                port = Integer.parseInt(reader.readLine());
                socket = new Socket(ip,port);
                if (socket.isConnected()){
                    System.out.println("We are connected. GREAT!");
                }
                break;
            default:
                break;
        }
    }

    public static Ship returnShipInstanceFromSymbol(String symbol){
        return switch (symbol){
            case "C" -> (new Ship(symbol, 5, 105));
            case "B" -> (new Ship(symbol, 4, 104));
            case "S" -> (new Ship(symbol, 3, 103));
            case "D" -> (new Ship(symbol, 2, 102));
            default -> (null);
        };
    }


}

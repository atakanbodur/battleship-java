package edu.ozyegin;

public class GameController {
    public GameController() {}
    public String shipNumberToString(int x){
        //why would I even need this? IDK.
        return switch (x){
            case 105 -> ("C");
            case 104 -> ("B");
            case 103 -> ("S");
            case 102 -> ("D");
            default -> ("N");
        };
    }
    public int shipSymbolToInt(String s){
        //again, IDK why would I need this, but it's OK.
        return switch (s){
            case "C" -> (105);
            case "B" -> (104);
            case "S" -> (103);
            case "D" -> (102);
            default -> (-1);
        };
    }
    public int libAlphabet(String s){
        //I will definitely need this though
        return switch (s) {
            case "A" -> (1);
            case "B" -> (2);
            case "C" -> (3);
            case "D" -> (4);
            case "E" -> (5);
            case "F" -> (6);
            case "G" -> (7);
            case "H" -> (8);
            case "I" -> (9);
            case "J" -> (10);
            default -> (-1);
        };
    }
    public int[] convertInputToArr(String input){
        //input is B;2-->y=2, x=2
        String[] inputs = input.split(";");
        try {
            int yCoordinate = libAlphabet(inputs[0]);
            int xCoordinate = Integer.parseInt(inputs[1]);
            int[] coordinates = new int[2];
            coordinates[0] = yCoordinate;
            coordinates[1] = xCoordinate;
            return coordinates;
        }
        catch (Exception e){
            System.out.println("Yeah, quite not the input I would like. I'll just return null and hope that somebody take cares of it :)");
            return null;
        }

    }
}

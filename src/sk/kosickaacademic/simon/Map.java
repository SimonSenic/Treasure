package sk.kosickaacademic.simon;

import java.util.Scanner;

public class Map {
    public int[][] map;
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Map(){
        map = new int[10][10];
        fillMapWithWater();
        fillMapWithLand();
        fillMap();
    }

    public void fillMapWithWater(){ //0 - water, 5 - border
        for(int i=0; i< map.length; i++)
            for(int j=0; j<map[0].length; j++)
                if(i==0 || i== map.length-1 || j==0 || j== map[0].length-1) map[i][j] = 5;
                else map[i][j] = 0;
    }

    public void fillMapWithLand(){ //1 - land
        int[] land = { 35, 36, 37, 38, 39, 42, 45, 55, 75 };
        int count = 0;
        for(int i=0; i< map.length; i++)
            for(int j=0; j<map[0].length; j++){
                count++;
                for(int k=0; k< land.length; k++) if(land[k]==count && map[i][j]==0) map[i][j]=1;
            }

    }

    public void fillMap(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter boat position X: ");
            int x = sc.nextInt();
            System.out.println("Enter boat position Y: ");
            int y = sc.nextInt();
            if(map[x][y] == 5 || map[x][y] == 1){
                System.out.println("Invalid position");
                continue;
            }
            map[x][y] = 2; break;
        }

        while(true){
            System.out.println("Enter treasure position X: ");
            int x = sc.nextInt();
            System.out.println("Enter treasure position Y: ");
            int y = sc.nextInt();
            if(map[x][y] == 5 || map[x][y] == 1 || map[x][y] == 2){
                System.out.println("Invalid position");
                continue;
            }
            map[x][y] = 3; break;
        }
    }

    public void printMap(){
        String color = "";
        for(int i=0; i< map.length; i++){
            for(int j=0; j<map[0].length; j++) {
                switch (map[i][j]) {
                    case 5: color = ANSI_BLACK_BACKGROUND; break;
                    case 0: color = ANSI_CYAN_BACKGROUND; break;
                    case 1: color = ANSI_YELLOW_BACKGROUND; break;
                    case 2: color = ANSI_PURPLE_BACKGROUND; break;
                    case 3: color = ANSI_RED_BACKGROUND; break;
                }
                if(i==0 || i==map.length-1) System.out.print(color +map[i][j] +" " +ANSI_RESET);
                else if(map[i][j]==5) System.out.print(color +map[i][j] +ANSI_RESET);
                else System.out.print(color +" " +map[i][j] +" " +ANSI_RESET);
            }
            System.out.println();
        }
    }

}

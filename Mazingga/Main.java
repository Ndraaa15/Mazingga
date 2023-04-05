package src.Mazingga;

import java.util.*;

/*
 *225150200111006_Gede Indra Adi Brata
 *225150200111007_Achmad Fauzi Aranda
 *225150207111015_Gratia Yudika Morado Silalahi
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sizeMaze = sc.nextInt(); sc.nextLine();

        String validPath = sc.nextLine();
        String [] validPathSplit = validPath.split(" ");
        int [] startCoordinate = Helper.getStartCoordinate(validPathSplit);

        int [] endCoordinate = Helper.getEndCoordinate(validPathSplit);

        int totalPlayer = sc.nextInt(); sc.nextLine();

        Player [] players = new Player[totalPlayer];

        for (int i = 0; i < totalPlayer; i++) {
            String command = sc.nextLine();
            String name = Helper.getName(command);
            String [] movements = Helper.getMovements(command);
            players[i] = new Player(name, Helper.createMazeMap(sizeMaze, new NodeMap[sizeMaze][sizeMaze], validPathSplit), movements, startCoordinate, endCoordinate);
        }

        for (int i = 0; i < totalPlayer; i++) {
            System.out.print("\n" + players[i].getName() + "\n");
            players[i].printMazeMap();
        }
    }
}

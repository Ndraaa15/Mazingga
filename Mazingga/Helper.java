package src.Mazingga;

class Helper {
    static String [] getMovements (String command){
        String [] commands = command.split(" ");
        String [] movements = new String[commands.length - 1];

        for (int i = 1; i < commands.length; i++) {
            movements[i - 1] = commands[i];
        }
        return movements;
    }

    static String getName (String command){
        String [] commands = command.split(" ");
        return commands[0];
    }

    static NodeMap[][] createMazeMap (int sizeMaze, NodeMap [][]mazeMap, String [] validPathSplit){
        //Menambahkan NodeMap (value, isValidPath) pada maze map yang akan dibuat
        for (int i = 0; i < sizeMaze ; i++) {
            for (int j = 0; j < sizeMaze; j++) {
                mazeMap[sizeMaze - j - 1][i] = new NodeMap (  "XX" , false);
            }
        }

        //Mengubah nilai NodeMap yang ada di MazeMap menjadi valid path yang sebelumnya false semua
        int counter = 0;
        int lastIndex = validPathSplit.length - 1;
        for (String s : validPathSplit) {
            String[] validCoordinate = s.split(",");
            int x = Integer.parseInt(validCoordinate[0]);
            int y = Integer.parseInt(validCoordinate[1]);
            if (counter == 0){
                mazeMap[sizeMaze - 1- y][x].isVisited = true;
                mazeMap[sizeMaze - 1 -y][x].value = "01";
            } else if (counter == lastIndex) {
                mazeMap[sizeMaze - 1- y][x].isVisited = true;
                mazeMap[sizeMaze - 1 -y][x].value = "FF";
            }else {
                mazeMap[sizeMaze - 1- y][x].isVisited = true;
                mazeMap[sizeMaze - 1 -y][x].value = "00";
            }
            counter++;
        }
        return mazeMap;
    }

    static String formatNumber (Integer numberPath){
        return numberPath <= 9 ? "0" + numberPath : numberPath.toString();
    }

    static int [] getStartCoordinate (String [] validPathSplit){
        String [] coordinate  = validPathSplit[0].split(",");
        int [] startCoordinate = new int[2];
        startCoordinate[0] = Integer.parseInt(coordinate[0]);
        startCoordinate[1] = Integer.parseInt(coordinate[1]);

        return startCoordinate;
    }

    static int [] getEndCoordinate (String [] validPathSplit){
        String [] coordinate  = validPathSplit[validPathSplit.length - 1].split(",");
        int [] endCoordinate = new int[2];
        endCoordinate[0] = Integer.parseInt(coordinate[0]);
        endCoordinate[1] = Integer.parseInt(coordinate[1]);

        return endCoordinate;
    }

}

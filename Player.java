package src.Mazingga;

class Player {
    private String name;
    private int [] startCoordinate;
    private int [] endCoordinate;
    private NodeMap [][] mazeMap;
    private String [] movements;
    private boolean isFinish = false;
    private Queue path = new Queue();
    private int step = 0;


    public Player(String name, NodeMap[][] mazeMap, String[] movements, int [] startCoordinate, int [] endCoordinate) {
        this.name = name;
        this.mazeMap = mazeMap;
        this.movements = movements;
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        mazeSolve(startCoordinate[0], startCoordinate[1]);
    }

    public String getName() {
        return name;
    }

    public void printMazeMap (){
        int counter = 0;

        while (counter < path.getSize()){
            Node temp = path.dequeue();
            mazeMap[temp.coordinate.x()][temp.coordinate.y()].value = temp.data;
            counter++;
        }

        for (int i = 0; i < mazeMap.length; i++) {
            for (int j = 0; j < mazeMap.length; j++) {
                System.out.printf("[%s]", mazeMap[i][j].value);
            }
            System.out.println();
        }
    }

    public void mazeSolve (int x, int y){
        if (x < 0 || x > mazeMap.length  - 1 || y < 0 || y > mazeMap.length - 1) {
            return;
        }

        if (!mazeMap[mazeMap.length - 1 - y][x].isVisited){
            return;
        }


        if (x == endCoordinate[0] && y == endCoordinate[1]){
            step++;
            path.enqueue(Helper.formatNumber(step), new Coordinate(mazeMap.length - 1 - y, x));
            isFinish = true;
            return;
        }

        mazeMap[mazeMap.length - 1 - y][x].isVisited = false;

        if (!isFinish){
            step++;
            path.enqueue(Helper.formatNumber(step), new Coordinate(mazeMap.length - 1 - y, x));
        }


        for (int i = 0; i < movements.length; i++) {
            if (movements[i].equals("UP")){
                mazeSolve(x, y + 1);
            } else if (movements[i].equals("DOWN")) {
                mazeSolve(x,y - 1);
            } else if (movements[i].equals("RIGHT")) {
                mazeSolve(x + 1, y);
            } else if (movements[i].equals("LEFT")){
                mazeSolve(x - 1, y);
            }
        }
    }

    int getStep (){
        return step;
    }
}

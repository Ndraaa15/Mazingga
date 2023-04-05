package src.Mazingga;


class MazeSolver {
    void checkRoute (boolean[][]maze, String [] direction, int x, int y, int a, int b, Queue path, int numberPath){
        int i = 1;
        while (i < direction.length - 1){
            if (direction[i].equals("UP")){
                if (y == maze.length - 1){
                    continue;
                }else {
                    y+=1;
                    if (maze[x][y]){
                        if (x == a && y == b){
                            return;
                        }
                        maze[x][y] = false;
                        path.enqueue(x + "," + y + "," + numberPath);
                        checkRoute(maze, direction, x, y, a, b, path,numberPath+1);
                    }
                }
            } else if (direction[i].equals("DOWN")) {
                if (y == 0){
                    continue;
                }else {
                    y-=1;
                    if (maze[x][y]){
                        if (x == a && y == b){
                            return;
                        }
                        maze[x][y] = false;
                        path.enqueue(x + "," + y + "," + numberPath);
                        checkRoute(maze, direction, x, y, a, b, path,numberPath+1);
                    }
                }
            } else if (direction[i].equals("LEFT")) {
                if (x == 0){
                    continue;
                }else {
                    x-=1;
                    if (maze[x][y]){
                        if (x == a && y == b){
                            return;
                        }
                        maze[x][y] = false;
                        path.enqueue(x + "," + y + "," + numberPath);
                        checkRoute(maze, direction, x, y, a, b, path,numberPath+1);
                    }
                }
            } else if (direction[i].equals("RIGHT")) {
                if (x == maze.length - 1){
                    continue;
                }else {
                    x+=1;
                    if (maze[x][y]){
                        if (x == a && y == b){
                            return;
                        }
                        maze[x][y] = false;
                        path.enqueue(x + "," + y + "," + numberPath);
                        checkRoute(maze, direction, x, y, a, b, path,numberPath+1);
                    }
                }
            }
            i++;
        }
    }
}






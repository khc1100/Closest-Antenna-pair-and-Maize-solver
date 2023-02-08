import java.util.ArrayList;

public class Aux {
    private static final int dimension = 15;
    private ArrayList<Point2D> visited = new ArrayList<>();

    boolean canMove(char maze[][], boolean found, int x, int y) {
        if(visited.contains(new Point2D(x,y)))
            return false;

        if(found) {
            return (x >= 0 && x < dimension && y >= 0 && y < dimension && maze[x][y] == '1');
        } else {
            return (x >= 0 && x < dimension && y >= 0 && y < dimension && (maze[x][y] == '0' || maze[x][y] == 'k' || maze[x][y] == '1'));
        }

    }


    public boolean validMazeSolve(char maze[][]) {
        assert (maze.length == dimension);
        for(int i=0 ; i<dimension ;++i){
            assert (maze[i].length == dimension);
        }

        if(maze[14][13] != '1')
            return false;

        return validMazeSolveUtil(maze, false, 0, 1);
    }

    // A recursive function to solve Maze problem
    boolean validMazeSolveUtil(char maze[][], boolean found, int x, int y) {
        // if (x, y) is the exit, and we have previously found the key, return true
        if (x == dimension - 1 && y == dimension - 2) {
            if(found)
                return maze[x][y]=='1';

            return false;
        }

        // Check if maze[x][y] is valid
        if (canMove(maze, found, x, y)) {

            if(maze[x][y] == 'k'){
                found = true;
                visited = new ArrayList<>();
            }

            visited.add(new Point2D(x, y));
            //Move right
            if (validMazeSolveUtil(maze, found, x + 1, y)) {
                return true;
                //Move left
            } else if (validMazeSolveUtil(maze, found, x - 1, y)) {
                return true;
                //Move downwards
            } else if (validMazeSolveUtil(maze, found, x, y + 1)) {
                return true;
                //Move upwards
            } else if (validMazeSolveUtil(maze, found, x, y - 1)) {
                return true;
            }

            visited.remove(new Point2D(x,y));

        }

        return false;
    }

}

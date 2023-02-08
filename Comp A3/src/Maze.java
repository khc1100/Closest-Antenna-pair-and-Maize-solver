import java.io.*;

public class Maze {
    private final int dimension = 15;
    public int counter = 0;

    // Checks if we can move to (x,y)
    boolean canMove(char maze[][], boolean found, int x, int y) {
        if(found) {
            return (x >= 0 && x < dimension && y >= 0 && y < dimension && (maze[x][y] == '.' || maze[x][y] == '0'));
        } else {
            return (x >= 0 && x < dimension && y >= 0 && y < dimension && (maze[x][y] == '.' || maze[x][y] == 'k'));
        }
    }


    boolean solveMaze(char maze[][]) {

        if (!solveMazeUtil(maze, false, 0, 1)) {

            System.out.print("Solution doesn't exist\n");
            return false;
        }

        return true;
    }

    // A recursive function to solve Maze problem
    boolean solveMazeUtil(char maze[][], boolean found, int x, int y) {
        // please do not delete/modify the next line!
        counter++;
        System.out.println(counter);
        char oldtile = maze[x][y];
        if(maze[x][y]=='k'){
            found = true;

        }
        else{
            if(found==false){
                maze[x][y]='0';
            }
            else {
                maze[x][y]='1';
            }

        }
        System.out.println("counter: "+counter);
        System.out.println("x :" + x);
        System.out.println("y :" + y);
        System.out.println(found);
        printMaze(maze);
        if(x == 14 && y == 13 && found==true){
            return true;
        }
        //INSERT CODE HERE WAAAAA
        //char lastTile = maze[x][y];
        //BASE CASE
        //if current tile is on true set found == to true

        if(counter!=0){
            if(x==1 && y==0)return false;
        }


        if ( x == 14 && y == 13 && found == true) {
            return true;
        }


        if(canMove(maze,found,x+1,y)){
            if(solveMazeUtil(maze,found,x+1,y) == true) return true;
        }
        if(canMove(maze,found,x-1,y)){
            if(solveMazeUtil(maze, found,x-1,y) == true) return true;
        }
        if(canMove(maze,found,x,y-1)){
            if(solveMazeUtil(maze,found,x,y-1) == true) return true;
        }
        if(canMove(maze,found,x,y+1)){
            if(solveMazeUtil(maze,found,x,y+1)==true)return true;
        }

       if(found){
           maze[x][y]=oldtile;
      }
       else {
           maze[x][y] = '.';
        }

        // Insert your solution here and modify the return statement.
        return false;
    }

    //Loads maze from text file
    char[][] loadMaze(String directory) throws IOException{
        char[][] maze = new char[dimension][dimension];

        try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length(); col++){
                    maze[row][col] = line.charAt(col);
                }
                row++;
            }
        }
        return maze;

    }

    //Prints maze
    private static void printMaze(char[][] maze) {
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze[0].length; j++)
                System.out.print(" " + maze[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String args[]) {
        Maze m = new Maze();
        for (int i = 0; i < 4; i++) {
            try {
                char[][] myMaze = m.loadMaze("src/mazes/m"+i+".txt");
                System.out.println("\nMaze "+i);
                Maze.printMaze(myMaze);
                if(m.solveMaze(myMaze)){
                    Maze.printMaze(myMaze);
                }
            } catch (Exception e){
                System.out.print("File was not found.");
            }

        }
    }
}
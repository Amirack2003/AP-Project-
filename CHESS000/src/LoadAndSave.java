import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class LoadAndSave {
        File file = new File("Src.txt");

        /**
         * this method saves the game in file
         * @param board the map of the current game
         * @param turn shows the turn to be played
         * @param robotColor shows the color of robot (0 if its 2Player)
         */
        public void save(int[][] board, boolean turn, int robotColor) throws FileNotFoundException {
                PrintStream output = new PrintStream(file);

                output.println(robotColor);
                output.println(turn);
                for(int i = 0 ; i < 8; i++){
                        for(int j = 0 ; j < 8; j++){
                                output.print(board[i][j] + " ");
                        }
                        output.println();
                }
                output.close();
        }

        /**
         * this method loads the game
         * @return temp the game
         */
        public Board load() throws IOException {
                int[][] arr = new int[8][8];
                boolean turn;
                int robotColor;

                Scanner input = new Scanner(file);

                robotColor = input.nextInt();
                turn = input.nextBoolean();
                for(int i = 0 ; i < 8; i++){
                        for(int j = 0 ; j < 8 ; j++){
                                arr[i][j] = input.nextInt();
                        }
                }
                Board temp = new Board();
                temp.setTurn(turn);
                temp.setRobotColor(robotColor);
                temp.setMap(arr);
                input.close();
                return temp;
        }
        /**
         * this method checks the last save to exist
         * @return true if the last save exist
         */
        public boolean IsSaved() throws FileNotFoundException {
                Scanner input = new Scanner(file);
                if(input.hasNext()){
                        input.close();
                        return true;
                }
                input.close();
                return false;
        }
}

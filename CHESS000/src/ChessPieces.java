import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ChessPieces{
    protected Point position;
    protected int type;
    File fileBlack,fileWhite;
    public ChessPieces(int x,int y,int type){
        position = new Point(x,y);
        this.type = type;
    }
    public void setFileWhite(File fileWhite) {
        this.fileWhite = fileWhite;
    }

    public void setFileBlack(File fileBlack) {
        this.fileBlack = fileBlack;
    }

    public File getFileWhite() {
        return fileWhite;
    }

    public File getFileBlack() {
        return fileBlack;
    }

    /**
     * this method resize Icon image
     * @param icon the Chesspiece
     * @param resizedHeight the size of height
     * @param resizedWidth the size of width
     */
    public Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        if(resizedHeight == 0 )resizedHeight = 1;
        if(resizedWidth == 0)resizedWidth = 1;
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    /**
     * this method checl the selected area to be valid
     * @param x x position of area
     * @param y y position of area
     */
    public boolean valid(int x,int y){
        if( (x != this.position.getX() || y != this.position.getY()) && x < 8 && x > -1 && y < 8 && y > -1){
            return true;
        }
        return false;
    }
}
class KING extends ChessPieces{
    public KING(int x, int y, int type) {
        super(x, y, type);
        setFileBlack(new File("IMG/BKING.PNG"));
        setFileWhite(new File("IMG/WKING.PNG"));
    }
    /**
     * this method shows the areas that the selected Chesspiece can go
     * @param map the map of the game/board
     * @return ans the array list of the areas that we can move the chesspiece
     */
    public ArrayList<Point> canGo(int[][] map){
        ArrayList<Point>ans = new ArrayList<>();
        for(int i = - 1 ; i < 2 ; i++){
            for(int j = -1 ; j < 2 ; j++){
                int x = this.position.getX() + i , y = this.position.getY() + j;
                if(this.valid(x,y)){
                    if(map[x][y]*this.type <= 0)ans.add(new Point(x,y));
                }
            }
        }
        return ans;
    }
}
class QUEEN extends ChessPieces{
    public QUEEN(int x, int y, int type) {
        super(x, y, type);
        setFileBlack(new File("IMG/BQUEEN.PNG"));
        setFileWhite(new File("IMG/WQUEEN.PNG"));
    }
    /**
     * this method shows the areas that the selected Chesspiece can go
     * @param map the map of the game/board
     * @return ans the array list of the areas that we can move the chesspiece
     */
    public ArrayList<Point> canGo(int[][] map){
        ArrayList<Point>ans = new ArrayList<>();
        boolean Flag = true;
        for(int i = 1 ; i < 9 && Flag; i++){
            int x = this.position.getX() + i, y = this.position.getY();
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY()));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = -1 ; i > -9 && Flag ; i--){
            int x = this.position.getX() + i, y = this.position.getY();
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY()));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int j = 1 ; j < 9 && Flag; j++){
            int x = this.position.getX(), y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX(),this.position.getY() + j));
                    Flag = (map[x][y] <= 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int j = -1 ; j > -9 && Flag; j--){
            int x = this.position.getX(), y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX(),this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = 1 , j = 1 ; i < 9 && Flag; i++,j++){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = 1 , j = -1 ; i < 9 && Flag; i++,j--){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = -1 , j = 1 ; i > -8 && Flag; i--,j++){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = -1 , j = -1 ; i > -8 && Flag; i--,j--){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        return ans;
    }
}
class ROOK extends ChessPieces{
    public ROOK(int x, int y, int type) {
        super(x, y, type);
        setFileBlack(new File("IMG/BROOK.PNG"));
        setFileWhite(new File("IMG/WROOK.PNG"));
    }
    /**
     * this method shows the areas that the selected Chesspiece can go
     * @param map the map of the game/board
     * @return ans the array list of the areas that we can move the chesspiece
     */
    public ArrayList<Point> canGo(int[][] map){
        ArrayList<Point>ans = new ArrayList<>();
        boolean Flag = true;
        for(int i = 1 ; i < 9 && Flag; i++){
            int x = this.position.getX() + i, y = this.position.getY();
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY()));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = -1 ; i > -9 && Flag ; i--){
            int x = this.position.getX() + i, y = this.position.getY();
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY()));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int j = 1 ; j < 9 && Flag; j++){
            int x = this.position.getX(), y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX(),this.position.getY() + j));
                    Flag = (map[x][y] <= 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int j = -1 ; j > -9 && Flag; j--){
            int x = this.position.getX(), y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX(),this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        return ans;
    }
}
class BISHOP extends ChessPieces{
    public BISHOP(int x, int y, int type) {
        super(x, y, type);
        setFileBlack(new File("IMG/BBISHOP.PNG"));
        setFileWhite(new File("IMG/WBISHOP.PNG"));
    }
    /**
     * this method shows the areas that the selected Chesspiece can go
     * @param map the map of the game/board
     * @return ans the array list of the areas that we can move the chesspiece
     */
    public ArrayList<Point> canGo(int[][] map){
        ArrayList<Point>ans = new ArrayList<>();
        boolean Flag = true;
        for(int i = 1 , j = 1 ; i < 9 && Flag; i++,j++){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = 1 , j = -1 ; i < 9 && Flag; i++,j--){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = -1 , j = 1 ; i > -8 && Flag; i--,j++){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        Flag = true;
        for(int i = -1 , j = -1 ; i > -8 && Flag; i--,j--){
            int x = this.position.getX() + i, y = this.position.getY() + j;
            if(this.valid(x,y)){
                if(map[x][y]*this.type <= 0){
                    ans.add(new Point(this.position.getX() + i,this.position.getY() + j));
                    Flag = (map[x][y] == 0);
                }
                else Flag = false;
            }
        }
        return ans;
    }
}
class KNIGHT extends ChessPieces{
    public KNIGHT(int x, int y, int type) {
        super(x, y, type);
        setFileBlack(new File("IMG/BKNIGHT.PNG"));
        setFileWhite(new File("IMG/WKNIGHT.PNG"));
    }
    /**
     * this method shows the areas that the selected Chesspiece can go
     * @param map the map of the game/board
     * @return ans the array list of the areas that we can move the chesspiece
     */
    public ArrayList<Point> canGo(int[][] map){
        ArrayList<Point>ans = new ArrayList<>();
        for(int i = -1 ; i < 2 ; i++){
            for(int j = -1 ; j < 2 ; j++){
                if(0 == j || i == 0)continue;
                int x = this.position.getX() + (i*2) , y = this.position.getY() + j;
                if(this.valid(x,y)){
                    if(map[x][y]*this.type <= 0)ans.add(new Point(x,y));
                }
            }
        }
        for(int i = -1 ; i < 2 ; i++){
            for(int j = -1 ; j < 2 ; j++){
                if(0 == j || i == 0)continue;
                int x = this.position.getX() + i , y = this.position.getY() + (j*2);
                if(this.valid(x,y)){
                    if(map[x][y]*this.type <= 0)ans.add(new Point(x,y));
                }
            }
        }
        return ans;
    }
}
class PAWN extends ChessPieces{
    public PAWN(int x, int y, int type) {
        super(x, y, type);
        setFileBlack(new File("IMG/BPAWN.PNG"));
        setFileWhite(new File("IMG/WPAWN.PNG"));
    }
    /**
     * this method shows the areas that the selected Chesspiece can go
     * @param map the map of the game/board
     * @return ans the array list of the areas that we can move the chesspiece
     */
    public ArrayList<Point> canGo(int[][] map){
        ArrayList<Point>ans = new ArrayList<>();
        int x = this.position.getX(), y = this.position.getY();
        if(map[x][y] < 0){
            if(this.valid(x + 1,y) && map[x+1][y] == 0)ans.add(new Point(x+1,y));
            if(this.valid(x + 1,y + 1) && map[x + 1][y + 1] > 0)ans.add(new Point(x+1,y+1));
            if(this.valid(x + 1,y - 1) && map[x + 1][y - 1] > 0)ans.add(new Point(x+1,y-1));
        }
        else{
            if(this.valid(x - 1,y) && map[x-1][y] == 0)ans.add(new Point(x-1,y));
            if(this.valid(x - 1,y - 1) && map[x - 1][y - 1] < 0)ans.add(new Point(x-1,y-1));
            if(this.valid(x - 1,y + 1) && map[x - 1][y + 1] < 0)ans.add(new Point(x-1,y+1));
        }
        return ans;
    }
}
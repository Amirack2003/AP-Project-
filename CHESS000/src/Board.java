import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Board {

    static boolean turn;
    static int robotColor;
    static AIPlayer aiPlayer = new AIPlayer();
    private static JPanel panel = new JPanel();
    static JButton buttons[][] = new JButton[8][8];
    static Point lastEvent = new Point(0,0);
    LoadAndSave loadAndSave = new LoadAndSave();
    private JLabel whos_turn_is = new JLabel();

    /**
     * this method set robot color
     * @param color color robot
     */
    public void setRobotColor(int color){
        this.robotColor = color;
    }

    /**
     * this method set whos turn it is
     * @param turn turn
     */
    public void setTurn(boolean turn){
        this.turn = !turn;
        this.setWhos_turn_is_text();
        this.turn = turn;
    }

    static int map[][] = {
            {-3,-5,-4,-1,-2,-4,-5,-3},
            {-6,-6,-6,-6,-6,-6,-6,-6},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {6,6,6,6,6,6,6,6},
            {3,5,4,2,1,4,5,3}};
    boarderPanels White = new boarderPanels() , Black = new boarderPanels();
    PAWN blackPawn;
    PAWN whitePawn;
    ROOK blackRook;
    ROOK whiteRook;
    KNIGHT blackKnight;
    KNIGHT whiteKnight;
    BISHOP blackBishop;
    BISHOP whiteBishop;
    KING blackKing;
    KING whiteKing;
    QUEEN blackQueen;
    QUEEN whiteQueen;



    public Board() throws IOException {
        panel.setLayout(new GridLayout(8,8));
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(listener);
                buttons[i][j].setBorder(null);
                panel.add(buttons[i][j]);
            }
        }
        setChessPieces();
        setWhos_turn_is_text();
    }

    public JLabel getWhos_turn_is() {
        return whos_turn_is;
    }
    public void setWhos_turn_is_text(){
        if(!turn)this.whos_turn_is.setText("White turn");
        else whos_turn_is.setText("Black turn");
    }

    /**
     * this method updates the whole map and then show it
     * @param temp its the new map
     */
    public void setMap(int[][] temp) throws IOException {
        for(int i = 0; i < 8; i++){
            System.arraycopy(temp[i], 0, map[i], 0, 8);
        }
        setChessPieces();
    }

    /**
     * this method place the selected Chesspiece on the selected area
     * @param pos First position of selected Chesspiece
     * @param x x position of the selected area
     * @param y y position of the selected area
     * @param type type of the selected Chesspiece
     */
    public void updMap(Point pos,int x,int y,int type){
        map[x][y] = type;
        map[pos.getX()][pos.getY()] = 0;
        try {
            setChessPieces();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method adds Eliminated Chesspieces to the boarders
     */
    public void setEliminatedPieces() throws IOException {
        int wide = 50 , height = 50;
        Black.setId(0);
        White.setId(0);
        for(int i = 0 ; i < 6 ; i++){
            for(int j = 1 ; j <= White.getnOfPieces(i) ; j++){
                if(i+1 == 1) {
                    whiteKing = new KING(i, j, 1);
                    BufferedImage image = ImageIO.read(whiteKing.getFileWhite());
                    White.setIcon(whiteKing.resizeIcon(new ImageIcon(image),wide,height));
                    JOptionPane.showMessageDialog(null, "BLACK WIN");
                    System.exit(0);
                }
                if(i+1 == 2){
                    whiteQueen = new QUEEN(i,j,2);
                    BufferedImage image = ImageIO.read(whiteQueen.getFileWhite());
                    White.setIcon(whiteKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i+1 == 3) {
                    whiteRook = new ROOK(i, j, 3);
                    BufferedImage image = ImageIO.read(whiteRook.getFileWhite());
                    White.setIcon(whiteKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i+1 == 4) {
                    whiteBishop = new BISHOP(i, j, 4);
                    BufferedImage image = ImageIO.read(whiteBishop.getFileWhite());
                    White.setIcon(whiteKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i+1 == 5) {
                    whiteKnight = new KNIGHT(i, j, 5);
                    BufferedImage image = ImageIO.read(whiteKnight.getFileWhite());
                    White.setIcon(whiteKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i+1 == 6) {
                    whitePawn = new PAWN(i, j, 6);
                    BufferedImage image = ImageIO.read(whitePawn.getFileWhite());
                    White.setIcon(whiteKing.resizeIcon(new ImageIcon(image),wide,height));
                }
            }
            for(int j = 1 ; j <= Black.getnOfPieces(i) ; j++){
                if(i*-1-1 == -1){
                    blackKing = new KING(i,j,-1);
                    BufferedImage image = ImageIO.read(blackKing.getFileBlack());
                    Black.setIcon(blackKing.resizeIcon(new ImageIcon(image),wide,height));
                    JOptionPane.showMessageDialog(null, "WHITE WIN");
                    System.exit(1);
                }
                if(i*-1-1 == -2) {
                    blackQueen = new QUEEN(i, j, -2);
                    BufferedImage image = ImageIO.read(blackQueen.getFileBlack());
                    Black.setIcon(blackKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i*-1-1 == -3) {
                    blackRook = new ROOK(i, j, -3);
                    BufferedImage image = ImageIO.read(blackRook.getFileBlack());
                    Black.setIcon(blackKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i*-1-1 == -4) {
                    blackBishop = new BISHOP(i, j, -4);
                    BufferedImage image = ImageIO.read(blackBishop.getFileBlack());
                    Black.setIcon(blackKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i*-1-1 == -5) {
                    blackKnight = new KNIGHT(i, j, -5);
                    BufferedImage image = ImageIO.read(blackKnight.getFileBlack());
                    Black.setIcon(blackKing.resizeIcon(new ImageIcon(image),wide,height));
                }
                if(i*-1-1 == -6) {
                    blackPawn = new PAWN(i, j, -6);
                    BufferedImage image = ImageIO.read(blackPawn.getFileBlack());
                    Black.setIcon(blackKing.resizeIcon(new ImageIcon(image),wide,height));
                }
            }
        }
    }

    /**
     * this method set the color of the playground and places the Chesspieces
     * Also counts the number of each Chesspieces for setEliminatedPieces() function
     */
    public void setChessPieces() throws IOException {
        Black.setnOfPieces();
        White.setnOfPieces();
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                if((i+j)%2 == 0){
                    buttons[i][j].setBackground(Color.GRAY);
                }
                else buttons[i][j].setBackground(Color.WHITE);
                int wide = 50 , height = 50 ;
                //**KING
                if(map[i][j] == 1){
                    whiteKing = new KING(i,j,1);
                    BufferedImage image = ImageIO.read(whiteKing.getFileWhite());
                    buttons[i][j].setIcon(whiteKing.resizeIcon(new ImageIcon(image),wide,height));
                    White.decrease(1);
                }
                if(map[i][j] == -1){
                    blackKing = new KING(i,j,-1);
                    BufferedImage image = ImageIO.read(blackKing.getFileBlack());
                    buttons[i][j].setIcon(blackKing.resizeIcon(new ImageIcon(image),wide,height));
                    Black.decrease(1);
                }
                //**QUEEN
                if(map[i][j] == 2){
                    whiteQueen = new QUEEN(i,j,2);
                    BufferedImage image = ImageIO.read(whiteQueen.getFileWhite());
                    buttons[i][j].setIcon(whiteQueen.resizeIcon(new ImageIcon(image),wide,height));
                    White.decrease(2);
                }
                if(map[i][j] == -2){
                    blackQueen = new QUEEN(i,j,-2);
                    BufferedImage image = ImageIO.read(blackQueen.getFileBlack());
                    buttons[i][j].setIcon(blackQueen.resizeIcon(new ImageIcon(image),wide,height));
                    Black.decrease(2);
                }
                //**ROOK
                if(map[i][j] == 3){
                    whiteRook = new ROOK(i,j,3);
                    BufferedImage image = ImageIO.read(whiteRook.getFileWhite());
                    buttons[i][j].setIcon(whiteRook.resizeIcon(new ImageIcon(image),wide,height));
                    White.decrease(3);
                }
                if(map[i][j] == -3){
                    blackRook = new ROOK(i,j,-3);
                    BufferedImage image = ImageIO.read(blackRook.getFileBlack());
                    buttons[i][j].setIcon(blackRook.resizeIcon(new ImageIcon(image),wide,height));
                    Black.decrease(3);
                }
                //**BISHOP
                if(map[i][j] == 4){
                    whiteBishop = new BISHOP(i,j,4);
                    BufferedImage image = ImageIO.read(whiteBishop.getFileWhite());
                    buttons[i][j].setIcon(whiteBishop.resizeIcon(new ImageIcon(image),wide,height));
                    White.decrease(4);
                }
                if(map[i][j] == -4){
                    blackBishop = new BISHOP(i,j,-4);
                    BufferedImage image = ImageIO.read(blackBishop.getFileBlack());
                    buttons[i][j].setIcon(blackBishop.resizeIcon(new ImageIcon(image),wide,height));
                    Black.decrease(4);
                }
                //**KNIGHT
                if(map[i][j] == 5){
                    whiteKnight = new KNIGHT(i,j,5);
                    BufferedImage image = ImageIO.read(whiteKnight.getFileWhite());
                    buttons[i][j].setIcon(whiteKnight.resizeIcon(new ImageIcon(image),wide,height));
                    White.decrease(5);
                }
                if(map[i][j] == -5){
                    blackKnight = new KNIGHT(i,j,-5);
                    BufferedImage image = ImageIO.read(blackKnight.getFileBlack());
                    buttons[i][j].setIcon(blackKnight.resizeIcon(new ImageIcon(image),wide,height));
                    Black.decrease(5);
                }
                //**PAWN
                if(map[i][j] == 6){
                    whitePawn = new PAWN(i,j,6);
                    BufferedImage image = ImageIO.read(whitePawn.getFileWhite());
                    buttons[i][j].setIcon(whitePawn.resizeIcon(new ImageIcon(image),wide,height));
                    White.decrease(6);
                }
                if(map[i][j] == -6){
                    blackPawn = new PAWN(i,j,-6);
                    BufferedImage image = ImageIO.read(blackPawn.getFileBlack());
                    buttons[i][j].setIcon(blackPawn.resizeIcon(new ImageIcon(image),wide,height));
                    Black.decrease(6);
                }
                //**null
                if(map[i][j] == 0){
                    buttons[i][j].setIcon(null);
                }
            }
        }
        if(!turn && robotColor != 0){
            turn = !turn;
            setMap(aiPlayer.nextMove(map,robotColor > 0));
            setWhos_turn_is_text();
        }
        loadAndSave.save(map,turn,robotColor);
        setEliminatedPieces();
    }

    /**
     * this method returns the main panel
     * @return Chessboard
     */
    public JPanel getPanel() {
        return this.panel;
    }


    ActionListener listener = new ActionListener() {
        @Override
        /**
         * this method is the way that Humanplayer play the game
         * It shows the places which selected Chesspieces can go
         * It only allows to play in your turn
         * It only allows to move your own ChessPieces
         * After Each valid movement ; setChessPieces()  got called
         * @param e the selected button/area
         */
        public void actionPerformed(ActionEvent e) {
            for(int i = 0 ; i < 8 ; i++){
                for(int j = 0 ; j < 8 ; j++){
                    if (e.getSource() == buttons[i][j] && (turn || robotColor == 0) ) {
                        if(map[i][j] != 0 && buttons[i][j].getBackground() != Color.red && buttons[i][j].getBackground() != Color.green){
                            try {
                                setChessPieces();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            ArrayList<Point>Places = new ArrayList<>();
                            if(map[i][j] == 1 || map[i][j] == -1){
                                KING king = new KING(i,j,map[i][j]);
                                Places.addAll(king.canGo(map));
                            }
                            else if(map[i][j] == 2 || map[i][j] == -2){
                                QUEEN queen = new QUEEN(i,j,map[i][j]);
                                Places.addAll(queen.canGo(map));
                            }
                            else if(map[i][j] == 3 || map[i][j] == -3){
                                ROOK rook = new ROOK(i,j,map[i][j]);
                                Places.addAll(rook.canGo(map));
                            }
                            else if(map[i][j] == 4 || map[i][j] == -4){
                                BISHOP bishop = new BISHOP(i,j,map[i][j]);
                                Places.addAll(bishop.canGo(map));
                            }
                            else if(map[i][j] == 5 || map[i][j] == -5){
                                KNIGHT knight = new KNIGHT(i,j,map[i][j]);
                                Places.addAll(knight.canGo(map));
                            }
                            else{
                                PAWN pawn = new PAWN(i,j,map[i][j]);
                                Places.addAll(pawn.canGo(map));
                            }
                            if(robotColor == 0){
                                if(turn == false && map[i][j] > 0)Places.clear();
                                if(turn && map[i][j] < 0)Places.clear();
                            }
                            if(robotColor == -1 && map[i][j] < 0)Places.clear();
                            if(robotColor == 1 && map[i][j] > 0)Places.clear();
                            for(Point temp : Places){
                                if(map[temp.getX()][temp.getY()] != 0){
                                    buttons[temp.getX()][temp.getY()].setBackground(Color.red);
                                }
                                else{
                                    buttons[temp.getX()][temp.getY()].setBackground(Color.green);
                                }
                            }
                            lastEvent = new Point(i,j);
                        }
                        else if(buttons[i][j].getBackground() == Color.green){
                            setWhos_turn_is_text();
                            updMap(lastEvent,i,j,map[lastEvent.getX()][lastEvent.getY()]);
                            turn = !turn;
                            try {
                                setChessPieces();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        else if(map[i][j] != 0 && buttons[i][j].getBackground() == Color.red){
                            setWhos_turn_is_text();
                            updMap(lastEvent,i,j,map[lastEvent.getX()][lastEvent.getY()]);
                            turn = !turn;
                            try {
                                setChessPieces();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        else {
                            try {
                                setChessPieces();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    else if(e.getSource() == buttons[i][j]){
                        try {
                            setChessPieces();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    };

}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class colorSelectMenu {
    static JPanel panel = new JPanel(), panel2 = new JPanel();
    static JButton White = new JButton();
    static JButton Black = new JButton();
    File file = new File("IMG/menu.jpg");


    public colorSelectMenu() {
        panel.setLayout(new GridLayout(2, 1));
        panel2.setLayout(new GridLayout(1, 2));
        panel.setBackground(Color.black);
        try {
            BufferedImage image = ImageIO.read(file);
            JLabel pic = new JLabel(new ImageIcon(image));
            panel.add(pic);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        Black.setText("BLACK");
        White.setText("WHITE");
        panel2.add(Black);
        panel2.add(White);
        panel.add(panel2);


        Black.addActionListener(new ActionListener() {
            @Override
            /**
             * this method run the game human vs robot / human is black
             */
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setBordLayout();
                Board board = null;
                try {
                    board = new Board();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                board.setRobotColor(1);
                board.setTurn(false);
                try {
                    board.setChessPieces();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mainFrame.delcomponent(panel);
                mainFrame.addcomponent(board.White.getPanel(), BorderLayout.WEST);
                mainFrame.addcomponent(board.Black.getPanel(), BorderLayout.EAST);
                mainFrame.addcomponent(board.getPanel(), BorderLayout.CENTER);
                mainFrame.addcomponent(board.getWhos_turn_is(),BorderLayout.NORTH);
                mainFrame.rep();
            }
        });

        White.addActionListener(new ActionListener() {
            @Override
            /**
             * this method run the game human vs robot / human is White
             */
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setBordLayout();
                Board board = null;
                try {
                    board = new Board();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                board.setRobotColor(-1);
                board.setTurn(true);
                mainFrame.delcomponent(panel);
                mainFrame.addcomponent(board.White.getPanel(), BorderLayout.WEST);
                mainFrame.addcomponent(board.Black.getPanel(), BorderLayout.EAST);
                mainFrame.addcomponent(board.getPanel(), BorderLayout.CENTER);
                mainFrame.addcomponent(board.getWhos_turn_is(),BorderLayout.NORTH);
                mainFrame.rep();
            }
        });
    }
    public static JPanel getPanel() {
        return panel;
    }
}

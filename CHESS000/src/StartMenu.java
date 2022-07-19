import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartMenu {
    static JPanel panel = new JPanel(), panel2 = new JPanel();
    static JButton startBot = new JButton();
    static JButton start = new JButton();
    File file = new File("IMG/menu.jpg");
    /**
     * this method loads the start menu
     */
    public StartMenu(){
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
        start.setText("2Player");
        startBot.setText("BOT");
        panel2.add(start);
        panel2.add(startBot);
        panel.add(panel2);

        start.addActionListener(new ActionListener() {
            @Override
            /**
             * this method start a two player chess game
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
                board.setTurn(true);
                board.setRobotColor(0);
                mainFrame.delcomponent(panel);
                mainFrame.addcomponent(board.White.getPanel(),BorderLayout.WEST);
                mainFrame.addcomponent(board.Black.getPanel(),BorderLayout.EAST);
                mainFrame.addcomponent(board.getPanel(),BorderLayout.CENTER);
                mainFrame.addcomponent(board.getWhos_turn_is(),BorderLayout.NORTH);
                mainFrame.rep();
            }
        });

        startBot.addActionListener(new ActionListener() {
            @Override
            /**
             * this method starts human vs robot chess game
             */
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setBordLayout();
                colorSelectMenu temp = new colorSelectMenu();
                mainFrame.delcomponent(panel);
                mainFrame.addcomponent(temp.getPanel());
                mainFrame.rep();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}

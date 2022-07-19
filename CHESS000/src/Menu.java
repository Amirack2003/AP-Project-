import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu {
    JPanel panel = new JPanel(), panel2 = new JPanel();
    JButton start = new JButton();
    JButton resume = new JButton();
    File file = new File("IMG/menu.jpg");
    /**
     * this method make the main menu panel
     */
    public Menu() {
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
        start.setText("Start");
        resume.setText("Resume");
        panel2.add(start);
        panel2.add(resume);
        panel.add(panel2);

        start.addActionListener(new ActionListener() {
            @Override
            /**
             * this method loads the start game menu
             */
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.delcomponent(panel);
                StartMenu startMenu = new StartMenu();
                mainFrame.addcomponent(startMenu.getPanel());
                mainFrame.rep();
            }
        });

        resume.addActionListener(new ActionListener() {
            /**
             * this method loads the last saved game
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadAndSave loadAndSave = new LoadAndSave();
                try {
                    if(!loadAndSave.IsSaved()){
                        JOptionPane.showMessageDialog(null, "No Saved Game Available!!!");
                    }
                    else {
                        Board board = loadAndSave.load();
                        MainFrame mainFrame = new MainFrame();
                        mainFrame.delcomponent(panel);
                        mainFrame.addcomponent(board.White.getPanel(),BorderLayout.WEST);
                        mainFrame.addcomponent(board.Black.getPanel(),BorderLayout.EAST);
                        mainFrame.addcomponent(board.getPanel(),BorderLayout.CENTER);
                        mainFrame.addcomponent(board.getWhos_turn_is(),BorderLayout.NORTH);
                        mainFrame.rep();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public JPanel getPanel() {
        return panel;
    }
}

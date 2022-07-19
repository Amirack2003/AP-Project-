import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        //Menu
        Menu menu = new Menu();
        mainFrame.addcomponent(menu.getPanel());
        mainFrame.rep();
    }
}

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private static JFrame mainframe = new JFrame();
    public MainFrame(){
        mainframe.setSize(1200,800);
        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void setBordLayout(){
        mainframe.setLayout(new BorderLayout());
    }
    public void addcomponent(Component x){
        mainframe.add(x);
    }
    public void addcomponent(Component x,String position){
        mainframe.add(x,position);
    }
    public void delcomponent(Component x){
        mainframe.remove(x);
    }
    public void rep(){
        mainframe.repaint();
        mainframe.revalidate();
    }
}

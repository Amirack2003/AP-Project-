import javax.swing.*;
import java.awt.*;

public class boarderPanels {
    private JPanel panel = new JPanel();
    private JButton[] buttons= new JButton[16];
    static private int numberOfPieces[] = {1,1,2,2,2,8};
    private int nOfPieces[] = new int[6];
    private int id;
    public boarderPanels(){
        panel.setLayout(new GridLayout(8,2));
        for(int i = 0 ; i < 16 ; i++){
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.YELLOW);
            panel.add(buttons[i]);
        }
        setId(0);
    }

    /**
     * this method is a setter that sets the index to iterate over buttons
     * @param id index
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * this method places the Eliminated Chesspieces on the boarders
     * @param icon The Chesspiece which has been Eliminated
     */
    public void setIcon(Icon icon){
        buttons[id].setIcon(icon);
        id++;
    }
    /**
     * this method is a setter that sets the number of each type of Chesspieces
     */
    public void setnOfPieces() {
        for(int i = 0 ; i < 6 ; i++)nOfPieces[i] = numberOfPieces[i];
    }

    /**
     * this method decrease the number of a specific Chesspiece type (for finding out the Eliminated Chesspieces)
     */
    public void decrease(int index){
        index--;
        this.nOfPieces[index]--;
    }

    public int getnOfPieces(int index) {
        return nOfPieces[index];
    }

    public JPanel getPanel() {
        return panel;
    }
}

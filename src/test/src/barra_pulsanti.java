package test.src;
import javax.swing.*;
import java.awt.*;

//classe che fa da barra di pulsanti

public class barra_pulsanti extends JPanel/* implements ActionListener*/ {
	public JButton[] bottoni;
	private int i=0;
	public barra_pulsanti(int numButtons) {
		bottoni= new JButton[numButtons];
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setBackground(Color.LIGHT_GRAY);
		
		setMaximumSize(new Dimension(200000,30));
	}
    public void addButton(String nome) {
    	bottoni[i]= new JButton(nome);
    	bottoni[i].setAlignmentX(CENTER_ALIGNMENT);
    	add(bottoni[i]);
    	i++;
    }
}
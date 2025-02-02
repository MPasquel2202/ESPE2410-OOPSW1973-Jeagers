package ec.edu.espe.model;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Brandon Pazmino
 * 
 */
public class SplashScreen extends JWindow{
    
        public SplashScreen() {

        setSize(400, 300);
        setLocationRelativeTo(null); 

        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.WHITE);
        content.setLayout(new BorderLayout());

        JLabel logo = new JLabel(new ImageIcon("/MOLELogo.png"));
        content.add(logo, BorderLayout.CENTER);
        JLabel loadingLabel = new JLabel("Cargando...", SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        content.add(loadingLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void close() {
        setVisible(false);
        dispose(); 
    }
    
}

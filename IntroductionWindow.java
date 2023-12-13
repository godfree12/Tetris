import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroductionWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Introduction");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            centerFrameOnScreen(frame);
            AudioManager audioManager = AudioManager.getInstance();
            audioManager.playSound("a.wav");

            JPanel panel = new JPanel(new GridBagLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    
                    ImageIcon background = new ImageIcon("C:/Users/ASUS/eclipse-workspace/Lyon_Projet_Java/src/t.jpg");
                    if (background.getImageLoadStatus() == MediaTracker.COMPLETE) {
                        System.out.println("L'image a �t� charg�e avec succ�s.");
                        System.out.println("Largeur de l'image : " + background.getIconWidth());
                        System.out.println("Hauteur de l'image : " + background.getIconHeight());

                        
                        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
                    } else {
                        System.err.println("Erreur lors du chargement de l'image.");
                    }
                }
            };

            JButton nouvellePartieButton = new JButton("Nouvelle partie");
            JButton optionsButton = new JButton("Options");
            JButton quitterButton = new JButton("Quitter");
            
            Color noir = Color.BLACK;
            Color rose = Color.PINK;
            nouvellePartieButton.setForeground(rose);
            nouvellePartieButton.setBackground(noir);
            
            optionsButton.setForeground(rose);
            optionsButton.setBackground(noir);
            
            quitterButton.setForeground(rose);
            quitterButton.setBackground(noir);
            
            Dimension buttonSize = new Dimension(150, 50); // Taille fixe des boutons

            nouvellePartieButton.setPreferredSize(buttonSize);
            optionsButton.setPreferredSize(buttonSize);
            quitterButton.setPreferredSize(buttonSize);

            nouvellePartieButton.addActionListener(e -> {// pour lancer le jeu
                //JOptionPane.showMessageDialog(frame, "Lancer une nouvelle partie !");
                TetrisUI tetrisUI = new TetrisUI();
                tetrisUI.startGame();
                //frame.dispose(); // Fermer la fenêtre d'introduction si nécessaire
            });

            optionsButton.addActionListener(e -> {
                showOptionsDialog(frame);
            });

            quitterButton.addActionListener(e -> System.exit(0));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(nouvellePartieButton, gbc);

            gbc.gridy = 1;
            panel.add(optionsButton, gbc);

            gbc.gridy = 2;
            panel.add(quitterButton, gbc);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
    
    
    private static void centerDialogOnScreen(JDialog dialog) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - dialog.getWidth()) / 2;
        int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
    }
    
    
    
    private static void showOptionsDialog(JFrame parentFrame) {
    	Color noir = Color.BLACK;
        Color rose = Color.PINK;
        JDialog optionsDialog = new JDialog(parentFrame, "Options", true);
        
        optionsDialog.setForeground(rose);
        optionsDialog.setBackground(noir);
        
        optionsDialog.setSize(300, 200);
        centerDialogOnScreen(optionsDialog);
        
       
        JButton viewScoreButton = new JButton("Voir le score");
        JButton decreaseVolumeButton = new JButton("Diminuer le volume");
        viewScoreButton.setForeground(rose);
        viewScoreButton.setBackground(noir);
        
        decreaseVolumeButton.setForeground(rose);
        decreaseVolumeButton.setBackground(noir);


        viewScoreButton.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(optionsDialog, "Score: ["+ "]");
        });

        decreaseVolumeButton.addActionListener(e -> {
            
        });

        JPanel optionsPanel = new JPanel(new GridLayout(2, 1));
        optionsPanel.add(viewScoreButton);
        optionsPanel.add(decreaseVolumeButton);
        
        
        optionsDialog.add(optionsPanel);
        optionsDialog.setVisible(true);
    }
    
}


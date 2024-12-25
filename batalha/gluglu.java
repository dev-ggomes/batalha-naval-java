package batalha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gluglu {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        final JFrame frame = new JFrame("Batalha Naval");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Criar um painel personalizado com imagem de fundo
        JPanel backgroundPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\ferao\\Desktop\\projeto\\imagem-de-fundo.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBorder(null);
        backgroundPanel.setLayout(null);

        JButton startButton = new JButton("Iniciar Jogo");
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setForeground(new Color(0, 0, 0));
        
        JButton exitButton = new JButton("Sair");
        exitButton.setForeground(new Color(0, 0, 0));
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);

        startButton.setFont(new Font("Berlin Sans FB", Font.BOLD, 25));
        startButton.setBounds(56, 150, 175, 39);

        exitButton.setFont(new Font("Berlin Sans FB", Font.BOLD, 25));
        exitButton.setBounds(407, 154, 100, 30);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	escolherBandeira.createAndShowModoJogoMenu();
                escolherBandeira(frame, backgroundPanel);
            }

			private void escolherBandeira(JFrame frame, JPanel backgroundPanel) {
				// TODO Auto-generated method stub
				
			}
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        backgroundPanel.add(startButton);
        backgroundPanel.add(exitButton);

        JLabel lblNewLabel = new JLabel("BATALHA NAVAL");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 40));
        lblNewLabel.setBounds(118, 11, 363, 112);
        backgroundPanel.add(lblNewLabel);

        frame.getContentPane().add(backgroundPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

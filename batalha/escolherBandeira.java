package batalha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class escolherBandeira {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowModoJogoMenu());
    }

    /**
     * @wbp.parser.entryPoint
     */
    public static void createAndShowModoJogoMenu() {
        JFrame modoJogoFrame = new JFrame("Modo de Jogo");
        modoJogoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modoJogoFrame.setSize(600, 400);

        JPanel modoJogoPanel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\ferao\\Desktop\\projeto\\imagem-de-fundo.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        modoJogoPanel.setBorder(null);
        modoJogoPanel.setLayout(null);

        JButton jogadorVsComputadorButton = new JButton("Jogador VS Computador");

        jogadorVsComputadorButton.setForeground(new Color(0, 0, 0));
        jogadorVsComputadorButton.setOpaque(false);
        jogadorVsComputadorButton.setContentAreaFilled(false);
        jogadorVsComputadorButton.setBorderPainted(false);

        jogadorVsComputadorButton.setFont(new Font("Berlin Sans FB", Font.BOLD, 20));

        jogadorVsComputadorButton.setBounds(131, 130, 300, 40);

        jogadorVsComputadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayerVsComputer().JanelaJogoPvE();
            }
        });

        modoJogoPanel.add(jogadorVsComputadorButton);

        modoJogoFrame.getContentPane().add(modoJogoPanel);
        modoJogoFrame.setLocationRelativeTo(null);
        modoJogoFrame.setVisible(true);
    }
}

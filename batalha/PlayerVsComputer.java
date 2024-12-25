package batalha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PlayerVsComputer {

    private JButton iniciarJogoButton;
    private BandeiraButton bandeira1Button;
    private BandeiraButton bandeira2Button;
    private BandeiraButton bandeira3Button;
    private BandeiraButton bandeira4Button;

    public void JanelaJogoPvE() {
        JFrame pveFrame = new JFrame("Jogo PvE");
        pveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pveFrame.setSize(600, 400);
        
        JPanel pvePanel = new JPanel();
        pvePanel.setLayout(null);

        JLabel label = new JLabel("Modo PvE: Iniciar jogo contra o computador.");
        label.setBounds(0, 0, 0, 0);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        pvePanel.add(label);

        // Adiciona botões para escolha da bandeira pelo jogador 1
        bandeira1Button = criarBandeiraButton("Portugal", "portugal.png", 0, 115);
        bandeira1Button.setOpaque(false);
        bandeira1Button.setContentAreaFilled(false);
        bandeira1Button.setBorderPainted(false);
        bandeira1Button.setForeground(new Color(0, 0, 0));
        
        bandeira2Button = criarBandeiraButton("Espanha", "espanha.png", 163, 115);
        bandeira2Button.setOpaque(false);
        bandeira2Button.setContentAreaFilled(false);
        bandeira2Button.setBorderPainted(false);
        bandeira2Button.setForeground(new Color(0, 0, 0));
        
        bandeira3Button = criarBandeiraButton("França", "franca.png", 344, 115);
        bandeira3Button.setOpaque(false);
        bandeira3Button.setContentAreaFilled(false);
        bandeira3Button.setBorderPainted(false);
        bandeira3Button.setForeground(new Color(0, 0, 0));
        
        bandeira4Button = criarBandeiraButton("Reino Unido", "reino-unido.png", 490, 115);
        bandeira4Button.setOpaque(false);
        bandeira4Button.setContentAreaFilled(false);
        bandeira4Button.setBorderPainted(false);
        bandeira4Button.setForeground(new Color(0, 0, 0));
        
        pvePanel.add(bandeira1Button);
        pvePanel.add(bandeira2Button);
        pvePanel.add(bandeira3Button);
        pvePanel.add(bandeira4Button);

        iniciarJogoButton = new JButton("Iniciar Jogo");
        iniciarJogoButton.setBounds(204, 203, 182, 97);
        iniciarJogoButton.setOpaque(false);
        iniciarJogoButton.setContentAreaFilled(false);
        iniciarJogoButton.setBorderPainted(false);
        iniciarJogoButton.setForeground(new Color(0, 0, 0));
        iniciarJogoButton.setFont(new Font("Berlin Sans FB", Font.BOLD, 25));
        iniciarJogoButton.setEnabled(false); // Inicia desabilitado
        iniciarJogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama o método main da classe Cucu
                Cucu.main(null);
            }
        });

        pvePanel.add(iniciarJogoButton);

        pveFrame.getContentPane().add(pvePanel);
        JLabel lblNewLabel = new JLabel("Jogador VS Computador");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 25));
        lblNewLabel.setBounds(126, 25, 340, 77);
        pvePanel.add(lblNewLabel);
        pveFrame.setLocationRelativeTo(null);
        pveFrame.setVisible(true);
    }

    private BandeiraButton criarBandeiraButton(String label, String imagePath, int x, int y) {
        BandeiraButton button = new BandeiraButton(label);
        button.setBounds(x, y, 94, 77);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilitarBotaoIniciarJogo();
                desabilitarBotaoBandeira(button);
            }
        });

        // Define a imagem para o botão
        try {
            URL resource = getClass().getResource(imagePath);
            ImageIcon icon = new ImageIcon(resource);
            Image scaledImage = icon.getImage().getScaledInstance(94, 50, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return button;
    }

    private void habilitarBotaoIniciarJogo() {
        iniciarJogoButton.setEnabled(true);
    }

    private void desabilitarBotaoBandeira(BandeiraButton button) {
        button.setEnabled(false);
    }

    private static class BandeiraButton extends JButton {
        private static final long serialVersionUID = 1L;

        public BandeiraButton(String label) {
            super(label);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayerVsComputer().JanelaJogoPvE();
            }
        });
    }
}

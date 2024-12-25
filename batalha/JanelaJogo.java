package batalha;

import javax.swing.*;

public class JanelaJogo extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JanelaJogo(String modo) {
        super("Modo de Jogo: " + modo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Adicione os componentes da sua interface de jogo aqui...

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

package batalha;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Cucu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton[][] playerButtons; // Botões para o tabuleiro do jogador
    private JButton[][] computerButtons; // Botões para o tabuleiro do computador
    private final int SIZE = 7; // Tamanho do tabuleiro
    private int playerSelectedCells = 0; // Número de células selecionadas pelo jogador
    private boolean playerCanPlay = true; // Indica se o jogador pode jogar
    private boolean gameOver = false; // Indica se o jogo acabou
    private boolean[][] computerCellsSelected; // Manter o controle das células selecionadas pelo computador

    public static void main(String[] args) {
        new Cucu(); // Cria uma instância do jogo
    }

    public Cucu() {
        super("Batalha Naval");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel(); // Painel de fundo do jogo
        backgroundPanel.setBackground(Color.BLUE); // Define a cor de fundo
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS)); // Layout do painel

        setSize(450, 700); // Define o tamanho da janela
        
        computerCellsSelected = new boolean[SIZE][SIZE]; // Inicializa a matriz de controle de células selecionadas pelo computador
        JPanel playerPanel = new JPanel(new GridLayout(SIZE, SIZE)); // Painel para o tabuleiro do jogador
        JPanel computerPanel = new JPanel(new GridLayout(SIZE, SIZE)); // Painel para o tabuleiro do computador

        JLabel label = new JLabel("Modo de Jogo: PvE"); // Rótulo para o modo de jogo
        label.setForeground(new Color(255, 255, 255)); // Cor do texto
        label.setFont(new Font("Tahoma", Font.BOLD, 14)); // Define a fonte e o estilo
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Alinhamento horizontal
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Adiciona borda ao rótulo

        playerButtons = new JButton[SIZE][SIZE]; // Inicializa a matriz de botões do jogador
        computerButtons = new JButton[SIZE][SIZE]; // Inicializa a matriz de botões do computador

        // Configuração dos botões para o tabuleiro do jogador e do computador
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                playerButtons[i][j] = new JButton(); // Cria um novo botão
                playerButtons[i][j].setBackground(Color.BLUE); // Define a cor de fundo
                playerButtons[i][j].setOpaque(true); // Torna o fundo opaco
                playerButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK)); // Adiciona borda
                playerButtons[i][j].setForeground(Color.BLACK); // Define a cor do texto
                playerButtons[i][j].addActionListener(this); // Adiciona um ouvinte de ação
                playerPanel.add(playerButtons[i][j]); // Adiciona o botão ao painel do jogador

                computerButtons[i][j] = new JButton(); // Cria um novo botão
                computerButtons[i][j].setBackground(Color.BLUE); // Define a cor de fundo
                computerButtons[i][j].setOpaque(true); // Torna o fundo opaco
                computerButtons[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, Color.BLACK)); // Adiciona borda
                computerButtons[i][j].setForeground(Color.BLACK); // Define a cor do texto
                computerButtons[i][j].addActionListener(this); // Adiciona um ouvinte de ação
                computerPanel.add(computerButtons[i][j]); // Adiciona o botão ao painel do computador
            }
        }

        // Adiciona os painéis e o rótulo ao painel de fundo
        backgroundPanel.add(playerPanel);
        backgroundPanel.add(label);
        backgroundPanel.add(computerPanel);

        getContentPane().add(backgroundPanel); // Adiciona o painel de fundo ao conteúdo da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true); // Torna a janela visível

        iniciarJogo(); // Inicia o jogo
    }

    // Exibe uma mensagem inicial para o jogador
    private void iniciarJogo() {
        JOptionPane.showMessageDialog(this, "Jogador 1: Selecione 10 células para posicionar os barcos.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return; // Se o jogo acabou, não faça nada
        }

        JButton clickedButton = (JButton)e.getSource(); // Obtém o botão clicado

        // Verifica de quem é a vez de jogar e executa a ação correspondente
        if (playerCanPlay) {
            if (playerSelectedCells < 10) {
                processPlayerMove(clickedButton); // Processa a seleção do jogador
                if (playerSelectedCells == 10) {
                    playerCanPlay = false; // Muda para a vez do computador
                    selecionarCelulasComputador(); // Computador seleciona suas células
                }
            } else {
                processPlayerAttack(clickedButton); // Processa o ataque do jogador
                playerCanPlay = false; // Muda para a vez do computador
            }
        } else {
            processComputerMove(clickedButton); // Processa o movimento do computador
            playerCanPlay = true; // Muda para a vez do jogador
        }
    }

    // Processa a seleção de células pelo jogador
    private void processPlayerMove(JButton clickedButton) {
        if (clickedButton.getBackground() == Color.BLUE) {
            clickedButton.setBackground(Color.GRAY); // Define a célula como selecionada
            playerSelectedCells++; // Incrementa o contador de células selecionadas
            if (playerSelectedCells == 10) {
                // Mensagem de instrução após o jogador selecionar todas as células
                JOptionPane.showMessageDialog(this, "Todos os barcos foram posicionados. É sua vez de atacar!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Você já selecionou essa célula."); // Mensagem de erro
        }
    }

    private void selecionarCelulasComputador() {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < SIZE * SIZE; i++) {
            indexes.add(i);
        }
        Collections.shuffle(indexes);
        int count = 0; // Variável para controlar o número de células selecionadas pelo computador
        while (count < 10) {
            int index1D = indexes.get(count); // Obtém o índice 1D
            int row = index1D / SIZE; // Calcula a linha
            int col = index1D % SIZE; // Calcula a coluna

            // Mantém as células invisíveis para o jogador, mantendo a cor de fundo azul
            computerButtons[row][col].setBackground(Color.BLUE);
            // Adiciona "y" apenas nas células onde o computador posiciona os barcos
            computerButtons[row][col].setText(" ");

            // Marca as células selecionadas pelo computador para controle interno
            computerCellsSelected[row][col] = true;
            count++; // Incrementa o contador de células selecionadas
        }
    }

    //Processa o ataque do jogador
    private void processPlayerAttack(JButton clickedButton) {
        int row = -1;
        int col = -1;

        // Encontra a célula selecionada pelo jogador
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (clickedButton == computerButtons[i][j]) {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1 && col != -1) {
                break;
            }
        }

        if (computerButtons[row][col].getText().equals(" ")) {
            // Se o jogador atingir uma célula onde há um barco, pinte-a de verde
            clickedButton.setBackground(Color.GREEN); // Define a célula como atingida (barco)
            computerCellsSelected[row][col] = false; // Marca a célula como não selecionada pelo computador
        } else {
            // Se o jogador atingir uma célula vazia (água), pinte-a de vermelho
            clickedButton.setBackground(Color.RED); // Define a célula como atingida (água)
        }
        checkGameOver(); // Verifica se o jogo acabou após o ataque do jogador
    }

    // Processa o movimento do computador
    private void processComputerMove(JButton clickedButton) {
        if (!playerCanPlay) {
            Random random = new Random();
            int row, col;

            do {
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            } while (computerCellsSelected[row][col]); // Verifica se a célula já foi selecionada

            computerCellsSelected[row][col] = true; // Marca a célula como selecionada pelo computador

            JButton targetButton = playerButtons[row][col];

            if (targetButton.getBackground() == Color.GRAY) {
                targetButton.setBackground(Color.GREEN);
                
            } else if (targetButton.getBackground() == Color.BLUE) {
                targetButton.setBackground(Color.RED);
                
            }
            checkGameOver();
        }
    }
    
 // Verifica se o jogo acabou
    private void checkGameOver() {
        int playerCount = 0;
        int computerCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (playerButtons[i][j].getBackground() == Color.GREEN) {
                    computerCount++;
                }
                if (computerButtons[i][j].getBackground() == Color.GREEN) {
                    playerCount++;
                }
            }
        }

        if (playerCount == 10 || computerCount == 10) {
            gameOver = true; // Define o jogo como encerrado
            if (playerCount == 10) {
                JOptionPane.showMessageDialog(this, "Parabéns! Você afundou todos os navios do adversário. Você venceu!"); // Mensagem de vitória do jogador
            } else {
                JOptionPane.showMessageDialog(this, "O computador afundou todos os seus navios. Você perdeu!"); // Mensagem de derrota do jogador
            }
        }
    }


}

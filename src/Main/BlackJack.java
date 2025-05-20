package Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BlackJack extends JFrame {
    private JTextArea textoJuego;
    private JButton pedirCartaBtn, plantarseBtn, jugarDeNuevoBtn;
    private JPanel panelCartasJugador, panelCartasCrupier;
    private JLabel puntosJugadorLabel, puntosCrupierLabel;

    public BlackJack() {
        setTitle("Blackjack");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Centrar la ventana
        setLocationRelativeTo(null);

        // Panel central con fondo verde
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(0, 100, 0)); // verde oscuro
        add(fondo, BorderLayout.CENTER);

        // Texto superior
        textoJuego = new JTextArea("Bienvenido al Blackjack");
        textoJuego.setEditable(false);
        textoJuego.setFont(new Font("Arial", Font.PLAIN, 14));
        textoJuego.setBackground(Color.DARK_GRAY);
        textoJuego.setForeground(Color.WHITE);
        textoJuego.setMargin(new Insets(10, 10, 10, 10));
        fondo.add(textoJuego, BorderLayout.NORTH);

        // Panel de cartas del jugador
        panelCartasJugador = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCartasJugador.setOpaque(false);

        // Panel de botones
        puntosJugadorLabel = new JLabel("Jugador: 0 pts");
        puntosJugadorLabel.setForeground(Color.BLACK);
        puntosJugadorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        puntosJugadorLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        pedirCartaBtn = new JButton("Pedir carta");
        plantarseBtn = new JButton("Plantarse");
        jugarDeNuevoBtn = new JButton("Jugar de nuevo");

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botones.add(puntosJugadorLabel);
        botones.add(pedirCartaBtn);
        botones.add(plantarseBtn);
        botones.add(jugarDeNuevoBtn);

        // Panel inferior que contiene cartas del jugador + botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setOpaque(false);
        panelInferior.add(panelCartasJugador, BorderLayout.CENTER);
        panelInferior.add(botones, BorderLayout.SOUTH);

        fondo.add(panelInferior, BorderLayout.SOUTH);

        // Panel de cartas del crupier
        panelCartasCrupier = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCartasCrupier.setOpaque(false);

        puntosCrupierLabel = new JLabel("Crupier: 0 pts");
        puntosCrupierLabel.setForeground(Color.WHITE);
        puntosCrupierLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.add(puntosCrupierLabel, BorderLayout.NORTH);
        panelSuperior.add(panelCartasCrupier, BorderLayout.CENTER);

        fondo.add(panelSuperior, BorderLayout.EAST);
    }

    public void mostrarCartasJugador(List<Carta> cartasJugador) {
        panelCartasJugador.removeAll();
        for (Carta carta : cartasJugador) {
            JLabel cartaLabel = new JLabel(carta.getImagen());
            panelCartasJugador.add(cartaLabel);
        }
        actualizar();
    }

    public void mostrarCartasCrupier(List<Carta> cartasCrupier) {
        panelCartasCrupier.removeAll();
        for (int i = 0; i < cartasCrupier.size(); i++) {
            JLabel cartaLabel;
            if (i == 0) {
                // Mostrar carta boca abajo (reverso)
                ImageIcon backIcon = new ImageIcon(getClass().getResource("/Resources/backCard.png"));
                Image img = backIcon.getImage().getScaledInstance(90, 130, Image.SCALE_SMOOTH);
                backIcon = new ImageIcon(img);
                cartaLabel = new JLabel(backIcon);
            } else {
                // Mostrar carta normalmente
                cartaLabel = new JLabel(cartasCrupier.get(i).getImagen());
            }
            panelCartasCrupier.add(cartaLabel);
        }
        actualizar();
    }

    // Getters
    public JButton getPedirCartaBtn() { return pedirCartaBtn; }
    public JButton getPlantarseBtn() { return plantarseBtn; }
    public JButton getJugarDeNuevoBtn() { return jugarDeNuevoBtn; }
    public JPanel getPanelCartasJugador() { return panelCartasJugador; }
    public JPanel getPanelCartasCrupier() { return panelCartasCrupier; }

    public void setTextoJuego(String texto) {
        textoJuego.setText(texto);
    }

    public void setPuntosJugador(int puntos) {
        puntosJugadorLabel.setText("Jugador: " + puntos + " pts");
    }

    public void setPuntosCrupier(int puntos) {
        puntosCrupierLabel.setText("Crupier: " + puntos + " pts");
    }

    public void actualizar() {
        repaint();
        revalidate();
    }
}

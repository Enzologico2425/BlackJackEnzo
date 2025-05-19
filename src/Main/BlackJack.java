package Main;

import javax.swing.*;
import java.awt.*;

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

        // Panel central con fondo verde (tapete)
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(0, 100, 0)); // verde oscuro
        add(fondo, BorderLayout.CENTER);

        // Texto superior (más pequeño)
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

        // Panel de cartas del crupier (arriba a la derecha)
        panelCartasCrupier = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCartasCrupier.setOpaque(false);

        // Etiqueta de puntos del crupier (más pequeña)
        puntosCrupierLabel = new JLabel("Crupier: 0 pts");
        puntosCrupierLabel.setForeground(Color.WHITE);
        puntosCrupierLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Panel superior derecho para crupier
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.add(puntosCrupierLabel, BorderLayout.NORTH);
        panelSuperior.add(panelCartasCrupier, BorderLayout.CENTER);

        fondo.add(panelSuperior, BorderLayout.EAST); // Posición superior derecha

        // Panel central para cartas del jugador
        fondo.add(panelCartasJugador, BorderLayout.CENTER);



        // Etiqueta de puntos del jugador (al lado de botón)
        puntosJugadorLabel = new JLabel("Jugador: 0 pts");
        puntosJugadorLabel.setForeground(Color.BLACK);
        puntosJugadorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        puntosJugadorLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // Botones
        pedirCartaBtn = new JButton("Pedir carta");
        plantarseBtn = new JButton("Plantarse");
        jugarDeNuevoBtn = new JButton("Jugar de nuevo");

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botones.add(puntosJugadorLabel);
        botones.add(pedirCartaBtn);
        botones.add(plantarseBtn);
        botones.add(jugarDeNuevoBtn);

        fondo.add(botones, BorderLayout.SOUTH);

    }
    public void mostrarCartasCrupier(java.util.List<Integer> cartasCrupier) {
        panelCartasCrupier.removeAll(); // Limpiar panel

        // Convertir la lista de enteros en una cadena con formato [ 10, 5, 6 ]
        String textoCartas = cartasCrupier.toString()
                .replace("[", "[ ")
                .replace("]", " ]");

        JLabel cartaLabel = new JLabel(textoCartas);
        cartaLabel.setForeground(Color.WHITE);
        cartaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panelCartasCrupier.add(cartaLabel);

        actualizar(); // Redibujar el panel
    }
    public void mostrarCartasJugador(java.util.List<Integer> cartasJugador) {
        panelCartasJugador.removeAll(); // Limpiar panel
        for (Integer carta : cartasJugador) {
            JLabel cartaLabel = new JLabel(carta.toString());
            cartaLabel.setForeground(Color.WHITE);
            cartaLabel.setFont(new Font("Arial", Font.BOLD, 0));
            panelCartasJugador.add(cartaLabel);
        }
        actualizar(); // Redibujar el panel
    }

    // Getters
    public JButton getPedirCartaBtn() {
        return pedirCartaBtn;
    }

    public JButton getPlantarseBtn() {
        return plantarseBtn;
    }

    public JButton getJugarDeNuevoBtn() {
        return jugarDeNuevoBtn;
    }

    public JPanel getPanelCartasJugador() {
        return panelCartasJugador;
    }

    public JPanel getPanelCartasCrupier() {
        return panelCartasCrupier;
    }

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

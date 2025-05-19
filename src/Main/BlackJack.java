package Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BlackJack extends JFrame {
    private JTextArea textoJuego;
    private JButton pedirCartaBtn, plantarseBtn, jugarDeNuevoBtn;
    private JPanel panelCartasJugador, panelCartasCrupier;

    public BlackJack() {
        setTitle("Blackjack");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Texto superior
        textoJuego = new JTextArea("Bienvenido al Blackjack");
        textoJuego.setEditable(false);
        textoJuego.setFont(new Font("Arial", Font.BOLD, 18));
        add(textoJuego, BorderLayout.NORTH);

        // Panel de cartas (centro)
        panelCartasJugador = new JPanel(new FlowLayout());
        panelCartasJugador.setBackground(new Color(0, 102, 0)); // verde mesa

        panelCartasCrupier = new JPanel(new FlowLayout());
        panelCartasCrupier.setBackground(new Color(0, 102, 0)); // verde mesa

        JPanel centro = new JPanel(new GridLayout(2, 1));
        centro.add(panelCartasCrupier);
        centro.add(panelCartasJugador);
        add(centro, BorderLayout.CENTER);

        // Botones
        pedirCartaBtn = new JButton("Pedir carta");
        plantarseBtn = new JButton("Plantarse");
        jugarDeNuevoBtn = new JButton("Jugar de nuevo");

        JPanel botones = new JPanel();
        botones.add(pedirCartaBtn);
        botones.add(plantarseBtn);
        botones.add(jugarDeNuevoBtn);
        add(botones, BorderLayout.SOUTH);
    }

    // Getters para acceder desde Main
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

    public void actualizar() {
        repaint();
        revalidate();
    }

    // NUEVO: Mostrar las cartas del jugador gráficamente
    public void mostrarCartasJugador(List<String> cartas) {
        panelCartasJugador.removeAll();

        for (String carta : cartas) {
            try {
                String ruta = "/resources/" + carta + ".png";
                ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
                JLabel etiqueta = new JLabel(icono);
                panelCartasJugador.add(etiqueta);
            } catch (Exception e) {
                System.err.println("No se pudo cargar la carta: " + carta);
            }
        }

        panelCartasJugador.revalidate();
        panelCartasJugador.repaint();
    }

    // NUEVO: Mostrar las cartas del crupier si lo necesitas
    public void mostrarCartasCrupier(List<String> cartas) {
        panelCartasCrupier.removeAll();

        for (String carta : cartas) {
            try {
                String ruta = "/resources/" + carta + ".png";
                ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
                JLabel etiqueta = new JLabel(icono);
                panelCartasCrupier.add(etiqueta);
            } catch (Exception e) {
                System.err.println("No se pudo cargar la carta del crupier: " + carta);
            }
        }

        panelCartasCrupier.revalidate();
        panelCartasCrupier.repaint();
    }
}

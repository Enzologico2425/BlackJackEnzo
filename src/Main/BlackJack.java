package Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BlackJack extends JFrame {
    private JTextArea textoJuego;
    private JButton pedirCartaBtn, plantarseBtn, jugarDeNuevoBtn, retirarPuntosBtn;
    private JPanel panelCartasJugador, panelCartasCrupier;
    private JLabel puntosJugadorLabel, puntosCrupierLabel;
    private JLabel puntosTotalesLabel;

    private Juego juego;
    private int puntosTotales = 500;

    private String nombreJugador;

    public BlackJack() {
        juego = new Juego();

        // aqui tambien pongo mi logo
        Image icono = new ImageIcon(getClass().getResource("/resources/lgodragon.png")).getImage();
        setIconImage(icono);

        setVisible(true);

        setTitle("Blackjack");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(0, 100, 0));
        add(fondo, BorderLayout.CENTER);

        textoJuego = new JTextArea("Bienvenido al Blackjack");
        textoJuego.setEditable(false);
        textoJuego.setFont(new Font("Arial", Font.PLAIN, 14));
        textoJuego.setBackground(Color.DARK_GRAY);
        textoJuego.setForeground(Color.WHITE);
        textoJuego.setMargin(new Insets(10, 10, 10, 10));
        fondo.add(textoJuego, BorderLayout.NORTH);

        panelCartasJugador = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCartasJugador.setOpaque(false);

        puntosJugadorLabel = new JLabel("");
        puntosJugadorLabel.setForeground(Color.BLACK);
        puntosJugadorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        puntosJugadorLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        puntosTotalesLabel = new JLabel("Puntos Totales: " + puntosTotales);
        puntosTotalesLabel.setForeground(Color.BLACK);
        puntosTotalesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        puntosTotalesLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        pedirCartaBtn = new JButton("Pedir carta");
        plantarseBtn = new JButton("Plantarse");
        jugarDeNuevoBtn = new JButton("Jugar de nuevo");
        jugarDeNuevoBtn.setEnabled(false);
        retirarPuntosBtn = new JButton("Retirar puntos");

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botones.add(puntosJugadorLabel);
        botones.add(pedirCartaBtn);
        botones.add(plantarseBtn);
        botones.add(retirarPuntosBtn);
        botones.add(puntosTotalesLabel);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setOpaque(false);
        panelInferior.add(panelCartasJugador, BorderLayout.CENTER);
        panelInferior.add(botones, BorderLayout.SOUTH);
        fondo.add(panelInferior, BorderLayout.SOUTH);

        panelCartasCrupier = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelCartasCrupier.setOpaque(false);

        puntosCrupierLabel = new JLabel("");
        puntosCrupierLabel.setForeground(Color.WHITE);
        puntosCrupierLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.add(puntosCrupierLabel, BorderLayout.NORTH);
        panelSuperior.add(panelCartasCrupier, BorderLayout.CENTER);
        fondo.add(panelSuperior, BorderLayout.EAST);

        actualizarInterfaz();

        // eventos de los botones
        pedirCartaBtn.addActionListener(e -> {
            String resultado = juego.pedirCartaJugador();
            textoJuego.setText(resultado);
            actualizarInterfaz();

            if (finDePartidaDetectada(resultado)) {
                finDelJuego(resultado.toLowerCase().contains("ganaste"));
            }
        });

        plantarseBtn.addActionListener(e -> {
            String resultado = juego.plantarse();
            textoJuego.setText(resultado);
            actualizarInterfaz();

            if (finDePartidaDetectada(resultado)) {
                finDelJuego(resultado.toLowerCase().contains("ganaste"));
            }
        });

        jugarDeNuevoBtn.addActionListener(e -> {
            juego.nuevaPartida();
            pedirCartaBtn.setEnabled(true);
            plantarseBtn.setEnabled(true);
            jugarDeNuevoBtn.setEnabled(false);
            textoJuego.setText("Nueva partida. ¡Suerte!");
            actualizarInterfaz();
        });

        retirarPuntosBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Quieres retirar tus puntos (" + puntosTotales + ") y salir del juego?",
                    "Retirar puntos", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                        "Has retirado " + puntosTotales + " puntos.\nGracias por jugar.");
                System.exit(0);
            }
        });
    }

    private void actualizarInterfaz() {
        mostrarCartasJugador(juego.getJugador());
        mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
        setPuntosJugador(suma(juego.getJugador()));
        setPuntosCrupier(juego.isMostrarCartaOcultaCrupier() ? suma(juego.getCrupier()) : 0);
        setPuntosTotales(puntosTotales);
    }

    private boolean finDePartidaDetectada(String mensaje) {
        String minus = mensaje.toLowerCase();
        return minus.contains("ganaste") || minus.contains("perdiste") || minus.contains("pasaste") ||
                minus.contains("blackjack") || minus.contains("empate") || minus.contains("gana el crupier");
    }

    private void finDelJuego(boolean victoria) {
        pedirCartaBtn.setEnabled(false);
        plantarseBtn.setEnabled(false);
        jugarDeNuevoBtn.setEnabled(true);

        if (victoria) {
            puntosTotales += 25;
        } else {
            puntosTotales -= 25;
        }
        setPuntosTotales(puntosTotales);

        int opcion = JOptionPane.showConfirmDialog(this,
                victoria ? "¡Ganaste con " + suma(juego.getJugador()) + " puntos! ¿Jugar otra vez?"
                        : "¡Has perdido! ¿Jugar de nuevo?",
                "Fin del juego", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            juego.nuevaPartida();
            pedirCartaBtn.setEnabled(true);
            plantarseBtn.setEnabled(true);
            jugarDeNuevoBtn.setEnabled(false);
            textoJuego.setText("Nueva partida. ¡Suerte!");
            actualizarInterfaz();
        } else {
            System.exit(0);
        }
    }

    public void mostrarCartasJugador(List<Carta> cartasJugador) {
        panelCartasJugador.removeAll();
        for (Carta carta : cartasJugador) {
            JLabel cartaLabel = new JLabel(carta.getImagen());
            panelCartasJugador.add(cartaLabel);
        }
        actualizar();
    }

    public void mostrarCartasCrupier(List<Carta> cartasCrupier, boolean mostrarTodo) {
        panelCartasCrupier.removeAll();
        for (int i = 0; i < cartasCrupier.size(); i++) {
            JLabel cartaLabel;
            if (i == 0 && !mostrarTodo) {
                ImageIcon backIcon = new ImageIcon(getClass().getResource("/Resources/backCard.png"));
                Image img = backIcon.getImage().getScaledInstance(90, 130, Image.SCALE_SMOOTH);
                backIcon = new ImageIcon(img);
                cartaLabel = new JLabel(backIcon);
            } else {
                cartaLabel = new JLabel(cartasCrupier.get(i).getImagen());
            }
            panelCartasCrupier.add(cartaLabel);
        }
        actualizar();
    }

    public void setNombreJugador(String nombre) {
        this.nombreJugador = nombre;
    }

    public String getNombreJugador() {
        return nombreJugador;
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

    public void setPuntosTotales(int puntos) {
        puntosTotales = puntos;
        puntosTotalesLabel.setText("Puntos Totales: " + puntosTotales);
    }

    public JButton getPedirCartaBtn() {
        return pedirCartaBtn;
    }

    public JButton getPlantarseBtn() {
        return plantarseBtn;
    }

    public JButton getJugarDeNuevoBtn() {
        return jugarDeNuevoBtn;
    }

    public JButton getRetirarPuntosBtn() {
        return retirarPuntosBtn;
    }

    public JPanel getPanelCartasJugador() {
        return panelCartasJugador;
    }

    public JPanel getPanelCartasCrupier() {
        return panelCartasCrupier;
    }

    public void actualizar() {
        repaint();
        revalidate();
    }

    private int suma(List<Carta> mano) {
        int suma = 0;
        for (Carta carta : mano) {
            suma += carta.getValor();
        }
        return suma;
    }
}

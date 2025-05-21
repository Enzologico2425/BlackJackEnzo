package Main;

import javax.swing.*;
import java.awt.*;

public class VentanaNombreJugador extends JFrame {
    private JTextField campoNombre;
    private JButton botonAceptar;
    private String nombreJugador;

    public VentanaNombreJugador() {

        //pongo mi logo de diseño en vez del de la aplicacion
        Image icono = new ImageIcon(getClass().getResource("/resources/lgodragon.png")).getImage();
        setIconImage(icono);

        setVisible(true);

        setTitle("Introducir nombre del jugador");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Usamos layout absoluto para simplificar

        JLabel etiqueta = new JLabel("Introduce tu nombre:");
        etiqueta.setBounds(20, 20, 200, 25);
        add(etiqueta);

        campoNombre = new JTextField();
        campoNombre.setBounds(20, 50, 250, 25);
        add(campoNombre);

        botonAceptar = new JButton("Aceptar");
        botonAceptar.setBounds(100, 90, 100, 25);
        add(botonAceptar);

        botonAceptar.addActionListener(e -> {
            nombreJugador = campoNombre.getText().trim();
            if (nombreJugador.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "¡Bienvenido, " + nombreJugador + "!");
                iniciarJuego(nombreJugador);  // CORREGIDO: pasamos el nombre
            }
        });
    }

    private void iniciarJuego(String nombre) {
        // Insertar jugador en BD (si no está ya)
        SQL.insertarJugador(nombre);

        this.dispose();  // cerrar ventana nombre

        BlackJack gui = new BlackJack();
        Juego juego = new Juego();

        gui.setVisible(true);

        gui.setTextoJuego("¡Bienvenido " + nombre + " al Blackjack!");
        gui.mostrarCartasJugador(juego.getJugador());
        gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
        gui.setPuntosJugador(juego.getPuntos());

        // Guardamos el nombre para usarlo después al guardar puntos
        gui.setNombreJugador(nombre);

        // Acción botones
        gui.getPedirCartaBtn().addActionListener(ev -> {
            String resultado = juego.pedirCartaJugador();
            gui.setTextoJuego(resultado);
            gui.mostrarCartasJugador(juego.getJugador());
            gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
            gui.setPuntosJugador(juego.getPuntos());
        });

        gui.getPlantarseBtn().addActionListener(ev -> {
            String resultado = juego.plantarse();
            gui.setTextoJuego(resultado);
            gui.mostrarCartasJugador(juego.getJugador());
            gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
            gui.setPuntosJugador(juego.getPuntos());
        });

        gui.getJugarDeNuevoBtn().addActionListener(ev -> {
            juego.nuevaPartida();
            gui.setTextoJuego("¡Nueva partida! Buena suerte.");
            gui.mostrarCartasJugador(juego.getJugador());
            gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
            gui.setPuntosJugador(juego.getPuntos());
        });

        // Botón retirar puntos
        gui.getRetirarPuntosBtn().addActionListener(ev -> {
            int puntos = juego.getPuntos();
            SQL.guardarPuntuacion(nombre, puntos);  // Guardamos en BD
            gui.setTextoJuego("Has retirado " + puntos + " puntos y se han guardado en la base de datos.");
            juego.nuevaPartida();
            gui.mostrarCartasJugador(juego.getJugador());
            gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
            gui.setPuntosJugador(juego.getPuntos());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaNombreJugador ventana = new VentanaNombreJugador();
            ventana.setVisible(true);
        });
    }
}

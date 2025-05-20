package Main;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            BlackJack gui = new BlackJack();
            gui.setVisible(true);

            Juego juego = new Juego();

            // Mostrar estado inicial
            gui.setTextoJuego("¡Bienvenido al Blackjack!");
            gui.mostrarCartasJugador(juego.getJugador());
            gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
            gui.setPuntosJugador(juego.getPuntos());

            // Acción botón "Pedir carta"
            gui.getPedirCartaBtn().addActionListener(e -> {
                String resultado = juego.pedirCartaJugador();
                gui.setTextoJuego(resultado);
                gui.mostrarCartasJugador(juego.getJugador());
                gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
                gui.setPuntosJugador(juego.getPuntos());
            });

            // Acción botón "Plantarse"
            gui.getPlantarseBtn().addActionListener(e -> {
                String resultado = juego.plantarse();
                gui.setTextoJuego(resultado);
                gui.mostrarCartasJugador(juego.getJugador());
                gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
                gui.setPuntosJugador(juego.getPuntos());
            });

            // Acción botón "Jugar de nuevo"
            gui.getJugarDeNuevoBtn().addActionListener(e -> {
                juego.nuevaPartida();
                gui.setTextoJuego("¡Nueva partida! Buena suerte.");
                gui.mostrarCartasJugador(juego.getJugador());
                gui.mostrarCartasCrupier(juego.getCrupier(), juego.isMostrarCartaOcultaCrupier());
                gui.setPuntosJugador(juego.getPuntos());
            });
        });
    }
}

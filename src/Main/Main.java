package Main;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            BlackJack gui = new BlackJack();
            gui.setVisible(true);

            Juego juego = new Juego();

            // Nueva partida inicial
            String texto = juego.nuevaPartida();
            gui.setTextoJuego(texto);
            gui.mostrarCartasJugador(juego.getJugador());
            gui.mostrarCartasCrupier(juego.getCrupier());
            gui.setPuntosJugador(juego.getPuntos()); // Mostrar puntos iniciales

            // Pedir carta
            gui.getPedirCartaBtn().addActionListener(e -> {
                String resultado = juego.pedirCartaJugador();
                gui.setTextoJuego(resultado);
                gui.mostrarCartasJugador(juego.getJugador());
                gui.setPuntosJugador(juego.getPuntos()); // Actualizar puntos
            });

            // Plantarse
            gui.getPlantarseBtn().addActionListener(e -> {
                String resultado = juego.plantarse();
                gui.setTextoJuego(resultado);
                gui.mostrarCartasJugador(juego.getJugador());
                gui.mostrarCartasCrupier(juego.getCrupier());
                gui.setPuntosJugador(juego.getPuntos()); // Actualizar puntos
            });

            // Jugar de nuevo
            gui.getJugarDeNuevoBtn().addActionListener(e -> {
                String resultado = juego.nuevaPartida();
                gui.setTextoJuego(resultado);
                gui.mostrarCartasJugador(juego.getJugador());
                gui.mostrarCartasCrupier(juego.getCrupier());
                gui.setPuntosJugador(juego.getPuntos()); // Reiniciar puntos
            });
        });
    }
}
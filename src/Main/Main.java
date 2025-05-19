package Main;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            BlackJack gui = new BlackJack();
            gui.setVisible(true);

            Juego juego = new Juego();
            gui.setTextoJuego("Tus cartas: " + juego.nuevaPartida());

            gui.getPedirCartaBtn().addActionListener(e -> {
                String resultado = juego.pedirCartaJugador();
                gui.setTextoJuego(resultado);
            });

            gui.getPlantarseBtn().addActionListener(e -> {
                String resultado = juego.plantarse();
                gui.setTextoJuego(resultado);
            });

            gui.getJugarDeNuevoBtn().addActionListener(e -> {
                String resultado = juego.nuevaPartida();
                gui.setTextoJuego(resultado);
            });
        });
    }
}

package Main;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // enseñamos la vetnanita para que el jugador ponga su nombre
            VentanaNombreJugador ventanaNombre = new VentanaNombreJugador();
            ventanaNombre.setVisible(true);
        });
    }
}
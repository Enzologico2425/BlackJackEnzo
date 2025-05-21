package Main;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Mostrar ventana para que el usuario introduzca su nombre antes de iniciar el juego
            VentanaNombreJugador ventanaNombre = new VentanaNombreJugador();
            ventanaNombre.setVisible(true);
        });
    }
}
package Main;
import java.awt.Image;
import javax.swing.*;

public class Carta {
    private final String nombre; // "A", "2", ..., "K"
    private final String palo;   // "H", "D", "C", "S"

    public Carta(String nombre, String palo) {
        this.nombre = nombre;
        this.palo = palo;
    }

    public int getValor() {
        switch (nombre) {
            case "A": return 11; // Puedes manejar el 1/11 según el contexto
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return Integer.parseInt(nombre);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getPalo() {
        return palo;
    }

    public ImageIcon getImagen() {
        String ruta = "/Resources/" + nombre + "-" + palo + ".png";
        java.net.URL imgURL = getClass().getResource(ruta);

        if (imgURL != null) {
            ImageIcon iconoOriginal = new ImageIcon(imgURL);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscalada);
        } else {
            System.err.println("No se encontró imagen: " + ruta);
            return new ImageIcon(); // Imagen vacía o error
        }
    }

    @Override
    public String toString() {
        return nombre + "-" + palo;
    }
}

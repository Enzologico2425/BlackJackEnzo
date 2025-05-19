package Main;

public class Carta {
    private String nombre;
    private String palo;

    public Carta(String nombre, String palo) {
        this.nombre = nombre;
        this.palo = palo;
    }

    public int getValor() {
        switch(nombre) {
            case "A": return 1; // o 11 si prefieres
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return Integer.parseInt(nombre);
        }
    }

    public String getNombre() { return nombre; }
    public String getPalo() { return palo; }
}

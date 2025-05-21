package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego {
    private final List<Carta> mazo;
    private final List<Carta> jugador;
    private final List<Carta> crupier;
    private boolean jugadorPlantado;
    private int puntos;
    private boolean mostrarCartaOcultaCrupier;

    private static final String[] PALOS = {"H", "D", "C", "S"};
    private static final String[] NOMBRES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Juego() {
        mazo = new ArrayList<>();
        jugador = new ArrayList<>();
        crupier = new ArrayList<>();
        jugadorPlantado = false;
        puntos = 500;  // Empiezas con 500 puntos
        mostrarCartaOcultaCrupier = false;
        crearMazo();
        repartirCartasIniciales();
    }

    private void crearMazo() {
        for (String palo : PALOS) {
            for (String nombre : NOMBRES) {
                mazo.add(new Carta(nombre, palo));
            }
        }
    }

    private Carta cartaAleatoria() {
        Random rand = new Random();
        return mazo.get(rand.nextInt(mazo.size()));
    }

    private void repartirCartasIniciales() {
        jugador.add(cartaAleatoria());
        jugador.add(cartaAleatoria());
        crupier.add(cartaAleatoria());
        crupier.add(cartaAleatoria());
    }

    public String pedirCartaJugador() {
        if (jugadorPlantado) return "Ya estás plantado.";

        jugador.add(cartaAleatoria());
        int suma = suma(jugador);

        if (suma == 21) {
            jugadorPlantado = true;
            mostrarCartaOcultaCrupier = true;
            puntos += 25;
            return "BLACKJACK! ¡Ganaste! Puntos: " + puntos;
        }

        if (suma > 21) {
            jugadorPlantado = true;
            mostrarCartaOcultaCrupier = true;
            puntos -= 25;
            return "¡Te pasaste! Has perdido. Puntos: " + puntos;
        }

        return "Carta añadida. Total: " + suma;
    }

    public String plantarse() {
        jugadorPlantado = true;
        revelarCartaCrupier();

        while (suma(crupier) < 16) crupier.add(cartaAleatoria());

        int sumaJugador = suma(jugador);
        int sumaCrupier = suma(crupier);

        if (sumaJugador > 21) {
            puntos -= 25;
            return "¡Te pasaste! Pierdes. Puntos: " + puntos;
        }
        if (sumaCrupier > 21) {
            puntos += 25;
            return "¡El crupier se pasó! ¡Ganaste! Puntos: " + puntos;
        }
        if (sumaJugador > sumaCrupier) {
            puntos += 25;
            return "¡Ganaste! Puntos: " + puntos;
        }
        if (sumaJugador < sumaCrupier) {
            puntos -= 25;
            return "Gana el crupier. Puntos: " + puntos;
        }

        return "¡Empate! Puntos: " + puntos;
    }

    public String nuevaPartida() {
        jugador.clear();
        crupier.clear();
        jugadorPlantado = false;
        mostrarCartaOcultaCrupier = false;
        repartirCartasIniciales();
        return "Nueva partida.";
    }

    private int suma(List<Carta> mano) {
        int suma = 0;
        for (Carta carta : mano) {
            suma += carta.getValor();
        }
        return suma;
    }

    public List<Carta> getJugador() {
        return new ArrayList<>(jugador);
    }

    public List<Carta> getCrupier() {
        return new ArrayList<>(crupier);
    }

    public int getPuntos() {
        return puntos;
    }

    public boolean estaPlantado() {
        return jugadorPlantado;
    }

    public void revelarCartaCrupier() {
        mostrarCartaOcultaCrupier = true;
    }

    public boolean isMostrarCartaOcultaCrupier() {
        return mostrarCartaOcultaCrupier;
    }

}

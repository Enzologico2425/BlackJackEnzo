package Main;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private final ArrayList<Integer> cartas;
    private final ArrayList<Integer> jugador;
    private final ArrayList<Integer> crupier;
    private boolean jugadorPlantado;
    private int puntos; // Contador de puntos

    public Juego() {
        cartas = new ArrayList<>();
        jugador = new ArrayList<>();
        crupier = new ArrayList<>();
        jugadorPlantado = false;
        puntos = 0;

        for (int i = 1; i <= 10; i++) cartas.add(i);
        for (int i = 0; i < 3; i++) cartas.add(10);

        jugador.add(cartaAleatoria());
        jugador.add(cartaAleatoria());
        crupier.add(cartaAleatoria());
        crupier.add(cartaAleatoria());
    }

    public int cartaAleatoria() {
        return cartas.get((int) (Math.random() * cartas.size()));
    }

    public String pedirCartaJugador() {
        if (jugadorPlantado) return "Ya estás plantado.";

        jugador.add(cartaAleatoria());
        int suma = suma(jugador);

        if (suma == 21) {
            jugadorPlantado = true;
            puntos += 50;
            return "Tus cartas: " + jugador + " - ¡BLACKJACK! ¡Ganaste! Puntos: " + puntos;
        }

        if (suma > 21) {
            jugadorPlantado = true;
            puntos -= 25;
            return "Tus cartas: " + jugador + " - ¡Te pasaste! Has perdido. Puntos: " + puntos;
        }

        return "Tus cartas: " + jugador;
    }

    public String plantarse() {
        jugadorPlantado = true;
        while (suma(crupier) < 16) crupier.add(cartaAleatoria());

        int sumaJugador = suma(jugador);
        int sumaCrupier = suma(crupier);

        if (sumaJugador > 21) {
            puntos -= 25;
            return "¡Te pasaste! Pierdes. Puntos: " + puntos;
        }
        if (sumaCrupier > 21) {
            puntos += 50;
            return "Crupier: " + crupier + " - ¡El crupier se pasó! ¡Ganaste! Puntos: " + puntos;
        }
        if (sumaJugador > sumaCrupier) {
            puntos += 50;
            return "¡Ganaste! Crupier: " + crupier + " Puntos: " + puntos;
        }
        if (sumaJugador < sumaCrupier) {
            puntos -= 25;
            return "Crupier: " + crupier + " - Gana el crupier. Puntos: " + puntos;
        }

        return "Crupier: " + crupier + " - ¡Empate! Puntos: " + puntos;
    }

    public String nuevaPartida() {
        jugador.clear();
        crupier.clear();
        jugadorPlantado = false;
        jugador.add(cartaAleatoria());
        jugador.add(cartaAleatoria());
        crupier.add(cartaAleatoria());
        crupier.add(cartaAleatoria());
        return "Nueva partida. Tus cartas: " + jugador;
    }

    private int suma(ArrayList<Integer> mano) {
        int suma = 0;
        for (int carta : mano) suma += carta;
        return suma;
    }



    public List<Integer> getJugador() {
        return new ArrayList<>(jugador);
    }

    public List<Integer> getCrupier() {
        return new ArrayList<>(crupier);
    }

    public int getPuntos() {
        return puntos;
    }
}

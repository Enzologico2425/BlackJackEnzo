package Main;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private final ArrayList<Integer> cartas;
    private final ArrayList<Integer> jugador;
    private final ArrayList<Integer> crupier;
    private boolean jugadorPlantado;

    public Juego() {
        cartas = new ArrayList<>();
        jugador = new ArrayList<>();
        crupier = new ArrayList<>();
        jugadorPlantado = false;

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
            return "Tus cartas: " + jugador + " - ¡BLACKJACK! ¡Ganaste!";
        }

        if (suma > 21) {
            jugadorPlantado = true;
            return "Tus cartas: " + jugador + " - ¡Te pasaste! Has perdido.";
        }

        return "Tus cartas: " + jugador;
    }

    public String plantarse() {
        jugadorPlantado = true;
        while (suma(crupier) < 16) crupier.add(cartaAleatoria());

        int sumaJugador = suma(jugador);
        int sumaCrupier = suma(crupier);

        if (sumaJugador > 21) return "¡Te pasaste! Pierdes.";
        if (sumaCrupier > 21) return "Crupier: " + crupier + " - ¡El crupier se pasó! ¡Ganaste!";
        if (sumaJugador > sumaCrupier) return "¡Ganaste! Crupier: " + crupier;
        if (sumaJugador < sumaCrupier) return "Crupier: " + crupier + " - Gana el crupier.";
        return "Crupier: " + crupier + " - ¡Empate!";
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
}

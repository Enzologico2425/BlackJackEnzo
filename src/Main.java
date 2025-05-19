import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> jugador = new ArrayList<>();
        ArrayList<Integer> crupier = new ArrayList<>();
        ArrayList<Integer> cartas = new ArrayList<>();

        // El As es 1 según las instrucciones
        for (int i = 1; i <= 10; i++) {
            cartas.add(i);
        }
        // Las 3 figuras (J, Q, K) que cuentan como 10
        for (int z = 0; z < 3; z++) {
            cartas.add(10);
        }

        jugador.add(cartas.get((int) (Math.random() * 12)));
        jugador.add(cartas.get((int) (Math.random() * 12)));
        crupier.add(cartas.get((int) (Math.random() * 12)));
        crupier.add(cartas.get((int) (Math.random() * 12)));

        System.out.println("Tus cartas: " + jugador);

        boolean seguirJugador = true, seguirCrupier = true;
        System.out.println("Pulsa 0 para plantarte, 1 para pedir otra carta:");
        int opcion = sc.nextInt();

        while (seguirJugador || seguirCrupier) {
            int sumaCrupier = 0, sumaJugador = 0;

            for (int carta : crupier) {
                sumaCrupier += carta;
            }

            if (sumaCrupier < 15) {
                seguirCrupier = true;
                crupier.add(cartas.get((int) (Math.random() * 12)));
            } else {
                seguirCrupier = false;
            }

            if (opcion == 1) {
                seguirJugador = true;
                jugador.add(cartas.get((int) (Math.random() * 12)));

                for (int carta : jugador) {
                    sumaJugador += carta;
                }

                System.out.println("Tus cartas: " + jugador);
                System.out.println("Pulsa 0 para plantarte, 1 para pedir otra carta:");
                opcion = sc.nextInt();
            } else if (opcion == 0) {
                seguirJugador = false;
                for (int carta : jugador) {
                    sumaJugador += carta;
                }
            }

            if (sumaJugador > 21 && (seguirJugador || seguirCrupier)) {
                seguirJugador = false;
                seguirCrupier = false;
                System.out.println("Cartas del crupier: " + crupier);
                System.out.println("¡Te has pasado! ¡Pierdes!");
            }

            if (sumaCrupier > 21 && (seguirJugador || seguirCrupier)) {
                seguirJugador = false;
                seguirCrupier = false;
                System.out.println("Cartas del crupier: " + crupier);
                System.out.println("Tus cartas: " + jugador);
                System.out.println("¡El crupier se pasa! ¡Has ganado!");
            }

            if (sumaJugador > sumaCrupier && (!seguirCrupier && !seguirJugador) && (sumaCrupier <= 21 && sumaJugador <= 21)) {
                System.out.println("¡Gana el jugador!");
                System.out.println("Cartas del crupier: " + crupier);
            }

            if (sumaCrupier > sumaJugador && (!seguirCrupier && !seguirJugador) && (sumaCrupier <= 21 && sumaJugador <= 21)) {
                System.out.println("Gana el crupier");
                System.out.println("Cartas del crupier: " + crupier);
            }

            if (sumaCrupier == sumaJugador && (!seguirCrupier && !seguirJugador) && (sumaCrupier <= 21 && sumaJugador <= 21)) {
                System.out.println("¡Empate!");
                System.out.println("Cartas del crupier: " + crupier);
            }

            sc.close();
        }
    }
}

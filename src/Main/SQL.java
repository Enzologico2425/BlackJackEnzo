package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQL {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/blackjack";
    private static final String USER = "enzo2";
    private static final String PASSWORD = "enzo2";

    // como conectar el sql al proyecto
    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // el metodo para insertar al jugador si ya hay uno con el mismo nombre salta mensaje y lo ignoramos
    public static void insertarJugador(String nombre) {
        String sql = "INSERT INTO usuarios (nombre) VALUES (?)";
        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            System.out.println("Jugador insertado correctamente.");
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                System.out.println("El jugador ya existe, no se inserta de nuevo.");
            } else {
                System.out.println("Error al insertar jugador: " + e.getMessage());
            }
        }
    }

    // metodo donde guardamos la puntuacion en relacion al nombre del usuario
    public static void guardarPuntuacion(String nombre, int puntos) {
        String sqlUsuario = "SELECT id FROM usuarios WHERE nombre = ?";
        String sqlInsertarPuntos = "INSERT INTO puntuaciones (usuario_id, puntos) VALUES (?, ?)";
        try (Connection con = conectar();
             PreparedStatement psUsuario = con.prepareStatement(sqlUsuario);
             PreparedStatement psInsertar = con.prepareStatement(sqlInsertarPuntos)) {

            psUsuario.setString(1, nombre);
            ResultSet rs = psUsuario.executeQuery();
            if (rs.next()) {
                int idUsuario = rs.getInt("id");
                psInsertar.setInt(1, idUsuario);
                psInsertar.setInt(2, puntos);
                psInsertar.executeUpdate();
                System.out.println("Puntuación guardada correctamente.");
            } else {
                System.out.println("Usuario no encontrado, no se guardó la puntuación.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar puntuación: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Connection con = conectar();
            System.out.println("Conexión exitosa a la base de datos.");
            con.close();
        } catch (Exception e) {
            System.out.println("¡Conexión fallida! Revisa la consola.");
            e.printStackTrace();
        }

    }
}

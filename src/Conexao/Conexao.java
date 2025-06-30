package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_petshop?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; // troque se necessário
    private static final String SENHA = "root"; // troque pela sua senha

    public static Connection getConexao() {
        try {
            // Carrega explicitamente o driver (recomendado em alguns ambientes)
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
        return null;
    }
}

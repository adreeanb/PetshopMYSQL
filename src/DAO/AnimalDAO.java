package DAO;
import Conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Gato;
import java.sql.Connection;
import java.sql.ResultSet;

public class GatoDAO {

    public void cadastrarGato(Gato gato) throws SQLException {
        String sqlAnimal = "INSERT INTO ANIMAL (NOME, ESPECIE, IDADE, PESO) VALUES (?, ?, ?, ?)";
        String sqlGato = "INSERT INTO GATO (ANIMAL_ID, RACA, GOSTA_DE_ARRANHAR) VALUES (?, ?, ?)";

        Connection conn = Conexao.getConexao();

        try {
            // 1. Cadastra o animal e pega o ID gerado
            PreparedStatement psAnimal = conn.prepareStatement(sqlAnimal, PreparedStatement.RETURN_GENERATED_KEYS);
            psAnimal.setString(1, gato.getNome());
            psAnimal.setString(2, gato.getEspecie());
            psAnimal.setInt(3, gato.getIdade());
            psAnimal.setDouble(4, gato.getPeso());

            psAnimal.executeUpdate();

            ResultSet rs = psAnimal.getGeneratedKeys();

            int idAnimal = -1;

            if (rs.next()) {
                idAnimal = rs.getInt(1);
                int idGerado = rs.getInt(1);
                gato.setId(idGerado); // armazena no objeto
            }

            rs.close();
            psAnimal.close();

            if (idAnimal != -1) {
                // 2. Cadastra o gato usando o ID do animal
                PreparedStatement psGato = conn.prepareStatement(sqlGato);
                psGato.setInt(1, idAnimal);
                psGato.setString(2, gato.getRaca());
                psGato.setBoolean(3, gato.getGostaDeArranhar());

                psGato.executeUpdate();
                psGato.close();

                System.out.println("Gato cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao obter o ID do animal.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível cadastrar o gato.");
        }
    }
}


package DAO;
import Conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Cachorro;
import java.sql.Connection;
import java.sql.ResultSet;

public class CachorroDAO {

    public void cadastrarCachorro(Cachorro cachorro) throws SQLException {
        String sqlAnimal = "INSERT INTO ANIMAL (NOME, ESPECIE, IDADE, PESO) VALUES (?, ?, ?, ?)";
        String sqlCachorro = "INSERT INTO CACHORRO (ANIMAL_ID, RACA, TEM_CARTEIRA_VACINACAO) VALUES (?, ?, ?)";

        Connection conn = Conexao.getConexao();

        try {
            // 1. Inserir dados na tabela ANIMAL
            PreparedStatement psAnimal = conn.prepareStatement(sqlAnimal, PreparedStatement.RETURN_GENERATED_KEYS);
            psAnimal.setString(1, cachorro.getNome());
            psAnimal.setString(2, cachorro.getEspecie());
            psAnimal.setInt(3, cachorro.getIdade());
            psAnimal.setDouble(4, cachorro.getPeso());

            psAnimal.executeUpdate();

            // Obter ID do animal recém-inserido
            ResultSet rs = psAnimal.getGeneratedKeys();
            int idAnimal = -1;
            if (rs.next()) {
                idAnimal = rs.getInt(1);
            }

            rs.close();
            psAnimal.close();

            // 2. Inserir dados na tabela CACHORRO usando o ID do animal
            if (idAnimal != -1) {
                PreparedStatement psCachorro = conn.prepareStatement(sqlCachorro);
                psCachorro.setInt(1, idAnimal);
                psCachorro.setString(2, cachorro.getRaca());
                psCachorro.setBoolean(3, cachorro.getCarteiraVacinacao());

                psCachorro.executeUpdate();
                psCachorro.close();

                System.out.println("Cachorro cadastrado com sucesso!");
            } else {
                System.out.println("Erro ao obter o ID do animal.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível cadastrar o cachorro.");
        }
    }
}


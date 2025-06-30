package DAO;
import Conexao.Conexao;
import Model.Animal;
import Model.Cachorro;
import Model.Gato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDAO {
    public Animal buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM ANIMAL WHERE NOME = ?";
        Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nome);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("ID");
            String especie = rs.getString("ESPECIE");
            int idade = rs.getInt("IDADE");
            double peso = rs.getDouble("PESO");

            if (especie.equalsIgnoreCase("gato")) {
                Gato gato = new Gato();
                gato.setId(id);
                gato.setNome(nome);
                gato.setEspecie(especie);
                gato.setIdade(idade);
                gato.setPeso(peso);

                // Buscar dados da tabela GATO
                String sqlGato = "SELECT RACA, GOSTA_DE_ARRANHAR FROM GATO WHERE ANIMAL_ID = ?";
                PreparedStatement psGato = conn.prepareStatement(sqlGato);
                psGato.setInt(1, id);
                ResultSet rsGato = psGato.executeQuery();

                if (rsGato.next()) {
                    gato.setRaca(rsGato.getString("RACA"));
                    gato.setGostaDeArranhar(rsGato.getBoolean("GOSTA_DE_ARRANHAR"));
                }

                rsGato.close();
                psGato.close();
                return gato;

            } else if (especie.equalsIgnoreCase("cachorro")) {
                Cachorro cachorro = new Cachorro();
                cachorro.setId(id);
                cachorro.setNome(nome);
                cachorro.setEspecie(especie);
                cachorro.setIdade(idade);
                cachorro.setPeso(peso);

                // Buscar dados da tabela CACHORRO
                String sqlCachorro = "SELECT RACA, TEM_CARTEIRA_VACINACAO FROM CACHORRO WHERE ANIMAL_ID = ?";
                PreparedStatement psCachorro = conn.prepareStatement(sqlCachorro);
                psCachorro.setInt(1, id);
                ResultSet rsCachorro = psCachorro.executeQuery();

                if (rsCachorro.next()) {
                    cachorro.setRaca(rsCachorro.getString("RACA"));
                    cachorro.setCarteiraVacinacao(rsCachorro.getBoolean("TEM_CARTEIRA_VACINACAO"));
                }

                rsCachorro.close();
                psCachorro.close();
                return cachorro;
            }
        }

        rs.close();
        ps.close();
        return null;
    }



}


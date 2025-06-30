package DAO;
import Conexao.Conexao;
import Model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicoDAO {

    public void cadastrarServico(Servico servico) throws SQLException {
        String sql = "INSERT INTO SERVICO (DESCRICAO, VALOR, DURACAO_ESTIMADA_MIN) VALUES (?, ?, ?)";

        Connection conn = Conexao.getConexao();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, servico.getDescricao());
            ps.setDouble(2, servico.getValor());
            ps.setInt(3, servico.getDuracaoEstimadaMin());

            ps.executeUpdate();
            ps.close();

            System.out.println("Serviço cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar o serviço.");
        }
    }
    public Servico buscarPorDescricao(String descricao) throws SQLException {
        String sql = "SELECT * FROM SERVICO WHERE DESCRICAO = ?";
        Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, descricao);
        ResultSet rs = ps.executeQuery();

        Servico servico = null;

        if (rs.next()) {
            servico = new Servico(
                    rs.getString("DESCRICAO"),
                    rs.getDouble("VALOR"),
                    rs.getInt("DURACAO_ESTIMADA_MIN")
            );
            servico.setId(rs.getInt("ID")); // Certifique-se de que a tabela tem a coluna ID
        }

        rs.close();
        ps.close();
        return servico;
    }

}


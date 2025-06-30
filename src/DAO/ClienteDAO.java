package DAO;
import Model.Cliente;
import Conexao.Conexao;

import java.sql.*;

public class ClienteDAO {
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO CLIENTE (NOME, CPF, TELEFONE, EMAIL) VALUES (?, ?, ?, ?)";
        Connection conn = Conexao.getConexao();

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                cliente.setId(idGerado);
            }
            rs.close();

            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível cadastrar o cliente!");
            throw e;
        }
    }

    public Cliente buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM CLIENTE WHERE CPF = ?";
        Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cpf);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("ID"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setTelefone(rs.getString("TELEFONE"));
            cliente.setEmail(rs.getString("EMAIL"));
            return cliente;
        }

        return null;
    }

}

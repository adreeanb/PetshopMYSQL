package DAO;
import Conexao.Conexao;
import Model.Atendimento;

import java.sql.*;
import java.time.LocalDate;

public class AtendimentoDAO {

    public void cadastrarAtendimento(Atendimento atendimento) throws SQLException {
        String sql = "INSERT INTO ATENDIMENTO (CLIENTE_ID, SERVICO_ID, ANIMAL_ID, DATA_HORA) VALUES (?, ?, ?, ?)";

        Connection conn = Conexao.getConexao();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, atendimento.getCliente().getId());
            ps.setInt(2, atendimento.getServico().getId());
            ps.setInt(3, atendimento.getAnimal().getId());
            ps.setDate(4, Date.valueOf(atendimento.getData())); // Converte LocalDate para java.sql.Date

            ps.executeUpdate();
            ps.close();

            System.out.println("Atendimento registrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar o atendimento.");
        }
    }

    public void listarTodosAtendimentos() throws SQLException {
        String sql = """
            SELECT 
                a.ID,
                c.NOME AS nome_cliente,
                an.NOME AS nome_animal,
                s.DESCRICAO AS descricao_servico,
                a.DATA_HORA
            FROM ATENDIMENTO a
            JOIN CLIENTE c ON a.CLIENTE_ID = c.ID
            JOIN ANIMAL an ON a.ANIMAL_ID = an.ID
            JOIN SERVICO s ON a.SERVICO_ID = s.ID
            ORDER BY a.DATA_HORA DESC
        """;

        Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n===== LISTA DE ATENDIMENTOS =====");

        while (rs.next()) {
            int id = rs.getInt("ID");
            String cliente = rs.getString("nome_cliente");
            String animal = rs.getString("nome_animal");
            String servico = rs.getString("descricao_servico");
            String data = rs.getString("DATA_HORA");

            System.out.printf("ID: %d | Cliente: %s | Animal: %s | Serviço: %s | Data: %s\n",
                    id, cliente, animal, servico, data);
        }

        rs.close();
        ps.close();
    }

    public void listarAtendimentosPorCpf(String cpf) throws SQLException {
        String sql = """
        SELECT 
            a.ID,
            c.NOME AS nome_cliente,
            an.NOME AS nome_animal,
            s.DESCRICAO AS descricao_servico,
            a.DATA_HORA
        FROM ATENDIMENTO a
        JOIN CLIENTE c ON a.CLIENTE_ID = c.ID
        JOIN ANIMAL an ON a.ANIMAL_ID = an.ID
        JOIN SERVICO s ON a.SERVICO_ID = s.ID
        WHERE c.CPF = ?
        ORDER BY a.DATA_HORA DESC
    """;

        Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cpf);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n===== ATENDIMENTOS DO CLIENTE (CPF: " + cpf + ") =====");

        boolean encontrou = false;
        while (rs.next()) {
            encontrou = true;
            int id = rs.getInt("ID");
            String nomeCliente = rs.getString("nome_cliente");
            String nomeAnimal = rs.getString("nome_animal");
            String servico = rs.getString("descricao_servico");
            String data = rs.getString("DATA_HORA");

            System.out.printf("ID: %d | Cliente: %s | Animal: %s | Serviço: %s | Data: %s\n",
                    id, nomeCliente, nomeAnimal, servico, data);
        }

        if (!encontrou) {
            System.out.println("Nenhum atendimento encontrado para esse CPF.");
        }

        rs.close();
        ps.close();
    }
    public void listarAtendimentosPorData(LocalDate data) throws SQLException {
        String sql = """
        SELECT 
            a.ID,
            c.NOME AS nome_cliente,
            an.NOME AS nome_animal,
            s.DESCRICAO AS descricao_servico,
            a.DATA_HORA
        FROM ATENDIMENTO a
        JOIN CLIENTE c ON a.CLIENTE_ID = c.ID
        JOIN ANIMAL an ON a.ANIMAL_ID = an.ID
        JOIN SERVICO s ON a.SERVICO_ID = s.ID
        WHERE DATE(a.DATA_HORA) = ?
        ORDER BY a.DATA_HORA
    """;

        Connection conn = Conexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, java.sql.Date.valueOf(data));

        ResultSet rs = ps.executeQuery();

        System.out.println("\n===== ATENDIMENTOS EM: " + data + " =====");

        boolean encontrou = false;
        while (rs.next()) {
            encontrou = true;
            int id = rs.getInt("ID");
            String nomeCliente = rs.getString("nome_cliente");
            String nomeAnimal = rs.getString("nome_animal");
            String servico = rs.getString("descricao_servico");
            String dataHora = rs.getString("DATA_HORA");

            System.out.printf("ID: %d | Cliente: %s | Animal: %s | Serviço: %s | Data/Hora: %s\n",
                    id, nomeCliente, nomeAnimal, servico, dataHora);
        }

        if (!encontrou) {
            System.out.println("Nenhum atendimento encontrado para essa data.");
        }

        rs.close();
        ps.close();
    }


}



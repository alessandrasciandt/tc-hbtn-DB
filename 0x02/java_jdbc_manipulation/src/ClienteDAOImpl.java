package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO{
    @Override
    public Connection connect(String urlConexao) {
        Connection conn = null;
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:sqlite_database_marco_2022.db");
        } catch (ClassNotFoundException e) {
            System.err.println("Ocorreu uma falha ao utilizar o driver");
        } catch (SQLException e) {
            System.err.println("Não foi possível abrir conexão com o banco!");
        }
        return conn;
    }

    @Override
    public void createTable(String urlConexao) {
        String url = "jdbc:sqlite:sqlite_database_marco_2022.db";
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE IF NOT EXISTS cliente (");
        sql.append("id integer PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome text NOT NULL, ");
        sql.append("idade integer, ");
        sql.append("cpf text NOT NULL, ");
        sql.append("rg text ");
        sql.append(")");
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql.toString());
        } catch (SQLException e) {
            System.out.println("Não foi possível criar a tabela");
        }
    }

    @Override
    public void insert(String url_conexao, Cliente cliente) {
        String sql = "INSERT INTO cliente(nome,idade, cpf, rg) VALUES(?,?,?,?)";
        try (Connection conn = this.connect(url_conexao);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setInt(2, cliente.getIdade());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getRg());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Não é possível inserir dados na Tabela");
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        String sql = "SELECT * FROM cliente";
        try (Connection conn = this.connect(urlConexao);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("nome") + "\t" + rs.getInt("idade") + "\t" +
                        rs.getString("cpf") + "\t" + rs.getString("rg"));
            }
        } catch (SQLException e) {
            System.out.println("Não é possível selecionar a tabela");
        }
    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        String sql = "UPDATE cliente SET nome = ? , idade = ? WHERE id = ?";
        try (Connection conn = this.connect(urlConexao);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, idade);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Não foi possível atualizar as informações");
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            Connection connection = connect(urlConexao);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException e) {
            System.err.println("Não foi possível excluir cliente na tabela");
        }
    }
}

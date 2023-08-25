import java.sql.*;

public class DAO {
    private Connection conexao;

    public DAO() {
        conexao = null;
    }

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String nomeServidor = "localhost";
        String meuBancoDeDados = "cachorro_db";
        int porta = 5432;
        String url = "jdbc:postgresql://" + nomeServidor + ":" + porta + "/" + meuBancoDeDados;
        String usuario = "postgres";
        String senha = "minhasenha";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, usuario, senha);
            status = (conexao != null);
            System.out.println("Conectado ao banco de dados!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conexão NÃO efetuada com o banco de dados -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO efetuada com o banco de dados -- " + e.getMessage());
        }

        return status;
    }

    public boolean fecharConexao() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean inserirCachorro(Cachorro cachorro) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO Cachorro (id, nome, raca, preco) "
                            + "VALUES (" + cachorro.getId() + ", '" + cachorro.getNome() + "', '"
                            + cachorro.getRaca() + "', " + cachorro.getPreco() + ");");
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean atualizarCachorro(Cachorro cachorro) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE Cachorro SET nome = '" + cachorro.getNome() + "', raca = '"
                        + cachorro.getRaca() + "', preco = " + cachorro.getPreco()
                        + " Onde id = " + cachorro.getId();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirCachorro(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("Deletar cachorro com id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Cachorro[] getCachorros() {
        Cachorro[] cachorros = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM Cachorro");
            if (rs.next()) {
                rs.last();
                cachorros = new Cachorro[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    cachorros[i] = new Cachorro(rs.getInt("id"), rs.getString("nome"),
                                                rs.getString("raca"), rs.getDouble("preco"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cachorros;
    }
}

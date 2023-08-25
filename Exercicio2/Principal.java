import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DAO dao = new DAO();

        dao.conectar();

        // Inserir um cachorro na tabela
        Cachorro cachorro = new Cachorro(11, "Max", "Golden Retriever", 500.0);
        if (dao.inserirCachorro(cachorro)) {
            System.out.println("Inserção com sucesso -> " + cachorro.toString());
        }

        // Mostrar cachorros com preço acima de 300
        System.out.println("==== Mostrar Cachorros com Preço Acima de 300 === ");
        Cachorro[] cachorros = dao.getCachorros();
        for (int i = 0; i < cachorros.length; i++) {
            System.out.println(cachorros[i].toString());
        }

        // Atualizar cachorro
        cachorro.setRaca("Labrador");
        dao.atualizarCachorro(cachorro);

        // Mostrar cachorros
        System.out.println("==== Mostrar Cachorros === ");
        cachorros = dao.getCachorros();
        for (int i = 0; i < cachorros.length; i++) {
            System.out.println(cachorros[i].toString());
        }

        // Excluir cachorro
        dao.excluirCachorro(cachorro.getId());

        // Mostrar cachorros
        cachorros = dao.getCachorros();
        System.out.println("==== Mostrar Cachorros === ");
        for (int i = 0; i < cachorros.length; i++) {
            System.out.println(cachorros[i].toString());
        }

        dao.fecharConexao();
		scanner.close();
    }
}

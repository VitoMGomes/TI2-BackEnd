public class Cachorro {
    private int id;
    private String nome;
    private String raca;
    private double preco;

    public Cachorro() {
        this.id = 0;
        this.nome = "";
        this.raca = "";
        this.preco = 0.0;
    }

    public Cachorro(int id, String nome, String raca, double preco) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Cachorro [id=" + id + ", nome=" + nome + ", raca=" + raca + ", preco=" + preco + "]";
    }
}

package Model;

public abstract class Animal {
	private int id;
	protected String nome;
	protected String especie;
	protected int idade;
	protected double peso;
	
	
	public Animal() {
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


		public String getEspecie() {
	        return especie;
	    }

	    public int getIdade() {
	        return idade;
	    }

	    public double getPeso() {
	        return peso;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public void setEspecie(String especie) {
	        this.especie = especie;
	    }

	    public void setIdade(int idade) {
	        this.idade = idade;
	    }

	    public void setPeso(double peso) {
	        this.peso = peso;
	    }


	public void exibirInfo() {
		System.out.println("Nome: "+ nome);
		System.out.println("Espécie: "+ especie);
		System.out.println("Idade: "+ idade + "anos");
		System.out.println("Peso: "+ peso + "kg");
	}
}

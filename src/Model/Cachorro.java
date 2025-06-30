package Model;

public class Cachorro extends Animal {
	
	private String raca;
	private boolean temCarteiraVacinacao;
	
	public Cachorro() {
		super();
	}
	
	public String getRaca(){
		return raca;
	}
	public boolean getCarteiraVacinacao() {
		return temCarteiraVacinacao;
	}
	
	public void setCarteiraVacinacao(boolean temCarteiraVacinacao) {
		this.temCarteiraVacinacao = temCarteiraVacinacao;
		
		if(temCarteiraVacinacao) {
			System.out.println("Sim");
		} else {
			System.out.println("Não");
		}
	}
	
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Raça: "+ raca);
		System.out.println("Tem carteira de vacinação? "+ temCarteiraVacinacao);
	}
	
}

package Model;

public class Gato extends Animal{
	private String raca;
	private boolean gostaDeArranhar;

	public Gato() {
		super();
	}
	
	public String getRaca(){
		return raca;
	}
	public boolean getGostaDeArranhar() {
		return gostaDeArranhar;
	}
	
	public void setGostaDeArranhar(Boolean gostaDeArranhar) {
		this.gostaDeArranhar = gostaDeArranhar;
	}
	
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Raça: "+ raca);
		System.out.println("Gosta de arranhar? "+ gostaDeArranhar);
	}
	
}

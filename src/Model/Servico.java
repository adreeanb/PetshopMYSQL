package Model;

public class Servico {
	private int id;
	private String descricao;
	private double valor;
	private int duracaoEstimadaMin;
	
	public Servico(String descricao, double valor, int duracaoEstimadaMin) {
		this.descricao = descricao;
		this.valor = valor;
		this.duracaoEstimadaMin = duracaoEstimadaMin;
	}
	public Servico() {

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getDuracaoEstimadaMin() {
		return duracaoEstimadaMin;
	}
	public void setDuracaoEstimadaMin(int duracaoEstimadaMin) {
		this.duracaoEstimadaMin = duracaoEstimadaMin;
	}
}

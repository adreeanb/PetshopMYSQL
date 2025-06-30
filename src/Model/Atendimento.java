package Model;
import java.time.LocalDate;

public class Atendimento {
	private Cliente cliente;
	private Servico servico;
	private Animal animal;
	private LocalDate data;
	
	public Atendimento(Cliente cliente, Servico servico, Animal animal, LocalDate data) {
		this.cliente = cliente;
		this.servico = servico;
		this.animal = animal;
		this.data = data;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
}

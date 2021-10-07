package poke;

public class Pokemon {
	private String nome;
	private String tipo;
	
	@Override
	public String toString() {
		return "[NOME] - " + nome + ", [TIPO] - " + tipo + "\n";
	}

	public Pokemon(String nome, String tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

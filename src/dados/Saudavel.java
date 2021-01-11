package dados;

public class Saudavel extends Pessoa {
	private Integer idade;

	public Saudavel(Integer id, String nome, Character genero, Integer idade) {
		super(id, nome, genero);
		setIdade(idade);
	}

	@Override
	public Character getSituacao() {
		return null;
	}

	// Método get
	public Integer getIdade() {
		return this.idade;
	}

	// Método set
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String toString() {
		String f = "|%-3s |%-30s |%-10s |%-5s |%-15s|";

		return String.format(f, this.getID(), this.getNome().toUpperCase(), this.getGenero(), this.getIdade(),
				"---------------");
	}

}

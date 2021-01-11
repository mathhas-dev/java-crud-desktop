package dados;

public class Contaminado extends Pessoa {
	private Character situacao;

	public Contaminado(Integer id, String nome, Character genero, Character situacao) {
		super(id, nome, genero);
		setSituacao(situacao);
	}

	@Override
	public Integer getIdade() {
		return null;
	}

	// Método get
	public Character getSituacao() {
		return this.situacao;
	}

	// Método set
	public void setSituacao(Character situacao) {
		this.situacao = situacao;
	}

	// Sobrescrita do método toString
	public String toString() {
		String f = "|%-3s |%-30s |%-10s |%-5s|%-15s|";

		return String.format(f, this.getID(), this.getNome().toUpperCase(), this.getGenero(), "------",
				this.getSituacao());
	}

}

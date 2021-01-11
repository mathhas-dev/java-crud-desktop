package dados;

public abstract class Pessoa {
	private Integer id;
	private StringBuilder nome;
	protected Character genero;

	public Pessoa(Integer id, String nome, Character genero) {
		setID(id);
		setNome(nome);
		setGenero(genero);
	}

	// M�todos get
	public int getID() {
		return this.id;
	}

	public String getNome() {
		return this.nome.toString();
	}

	public char getGenero() {
		return this.genero;
	}

	public abstract Integer getIdade();

	public abstract Character getSituacao();

	// M�todos set
	public void setID(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = new StringBuilder(nome);
	}

	public void setGenero(Character genero) {
		this.genero = genero;
	}

}

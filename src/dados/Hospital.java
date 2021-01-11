package dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hospital {
	private List<Pessoa> pessoas;

	public Hospital() {
		pessoas = new ArrayList<Pessoa>();
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public int getQtdPessoas() {
		return this.pessoas.size();
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoas.add(pessoa);
	}

	public void setPessoasFromDB(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public boolean objetoDuplicado(String nome, char sexo, Character situacao, Integer idade) {
		// Apenas se todos os dados do objeto forem iguais, é considerado duplicado
		if (this.getPessoas().isEmpty())
			return false;
		else {
			for (Pessoa p : this.getPessoas()) {
				if (p.getNome().equals(nome) && p.getGenero() == sexo && p.getIdade() == idade
						&& p.getSituacao() == situacao) {
					return true;
				}
			}
			return false;
		}
	}

	public List<Pessoa> listaPessoasComNomeBuscado(String nome) {
		List<Pessoa> listaComNomes = new ArrayList<Pessoa>();
		for (Pessoa p : this.pessoas) {
			if (p.getNome().contains(nome))
				listaComNomes.add(p);
		}
		return listaComNomes;
	}

	public List<Pessoa> pessoasOrdenadasPorID() {
		List<Pessoa> lista = this.pessoas;
		Collections.sort(lista, new OrdenaPorID());
		return lista;
	}
}

package dados;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import saida.Saida;

public class PessoaTableModel extends AbstractTableModel {
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private String[] colunas = { "ID", "Nome", "Gênero", "Idade", "Situação" };

	@Override
	public int getColumnCount() {
		return this.colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.pessoas.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return pessoas.get(linha).getID();
		case 1:
			return pessoas.get(linha).getNome().toUpperCase();
		case 2:
			return Saida.generoPorExtensoMaiusculo(pessoas.get(linha).getGenero());
		case 3:
			return pessoas.get(linha).getIdade();
		case 4:
			return Saida.situacaoPorExtensoMaiusculo(pessoas.get(linha).getSituacao());
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return this.colunas[column];
	}

	public void setItem(Pessoa p) {
		this.pessoas.add(p);
		this.fireTableDataChanged();
	}

	public void setList(List<Pessoa> lista) {
		this.limparDados();
		for (Pessoa p : lista) {
			this.pessoas.add(p);
		}
		this.fireTableDataChanged();
	}

	public void limparDados() {
		this.pessoas.clear();
	}
}

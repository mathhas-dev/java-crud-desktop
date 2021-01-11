package saida;

import java.util.List;
import javax.swing.JOptionPane;
import dados.*;
import validacao.Valida;

public class Saida {
	public static void saltaLinhasConsole(int linhasPuladas) {
		for (int aux = 0; aux < linhasPuladas; aux++)
			System.out.println();
	}

	public static String espacos(int numero) {
		StringBuilder espacoString = new StringBuilder();
		for (int aux = 0; aux < numero; aux++) {
			espacoString.append(" ");
		}
		return espacoString.toString();
	}

	public static void divideTabela() {
		for (int i = 0; i < 73; i++)
			System.out.print("-");
		System.out.println();
	}

	public static String formataInteiroMenorQueDez(int num) {
		return ((num > 9) ? String.valueOf(num) : '0' + String.valueOf(num));
	}

	private static void mostraRelatorio(int saudavel, int emTratamento, int contamidadoCurado,
			int mulheresContaminadasFalecidas, int homensContaminadosFalecidos) {
		StringBuffer relatorio = new StringBuffer();
		relatorio.append(espacos(20) + formataInteiroMenorQueDez(saudavel) + " = NÃO CONTAMINADO\n");
		relatorio.append(espacos(20) + formataInteiroMenorQueDez(emTratamento) + " = CONTAMINADOS EM TRATAMENTO\n");
		relatorio.append(espacos(20) + formataInteiroMenorQueDez(contamidadoCurado) + " = CONTAMINADOS CURADOS\n");
		relatorio.append(espacos(20) + formataInteiroMenorQueDez(mulheresContaminadasFalecidas)
				+ " = MULHERES CONTAMINADAS FALECIDAS\n");
		relatorio.append(espacos(20) + formataInteiroMenorQueDez(homensContaminadosFalecidos)
				+ " = HOMENS CONTAMINADOS FALECIDOS\n");
		System.out.println(relatorio);
	}
	
	private static void mostraTotalCadastradosRelatorio(int total) {
		// Professor, está comentado o código abaixo, pois por mais que ele escreva vermelho na console,
		// ele printa no lugar errado (antes de aparecer o relatório, e não no fim). A única solução que eu
		// encontrei, foi importar uma bibliota com o Maven, mas como não tenho domínio, não quis arriscar.
		
		// System.err.print(espacos(20) + formataInteiroMenorQueDez(total) + " = TOTAL DE PESSOAS CADASTRADAS");

		System.out.println(espacos(20) + formataInteiroMenorQueDez(total) + " = TOTAL DE PESSOAS CADASTRADAS");		
	}

	public static void relatorioFinal(List<Pessoa> pessoas) {
		int saudavel = 0;
		int emTratamento = 0;
		int contamidadoCurado = 0;
		int mulheresContaminadasFalecidas = 0;
		int homensContaminadosFalecidos = 0;
		int total = pessoas.size();

		for (Pessoa obj : pessoas) {
			if (obj.getSituacao() == null) {
				saudavel++;
			} else {
				switch (obj.getSituacao()) {
				case 'E':
					emTratamento++;
					break;
				case 'F':
					if (obj.getGenero() == 'M')
						homensContaminadosFalecidos++;
					else
						mulheresContaminadasFalecidas++;
					break;
				case 'C':
					contamidadoCurado++;
				}
			}
		}
		saltaLinhasConsole(50);
		mostraRelatorio(saudavel, emTratamento, contamidadoCurado, mulheresContaminadasFalecidas,
				homensContaminadosFalecidos);
		mostraTotalCadastradosRelatorio(total);

	}

	public static void messageDialogSemIcone(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, -1);
	}

	public static void buscaID(List<Pessoa> pessoas, String textFieldID) {
		boolean encontrou = false;
		String mensagem, titulo;
		int id = Valida.inteiro(textFieldID);
		if (id != -1) {
			for (Pessoa p : pessoas) {
				if (!encontrou && p.getID() == id) {
					encontrou = true;
					mensagem = "ID: " + p.getID() + "\nNome: " + p.getNome() + "\nGênero: " + p.getGenero()
							+ "\nIdade: " + p.getIdade() + "\nSituação: " + p.getSituacao();
					titulo = p.getNome();
					messageDialogSemIcone(mensagem, titulo);
				}
			}
			if (!encontrou) {
				mensagem = "ID não encontrado no banco de dados!";
				titulo = "Atenção!";
				messageDialogSemIcone(mensagem, titulo);
			}
		}
	}

	public static String situacaoPorExtensoMaiusculo(Character c) {
		if (c == null)
			return null;
		switch (c) {
		case 'C':
			return "CURADO";
		case 'E':
			return "EM TRATAMENTO";
		default:
			return "FALECIDO";
		}
	}

	public static String generoPorExtensoMaiusculo(Character c) {
		return (c == 'M') ? "MASCULINO" : "FEMININO";
	}

	public static int confirmarEncerramento() {
		int res = JOptionPane.showConfirmDialog(null, "Deseja realmente encerrar o programa?", "Encerrar",
				JOptionPane.OK_CANCEL_OPTION);
		return res;
	}

}

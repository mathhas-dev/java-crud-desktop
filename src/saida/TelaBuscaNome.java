package saida;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.PessoaDAO;
import dados.Hospital;
import dados.OrdemAlfabetica;
import dados.Pessoa;
import dados.PessoaTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class TelaBuscaNome extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	private JButton btnConfirmar, btnCancelar;
	private GroupLayout gl_contentPane;

	public TelaBuscaNome(Hospital hospital, PessoaTableModel pessoaTableModel) {
		setTitle("Insira o nome desejado");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 214);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		label = new JLabel("Insira aqui o nome que deseja buscar pessoas correspondentes:");

		textField = new JTextField();
		textField.setColumns(10);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addMouseListener(new MouseAdapter() {
			// Efetua a busca ao clicar no botão confirmar
			@Override
			public void mouseClicked(MouseEvent a) {
				// Popula novamente o array com dados na memoria, com os dados do banco
				hospital.setPessoasFromDB(PessoaDAO.getPessoasFromDB());

				List<Pessoa> listaOrdemAlfabetica = hospital.listaPessoasComNomeBuscado(textField.getText().trim());

				Collections.sort(listaOrdemAlfabetica, new OrdemAlfabetica());

				pessoaTableModel.setList(listaOrdemAlfabetica);
				dispose();
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			// Fecha a JFrame
			@Override
			public void mouseClicked(MouseEvent a) {
				dispose();
			}
		});

		// Organiza os componentes swing no layout GroupModel
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(26)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField, Alignment.LEADING).addComponent(label, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(90).addComponent(btnConfirmar)
								.addGap(64).addComponent(btnCancelar)))
				.addContainerGap(38, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(34).addComponent(label)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnConfirmar).addComponent(btnCancelar))
										.addContainerGap(41, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}
}

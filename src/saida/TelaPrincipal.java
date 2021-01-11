package saida;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banco.PessoaDAO;
import dados.Hospital;
import dados.PessoaTableModel;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane, panel;
	private JMenuBar menuBar;
	private JMenu mnMenu, mnBuscasAvancadas, mnEncerrar;
	private JMenuItem mntmCadPessSaudavel, mntmCadPessEnfermidade, mntmListarPorID, mntmBuscarPorID, mntmBuscarPorNome;
	private JTabbedPane tabbedPane;
	private GroupLayout gl_panel;
	private PessoaTableModel pessoaTableModel;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel qtdDadosRecuperados;

	public TelaPrincipal(Hospital hospital) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1045, 462);
		setLocation(0, 110);

		// Ao fechar a janela principal, apresenta o relatório e encerra o programa
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (Saida.confirmarEncerramento() == 0) {
					Saida.relatorioFinal(hospital.getPessoas());
					System.exit(0);
				}
			}
		});

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnMenu = new JMenu("Cadastrar");
		menuBar.add(mnMenu);

		mntmCadPessSaudavel = new JMenuItem("Cadastrar Pessoa Saudável");
		mntmCadPessSaudavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastrarSaudavel telaCadastrarSaudavel = new TelaCadastrarSaudavel(hospital, pessoaTableModel,
						qtdDadosRecuperados);
				telaCadastrarSaudavel.setVisible(true);
			}
		});

		mnMenu.add(mntmCadPessSaudavel);

		mntmCadPessEnfermidade = new JMenuItem("Cadastrar Pessoa com histórico de Covid-19");
		mntmCadPessEnfermidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarEnfermo telaCadastrarEnfermo = new TelaCadastrarEnfermo(hospital, pessoaTableModel,
						qtdDadosRecuperados);
				telaCadastrarEnfermo.setVisible(true);
			}
		});
		mnMenu.add(mntmCadPessEnfermidade);

		mnBuscasAvancadas = new JMenu("Buscas avançadas");
		menuBar.add(mnBuscasAvancadas);

		mntmListarPorID = new JMenuItem("Listar ordenando por ID");
		mnBuscasAvancadas.add(mntmListarPorID);
		mntmListarPorID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Popula novamente o array com dados na memoria, com os dados do banco
				hospital.setPessoasFromDB(PessoaDAO.getPessoasFromDB());

				// Insere os dados atualizados na classe de dados da tabela
				pessoaTableModel.setList(hospital.pessoasOrdenadasPorID());
				
				// Seta número de dados recuperados
				String n = String.valueOf(pessoaTableModel.getRowCount());
				qtdDadosRecuperados.setText(n + " Dados recuperados");
				qtdDadosRecuperados.setForeground(Color.RED);
				qtdDadosRecuperados.setVisible(true);
			}
		});

		mntmBuscarPorID = new JMenuItem("Buscar Pessoa pelo ID");
		mnBuscasAvancadas.add(mntmBuscarPorID);
		mntmBuscarPorID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaBuscaPessoaPorID telaBuscaPessoaPorID = new TelaBuscaPessoaPorID(hospital);
				telaBuscaPessoaPorID.setVisible(true);
			}
		});

		mntmBuscarPorNome = new JMenuItem("Buscar Pessoa pelo NOME");
		mnBuscasAvancadas.add(mntmBuscarPorNome);

		mntmBuscarPorNome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaBuscaNome telaBuscarNome = new TelaBuscaNome(hospital, pessoaTableModel);
				telaBuscarNome.setVisible(true);
				telaBuscarNome.addWindowListener(new WindowListener() {

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// Seta número de dados recuperados
						String n = String.valueOf(pessoaTableModel.getRowCount());
						qtdDadosRecuperados.setText(n + " Dados recuperados");
						qtdDadosRecuperados.setForeground(Color.RED);
						qtdDadosRecuperados.setVisible(true);

					}

					@Override
					public void windowClosing(WindowEvent e) {

					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}
				});
			}
		});

		qtdDadosRecuperados = new JLabel();
		qtdDadosRecuperados.setVisible(false);

		mnEncerrar = new JMenu("Encerrar");
		mnEncerrar.setForeground(Color.RED);
		mnEncerrar.addMouseListener(new MouseAdapter() {
			// Ao fechar a janela principal, apresenta o relatório e encerra o programa
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Saida.confirmarEncerramento() == 0) {
					Saida.relatorioFinal(hospital.getPessoas());
					System.exit(0);
				}
			}
		});

		menuBar.add(mnEncerrar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		panel = new JPanel();
		tabbedPane.addTab("Tabela de Cadastrados", null, panel, null);

		pessoaTableModel = new PessoaTableModel();
		pessoaTableModel.setList(hospital.pessoasOrdenadasPorID());
		scrollPane = new JScrollPane();

		// Organiza os componentes swing no layout GroupModel
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING,
								gl_panel.createSequentialGroup().addComponent(qtdDadosRecuperados).addContainerGap())
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addComponent(qtdDadosRecuperados)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		// Monta a tabela
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(pessoaTableModel);
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);

		setVisible(true);
	}
}

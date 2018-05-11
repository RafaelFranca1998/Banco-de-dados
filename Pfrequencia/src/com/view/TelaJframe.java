package com.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.dao.AlunoDao;
import com.dao.DataSource;
import com.model.Aluno;

public class TelaJframe {
	//Cria as variaveis e as inicializa. 
	private JFrame frmProgramaAluno = new JFrame();
	private TextField textFieldNome = new TextField();
	private JTextField textFieldEmail = new JTextField();
	private JTextField textFieldFrequencia = new JTextField();
	private JTable tableResultado = new JTable();
	private JScrollPane scrollPane = new JScrollPane();
	private JLabel lblNome = new JLabel("Nome");
	private JButton btnRemover = new JButton("Remover");
	private JButton btnNewButton = new JButton("Inserir");

	private DataSource ds;//  classe que inicia uma Conexão.
	private AlunoDao dao; // classe que dá instruções para o banco de dados
	private Aluno aluno; //classe que modifica os dados do usuário

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJframe window = new TelaJframe();
					window.frmProgramaAluno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaJframe() {
		initialize();
		readJtable();
	}
/**
 * Preenche a tabela com dados.
 */
	public void readJtable() {
		ds = new DataSource();
		dao = new AlunoDao(ds);
		aluno = new Aluno();
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		for (Aluno user : dao.listarAluno()) {
			modelo.addRow(new Object[] { user.getId(), user.getNome(), user.getEmail(), user.getFrequencia() });
		}
	}

	public void clearJtable() {
		DefaultTableModel modelo = (DefaultTableModel) tableResultado.getModel();
		modelo.setNumRows(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		textFieldNome.setBounds(509, 180, 115, 23);

		textFieldEmail.setBounds(509, 234, 115, 20);
		frmProgramaAluno.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldFrequencia.setBounds(509, 285, 115, 20);
		textFieldFrequencia.setColumns(10);

		lblNome.setBounds(513, 160, 46, 14);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(513, 209, 46, 14);

		JLabel lblFrequencia = new JLabel("Frequ\u00EAncia");
		lblFrequencia.setBounds(509, 265, 84, 14);
	
		
		scrollPane.setBounds(26, 39, 459, 266);
		
		btnRemover.setBounds(199, 327, 89, 24);
		
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(518, 327, 89, 24);

		btnRemover.setBackground(Color.LIGHT_GRAY);
		
		frmProgramaAluno.getContentPane().add(btnRemover);
		frmProgramaAluno.getContentPane().add(btnNewButton);
		frmProgramaAluno.getContentPane().add(lblFrequencia);
		frmProgramaAluno.getContentPane().add(scrollPane);
		frmProgramaAluno.getContentPane().add(textFieldFrequencia);
		frmProgramaAluno.getContentPane().add(textFieldNome);
		frmProgramaAluno.getContentPane().add(lblNome);
		frmProgramaAluno.getContentPane().add(lblEmail);
		frmProgramaAluno.getContentPane().setLayout(null);
		frmProgramaAluno.setResizable(false);
		frmProgramaAluno.setBounds(100, 100, 650, 400);
		frmProgramaAluno.setTitle("Programa Aluno");
		frmProgramaAluno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



	
		btnRemover.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					ds = new DataSource();
					dao = new AlunoDao(ds);
					Object coluna = tableResultado.getValueAt(tableResultado.getSelectedRow(), 0); // retorna o valor da linha selecionada na tabela posição 0
					JOptionPane pane = new JOptionPane(); // inicia joptionpane
					if (pane.showConfirmDialog(tableResultado,
							"Esta Ação não poderá ser desfeita! \n Deseja remover o Aluno da Lista?", "Atenção!",
							pane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { // pergunta se o usuário realmente quer excluir o aluno
						int id = Integer.parseInt(coluna.toString()); // converte o objeto para inteiro e depois para string
						System.out.println("Aqui o id" + id + pane.YES_OPTION);// teste no console
						aluno.setId(id); // modifica o dado id
						dao.delete(aluno); // invoca o metodo delete da classe AlunoDAO
						clearJtable(); // limpa a tabela
						readJtable(); // lê a tabela
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(tableResultado, "Selecione um Usuário");
				}
			}
		});
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ds = new DataSource();
				dao = new AlunoDao(ds);

				clearJtable();
				try {
					aluno.setNome(textFieldNome.getText());
					aluno.setEmail(textFieldEmail.getText());
					aluno.setFrequencia(Integer.parseInt(textFieldFrequencia.getText()));
					dao.create(aluno);
					JOptionPane.showMessageDialog(frmProgramaAluno, "Adicionado!", "Menssagem", 1);
					textFieldFrequencia.setText("");
					textFieldEmail.setText("");
					textFieldNome.setText("");
				} catch (Exception a) {
					JOptionPane.showMessageDialog(frmProgramaAluno, "Preencha todos os campos", "Erro!", 2);
				}
				readJtable();
			}
		});
	
		tableResultado.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableResultado.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Aluno", "Email", "Frequência" }) {
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, true, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		scrollPane.setViewportView(tableResultado);
	}
}

package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class checkManager extends JFrame implements ActionListener {
	public checkManager checkManager=null;
	private JTextField idField = new JTextField(8);
	private JTextField passField = new JTextField(7);
	manager manager = new manager();

	public void chcManager() {
		JPanel panel = new JPanel();
		JLabel labelid = new JLabel("ID : ");
		JLabel labelpw = new JLabel("PASS : ");
		JButton log = new JButton("로그인");

		log.addActionListener(this);
		idField.addActionListener(this);
		passField.addActionListener(this);

		setTitle("매니저 로그인");
		setSize(280, 150);
		setResizable(false);
		setLocationRelativeTo(null);

		add(panel);
		panel.setLayout(null);
		labelid.setBounds(10,10,80,25);
		panel.add(labelid);
		idField.setBounds(100,10,160,25);
		panel.add(idField);
		labelpw.setBounds(10,40,80,25);
		panel.add(labelpw);
		passField.setBounds(100,40,160,25);
		panel.add(passField);
		log.setBounds(160,80,100,25);
		log.setBackground(new Color(60,179,113));
		panel.add(log);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = "gusrjs";
		String password = "1";

		String inputId = idField.getText();
		String inputPassword = passField.getText();

		// 로그인 검증
		if (id.equals(inputId) && password.equals(inputPassword)) {
			JOptionPane.showMessageDialog(this, "이현건님, 환영합니다.");
			manager.main();
		} else {
			JOptionPane.showMessageDialog(this, "다시 시도해주세요.");
		}

	}

}

package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CafeDAO.userDAO;

public class userTel extends JFrame implements ActionListener {
	public userTel userTel = null;
	private JTextField inputField = new JTextField();
	private JPanel buttonPanel = new JPanel();
	private JPanel actionPanel = new JPanel();
	private String button[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#" };
	private JButton deleteButton = new JButton("지움");
	private JButton nextButton = new JButton("다음");
	coffeeMain coffeemain = new coffeeMain();

	public void usertel() {
		setLayout(null);

		setTitle("고객 전화번호");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setResizable(false);

		// 입력 칸
		inputField.setEditable(true);
		inputField.setBackground(Color.white);
		inputField.setBounds(60, 20, 270, 50);
		inputField.setFont(new Font("Arial", Font.BOLD, 20));

		// 동작 버튼
		actionPanel.setLayout(new FlowLayout());
		actionPanel.add(deleteButton);
		actionPanel.add(nextButton);
		add(inputField);
		add(buttonPanel);
		add(actionPanel);
		actionPanel.setBounds(60, 290, 270, 50);

		// 숫자버튼
		buttonPanel.setLayout(new GridLayout(4, 3));
		buttonPanel.setBounds(60, 70, 270, 200);
		buttonPanel.setBackground(new Color(200, 150, 100));
		buttonPanel.setFont(new Font("Arial", Font.BOLD, 50));

		// 지움버튼
		deleteButton.setBackground(new Color(255, 99, 71));
		deleteButton.setForeground(Color.white);
		deleteButton.addActionListener(this);

		// 다음버튼
		nextButton.setBackground(new Color(60, 179, 113));
		nextButton.setForeground(Color.white);
		nextButton.addActionListener(this);

		JButton buttons[] = new JButton[button.length];

		for (int i = 0; i < button.length; i++) {
			buttons[i] = new JButton(button[i]);
			buttons[i].setBackground(new Color(160, 82, 45));
			buttons[i].setBorderPainted(true);
			buttonPanel.add(buttons[i]);
		}

		add(inputField);
		add(buttonPanel);
		inputField.addActionListener(this);

		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == deleteButton) {
			inputField.setText(" ");
		} else if (e.getSource() == nextButton) {
			JOptionPane.showMessageDialog(this, "메인메뉴 페이지로 이동합니다.");
			coffeemain.coffeemain();
//			userDAO.userdao.add();

		}
	}

}

package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class start extends JFrame implements ActionListener {
	private JButton button = new JButton("주문하기");
	private JButton button2 = new JButton("매니저");
	JLabel label1 = new JLabel("휴먼 카페");
	checkManager checkManager = new checkManager();
	userTel userTel = new userTel();

	public void main() {
		setTitle("휴먼 카페");
		setSize(800, 800);
		setLocationRelativeTo(null); // 화면 중앙에 위치

		Container con = getContentPane();
		con.setLayout(null);

		// 이미지 화면에 맞게 구현방법
		ImageIcon image = new ImageIcon("C:\\Users\\human-18\\Desktop\\카페 이미지\\커피.jfif");
		Image img = image.getImage();
		Image changeimg = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
		JLabel label = new JLabel(new ImageIcon(changeimg));

		label.setBounds(0, 0, 800, 800);
		con.add(label);

		button.setBounds(300, 200, 200, 50);
		con.add(button);
		button.addActionListener(this);

		button2.setBounds(300, 350, 200, 50);
		con.add(button2);
		button2.addActionListener(this);

		label1.setBounds(300, 100, 200, 50); // 위치와 크기 설정
		label1.setForeground(Color.WHITE); // 글자 색상 설정
		con.add(label1);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button2) {
			checkManager.chcManager();
		} else if (e.getSource() == button) {
			userTel.usertel();
		}

	}

}

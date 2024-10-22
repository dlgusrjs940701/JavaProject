package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class start extends JFrame implements ActionListener {
	private JButton button = new JButton("주문하기");
	private JButton button2 = new JButton("매니저");
	checkManager checkManager = new checkManager();
	userTel userTel = new userTel();

	public void main() {
		setTitle("휴먼 카페");
		setSize(600, 500);
		setLocationRelativeTo(null); // 화면 중앙에 위치

		Container con = getContentPane();
		con.setLayout(null);

		// 이미지 화면에 맞게 구현방법
		ImageIcon image = new ImageIcon("C:\\Users\\human-18\\Desktop\\카페 이미지\\커피1.jfif");
		Image img = image.getImage();
		Image changeimg = img.getScaledInstance(600, 500, Image.SCALE_SMOOTH);
		JLabel label = new JLabel(new ImageIcon(changeimg));
		label.setBounds(0, 0, 600, 500);
		con.add(label);
		
		styleButton(button,80,80);
		label.add(button);
		button.addActionListener(this);
		
		styleButton(button2,320,80);
		label.add(button2);
		button2.addActionListener(this); 

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void styleButton(JButton button3, int x, int y) {
		// TODO Auto-generated method stub
		// 버튼 디자인
		button3.setBounds(x, y, 200, 50);
		button3.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
		button3.setBackground(new Color(255, 255, 255));
		button3.setForeground(new Color(51, 51, 51));
		button3.setFocusPainted(false); // 버튼의 포커스 효과 제거
		button3.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 변경
		button3.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
		button3.setOpaque(true);
		button3.setContentAreaFilled(true); // 배경 색상 제거
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

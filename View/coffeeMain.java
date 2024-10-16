package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class coffeeMain extends JFrame implements ActionListener {

	private JButton button[] = new JButton[16];
	private JComboBox<String> menuComboBox = new JComboBox();
	private DefaultTableModel cartModel = new DefaultTableModel();
	private JTable cartList = new JTable();
	private String[][] menus = { { "아메리카노", "카페라떼", "바닐라라떼" }, // 커피
			{ "레몬에이드", "자몽에이드" }, // 논커피
			{ "사과에이드" }, // 에이드
			{ "케이크", "쿠키" } // 디저트
	};
	private String[] columns = { "메뉴 이름", "수량", "가격" };

	public void coffeemain() {
		setTitle("메인화면");
		setSize(1000, 900);
		setLayout(new BorderLayout());

		// 상단 메뉴
		JMenuBar menuBar = new JMenuBar();
		String[] categories = { "커피", "논커피", "에이드", "디저트" };
		for (String category : categories) {
			JMenu menu = new JMenu(category);
			menu.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
			menu.addActionListener(this);
			menuBar.add(menu);
		}
		setJMenuBar(menuBar);

		// 중앙 버튼
		JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton("버튼" + (i + 1));
			buttonPanel.add(button[i]);
			button[i].setBorderPainted(false);
			button[i].setBackground(Color.white);
			button[i].setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
//			button[i].setOpaque(false);
		}

		// 장바구니
		cartModel = new DefaultTableModel(columns, 0);
		cartList = new JTable(cartModel);
		JScrollPane cartScroll = new JScrollPane(cartList);
		cartScroll.setPreferredSize(new Dimension(100, 100));
		cartList.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));

		// 결제 방법
		String[] paymentMethod = { "신용카드", "현금", "계좌이체" };
		JComboBox<String> paymentComboBox = new JComboBox<String>(paymentMethod);
		paymentComboBox.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
		JButton payButton = new JButton("결제하기");
		payButton.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
		payButton.addActionListener(this);

		// 장바구니, 결제방법
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(cartScroll, BorderLayout.CENTER);
		JPanel paymentPanel = new JPanel();
		paymentPanel.add(paymentComboBox);
		paymentPanel.add(payButton);
		bottomPanel.add(paymentPanel, BorderLayout.SOUTH);

		add(buttonPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.EAST);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

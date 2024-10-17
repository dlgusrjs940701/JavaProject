package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CafeDAO.menuDAO;
import CafeDTO.menuDTO;

public class coffeeMain extends JFrame implements ActionListener {

	public coffeeMain coffeemain = null;
	private JButton button[] = new JButton[16];
	private JComboBox<String> menuComboBox = new JComboBox();
	private DefaultTableModel cartModel = new DefaultTableModel();
	private JTable cartList = new JTable();
	private String[] columns = { "메뉴 이름", "수량", "가격" };
	private JPanel buttonPanel = new JPanel(new GridLayout(5, 5));
	private String category = null;

	public void coffeemain() {
		setTitle("메인화면");
		setSize(1000, 900);
		setLayout(new BorderLayout());

		// 상단 메뉴
		JMenuBar menuBar = new JMenuBar();
		String[] categories = { "커피", "논커피", "에이드", "디저트" };
		for (String category : categories) {
//			JMenu menu=new JMenu(category);
			JMenuItem menuItem = new JMenuItem(category);
			menuItem.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
			menuItem.addActionListener(this);
			menuBar.add(menuItem);
		}
		setJMenuBar(menuBar);

		// 중앙 버튼
//		loadMenu("커피");
		for (int i = 0; i < button.length; i++) {
			button[i] = new JButton();
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
//		add(cartScroll, BorderLayout.EAST);

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
		String category = e.getActionCommand();
//	    System.out.println(category); 
		loadMenu(category);
	}

	private void loadMenu(String category) {
//		System.out.println(category);
		buttonPanel.removeAll();
//		System.out.println(category);
		menuDAO menudao = menuDAO.getinstance();
		menuDTO mdto = new menuDTO();
		mdto.setMenu_category(category);
//		System.out.println(mdto.getMenu_category());
		ArrayList<menuDTO> menus = menudao.getCategoryMenu(mdto);

//		System.out.println(category);
//		System.out.println( menus.size());

		for (menuDTO menu : menus) {
			JButton menuButton = new JButton(menu.getMenu_name() + " - " + menu.getMenu_price() + " 원 ");
			buttonPanel.add(menuButton);
		}
		buttonPanel.revalidate();
		buttonPanel.repaint();
	}

}

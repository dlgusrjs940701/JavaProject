package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import CafeDAO.menuDAO;
import CafeDAO.paymentDAO;
import CafeDTO.menuDTO;
import CafeDTO.paymentDTO;

public class coffeeMain extends JFrame implements ActionListener {

	public coffeeMain coffeemain = null;
	private JButton button[] = new JButton[16];
	private DefaultTableModel cartModel = new DefaultTableModel();
	private JTable cartList = new JTable();
	private String[] columns = { "메뉴 이름", "수량", "가격" };
	private JPanel buttonPanel = new JPanel(new GridLayout(5, 5));
//	private paymentDTO paymentDTO = null;

	public void coffeemain() {
		setTitle("휴먼 카페");
		setSize(1000, 980);
		setLayout(new BorderLayout());

		// 상단 메뉴
		JMenuBar menuBar = new JMenuBar();
		String[] categories = { "커피", "논커피", "에이드", "디저트" };
		for (String category : categories) {
			JMenu menu = new JMenu(category);
//			JMenuItem menuItem = new JMenuItem();
			menu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					loadMenu(category);
				}

			});
			menu.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
			menu.setForeground(Color.WHITE);
			menuBar.add(menu);
			menuBar.setBackground(Color.BLACK);
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
		cartScroll.setPreferredSize(new Dimension(300, 300));
		cartList.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));

		// 결제 방법
		String[] paymentMethod = { "신용카드", "현금", "계좌이체" };
		JComboBox<String> paymentComboBox = new JComboBox<String>(paymentMethod);
		paymentComboBox.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
		JButton payButton = new JButton("결제하기");
		payButton.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
		payButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int totalAmount = totalPay();
				String msg = "총 결제 금액 : " + totalAmount + " 원 결제를 진행하시겠습니까?";
				int ok = JOptionPane.showConfirmDialog(null, msg, "결제 확인", JOptionPane.OK_OPTION);
				if (ok == JOptionPane.OK_OPTION) {
					paymentDTO pdto=new paymentDTO();
//					pdto.setPayment_id(null);
					pdto.setPayment_method((String)paymentComboBox.getSelectedItem());
					pdto.setAmount(String.valueOf(totalAmount));
//					pdto.setPayment_time(null);
					pdto.setStatus("완료");
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다");
					cartModel.setRowCount(0);
					paymentDAO.getinstance().addTotalPay(pdto);
				} else {
					JOptionPane.showMessageDialog(null, "결제가 취소되었습니다");
				}
			}

			private int totalPay() {
				// TODO Auto-generated method stub
				int totalAmount = 0;
				for (int i = 0; i < cartModel.getRowCount(); i++) {
					int price = (int) cartModel.getValueAt(i, 2);
					totalAmount += price;
				}
				return totalAmount;
			}
		});

		// 장바구니, 결제방법
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(cartScroll, BorderLayout.CENTER);
		JPanel paymentPanel = new JPanel();
		paymentPanel.add(paymentComboBox);
		paymentPanel.add(payButton);
		bottomPanel.add(paymentPanel, BorderLayout.SOUTH);
		
		loadMenu("커피");

		add(buttonPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.EAST);

		setVisible(true);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String category = e.getActionCommand();
//	    System.out.println(category); 
		loadMenu(category);
	}

	// 카테고리별로 메뉴
	private void loadMenu(String category) {
		buttonPanel.removeAll();
		menuDAO menudao = menuDAO.getinstance();
		menuDTO mdto = new menuDTO();
		mdto.setMenu_category(category);
		ArrayList<menuDTO> menus = menudao.getCategoryMenu(mdto);

		// 몇개로 나열할것인지 설정
		int columns = 3; // 열 수 조정
		int rows = (menus.size() + columns - 1) / columns; // 필요한 행 수 계산
		buttonPanel.setLayout(new GridLayout(rows, columns)); // 그리드 레이아웃 설정

		for (menuDTO menu : menus) {
			ImageIcon image = new ImageIcon(menu.getImage());
			Image img = image.getImage();
			Image changeimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			JButton menuButton = new JButton(new ImageIcon(changeimg));
			menuButton.setText(menu.getMenu_name() + " - " + menu.getMenu_price() + " 원");
			menuButton.setHorizontalTextPosition(JButton.CENTER);
			menuButton.setVerticalTextPosition(JButton.BOTTOM);
			menuButton.setPreferredSize(new Dimension(100, 50)); // 버튼 크기
			menuButton.setFont(new Font("Malgun Gothic", Font.BOLD, 16)); // 폰트 스타일
			menuButton.setBackground(new Color(240, 240, 240)); // 배경색
			menuButton.setForeground(Color.DARK_GRAY); // 텍스트 색상
			menuButton.setBorderPainted(true); // 테두리 없애기
			menuButton.setOpaque(true); // 배경색을 보여주기 위해
			menuButton.setFocusPainted(false); // 포커스 시 테두리 없애기
			// 마우스 오버
			menuButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					menuButton.setBackground(new Color(220, 220, 220));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					menuButton.setBackground(new Color(240, 240, 240));
				}
			});
			// 수량과 옵션 선택창
			menuButton.addActionListener(new ActionListener() {

				// 수량 옵션
				private void updateNum(JTextField numField, int i) {
					// TODO Auto-generated method stub
					int num = Integer.parseInt(numField.getText());
					num += i;
					if (num < 1) {
						num = 1;
					}
					numField.setText(String.valueOf(num));
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					JPanel order = new JPanel();
					JLabel numLabel = new JLabel("수량");
					JButton btn1 = new JButton("-");
					JButton btn2 = new JButton("+");
					JTextField numField = new JTextField(3);
					numField.setText("1");

					btn1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							updateNum(numField, -1);
						}

					});

					btn2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							updateNum(numField, +1);
						}

					});

					String[] options = { "기본", "샷추가", "우유 추가" };
					JComboBox<String> option1 = new JComboBox<>(options);

					order.add(numLabel);
					order.add(btn1);
					order.add(numField);
					order.add(btn2);
					order.add(new JLabel("옵션 : "));
					order.add(option1);

					int option = JOptionPane.showConfirmDialog(null, order, menu.getMenu_name(),
							JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						int num = Integer.parseInt(numField.getText());
						String selOption = (String) option1.getSelectedItem();
						int selMenuPrice = menu.getMenu_price();
						String selMenuName = menu.getMenu_name();
						int totalPrice = selMenuPrice * num;
						Object[] row = { selMenuName, num, totalPrice, selOption };
						cartModel.addRow(row);
						System.out.println("장바구니에 추가 : " + menu.getMenu_name() + ", 수량 : " + num);
					}
				}

			});
			buttonPanel.add(menuButton);

		}

		buttonPanel.revalidate();
		buttonPanel.repaint();
	}

}

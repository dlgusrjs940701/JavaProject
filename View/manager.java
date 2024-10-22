package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import CafeDAO.menuDAO;
import CafeDAO.paymentDAO;
import CafeDTO.menuDTO;
import CafeDTO.paymentDTO;

public class manager extends JFrame implements ActionListener {

	public manager manager = null;
	JFrame frame = new JFrame("이현건 매니저님, 환영합니다.");
	Container con = frame.getContentPane();
	JButton button1 = new JButton("닫기");
	JLabel totalSales = new JLabel("총 매출: 원");

	public void main() {
		frame.setPreferredSize(new Dimension(800, 800));
		frame.setLocation(800, 800);

		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		JLabel dateLabel = new JLabel("오늘 날짜: " + date);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);

		String[] indicate = { "주문번호", "결제수단", "가격", "결제시간", "결제상태" };
		DefaultTableModel model = new DefaultTableModel(indicate, 0);
		JTable table = new JTable(model);

		// 결제데이터
		ArrayList<paymentDTO> pay = paymentDAO.getinstance().loadPay();

		for (paymentDTO pay1 : pay) {
			Object[] row = { pay1.getPayment_id(), pay1.getPayment_method(), pay1.getAmount(), pay1.getPayment_time(),
					pay1.getStatus(), };

			model.addRow(row);

		}

		int total = 0;
		for (paymentDTO pay1 : pay) {
			total += Integer.parseInt(pay1.getAmount());
		}
		totalSales.setText("총 매출 : " + total + "원");

		JPanel infoPanel = new JPanel();
		infoPanel.add(totalSales);
		con.add(infoPanel, BorderLayout.SOUTH);
		con.add(dateLabel, BorderLayout.NORTH);
		con.add(new JScrollPane(table), BorderLayout.CENTER);

		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

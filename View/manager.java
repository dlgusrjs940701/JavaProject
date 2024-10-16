package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class manager extends JFrame implements ActionListener {
	JFrame frame = new JFrame("이현건 매니저님, 환영합니다.");
	Container con = frame.getContentPane();
	JPanel panel = new JPanel();
	JTextField text1 = new JTextField(6);
	JTextField text2 = new JTextField(4);
	JTextField text3 = new JTextField(2);
	JButton button1 = new JButton("추가");
	JButton button2 = new JButton("삭제");
	JLabel totalSales = new JLabel("총 매출: 원");
	JLabel visitCount = new JLabel("방문 횟수: 번");
	JPanel panel1 = new JPanel();

	public void main() {
		frame.setPreferredSize(new Dimension(800, 800));
		frame.setLocation(800, 800);

		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		JLabel dateLabel = new JLabel("오늘 날짜: " + date);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);

		String indicate[] = { "주문번호", "메뉴", "가격" };
		DefaultTableModel model = new DefaultTableModel(indicate, 0);
		JTable table = new JTable(model);

		con.add(dateLabel, BorderLayout.NORTH);
		con.add(new JScrollPane(table), BorderLayout.CENTER);

		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.add(totalSales);
		panel1.add(visitCount);
		con.add(panel1, BorderLayout.SOUTH);

		panel.add(button1);
		panel.add(button2);
		con.add(panel, BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

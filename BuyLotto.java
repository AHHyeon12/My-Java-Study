

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;

public class BuyLotto extends JPanel {
	public BuyLotto() {
		setSize(1100,600);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblNorth = new JLabel("로또 구매");
		lblNorth.setFont(new Font("휴먼모음T", Font.PLAIN, 16));
		pnlNorth.add(lblNorth);
		
		JPanel pnlSouth = new JPanel();
		add(pnlSouth, BorderLayout.SOUTH);
		
		JPanel pnlCenter = new JPanel();
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new GridLayout(0, 5, 0, 0));
		
		JPanel pnlBuy = new JPanel();
		pnlCenter.add(pnlBuy);
		pnlBuy.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBuyChk = new JPanel();
		pnlBuy.add(pnlBuyChk, BorderLayout.CENTER);
		pnlBuyChk.setLayout(new GridLayout(0, 5, 0, 0));
		
		for(int i = 1; i < 46; i++) {
			String str = String.valueOf(i);
			JCheckBox check = new JCheckBox(str);
			pnlBuyChk.add(check);
		}
		
		JPanel pnlBuyBtn = new JPanel();
		pnlBuy.add(pnlBuyBtn, BorderLayout.SOUTH);
		pnlBuyBtn.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnHand = new JButton("수동");
		pnlBuyBtn.add(btnHand);
		
		JButton btnAuto = new JButton("자동");
		pnlBuyBtn.add(btnAuto);
		
		JPanel panel_4 = new JPanel();
		pnlCenter.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		pnlCenter.add(panel_5);
	}
}

package win.iscary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameUI extends JFrame{
	
	private int Numbers[][];
	private MyListener listener;
	private Action action;
	private JLabel scoreTextLabel;
	private JLabel scoreLabel;
	private JButton backButton;
	
	public GameUI() {
		Numbers = new int[4][4];
		for (int i = 1; i <= 15; i++) {
			Numbers[i/4][i%4] = (int)Math.pow(2, i);
		}
	}
	
	public GameUI(int Numbers[][], Action action) {
		this.Numbers = Numbers;
		this.action = action;
	}
	
	public void Init() {
		this.setTitle("2048");
		this.setIconImage((new ImageIcon("images/2048.png").getImage()));
		this.setSize(400, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scoreTextLabel = new JLabel("Score:");
		scoreTextLabel.setBounds(20, 20, 100, 20);
		scoreTextLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 25));
		scoreTextLabel.setForeground(Color.BLACK);
		this.add(scoreTextLabel);
		scoreLabel = new JLabel("000000");
		scoreLabel.setBounds(105, 21, 100, 20);
		scoreLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 25));
		scoreLabel.setForeground(Color.BLACK);
		this.add(scoreLabel);
		
		listener = new MyListener(Numbers, action, this);
		this.addKeyListener(listener);
		
		backButton = new JButton("Back");
		backButton.setBounds(280, 15, 80, 40);
		backButton.setFocusable(false);
		backButton.addActionListener(listener);
		this.add(backButton);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0xBBADA0));
		g.fillRoundRect(15, 110, 370, 370, 15, 15);
		
		g.setColor(new Color(0xCDC1B4));
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				g.fillRoundRect(25+i*90, 120+j*90, 80, 80, 15, 15);
			}
		}
		
		int FontSize = 30;
		int MoveX = 0;
		int MoveY = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(Numbers[i][j]!=0) {
					switch (Numbers[i][j]) {
					case 2:
						g.setColor(new Color(0xeee4da));
						FontSize = 30;
						MoveX = 0;
						MoveY = 0;
						break;
					case 4:
						g.setColor(new Color(0xede0c8));
						FontSize = 30;
						MoveX = 0;
						MoveY = 0;
						break;
					case 8:
						g.setColor(new Color(0xf2b179));
						FontSize = 30;
						MoveX = 0;
						MoveY = 0;
						break;
					case 16:
						g.setColor(new Color(0xf56563));
						FontSize = 29;
						MoveX = -5;
						MoveY = 0;
						break;
					case 32:
						g.setColor(new Color(0xf67c5f));
						FontSize = 29;
						MoveX = -5;
						MoveY = 0;
						break;
					case 64:
						g.setColor(new Color(0xf65e3b));
						FontSize = 29;
						MoveX = -5;
						MoveY = 0;
						break;
					case 128:
						g.setColor(new Color(0xfedcf72));
						FontSize = 28;
						MoveX = -14;
						MoveY = 0;
						break;
					case 256:
						g.setColor(new Color(0xedcc61));
						FontSize = 28;
						MoveX = -14;
						MoveY = 0;
						break;
					case 512:
						g.setColor(new Color(0xedc850));
						FontSize = 28;
						MoveX = -14;
						MoveY = 0;
						break;
					case 1024:
						g.setColor(new Color(0xedc53f));
						FontSize = 27;
						MoveX = -20;
						MoveY = 0;
						break;
					case 2048:
						g.setColor(new Color(0xedc22e));
						FontSize = 27;
						MoveX = -20;
						MoveY = 0;
						break;
					case 4096:
						g.setColor(new Color(0x00cc33));
						FontSize = 27;
						MoveX = -20;
						MoveY = 0;
						break;
					case 8192:
						g.setColor(new Color(0x0099ff));
						FontSize = 27;
						MoveX = -20;
						MoveY = 0;
						break;
					default:
						g.setColor(Color.WHITE);
						FontSize = 25;
						MoveX = -26;
						MoveY = 0;
						break;
							
					}
					g.fillRoundRect(25+j*90, 120+i*90, 80, 80, 15, 15);
					g.setColor(new Color(0x000000));
					g.setFont(new Font("Arial", Font.PLAIN, FontSize));
					g.drawString(Numbers[i][j]+"", 25+j*90+30+MoveX, 120+i*90+50+MoveY);
				}
			}
		}
		//²âÊÔGAME OVERÏÔÊ¾
//		paintDeath(g);
	}

	public void upgradeScore() {
		scoreLabel.setText(action.calScore() + "");
	}
	
	public void paintDeath(Graphics g) {
		int FontSize = 30;
		int MoveX = 0;
		int MoveY = 0;
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				switch (Numbers[i][j]) {
					case 2:
					case 4:
					case 8:
						g.setColor(Color.LIGHT_GRAY);
						FontSize = 30;
						MoveX = 0;
						MoveY = 0;
						break;
					case 16:
					case 32:
					case 64:
						g.setColor(Color.LIGHT_GRAY);
						FontSize = 29;
						MoveX = -5;
						MoveY = 0;
						break;
					case 128:
					case 256:
					case 512:
						g.setColor(Color.LIGHT_GRAY);
						FontSize = 28;
						MoveX = -14;
						MoveY = 0;
						break;
					case 1024:
					case 2048:
					case 4096:
					case 8192:
						g.setColor(Color.LIGHT_GRAY);
						FontSize = 27;
						MoveX = -20;
						MoveY = 0;
						break;
					default:
						g.setColor(Color.LIGHT_GRAY);
						FontSize = 25;
						MoveX = -26;
						MoveY = 0;
						break;		
				}
				g.fillRoundRect(25+j*90, 120+i*90, 80, 80, 15, 15);
				g.setColor(new Color(0x000000));
				g.setFont(new Font("Arial", Font.PLAIN, FontSize));
				g.drawString(Numbers[i][j]+"", 25+j*90+30+MoveX, 120+i*90+50+MoveY);
			}
		}
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 40));
		g.drawString("GAME OVER", 81, 300);
	}
	
	public void changeWinColor() {
		scoreLabel.setForeground(Color.ORANGE);
//		this.setFont(new Font("Arial", Font.BOLD, 30));
//		this.getGraphics().drawString("made it", 100, 50);
	}
	
	public void openBackButton() {
		backButton.setEnabled(true);
	}
	
	public void closeBackButton() {
		backButton.setEnabled(false);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameUI game = new GameUI();
		game.Init();
		game.changeWinColor();
		

	}

}

package win.iscary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyListener extends KeyAdapter implements ActionListener{

	int Numbers[][];
	private Action action;
	GameUI ui;
	
	public MyListener() {
		super();
		Numbers = new int[4][4];
		action = new Action(Numbers);
	}
	
	public MyListener(int Numbers[][], Action action, GameUI ui) {
//		super();
		this.Numbers = Numbers;
		this.action = action;
		this.ui = ui;
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:
			//��
			action.moveLeft();
			action.addLeft();
			action.moveLeft();
			break;
		case 38:
			//��
			action.moveUp();
			action.addUp();
			action.moveUp();
			break;
		case 39:
			//��
			action.moveRight();
			action.addRight();
			action.moveRight();
			break;
		case 40:
			//��
			action.moveDown();
			action.addDown();
			action.moveDown();
			break;
		default:
			break;
		}
//		action.showNow();
//		action.showOld();
		if(action.saveNumbers()) {
			action.putNewNumber();
			action.saveNumbers();
			action.saveBackNumbers();//�䶯һ�α���һ������
			ui.paint(ui.getGraphics());
			ui.upgradeScore();
			ui.openBackButton();;//backButton�ָ�Ϊ����
		}
		if(action.checkWin()) {
			ui.changeWinColor();
		}
		if(action.checkDeath()) {
//			System.out.println("death!");
			ui.paintDeath(ui.getGraphics());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(action.recover()) {
			System.out.println("backButton");
			ui.closeBackButton();//backButton����Ϊ������
			ui.paint(ui.getGraphics());
			ui.upgradeScore();
		} else {
			System.out.println("can't back");
		}
		
	}

}

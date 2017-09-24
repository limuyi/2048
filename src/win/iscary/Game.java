package win.iscary;

public class Game {
	
	private int Numbers[][];
	private Action action;
	private GameUI ui;

	private void startGame() {
		Numbers = new int[4][4];
		action = new Action(Numbers);
		action.putNewNumber();
		action.putNewNumber();
		action.saveNumbers();
		action.saveBackNumbers();
		action.saveBackNumbers();//�������Σ���ֹ����û�����������
		ui = new GameUI(Numbers, action);
		ui.Init();
		ui.upgradeScore();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game().startGame();
	}

}

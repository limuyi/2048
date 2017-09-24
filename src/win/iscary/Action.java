package win.iscary;

import java.util.Random;

public class Action {
	
	int Numbers[][];
	int Old_Numbers[][];
	int Back_Numbers[][];
	int Back_Numbers2[][];
	Random random;
	int score;
	
	public Action() {
		Numbers = new int[4][4];
		Numbers[1][1]=2;
		Numbers[1][2]=2;
		Numbers[2][1]=2;
		Numbers[2][2]=2;
		Numbers[3][2]=4;
		random = new Random();
		Old_Numbers = new int[4][4];
		Back_Numbers = new int[4][4];
		Back_Numbers2 = new int[4][4];
	}
	
	public Action(int Numbers[][]) {
		this.Numbers = Numbers;
		random = new Random();
		Old_Numbers = new int[4][4];
		Back_Numbers = new int[4][4];
		Back_Numbers2 = new int[4][4];
		score = 0;
	}
	
	//��ӡ��ǰNumbers��������
	public void showNumbers(int n[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(n[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void showNow() {
		showNumbers(Numbers);
	}
	
	public void showOld() {
		showNumbers(Old_Numbers);
	}
	
	public String calScore() {
		score = 0;
		for (int i = 0; i <4; i++) {
			for (int j = 0; j < 4; j++) {
				score += Numbers[i][j];
			}
		}
		return score+"";
	}
	
	//�������2��4
	public int getRandomNumber() {
		return random.nextInt(4)==1?4:2;
	}
	
	//�������һ��λ�ã�������pos������
	public int[] getRandomPosition() {
		int[] pos = new int[2];
		pos[0] = random.nextInt(4);
		pos[1] = random.nextInt(4);
		return pos;
	}
	
	//����������꣬�ж��Ƿ�Ϊ���ա���0����true�����򷵻�false
	public boolean checkPositionEmpty(int x,int y) {
		return Numbers[x][y]==0?true:false;	
	}
	
	//�ڿ�λ���������2��4
	public void putNewNumber() {
		int[] pos;
		do {
			pos = getRandomPosition();
		} while(!checkPositionEmpty(pos[0], pos[1]));
		Numbers[pos[0]][pos[1]] = getRandomNumber();
//		System.out.println(pos[0]+""+pos[1]+""+Numbers[pos[0]][pos[1]]);
	}

	//������������֣��иĶ�����true���޸Ķ�����false
	public boolean saveNumbers() {
		boolean flag = false;
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(Old_Numbers[i][j] != Numbers[i][j]) {
					Old_Numbers[i][j] = Numbers[i][j];
					flag = true;
				}
			}
		}
		return flag;
	}
	
	//����ָ���������
	public void saveBackNumbers() {
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Back_Numbers2[i][j]=Back_Numbers[i][j];
				Back_Numbers[i][j]=Numbers[i][j];
			}
		}
	}
	
	//�������������ж��Ƿ���ͬ
	public boolean isSame(int[][] a, int[][] b) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(a[i][j] != b[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	//���˵���һ��
	public boolean recover() {
		if(!isSame(Numbers, Back_Numbers2)) {
			for(int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Numbers[i][j]=Back_Numbers2[i][j];
				}
			}
			saveNumbers();//ͬ����ǰ��Old_Numbers���Ա��ж���һ�β����Ƿ���Ч
			saveBackNumbers();//ͬ����ǰ�ı������飬��ֹ���˺�ı������黹�ǻ���ǰ�ļ�¼
			calScore();//���·���
			return true;
		} else {
			return false;
		}
	}
	
	//�����������ϲ���
	public void moveUp() {
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for(int k = i-1; k >= 0; k--) {
					if(Numbers[k][j]==0) {
						Numbers[k][j]=Numbers[k+1][j];
						Numbers[k+1][j]=0;
					}
				}
			}
		}
	}
	
	//�����������²���
	public void moveDown() {
		for (int i = 3; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				for(int k = i+1; k < 4; k++) {
					if(Numbers[k][j]==0) {
						Numbers[k][j]=Numbers[k-1][j];
						Numbers[k-1][j]=0;
					}
				}
			}
		}
	}
	
	//��������������
	public void moveLeft() {
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for(int k = i-1; k >= 0; k--) {
					if(Numbers[j][k]==0) {
						Numbers[j][k]=Numbers[j][k+1];
						Numbers[j][k+1]=0;
					}
				}
			}
		}
	}
	
	//�����������Ҳ���
	public void moveRight() {
		for (int i = 3; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				for(int k = i+1; k < 4; k++) {
					if(Numbers[j][k]==0) {
						Numbers[j][k]=Numbers[j][k-1];
						Numbers[j][k-1]=0;
					}
				}
			}
		}
	}
	
	//���������������
	public void addUp() {
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for(int k = i-1; k >= 0; k--) {
					if(Numbers[k][j]==Numbers[k+1][j]) {
						Numbers[k+1][j]+=Numbers[k][j];
						Numbers[k][j]=0;
					}
				}
			}
		}
//		System.out.println("Left");
	}
	
	//���������������
	public void addDown() {
		for (int i = 3; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				for(int k = i+1; k < 4; k++) {
					if(Numbers[k][j]==Numbers[k-1][j]) {
						Numbers[k-1][j]+=Numbers[k][j];
						Numbers[k][j]=0;
					}
				}
			}
		}
//		System.out.println("Right");
	}
	
	//���������������
	public void addLeft() {
		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for(int k = i-1; k >= 0; k--) {
					if(Numbers[j][k]==Numbers[j][k+1]) {
						Numbers[j][k+1]+=Numbers[j][k];
						Numbers[j][k]=0;
					}
				}
			}
		}
//		System.out.println("Up");
	}
	
	//���������������
	public void addRight() {
		for (int i = 3; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				for(int k = i+1; k < 4; k++) {
					if(Numbers[j][k]==Numbers[j][k-1]) {
						Numbers[j][k-1]+=Numbers[j][k];
						Numbers[j][k]=0;
					}
				}
			}
		}
//		System.out.println("Down");
	}
	
	//�ж���Ϸ�Ƿ��������������true�����򷵻�false
	public boolean checkDeath() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				//�п�λһ����Ϊdeath
				if(Numbers[i][j] == 0) {
					return false;
				}
				for(int k = i+1; k < 4; k++) {
					//���Ƿ���Ժϲ����¿��Ժ��������ж�һ�򼴿ɣ�������ͬ��
					if(Numbers[k][j]==Numbers[k-1][j] || Numbers[j][k]==Numbers[j][k-1]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	//�ж��Ƿ����2048�����ַ���true�����򷵻�false
	public boolean checkWin() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(Numbers[i][j]==2048) {
					return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		Action test = new Action();
		test.showNow();
		System.out.println("up--------------");
		test.moveUp();
		test.showNow();
		System.out.println("old--------------");
		test.saveNumbers();
		test.showOld();
		System.out.println("up--------------");
		test.moveUp();
		test.showNow();
		test.showOld();
		System.out.println("old--------------");
		if(test.saveNumbers())
		test.showOld();
	}

}

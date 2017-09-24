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
	
	//打印当前Numbers，测试用
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
	
	//随机产生2或4
	public int getRandomNumber() {
		return random.nextInt(4)==1?4:2;
	}
	
	//随机产生一个位置，储存在pos数组中
	public int[] getRandomPosition() {
		int[] pos = new int[2];
		pos[0] = random.nextInt(4);
		pos[1] = random.nextInt(4);
		return pos;
	}
	
	//传入横纵坐标，判断是否为“空”，0返回true，否则返回false
	public boolean checkPositionEmpty(int x,int y) {
		return Numbers[x][y]==0?true:false;	
	}
	
	//在空位上随机放置2或4
	public void putNewNumber() {
		int[] pos;
		do {
			pos = getRandomPosition();
		} while(!checkPositionEmpty(pos[0], pos[1]));
		Numbers[pos[0]][pos[1]] = getRandomNumber();
//		System.out.println(pos[0]+""+pos[1]+""+Numbers[pos[0]][pos[1]]);
	}

	//储存变更后的数字，有改动返回true，无改动返回false
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
	
	//储存恢复备份数组
	public void saveBackNumbers() {
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Back_Numbers2[i][j]=Back_Numbers[i][j];
				Back_Numbers[i][j]=Numbers[i][j];
			}
		}
	}
	
	//传入两个数组判断是否相同
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
	
	//回退到上一步
	public boolean recover() {
		if(!isSame(Numbers, Back_Numbers2)) {
			for(int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					Numbers[i][j]=Back_Numbers2[i][j];
				}
			}
			saveNumbers();//同步当前的Old_Numbers，以便判断下一次操作是否有效
			saveBackNumbers();//同步当前的备份数组，防止回退后的备份数组还是回退前的记录
			calScore();//更新分数
			return true;
		} else {
			return false;
		}
	}
	
	//所有数字向上补齐
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
	
	//所有数字向下补齐
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
	
	//所有数字向左补齐
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
	
	//所有数字向右补齐
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
	
	//所有数字向上相加
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
	
	//所有数字向下相加
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
	
	//所有数字向左相加
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
	
	//所有数字向右相加
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
	
	//判断游戏是否结束，结束返回true，否则返回false
	public boolean checkDeath() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				//有空位一定不为death
				if(Numbers[i][j] == 0) {
					return false;
				}
				for(int k = i+1; k < 4; k++) {
					//上是否可以合并（下可以忽略上下判断一向即可），左右同理
					if(Numbers[k][j]==Numbers[k-1][j] || Numbers[j][k]==Numbers[j][k-1]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	//判断是否出现2048，出现返回true，否则返回false
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

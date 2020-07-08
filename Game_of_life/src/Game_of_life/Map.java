package Game_of_life;

public class Map {
	public static Cell[][] initial() {
		Cell [][]cell=new Cell[99][99];
		for(int i=0;i<99;i++) {
			for(int j=0;j<99;j++) {
				 cell[i][j]=new Cell();
			}
		}
		return cell;
	}
	
	//计算每个细胞周围存活细胞个数
	static void calculate_living(Cell cell[][]) {
		for(int i=0;i<99;i++) {
			for(int j=0;j<99;j++) {
				int m=0;
				if(i>0&&j>0) m+=cell[i-1][j-1].get_state();
				if(j>0) m+=cell[i][j-1].get_state();
				if(i<98&&j>0) m+=cell[i+1][j-1].get_state();
				if(i<98) m+=cell[i+1][j].get_state();
				if(i<98&&j<98) m+=cell[i+1][j+1].get_state();
				if(j<98) m+=cell[i][j+1].get_state();
				if(i>0&&j<98) m+=cell[i-1][j+1].get_state();
				if(i>0) m+=cell[i-1][j].get_state();
				cell[i][j].alter_living_num(m);
			}
		
		}
	}
}

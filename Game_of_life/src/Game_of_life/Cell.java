package Game_of_life;

public class Cell {
	int living_num; //ϸ����Χ������
	int state; //ϸ������״̬,1��ʾ��0��ʾ����
	Cell(){
		living_num=0;
		state=0;
		}
		
	public int get_living_num() {
		return living_num;
		}
	public int get_state() {
		return state;
		}
	public void alter_living_num(int living_num1) {
		living_num=living_num1;
		}
	public void alter_state(int state1) {
		state=state1;
		}
	//��ϸ����Χ������ж��Ƿ�����
	int judge() {
		int a=get_living_num();
		int b=get_state();
		if(a>3||a<2) b=0;
		else if(a==3) b=1;
		return b;
		}
}

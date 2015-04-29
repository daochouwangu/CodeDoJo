import java.util.HashSet;
import java.util.Set;


public class SudokuGame {
	public static final int LINENUM = 4;
	public int[][] result = new int[LINENUM][LINENUM];
	public static final int[] prime = {2,3,5,7};
	public int[][] getResult(int a[][]) throws Exception{
		int[][] groups = createGroups(a);
		if(isTrue(a))
			return a;
		for(int i=0;i<LINENUM;i++){
			for(int j=0;j<LINENUM;j++){
				if(a[i][j]!=0)
					continue;
				Set<Integer> keys = getKey(i,j,groups);
				if(keys.size()==1){
					a[i][j] = keys.iterator().next();
					return getResult(a);
				}
			}
		}
		return result;
	}
	public Set<Integer> getKey(int x,int y,int[][] groups) throws Exception{
		Set<Integer> keys = new HashSet<>();
		//获得所属组的ID
		int[] groupsindex = getGroupsByCoordinate(x,y);
		for(int i=0;i<groupsindex.length;i++){
			//获得目前组的解
			Set<Integer> pos = getPossibles(groups[groupsindex[i]]);
			if(pos==null)
				throw new Exception();
			if(keys.isEmpty())
				keys.addAll(pos);
			else
				keys.retainAll(pos);
		}
		return keys;
	}
	public int[] getGroupsByCoordinate(int x,int y) throws Exception{
		int[] a = new int[3];
		a[0] = x;
		a[1] = y+LINENUM;
		a[2] = getQuadrant(x,y)+LINENUM*2;
		return a;
	}
	public int getQuadrant(int x,int y) throws Exception{
		if(x<LINENUM/2&&y<LINENUM/2){
			return 0;
		}else if(x<LINENUM/2&&y>=LINENUM/2){
			return 1;
		}else if(x>=LINENUM/2&&y<LINENUM/2){
			return 2;
		}else if(x>=LINENUM/2&&y>=LINENUM/2){
			return 3;
		}
		throw new Exception("错误的坐标");
	}
	public Set<Integer> getPossibles(int a[]){
		Set<Integer> b = new HashSet<>();
		int product=1;
		for(int i=0;i<a.length;i++){
			if(a[i]!=0)
				product *= prime[a[i]-1]; 
		}
		for(int j=0;j<LINENUM;j++){
			if(product%prime[j]!=0)
				b.add(j+1);
		}
		return b;
	}
	public int[][] createGroups(int a[][]){
		int[][] groups = new int[LINENUM*3][LINENUM];
		//生成横向四组
		for(int i=0;i<LINENUM;i++){
			groups[i] = a[i];
		}
		//生成纵向四组
		for(int i=0;i<LINENUM;i++){
			for(int j=0;j<LINENUM;j++){
				groups[i+LINENUM][j] = a[j][i];
			}
		}
		int index = 0;
		//生成小四方的数组
		for(int i=0;i<LINENUM/2;i++){
			for(int j=0;j<LINENUM/2;j++){
				groups[2*LINENUM][index++] = a[i][j];
			}
		}
		index = 0;
		//生成小四方的数组
		for(int i=0;i<LINENUM/2;i++){
			for(int j=LINENUM/2;j<LINENUM;j++){
				groups[2*LINENUM+1][index++] = a[i][j];
			}
		}
		index = 0;
		//生成小四方的数组
		for(int i=LINENUM/2;i<LINENUM;i++){
			for(int j=0;j<LINENUM/2;j++){
				groups[2*LINENUM+2][index++] = a[i][j];
			}
		}
		index = 0;
		//生成小四方的数组
		for(int i=LINENUM/2;i<LINENUM;i++){
			for(int j=LINENUM/2;j<LINENUM;j++){
				groups[2*LINENUM+3][index++] = a[i][j];
			}
		}
		return groups;
	} 
	public boolean isTrue(int a[][]){
		if(hasIllegalNum(a))
			return false;
		return !hasIllegalGroup(createGroups(a));
	} 
	public void printResult(int a[][]) throws Exception{
		this.printGroups(this.getResult(a));
	}
	public void printGroups(int groups[][]){
		for(int i=0;i<groups.length;i++){
			for(int j = 0;j<LINENUM;j++){
				System.out.print(groups[i][j]);
			}
			System.out.println();
		}
	}
	public boolean hasIllegalGroup(int group[][]){
		for(int i=0;i<group.length;i++){
			if(isIllegalGroup(group[i]))
				return true;
		}
		return false;
	}
	public boolean hasIllegalNum(int a[][]){
		for(int i=0;i<LINENUM;i++){
			for(int j=0;j<LINENUM;j++){
				if(isIllegalNum(a[i][j]))
					return true;
			}
		}
		return false;
	}
	public boolean isIllegalNum(int a){
		if(a<1||a>LINENUM){
			return true;
		}
		return false;
	}
	public boolean isIllegalGroup(int a[]){
		int product = 1;
		for(int i=0;i<LINENUM;i++){
			if(isIllegalNum(a[i]))
				return true;
			int number = prime[a[i]-1];
			if(product%number==0)
				return true;
			product = product * number;
		}
		return false;
	}
//	public static void main(String[] args) throws Exception{
//		new SudokuGame().printGroups(new SudokuGame().getResult(new int[][]
//				{{0,0,0,0},
//				{3,0,0,0},
//				{0,0,4,0},
//				{0,2,3,0}}));
//		new SudokuGame().printGroups(new SudokuGame().getResult(new int[][]
//				{{0,4,0,0},
//				{0,0,0,4},
//				{1,0,0,0},
//				{0,0,3,0}}));
//		new SudokuGame().printGroups(new SudokuGame().getResult(new int[][]
//				{{2,0,0,0},
//				{3,0,0,2},
//				{0,0,0,0},
//				{0,0,4,0}}));
//	}
}
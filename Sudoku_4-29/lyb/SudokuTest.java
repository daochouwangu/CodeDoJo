import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SudokuTest {
	@Test
	public void isIllegalNum() {
		assertEquals(true,new SudokuGame().isIllegalNum(0));
		assertEquals(false,new SudokuGame().isIllegalNum(1));
		assertEquals(false,new SudokuGame().isIllegalNum(2));
		assertEquals(false,new SudokuGame().isIllegalNum(3));
		assertEquals(false,new SudokuGame().isIllegalNum(4));
		assertEquals(true,new SudokuGame().isIllegalNum(5));
	}
	@Test
	public void hasIllegalNum() {
		assertEquals(true,new SudokuGame().hasIllegalNum(new int[][]{{1,2,3,4},{5,1,2,3},{4,5,2,1},{2,3,4,2}}));
		assertEquals(true,new SudokuGame().hasIllegalNum(new int[][]{{1,2,3,4},{4,1,2,3},{4,0,2,1},{2,3,4,2}}));
		assertEquals(false,new SudokuGame().hasIllegalNum(new int[][]{{1,2,3,4},{3,1,2,3},{4,1,2,1},{2,3,4,2}}));
		assertEquals(false,new SudokuGame().hasIllegalNum(new int[][]{{1,2,3,4},{2,1,2,3},{4,4,2,1},{2,3,4,2}}));
		assertEquals(false,new SudokuGame().hasIllegalNum(new int[][]{{1,2,3,4},{1,1,2,3},{4,3,2,1},{2,3,4,2}}));
		assertEquals(true,new SudokuGame().hasIllegalNum(new int[][]{{1,2,3,4},{1,1,2,3},{4,0,2,1},{2,3,4,2}}));
	}
	@Test
	public void isRightGroup() {
		assertEquals(false,new SudokuGame().isIllegalGroup(new int[]{1,2,3,4}));
		assertEquals(true,new SudokuGame().isIllegalGroup(new int[]{1,2,0,4}));
		assertEquals(true,new SudokuGame().isIllegalGroup(new int[]{1,2,5,4}));
		assertEquals(true,new SudokuGame().isIllegalGroup(new int[]{2,2,3,4}));
		assertEquals(true,new SudokuGame().isIllegalGroup(new int[]{3,2,3,4}));
		assertEquals(true,new SudokuGame().isIllegalGroup(new int[]{4,2,3,4}));
		assertEquals(true,new SudokuGame().isIllegalGroup(new int[]{1,1,1,1}));
	}
	@Test
	public void isTrue() {
		assertEquals(true,new SudokuGame().isTrue(new int[][]
				{{1,3,2,4},
				{2,4,1,3},
				{3,2,4,1},
				{4,1,3,2}}));
		assertEquals(false,new SudokuGame().isTrue(new int[][]
				{{1,3,2,4},
				{2,4,1,3},
				{4,2,4,1},
				{3,1,3,2}}));
		assertEquals(false,new SudokuGame().isTrue(new int[][]
				{{1,3,2,4},
				{1,4,2,3},
				{3,2,4,1},
				{4,1,3,2}}));
		assertEquals(false,new SudokuGame().isTrue(new int[][]
				{{1,3,2,4},
				{2,4,1,3},
				{3,2,4,1},
				{5,1,3,2}}));
		assertEquals(true,new SudokuGame().isTrue(new int[][]
				{{1,2,3,4},
				{3,4,1,2},
				{2,3,4,1},
				{4,1,2,3}}));
		assertEquals(true,new SudokuGame().isTrue(new int[][]
				{{1,3,2,4},
				{2,4,1,3},
				{3,2,4,1},
				{4,1,3,2}}));
	}
	@Test
	public void getPossible() {
		List<Integer> a =new ArrayList<Integer>();
		a.add(1);
		assertEquals(a,new SudokuGame().getPossibles(new int[]{2,4,3,0}));
		List<Integer> b =new ArrayList<Integer>();
		b.add(2);
		assertEquals(b,new SudokuGame().getPossibles(new int[]{1,3,0,4}));
		List<Integer> c =new ArrayList<Integer>();
		c.add(3);
		assertEquals(c,new SudokuGame().getPossibles(new int[]{0,1,4,2}));
		List<Integer> d =new ArrayList<Integer>();
		d.add(4);
		assertEquals(d,new SudokuGame().getPossibles(new int[]{1,0,2,3}));
	}
	@Test
	public void getPossibles() {
		List<Integer> a =new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		List<Integer> b =new ArrayList<Integer>();
		b.add(2);
		b.add(3);
		List<Integer> c =new ArrayList<Integer>();
		c.add(3);
		c.add(4);
		List<Integer> d =new ArrayList<Integer>();
		d.add(1);
		d.add(4);
		assertEquals(a,new SudokuGame().getPossibles(new int[]{0,4,3,0}));
		assertEquals(b,new SudokuGame().getPossibles(new int[]{1,0,0,4}));
		assertEquals(c,new SudokuGame().getPossibles(new int[]{0,1,0,2}));
		assertEquals(d,new SudokuGame().getPossibles(new int[]{0,0,2,3}));
	}
	@Test
	public void getQuadrant() throws Exception{
		assertEquals(0,new SudokuGame().getQuadrant(1, 1));
		assertEquals(1,new SudokuGame().getQuadrant(1, 3));
		assertEquals(2,new SudokuGame().getQuadrant(3, 1));
		assertEquals(3,new SudokuGame().getQuadrant(3, 3));
	}
}

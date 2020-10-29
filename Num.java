package median;

import java.util.Scanner;

public class Num implements Comparable {
	private int x;
	private double w;
   
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getW() {
		return w;
	}
	public void setW(double w) {
		this.w = w;
	}
	@Override
	public int compareTo(Object o) {
		int y=((Num)o).x;
		return(x<y? -1 :(x==y ? 0:1));
	}
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		Num[] num=new Num[n];
		for(int i=0;i<n;i++) {
			num[i]=new Num();
			num[i].setX(in.nextInt());
			num[i].setW(in.nextDouble());
		}
		for(Num a:num) {
			System.out.println(a.getX()+" "+a.getW());
		}
		in.close();
	}
}

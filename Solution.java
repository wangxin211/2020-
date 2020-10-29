package median;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public static void swap(Num[] a,int i,int j) {
		//交换数组a中i,j位置的元素
		Num temp=new Num();
		temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	public static int partition(Num[] a,int p,int r,Num x) {
		//以x为基准划分a[p:r]
		int l=0;
		for(int k=p;k<r+1;k++) {
			if(a[k].equals(x)) {
				l=k;
				break;
			}
		}
		swap(a,p,l);
		int i=p;
		int j=r+1;
		while(true) {
			while(a[++i].compareTo(a[p])<0&&i<r);
			while(a[--j].compareTo(a[p])>0);
			if(i>=j) break;
			swap(a,i,j);
		}
		a[p]=a[j];
		a[j]=x;
		return j;
	}
	public static Num select(Num[] a,int p,int r,int k) {
		//线性时间选择函数
    	if(r-p<5) {
    		Arrays.sort(a,p,r);
    		return(a[p+k-1]);
    	}
    	for(int i=0;i<=(r-p-4)/5;i++) {
    		int s=p+5*i;
    		int t=s+4;
    		Arrays.sort(a,s,t);
    		swap(a,p+i,s+2);
    	}
    	Num x=select(a,p,p+(r-p-4)/5,(r-p+6)/10);
    	int i=partition(a,p,r,x);
    	int j=i-p+1;
    	if(k<=j) return select(a,p,i,k);
    	else return select(a,i+1,r,k-j);        
    }
	public static  int Median(Num[]a,int p,int r) {
		//查找中位数的函数
		if(r-p==0) return a[p].getX();
		Num x=select(a,p,r,(-p+r+2)/2);
		int i=partition(a,p,r,x);
		double leftnum=0,rightnum=0;
		for(int m=p;m<i;m++) {
			leftnum+=a[m].getW();
		}
		for(int n=i+1;n<=r;n++) {
			rightnum+=a[n].getW();
			}
		if(leftnum<=0.5&&rightnum<=0.5) {
			return a[i].getX();		
		}
		else if(leftnum>0.5) {
			a[i].setW(a[i].getW()+rightnum);
			return Median(a,p,i);
		}
		else {
			a[i].setW(a[i].getW()+leftnum);
			return Median(a,i,r);
		}
	}

	public static void main(String[] args) throws IOException {
		List< String> input=new ArrayList<String>();
		BufferedReader in=new BufferedReader(new FileReader("src/median/exp1_in.txt"));
		String b;
		while((b=in.readLine())!= null) {
			input.add(b);
		}
		for(int i=0;i<input.size();i+=3) {
		   String sp1[]=input.get(i).split(" ");
		   String sp2[]=input.get(i+1).split(" ");
		   String sp3[]=input.get(i+2).split(" ");
		   int n=Integer.parseInt(sp1[0]);
		   Num num[]=new Num[n];
		   for(int j=0;j<n;j++) {
			   num[j]=new Num();
		       num[j].setX(Integer.parseInt(sp2[j]));
		       num[j].setW(Double.parseDouble(sp3[j]));
		   }
		   System.out.println(Median(num,0,n-1));
		}
//		Scanner in=new Scanner(System.in);
//		int n=in.nextInt();
//		Num[] num=new Num[n];
//		for(int i=0;i<n;i++) {
//			num[i]=new Num();
//			num[i].setX(in.nextInt());
//			num[i].setW(in.nextDouble());
//		}
//		in.close();
//		System.out.println(Median(num,0,n-1));	
	}

}

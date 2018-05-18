package util.ops;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public final class ArrayOps {
	
	private ArrayOps() {}
	
	public static double[] elementMultiply(double[] a, double[] b) {
		double[] c = new double[a.length];
		for(int i = 0; i < a.length; i++) c[i] = a[i] * b[i];
		return c;
	}
	
	public static double[] elementDivide(double[] a, double[] b) {
		double[] c = new double[a.length];
		for(int i = 0; i < a.length; i++) c[i] = a[i] / b[i];
		return c;
	}
	
	public static double[] elementAdd(double[] a, double[] b) {
		double[] c = new double[a.length];
		for(int i = 0; i < a.length; i++) c[i] = a[i] + b[i];
		return c;
	}
	
	public static double[] elementSubtract(double[] a, double[] b) {
		double[] c = new double[a.length];
		for(int i = 0; i < a.length; i++) c[i] = a[i] - b[i];
		return c;
	}
	
	public static double[] unaryOp(double[] a, UnaryOperator<Double> o) {
		double[] b = new double[a.length];
		for(int i = 0; i < a.length; i++) b[i] = o.apply(a[i]);
		return b;
	}
	
	public static double[] binaryOp(double[] a, double[] b, BinaryOperator<Double> o) {
		double[] c = new double[a.length];
		for(int i = 0; i < a.length; i++) c[i] = o.apply(a[i], b[i]);
		return c;
	}
	
	public static double[] copy(double[] a) {
		double[] b = new double[a.length];
		for(int i = 0; i < a.length; i++)
			b[i] = a[i];
		return b;			
	}
	
	public static double[][] append(double[][] a, double[] value) {
		double[][] b = new double[a.length + 1][];
		for(int i = 0; i < a.length; i++)
			b[i] = a[i];
		b[a.length] = value;
		return b;
	}
	
	public static double[] append(double[] a, double value) {
		double[] b = new double[a.length + 1];
		for(int i = 0; i < a.length; i++)
			b[i] = a[i];
		b[a.length] = value;
		return b;
	}
		
	public static double sum(double[] a) {
		double t = 0;
		for(double d : a) t += d;
		return t;
	}
	
	public static String toString(double[] a) {
		if(a.length == 0) return "[]";
		String s = "[";
		for(int i = 0; i < a.length - 1; i++) s += String.valueOf(a[i]) + ", ";
		s += String.valueOf(a[a.length - 1]) + "]";
		return s;
	}
	
}

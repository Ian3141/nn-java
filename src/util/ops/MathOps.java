package util.ops;

public final class MathOps {
	public static double round(double d, int fp) {
		double a = Math.pow(10, fp);
		return (int)(d * a) / (double) a;
	}
}

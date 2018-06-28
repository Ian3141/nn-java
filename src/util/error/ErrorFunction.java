package util.error;

public abstract class ErrorFunction {	
	public abstract double derivative(double observed, double target);
	
	public abstract double getError(double[] y, double[] t);
}

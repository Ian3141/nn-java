package util.error;

import java.util.function.BinaryOperator;

public abstract class ErrorFunction {
	public BinaryOperator<Double> derivativeFunction;
	
	public abstract double getError(double[] y, double[] t);
}

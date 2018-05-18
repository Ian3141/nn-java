package util.error;

import java.util.function.BinaryOperator;
import util.ops.ArrayOps;

public class MeanSquaredErrorFunction extends ErrorFunction {

	public MeanSquaredErrorFunction() {
		derivativeFunction = new BinaryOperator<Double>() {
			@Override
			/**
			 * @param t The observed output
			 * @param u The target output
			 */
			public Double apply(Double t, Double u) {
				return (t-u);
			}
		};
	}

	@Override
	public double getError(double[] y, double[] t) {
		return .5 * ArrayOps.sum(ArrayOps.unaryOp(ArrayOps.elementSubtract(y, t), (d) -> {return d*d;}));
	}

}

package util.error;

import util.ops.ArrayOps;

public class MeanSquaredErrorFunction extends ErrorFunction {
	
	@Override
	public double derivative(double observed, double target) {
		return observed - target;
	}

	@Override
	public double getError(double[] y, double[] t) {
		return .5 * ArrayOps.sum(ArrayOps.unaryOp(ArrayOps.elementSubtract(y, t), (d) -> {return d*d;}));
	}

}

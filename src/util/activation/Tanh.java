package util.activation;

public class Tanh implements Activation {

	public double activate(double d) {
		return Math.tanh(d);
	}

	public double derivative(double output) {
		return 1-(output * output);
	}

}

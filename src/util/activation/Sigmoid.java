package util.activation;

public class Sigmoid implements Activation {

	public double activate(double d) {
		return 1/(1+Math.pow(Math.E, -d));
	}
	
	public double derivative(double output) {
		return output * (1-output);
	}

}

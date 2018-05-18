package core.layer;

import util.activation.*;

public class StandardFeedForwardLayer extends FeedForwardNetworkLayer {
		
	public Activation activation;
		
	public StandardFeedForwardLayer(int sizeIn, int sizeOut, Activation activation) {
		this.activation = activation;
		weights = new double[sizeOut][sizeIn + 1];
		gradients = new double[sizeOut][sizeIn + 1];
		output = new double[sizeOut];
		this.sizeIn = sizeIn;
		this.sizeOut = sizeOut;
		randomizeWeights(1.5);
	}
	
	public void randomizeWeights(double magnitude) {
		for(int i = 0; i < weights.length; i++)
			for(int j = 0; j < weights[i].length; j++) 
				weights[i][j] = Math.random() * (magnitude * 2) - magnitude;
	}
	
	@Override
	public double[] propagate(double[] input) {
		double total;
		for(int i = 0; i < sizeOut; i++) {
			total = 0;
			for(int j = 0; j < sizeIn; j++) 
					total += input[j] * weights[i][j];
			total += weights[i][sizeIn]; //bias
			output[i] = activation.activate(total);
		}
		return output;
	}

	@Override
	public double[] backpropagate(double[] error, double[] input) {
		
		double[] prevError = new double[sizeIn];
		double net;
		
		for(int i = 0; i < sizeOut; i++) {
			net = error[i] * activation.derivative(output[i]);
			
			for(int j = 0; j < sizeIn; j++) {
				gradients[i][j] += net * input[j];
				prevError[j] += net * weights[i][j];
			}
			gradients[i][sizeIn] += net;
		}
		
		return prevError;
	}

}

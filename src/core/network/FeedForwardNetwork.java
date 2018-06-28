package core.network;

import core.layer.*;
import util.activation.*;
import util.error.*;
import util.ops.*;

public class FeedForwardNetwork {

	public FeedForwardNetworkLayer[] layers;

	ErrorFunction errorFunction = new MeanSquaredErrorFunction();

	public static final double DEFAULT_LEARNING_RATE = 0.5;

	public double learningRate = DEFAULT_LEARNING_RATE;

	/**
	 * Creates a {@code FeedForwardNetwork} given layer sizes
	 * @param layers the sizes of the layers, in order
	 */
	public FeedForwardNetwork(FeedForwardNetworkLayer... layers) {
		this.layers = layers;
	}

	public void setLearningRate(double lr) {
		learningRate = lr;
	}

	public double[] propagate(double[] input) {
		double[] data = ArrayOps.copy(input);
		for(FeedForwardNetworkLayer l : layers) {
			data = l.propagate(data);
		}
		return data;
	}

	public double backpropagate(double[][] inputs, double[][] tgts) {
		double totalError = 0;
		clearGradients();
		
		for(int i = 0; i < inputs.length; i++) {

			double[] input = ArrayOps.copy(inputs[i]);
			double[] output = ArrayOps.copy(propagate(input));

			totalError += errorFunction.getError(output, tgts[i]);

			double[] errorDerivatives = ArrayOps.binaryOp(output, tgts[i], errorFunction::derivative);

			for(int l = layers.length - 1; l > 0; l--)
				errorDerivatives = layers[l].backpropagate(errorDerivatives, layers[l-1].output);	

			layers[0].backpropagate(errorDerivatives, input);	
		}
		
		updateWeights();
		
		return totalError;
	}

	public void updateWeights() {
		for(FeedForwardNetworkLayer l : layers)
			l.updateWeights(learningRate);
	}

	public void updateWeights(double clip) {
		for(FeedForwardNetworkLayer l : layers)
			l.updateWeights(learningRate, clip);
	}

	public void clearGradients() {
		for(FeedForwardNetworkLayer l : layers)
			l.clearGradients();
	}

	public double trainStep(double[][] inputs, double[][] targets, boolean display) {
		double error = backpropagate(inputs, targets);
		if(display) {
			for(double[] i : inputs)
				System.out.println(ArrayOps.toString(i) + ": " + ArrayOps.toString(propagate(i)));
			System.out.println("Error: " + MathOps.round(error, 8));
			System.out.println();
		}
		return error;
	}

	/**
	 * Creates a standard {@code FeedForwardNetwork} given an activation function and layer sizes
	 * @param activation the activation function of the network
	 * @param layerSizes the sizes of the layers
	 * @return the network
	 */
	public static FeedForwardNetwork createStandardNetwork(Activation activation, int... layerSizes) {
		StandardFeedForwardLayer[] layers = new StandardFeedForwardLayer[layerSizes.length-1];
		for(int i = 0; i < layerSizes.length-1; i++) layers[i] = new StandardFeedForwardLayer(layerSizes[i], layerSizes[i+1], activation);
		
		return new FeedForwardNetwork(layers);
	}

}

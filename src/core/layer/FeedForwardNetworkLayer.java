package core.layer;

public abstract class FeedForwardNetworkLayer {
	
	public int sizeIn, sizeOut;
	
	public double[][] weights;
	public double[][] gradients;
	
	public double[] output;
	
	public abstract double[] propagate(double[] input);
	
	public abstract double[] backpropagate(double[] error, double[] input);
	
	public void updateWeights(double learningRate) {
		updateWeights(learningRate, 10000.0);
	}
	
	public void updateWeights(double learningRate, double clip) {
		for(int i = 0; i < weights.length; i++)
			for(int j = 0; j < weights[i].length; j++)
				if(gradients[i][j] * learningRate < -clip)
					weights[i][j] += clip;
				else if(gradients[i][j] * learningRate > clip)
					weights[i][j] -= clip;
				else
					weights[i][j] -= gradients[i][j] * learningRate;
	}
	
	public void clearGradients() {
		for(int i = 0; i < gradients.length; i++)
			for(int j = 0; j < gradients[i].length; j++)
				gradients[i][j] = 0;
	}
	
}

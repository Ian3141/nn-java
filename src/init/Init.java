package init;

import core.network.FeedForwardNetwork;
import io.DataReader;
import util.activation.*;

public class Init {

	public static void main(String[] args) {
		
		//TODO: Should be training a lot faster -- check gradient calculation? maybe weight update? or error function?

		//Create network
		FeedForwardNetwork net = FeedForwardNetwork.createStandardNetwork(new Tanh(), 2, 4, 1);
		net.setLearningRate(0.4);

		//Fetch training data
		double[][] inputs = DataReader.safeGetDataFromFile("input.txt");
		double[][] targets = DataReader.safeGetDataFromFile("targets.txt");

		//Train network
		for(int n = 0; n < 1000; n++)
			net.trainStep(inputs, targets, true);
	}
}

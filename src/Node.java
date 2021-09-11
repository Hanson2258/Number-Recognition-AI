import java.util.ArrayList;
import java.lang.Math;

public class Node {

  // Variable declaration
  public double[] inputs = new double[Main.inputLayer.length];
  public double output = 0.00;
  public double finalOutput = 0.00;
  public double[] weights = new double[Main.inputLayer.length];


  public double secondLayerNode() {
    inputs = Main.inputLayer;
    
    for(int i=0;i<inputs.length; i++) {
      output = inputs[i] * weights[i];
      finalOutput += output;
    }

    finalOutput = sigmoid(finalOutput);
    
    return finalOutput;
  }

  // layerNum represents the layer the values are being obtained from,
  // it does not represent the layer the values are currently being set in
  public double otherLayersNode(int layerNum) {
    Layer layer = Main.layerList.get(layerNum);

    for(int i=0;i<Main.numNodesPerLayer; i++) {
      Node node = layer.nodeList.get(i);

      inputs[i] = node.finalOutput;
    }    
    
    for(int i=0;i<inputs.length; i++) {
      output = inputs[i] * weights[i];
      finalOutput += output;
    }

    finalOutput = sigmoid(finalOutput);
    
    return finalOutput;
  }

  private double sigmoid(double x) {
    return 1 / (1 + Math.exp(-x));
  }

  public void randomWeightsValue() {
    for(int i=0;i<inputs.length; i++){
      weights[i] = Math.random();
    }
  }
}
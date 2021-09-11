import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Main {

  // Variable declaration
  public static ArrayList<Layer> layerList = new ArrayList<Layer>();  
  public static double[] inputLayer;
  public static int numMiddleLayers = 5;
  public static int numNodesPerLayer = 8;
  public static int numAnswers = 10;

  public static void main(String[] args) {

    try {
      BufferedImage myPicture = ImageIO.read(new File("num6.png"));

      inputLayer = new double[myPicture.getWidth() * myPicture.getHeight()];

      int[] pixels = new int[myPicture.getWidth() * myPicture.getHeight()];

      for( int i = 0; i < myPicture.getWidth(); i++ )
          for( int j = 0; j < myPicture.getHeight(); j++ )
              pixels[(i+1)*j] = myPicture.getRGB( i, j );

      // for(int i=0;i<inputLayer.length; i++){
      //   inputLayer[i] = Math.random();
      // }
      createRandomNetwork();
      secondLayerOutput();

      for(int i=0;i<numMiddleLayers - 1; i++){
        layerOutput(i);
      }

      // Final layer
      layerOutput(numMiddleLayers - 1);

      System.out.println("Final Layer outputs:");

      Layer layer = layerList.get(numMiddleLayers);

      int biggestIndex = 0;
      double nodeFinalOutputValue = 0.00;

      for(int i=0;i<numAnswers; i++){
        Node node = layer.nodeList.get(i);

        if (node.finalOutput > nodeFinalOutputValue) {
          nodeFinalOutputValue = node.finalOutput;
          biggestIndex = i;
        }

        System.out.println(node.finalOutput);
      }

      System.out.println();
      System.out.println("The biggest index is: " + biggestIndex);

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    try {
      FileWriter fw = new FileWriter("TextFile.txt");
      PrintWriter pw = new PrintWriter(fw);

      pw.println("Hello World");
      String json = new Gson().toJson(layerList);
//      Gson gson = new Gson();
//
//      JsonElement element = gson.toJsonTree(layerList , new TypeToken<List<Layer>>() {}.getType());
//
//      JsonArray jsonArray = element.getAsJsonArray();
//      pw.println(jsonArray);
      pw.println(json);
      pw.close();
      System.out.println("the file should have been created.");
        
    } catch (IOException e) {
      System.out.println("error!");  
    }
  }


  //Methods below

  public static int outputNumber() {
    return 0;
  }

  public static void createRandomNetwork() {

    for(int i=0;i<numMiddleLayers; i++) {
      Layer layer = new Layer();
      layerList.add(layer);

      for(int j=0;j<numNodesPerLayer; j++) {
        Node node = new Node();

        node.randomWeightsValue();

        layer.nodeList.add(node);
      }
    }

    // Final layer
    Layer layer = new Layer();
    layerList.add(layer);

    for(int i=0;i<numAnswers; i++) {
      Node node = new Node();

      node.randomWeightsValue();

      layer.nodeList.add(node);
    }
  }

  public static void secondLayerOutput() {
    Layer secondLayer = layerList.get(0);
    double secondLayerOutput = 0.00;

    for(int i=0;i<numNodesPerLayer; i++) {
      Node node = secondLayer.nodeList.get(i);

      secondLayerOutput += node.secondLayerNode();
    }
  }

  public static void layerOutput(int layerNum) {
    Layer layer = layerList.get(layerNum + 1);
    double layerOutput = 0.00;

    for(int i=0;i<layer.nodeList.size(); i++) {
      Node node = layer.nodeList.get(i);

      layerOutput += node.otherLayersNode(layerNum);
    }
  }
}
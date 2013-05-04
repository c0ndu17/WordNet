import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

public class WordNet {

  private Digraph Wordnet;
  private String[][] SynSets;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    boolean RootedDAG = false;
    String[] sets = synsets.split("\n");
    SynSets= new String[sets.length][2];
    Wordnet = new Digraph(sets.length);
    for (int i =0; i< sets.length; i++){
      String[] Delimiter = sets[i].split(",");
      SynSets[i][0] = Delimiter[1];
      SynSets[i][1] = Delimiter[2];
    }
    sets = hypernyms.split("\n");
    for(int i = 0; i < sets.length; i++){
      String[] Delimiter = sets[i].split(",");
      int Above = Integer.parseInt(Delimiter[0]);
      if(Delimiter.length==1) {
        if(!RootedDAG) RootedDAG = true;
        else throw new IllegalArgumentException();
      }
      for(int j = 1; j< Delimiter.length; j++){
        Wordnet.addEdge(Above, Integer.parseInt(Delimiter[j]));
      }
    }
    //if(!RootedDAG) throw new IllegalArgumentException();
  }

  // returns all WordNet nouns
  public Iterable<String> nouns(){
    List<String> ReturnType = new ArrayList<String>();
    for(int i =0; i< SynSets.length; i++)
      ReturnType.add(SynSets[i][0]);
    return ReturnType;
  }

  public int size(){
    return SynSets.length;
  }

  // is the word a WordNet noun?
  public boolean isNoun (String word){
  for(int i = 0; i< SynSets.length; i++){
    if(SynSets[0][1].contains(" "+word+" ")) return true;
  }
  return false;
  }


  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB){



  return 0;
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB){
  return "a";
  }

  public void Synprint(){
    for(int i =0; i<SynSets.length; i++){
      System.out.println(i+" " + SynSets[0][i]+ "\t" + SynSets[1][i] );
    }
  }

  // do unit testing of this class
  public static void main(String[] args){
    In fileSynset = new In(args[0]);
    In fileHypernyms = new In(args[1]);
    String Hypernyms = fileHypernyms.readAll();
    String Synset = fileSynset.readAll();
    WordNet Net = new WordNet(Synset, Hypernyms);
  }

}

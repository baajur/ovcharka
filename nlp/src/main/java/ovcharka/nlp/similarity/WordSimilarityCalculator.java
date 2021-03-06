package ovcharka.nlp.similarity;

import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.Lin;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;

public class WordSimilarityCalculator implements SimilarityCalculator {
    private static ILexicalDatabase db = new NictWordNet();
    private static RelatednessCalculator calculator = new Lin(db);

    static {
        //use Most Frequent Sense -- 'true' increases speed
        WS4JConfiguration.getInstance().setMFS(false);
    }

    public boolean areSimilar(String first, String second) {
        return calcSimilarityScore(first, second) > 0.6;
    }


    public double calcSimilarityScore(String word1, String word2) {
        double score = calculator.calcRelatednessOfWords(word1, word2);
        return (score == Double.MAX_VALUE) ? 1 : score;
    }
}

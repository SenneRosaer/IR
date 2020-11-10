import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

    public class OwnAnalyzer extends Analyzer {
        @Override
        protected TokenStreamComponents createComponents(String fieldname){
            final Tokenizer source = new OwnTokenizer();
            TokenStream result = new StopFilter(source, EnglishAnalyzer.getDefaultStopSet());
            result = new RemoveSignAtEndTokenFilter(result);
            result = new RemoveSingleSymbols(result);
            result = new LowerCaseFilter(result);
            return new TokenStreamComponents(source, result);
        }
    }





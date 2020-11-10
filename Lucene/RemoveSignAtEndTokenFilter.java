
import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.util.ArrayList;
import java.util.Arrays;




public class RemoveSignAtEndTokenFilter extends TokenFilter {
    private final CharTermAttribute termAtt = (CharTermAttribute)this.addAttribute(CharTermAttribute.class);

    public RemoveSignAtEndTokenFilter(TokenStream in) {
        super(in);
    }

    public final boolean incrementToken() throws IOException {
        if (this.input.incrementToken()) {
            char[] tmp = this.termAtt.buffer();
            ArrayList<Character> toRemove = new ArrayList<Character>(Arrays.asList(':', '.', '!', '?', ',', ';'));
            for(int i=0; i < this.termAtt.length(); i++){
                if (toRemove.contains(tmp[i])){
                    if(i == termAtt.length()-1){
                        Character.toChars(0, this.termAtt.buffer(), this.termAtt.length()-1);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}


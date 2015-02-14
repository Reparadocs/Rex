import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class LexerDict
{
   private Map<Pattern, TokenType> tokenMap;

   public LexerDict()
   {
      tokenMap = new LinkedHashMap<Pattern, TokenType>();
      createDict();
   }

   private void createDict()
   {
      addPattern("[0-9]+", TokenType.INTEGER);
      addPattern(";", TokenType.END);

      addPattern("\\(", TokenType.L_PAREN);
      addPattern("\\)", TokenType.R_PAREN);
      addPattern("\\*", TokenType.MLT_OPERATOR);
      addPattern("/", TokenType.DIV_OPERATOR);
      addPattern("%", TokenType.MOD_OPERATOR);
      addPattern("\\+", TokenType.ADD_OPERATOR);
      addPattern("\\-", TokenType.SUB_OPERATOR);
   }

   private void addPattern(String regex, TokenType type)
   {
      tokenMap.put(Pattern.compile("^(" + regex + ")(.*)$"), type);
   }

   public TokenMatcher match(String token)
   {
      for(Pattern pattern : tokenMap.keySet())
      {
         Matcher match = pattern.matcher(token);
         if(match.find())
         {
            return new TokenMatcher(match, tokenMap.get(pattern));
         }
      }
      return null;
   }

}
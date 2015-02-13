import java.util.regex.Pattern;
import java.util.HashMap;

public class LexerDict
{
   private HashMap<Pattern, TokenType> tokenMap;

   public LexerDict()
   {
      tokenMap = new HashMap<Pattern, TokenType>();
      createDict();
   }

   private void createDict()
   {
      addToken("[0-9]+", TokenType.INTEGER);
      addToken(";", TokenType.END);

      addToken("\\(", TokenType.L_PAREN);
      addToken("\\)", TokenType.R_PAREN);
      addToken("\\*", TokenType.MLT_OPERATOR);
      addToken("/", TokenType.DIV_OPERATOR);
      addToken("%", TokenType.MOD_OPERATOR);
      addToken("\\+", TokenType.ADD_OPERATOR);
      addToken("\\-", TokenType.SUB_OPERATOR);
   }

   private void addToken(String regex, TokenType tokenType)
   {
      tokenMap.put(Pattern.compile("^" + regex + "$"), tokenType);
   }

   public TokenType match(String token)
   {
      for(Pattern pattern : tokenMap.keySet())
      {
         if(pattern.matcher(token).find())
         {
            return tokenMap.get(pattern);
         }
      }
      return TokenType.NONE;
   }

}
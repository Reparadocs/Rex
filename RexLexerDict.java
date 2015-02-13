import java.util.regex.Pattern;
import java.util.HashMap;

public class RexLexerDict
{
   private HashMap<Pattern, RexTokenType> tokenMap;

   public RexLexerDict()
   {
      tokenMap = new HashMap<Pattern, RexTokenType>();
      createDict();
   }

   private void createDict()
   {
      addToken("[0-9]+", RexTokenType.INTEGER);
      addToken(";", RexTokenType.END);

      addToken("\\(", RexTokenType.L_PAREN);
      addToken("\\)", RexTokenType.R_PAREN);
      addToken("\\*", RexTokenType.MLT_OPERATOR);
      addToken("/", RexTokenType.DIV_OPERATOR);
      addToken("%", RexTokenType.MOD_OPERATOR);
      addToken("\\+", RexTokenType.ADD_OPERATOR);
      addToken("\\-", RexTokenType.SUB_OPERATOR);
   }

   private void addToken(String regex, RexTokenType tokenType)
   {
      tokenMap.put(Pattern.compile("^" + regex + "$"), tokenType);
   }

   public RexTokenType match(String token)
   {
      for(Pattern pattern : tokenMap.keySet())
      {
         if(pattern.matcher(token).find())
         {
            return tokenMap.get(pattern);
         }
      }
      return RexTokenType.NONE;
   }

}
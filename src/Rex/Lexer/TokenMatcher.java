import java.util.regex.Matcher;

public class TokenMatcher
{
   private Matcher matcher;
   private TokenType type;

   public TokenMatcher(Matcher matcher, TokenType type)
   {
      this.matcher = matcher;
      this.type = type;
   }

   public Matcher getMatcher()
   {
      return matcher;
   }

   public TokenType getType()
   {
      return type;
   }
}

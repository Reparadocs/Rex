public class Token
{
   private String data;
   private TokenType type;

   public Token(String data, TokenType type)
   {
      this.data = data;
      this.type = type;
   }

   public String getData()
   {
      return data;
   }

   public TokenType getType()
   {
      return type;
   }
}
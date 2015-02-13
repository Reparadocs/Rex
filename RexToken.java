public class RexToken
{
   private String data;
   private RexTokenType type;

   public RexToken(String data, RexTokenType type)
   {
      this.data = data;
      this.type = type;
   }

   public String getData()
   {
      return data;
   }

   public RexTokenType getType()
   {
      return type;
   }
}
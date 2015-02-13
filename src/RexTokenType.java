public enum RexTokenType
{
   INTEGER,
   END,
   NONE,
   L_PAREN(0),
   R_PAREN(0),
   MLT_OPERATOR(1),
   DIV_OPERATOR(1),
   MOD_OPERATOR(1),
   ADD_OPERATOR(2),
   SUB_OPERATOR(2)
   ;

   private Integer precedence;

   RexTokenType()
   {
      this.precedence = -1;
   }

   RexTokenType(int precedence)
   {
      this.precedence = precedence;
   }

   public boolean isLessThan(RexTokenType other)
   {
      return this.precedence < other.precedence;
   }
}
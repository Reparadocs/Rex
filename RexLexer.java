import java.util.Scanner;
import java.util.ArrayList;

public class RexLexer
{
   Scanner scanner;
   RexParser parser;
   RexLexerDict tokenDict;

   public RexLexer(Scanner scanner, RexParser parser)
   {
      this.scanner = scanner;
      this.parser = parser;
      this.tokenDict = new RexLexerDict();
   }

   public void Start()
   {
      ArrayList<RexToken> curPhrase = new ArrayList<RexToken>();

      while(scanner.hasNext())
      {
         String token = scanner.next();
         RexTokenType tokenType = tokenDict.match(token);
         if(tokenType == RexTokenType.NONE)
         {
            System.out.println("Syntax Error: " + token);
            return;
         }

         if(tokenType == RexTokenType.END)
         {
            parser.parse(curPhrase);
            curPhrase = new ArrayList<RexToken>();
         }
         else
         {
            curPhrase.add(new RexToken(token, tokenType));
         }
      }
   }
}
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Lexer
{
   private Scanner scanner;
   private Parser parser;
   private LexerDict tokenDict;

   public Lexer(Scanner scanner, Parser parser)
   {
      this.scanner = scanner;
      this.parser = parser;
      this.tokenDict = new LexerDict();
   }

   public void Start()
   {
      ArrayList<Token> curPhrase = new ArrayList<Token>();

      while(scanner.hasNext())
      {
         String tokenData = scanner.next();

         while(tokenData.length() != 0)
         {
            TokenMatcher tokenMatcher = tokenDict.match(tokenData);
            if(tokenMatcher == null)
            {
               ErrorHandler.handle("Syntax Error:" + tokenData);
            }

            tokenData = tokenMatcher.getMatcher().group(2);
            if(tokenMatcher.getType() == TokenType.END)
            {
               parser.parse(curPhrase);
               curPhrase = new ArrayList<Token>();
            }
            else
            {
               curPhrase.add(new Token(tokenMatcher.getMatcher().group(1), 
                  tokenMatcher.getType()));
            }
         }
      }
   }
}
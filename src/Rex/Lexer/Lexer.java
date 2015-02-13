import java.util.Scanner;
import java.util.ArrayList;

public class Lexer
{
   Scanner scanner;
   Parser parser;
   LexerDict tokenDict;

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
         TokenType tokenType = tokenDict.match(tokenData);
         if(tokenType == TokenType.NONE)
         {
            System.out.println("Syntax Error: " + tokenData);
            return;
         }

         if(tokenType == TokenType.END)
         {
            parser.parse(curPhrase);
            curPhrase = new ArrayList<Token>();
         }
         else
         {
            curPhrase.add(new Token(tokenData, tokenType));
         }
      }
   }
}
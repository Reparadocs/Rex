import java.util.ArrayList;

public class Parser
{
   public Parser()
   {

   }

   public void parse(ArrayList<Token> phrase)
   {
      SyntaxTree AST = new SyntaxTree(phrase);
      System.out.println(AST.execute().toString());
   }
}
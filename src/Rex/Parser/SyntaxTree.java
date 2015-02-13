import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class SyntaxTree
{
   ASTNode root;

   public SyntaxTree(List<Token> tokens)
   {
      this.root = buildTree(tokens);
   }

   private ASTNode buildTree(List<Token> tokens)
   {
      LinkedList<TokenType> operatorStack = new LinkedList<TokenType>();
      LinkedList<ASTNode> expressionStack = new LinkedList<ASTNode>();
      for(Token t : tokens)
      {
         switch(t.getType())
         {
            case INTEGER:
               expressionStack.push(new NumberNode(
                  Integer.parseInt(t.getData())));
               break;
            case L_PAREN:
               operatorStack.push(TokenType.L_PAREN);
               break;
            case R_PAREN:
               while(operatorStack.peek() != TokenType.L_PAREN)
               {
                  TokenType op = operatorStack.pop();
                  expressionStack.push(new BinaryOpNode(
                     Arrays.asList(expressionStack.pop(),
                     expressionStack.pop()),op));
               }

               if(operatorStack.isEmpty())
               {
                  throw new IllegalArgumentException("No closing parentheses");
               }

               operatorStack.pop();
               break;
            case MLT_OPERATOR:
            case DIV_OPERATOR:
            case MOD_OPERATOR:
            case ADD_OPERATOR:
            case SUB_OPERATOR:
               while(!operatorStack.isEmpty() &&
                  t.getType().isLessThan(operatorStack.peek()))
                  {
                     TokenType op = operatorStack.pop();
                     expressionStack.push(new BinaryOpNode(
                     Arrays.asList(expressionStack.pop(),
                     expressionStack.pop()),op));
                  }
               operatorStack.push(t.getType());
               break;
         }
      }

      /* Something's going to go horribly wrong here */
      while(!operatorStack.isEmpty())
      {
         TokenType op = operatorStack.pop();
         expressionStack.push(new BinaryOpNode(
            Arrays.asList(expressionStack.pop(),
            expressionStack.pop()),op));
      }

      if(expressionStack.size() != 1)
      {
         while(!expressionStack.isEmpty())
            System.out.println(expressionStack.pop().getClass());
         throw new RuntimeException("Something went wrong when building the tree.");
      }

      return expressionStack.pop();
   }

   public Object execute()
   {
      return root.handle();
   }
}
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class SyntaxTree
{
   ASTNode root;
   LinkedList<TokenType> operatorStack;
   LinkedList<ASTNode> expressionStack;

   public SyntaxTree(List<Token> tokens)
   {
      operatorStack = new LinkedList<TokenType>();
      expressionStack = new LinkedList<ASTNode>();
      this.root = buildTree(tokens);
   }

   private ASTNode buildTree(List<Token> tokens)
   {
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
               while(!operatorStack.isEmpty() && operatorStack.peek() != TokenType.L_PAREN)
               {
                  createBinOpNode();
               }

               if(operatorStack.isEmpty())
               {
                  ErrorHandler.handle("No opening parentheses");
               }

               operatorStack.pop();
               break;
            case MLT_OPERATOR:
            case DIV_OPERATOR:
            case MOD_OPERATOR:
            case ADD_OPERATOR:
            case SUB_OPERATOR:
               while(!operatorStack.isEmpty() && t.getType().isLessThan(operatorStack.peek()))
                  {
                     createBinOpNode();
                  }
               operatorStack.push(t.getType());
               break;
         }
      }

      while(!operatorStack.isEmpty())
      {
         if(operatorStack.peek() == TokenType.L_PAREN)
         {
            ErrorHandler.handle("No closing parentheses");
         }
         createBinOpNode();
      }

      if(expressionStack.size() != 1)
      {
         ErrorHandler.handle("Couldn't build the tree properly, more than one root");
      }

      return expressionStack.pop();
   }

   public Object execute()
   {
      return root.handle();
   }

   private void createBinOpNode()
   {
      if(expressionStack.size() < 2)
      {
         ErrorHandler.handle("Tried to create BinOpNode, less than 2 operands");
      }
      if(operatorStack.isEmpty())
      {
         ErrorHandler.handle("Tried to create BinOpNode, no operator");
      }
      TokenType op = operatorStack.pop();
      expressionStack.push(new BinaryOpNode(expressionStack, op));
   }
}
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryOpNode extends ASTNode
{
   private static final int NUM_CHILDREN = 2;

   private TokenType operator;

   public BinaryOpNode(LinkedList<ASTNode> expressionStack, TokenType operator)
   {
      super(expressionStack, NUM_CHILDREN);
      this.operator = operator;
   }

   public Object handle()
   {
      //Temporary till we get more types
      List<Integer> results = 
         BinaryOpNode.<Integer>handleChildrenInt(this.children);
      
      switch(operator)
      {

         case MLT_OPERATOR:
            return results.get(0) * results.get(1);
         case DIV_OPERATOR:
            return results.get(0) / results.get(1);
         case MOD_OPERATOR:
            return results.get(0) % results.get(1);
         case ADD_OPERATOR:
            return results.get(0) + results.get(1);
         case SUB_OPERATOR:
            return results.get(0) - results.get(1);
         default:
            ErrorHandler.handle("Not a binary operator");
            return null;
      }
   }

   private static <T> List<T> handleGenericChildren(List<ASTNode> children)
   {
      ArrayList<T> results = new ArrayList<T>();
      for(int i = children.size()-1; i >= 0; i--)
      {
         results.add((T)(children.get(i).handle()));
      }
      return results;
   }

}
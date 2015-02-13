import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class BinaryOpNode extends ASTNode
{
   private static final int NUM_CHILDREN = 2;

   private TokenType operator;

   public BinaryOpNode(List<ASTNode> children, TokenType operator)
   {      
      super(children, NUM_CHILDREN);
      this.operator = operator;
   }

   /* This is a bad way of translating Objects to Ints */
   public Object handle()
   {
      List<Object> objResults = handleChildren();
      List<Integer> results = new ArrayList<Integer>();
      for(Object o : objResults)
      {
         results.add((Integer)o);
      }
      
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
            throw new NoSuchElementException();
      }
   }
}
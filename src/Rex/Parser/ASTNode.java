import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ASTNode
{
   protected List<ASTNode> children;

   public final int numChildren;

   protected ASTNode(LinkedList<ASTNode> expressionStack, int numChildren)
   {
      children = new ArrayList<ASTNode>();
      for(int i = 0; i < numChildren; i++)
      {
         children.add(expressionStack.pop());
      }
      this.numChildren = numChildren;
   }

   protected List<Object> handleChildren()
   {
      ArrayList<Object> results = new ArrayList<Object>();
      for(int i = children.size()-1; i >= 0; i--)
      {
         results.add(children.get(i).handle());
      }
      return results;
   }

   public abstract Object handle();

}
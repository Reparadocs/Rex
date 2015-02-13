import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode
{
   protected List<ASTNode> children;

   public final int numChildren;

   protected ASTNode(List<ASTNode> children, int numChildren)
   {
      if(numChildren != 0 && children.size() != numChildren)
      {
         throw new IllegalArgumentException("Wrong number of children");
      }
      this.numChildren = numChildren;
      this.children = children;
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
public class NumberNode extends ASTNode
{
   private static final int NUM_CHILDREN = 0;

   private int data;

   public NumberNode(int data)
   {
      super(null, NUM_CHILDREN);
      this.data = data;
   }

   public Object handle()
   {
      return data;
   }
}
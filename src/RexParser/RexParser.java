import java.util.ArrayList;

public class RexParser
{
   public RexParser()
   {

   }

   public void parse(ArrayList<RexToken> phrase)
   {
      for(RexToken t : phrase)
      {
         System.out.println(t.getData() + " ||| " + t.getType().name());
      }
   }
}
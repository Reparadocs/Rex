public class ErrorHandler
{
   public static void handle(String error)
   {
      throw new RuntimeException(error);
   }
}
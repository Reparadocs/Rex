import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Rex
{
   public static void main(String[] args)
   {
      Scanner scanner;
      RexLexer lexer;
      RexParser parser;

      if(args.length > 1)
      {
         System.out.println("Too many arguments, Exiting...");
         return;
      }
      else if (args.length == 1)
      {
         File file;

         try
         {
            file = new File(args[0]);
            scanner = new Scanner(file);
         }
         catch (FileNotFoundException e)
         {
            System.out.println("Filename not valid, Exiting...");
            return;
         }
      }
      else
      {
         scanner = new Scanner(System.in);
         System.out.println("Rex Initialized");
      }

      parser = new RexParser();
      lexer = new RexLexer(scanner, parser);
      lexer.Start();
   }
}
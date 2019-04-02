package p8_package;

public class RBT_MainClass
   {

      public static void main(String[] args)
         {
            RB_TreeClass copiedClass, testClass = new RB_TreeClass();
            //char removedItem, foundItem;

            // balanced cases
            testClass.insert( 'M' );
            testClass.insert( 'G' );
            testClass.insert( 'R' );
            testClass.insert( 'B' );         
            testClass.insert( 'J' );
            testClass.insert( 'P' );
            testClass.insert( 'U' );
            testClass.insert( 'A' );
            testClass.insert( 'C' );
            testClass.insert( 'H' );
            testClass.insert( 'L' );
            testClass.insert( 'N' );
            testClass.insert( 'Q' );
            testClass.insert( 'S' );
            testClass.insert( 'X' );
           
            // Right right cases
            testClass.insert( 'A' );
            testClass.insert( 'B' );
            testClass.insert( 'C' );
            testClass.insert( 'D' );         
            testClass.insert( 'E' );
            testClass.insert( 'F' );
            testClass.insert( 'G' );
            testClass.insert( 'H' );
            testClass.insert( 'I' );
            testClass.insert( 'J' );
            testClass.insert( 'K' );
          
            // Left left cases
            testClass.insert( 'Z' );
            testClass.insert( 'Y' );
            testClass.insert( 'X' );
            testClass.insert( 'W' );         
            testClass.insert( 'V' );
            testClass.insert( 'U' );
            testClass.insert( 'T' );
            testClass.insert( 'S' );
            testClass.insert( 'R' );
            testClass.insert( 'Q' );
            testClass.insert( 'P' );

            System.out.println( "\nTree height: " + testClass.findTreeHeight() );
  
            System.out.println( "\nTree Data Display:" );
                        
            testClass.displayTreeStructure();
            
            testClass.setTreeDisplayCharacter( RB_TreeClass.NODE_COLOR );
            
            System.out.println( "\nTree Red/Black Color Display:" );
            
            testClass.displayTreeStructure();
            
            System.out.println( "\nInorder List Data Display:" );
            
            testClass.displayTree( RB_TreeClass.INORDER_TRAVERSE );
            
            System.out.println( "\nImplementing Copy Constructor" );
            copiedClass = new RB_TreeClass( testClass );
            
            copiedClass.displayTree( RB_TreeClass.INORDER_TRAVERSE );

            System.out.println( "\nPreorder List Data Display:" );
            
            copiedClass.displayTree( RB_TreeClass.PREORDER_TRAVERSE );
            
            System.out.println( "\nPostorder List Data Display:" );
            
            copiedClass.displayTree( RB_TreeClass.POSTORDER_TRAVERSE );
            
            System.out.println( "\nCopied List Data Display:" );
            
            copiedClass.setTreeDisplayCharacter( RB_TreeClass.NODE_DATA );

            copiedClass.displayTreeStructure();
            
            System.out.println( "\nCopied Tree Red/Black Color Display:" );
            
            copiedClass.setTreeDisplayCharacter( RB_TreeClass.NODE_COLOR );
            
            copiedClass.displayTreeStructure();

         }

   }

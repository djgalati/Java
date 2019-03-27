package p7_package;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GenericBSTClassTest
{
 Generic_BST_Class<StateDataClass> testClass 
                                    = new Generic_BST_Class<StateDataClass>();
 
 StateDataClass[] SDC_Array = { new StateDataClass( "Nevada", 3034392 ),
                                new StateDataClass( "Missouri", 6126452 ),
                                new StateDataClass( "Utah", 3161105 ),
                                new StateDataClass( "Hawaii", 1420491 ),
                                new StateDataClass( "South Carolina", 5084127 ),
                                new StateDataClass( "North Carolina", 10383620 ),
                                new StateDataClass( "Maine", 1338404 ),
                                new StateDataClass( "Texas", 28701845 ),
                                new StateDataClass( "Kentucky", 4468402 ),
                                new StateDataClass( "Colorado", 5695564 ),
                                new StateDataClass( "Ohio", 11689442 ) };
 
 StateDataClass removedItem, foundItem;
 int index, numElements = 11;
 
   @Test
   public void insertTest()
      {
       System.out.println( "\nBeginning Tests ------------------" );
       
       try
          {
           for( index = 0; index < numElements; index++ )
              {
               testClass.insert( SDC_Array[ index ] );
              }
           
           testClass.clearTree();
          }
       
       catch( NullPointerException NPE )
          {
           System.out.println( "***** Insertion Failure *****");
          }
      }

   @Test
   public void removeTest()
      {
       for( index = 0; index < numElements; index++ )
          {
           testClass.insert( SDC_Array[ index ] );
          }

       try
          {
           // remove node with ONLY RIGHT NODE
           removedItem = removeState( testClass, "" ); // fill in state name
           System.out.println( removedItem.toString() );
           assertEquals( removedItem.toString(), "" ); // fill in string result

           // remove node with ONLY LEFT NODE
           removedItem = removeState( testClass, "" ); // fill in state name
           System.out.println( removedItem.toString() );
           assertEquals( removedItem.toString(), "" ); // fill in string result

           // remove node with TWO CHILDREN
           removedItem = removeState( testClass, "" ); // fill in state name          
           System.out.println( removedItem.toString() );
           assertEquals( removedItem.toString(), "" ); // fill in string result
           
           // remove node not available
           System.out.println( removedItem.toString() );
           removedItem = removeState( testClass, "" ); // fill in state name           
           assertEquals( removedItem, null );
           
           testClass.clearTree();
          }
       
       catch( AssertionError AE )
          {
           System.out.println( "***** Removal Failure *****");
          }
      }

   @Test
   public void SearchTest()
      {
      for( index = 0; index < numElements; index++ )
         {
          testClass.insert( SDC_Array[ index ] );
         }

       try
          {
           // search for any state
           foundItem = searchForState( testClass, "Colorado" ); 
           System.out.println( foundItem.toString() );
           assertEquals( foundItem.toString(), 
                                "State Name: Colorado, Population: 5695564" );
           
           // remove same state
           removedItem = removeState( testClass, "Colorado" );
           System.out.println( foundItem.toString() );
           assertEquals( removedItem.toString(), 
                                "State Name: Colorado, Population: 5695564" );

           // retry search
           foundItem = searchForState( testClass, "Colorado" ); 
           assertEquals( foundItem, null );           
          }
       
       catch( AssertionError AE )
          {
           System.out.println( "***** Search Failure *****");
          }

       System.out.println( "Tests Complete ------------------" );
      }
   
   /**
    * Method simplifies removal of data from BST
    * 
    * @param bst Generic_BST_Class object from which to remove state
    * 
    * @param toRemove String name of state to be removed
    * 
    * @return StateDataClass object removed, or null if not found
    */
   public static StateDataClass removeState( 
                      Generic_BST_Class<StateDataClass> bst, String toRemove )
      {
       return bst.removeItem( new StateDataClass( toRemove, 0 ) );
      }
   
   /**
    * Method simplifies searching for data in BST
    * 
    * @param bst Generic_BST_Class object in which to search for state
    * 
    * @param toRemove String name of state to be found
    * 
    * @return StateDataClass object found, or null if not found
    */
   public static StateDataClass searchForState( 
                        Generic_BST_Class<StateDataClass> bst, String toFind )
      {
       return bst.search( new StateDataClass( toFind, 0 ) );
      }
  
}

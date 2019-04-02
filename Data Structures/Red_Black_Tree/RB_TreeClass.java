package p8_package;

/**
 * Binary Search Tree (BST) class with self-balancing attributes
 * specifically using the Red/Black Tree (RBT) strategy
 * 
 * @author ML and DG
 *
 */
public class RB_TreeClass
   {
      /**
       * Binary Search Tree node class for managing
       * generic data
       * 
       * @author ML and DG
       *
       */
      private class RBT_Node
         {
          /**
           * Character node data
           */
           private char nodeData;
           
           /**
            * Color for node
            */
           private int nodeColor;
           
           /**
            * Parent reference for node
            */
           private RBT_Node parentRef;
           
           /**
            * Left child reference for node
            */
           private RBT_Node leftChildRef;
           
           /**
            * Right child reference for node
            */
           private RBT_Node rightChildRef;

           /**
            * Initialization constructor for data
            * 
            * @param inData char quantity
            */
           public RBT_Node( char inData )
              {
               nodeData = inData;
               
               nodeColor = COLOR_RED;
               
               parentRef = leftChildRef = rightChildRef = null;
              }

            /**
            * copy constructor for RBT_Node
            * 
            * @param copied RBT_Node object to be duplicated
            */
           public RBT_Node( RBT_Node copied )
              {
               nodeData = copied.nodeData;
               
               nodeColor = copied.nodeColor;
              
               parentRef = leftChildRef = rightChildRef = null;
              }         

         }
      
      /**
       * Root of RBTree
       */
      private RBT_Node RBTree_Root;
      
      /**
       * Constant comma break for printing traversals
       */
      public final String SLASH_BREAK = "/";
      
      /**
       * Constant comma break for printing traversals
       */
      public final String SEMICOLON_BREAK = "; ";
      
      /**
       * Null character returned if data not available
       */
      public final char NULL_CHAR = '\n';
      
      /**
       * Color black constant
       */
      private final int COLOR_BLACK = 66;
      
      /** 
       * Color red constant
       */
      private final int COLOR_RED = 82;
      
      /**
       * Provides user choice to display node color
       * in BST Tree display
       */
      public static final int NODE_COLOR = 191;
      
      /**
       * Provides user choice to display node data
       * in BST Tree display
       */
      public static final int NODE_DATA = 192;
      
      /**
       * Variable provides user-selected choice between output
       * of data or color, initialized to NODE_DATA
       */
      private int treeCharDisplayCode;

      /**
       * Traverse code - preorder
       */
      public static final int PREORDER_TRAVERSE = 101;
      
      /**
       * Traverse code - inorder
       */
      public static final int INORDER_TRAVERSE = 102;
      
      /**
       * Traverse code - postorder
       */
      public static final int POSTORDER_TRAVERSE = 103;
      
      /**
       * Class global variable used to display tree structure
       */
      private boolean rowStartFlag;
      
      /**
       * Constant used to represent space
       * 
       */
      private static final char SPACE = ' ';
      
      /**
       * Constant used to represent dash
       * 
       */
      private static final char DASH = '-';
            
      /**
       * Default class constructor, no initialization actions
       */
      public RB_TreeClass()
         {
          RBTree_Root = null;
          
          treeCharDisplayCode = NODE_DATA;
          
          rowStartFlag = false;
         }
      
      /**
       * Class copy constructor
       * <p>
       * Note: Uses copyConstructorHelper
       * 
       * @param copied reference to RB_TreeClass object 
       */
      public RB_TreeClass( RB_TreeClass copied )
         {
          treeCharDisplayCode = copied.treeCharDisplayCode;
          
          rowStartFlag = copied.rowStartFlag;
          
          RBTree_Root = copyConstructorHelper( copied.RBTree_Root );
         }

      /** 
       * Implements tree duplication effort with recursive method;
       * copies data into newly created nodes and creates all new
       * references to child nodes
       * <p>
       * Note: Uses pre order traversal strategy
       * 
       * @param workingCopiedRef RBT_Node reference that is updated
       * to lower level children with each recursive call
       * 
       * @return RBT_Node reference that links to next higher level node;
       * last return is to root node of THIS object
       */
      private RBT_Node copyConstructorHelper( RBT_Node workingCopiedRef )
        {
            if(workingCopiedRef.parentRef==null)
            {
                return workingCopiedRef;
            }
            RBT_Node copied = new RBT_Node(workingCopiedRef);
            copied.leftChildRef = 
                    copyConstructorHelper(workingCopiedRef.leftChildRef);
            copied.rightChildRef = 
                    copyConstructorHelper(workingCopiedRef.rightChildRef);
            copied.leftChildRef.parentRef=copied;
            copied.rightChildRef.parentRef=copied;
            return copyConstructorHelper(workingCopiedRef.parentRef);
         }

      /**
       * Local recursive method to display a specified number
       * of a specified character
       * 
       * @param numChars number of characters to display
       * 
       * @param outChar character to display
       */
      private void displayChars( int numChars, char outChar )
         {
          if( numChars > 0 )
             {
              System.out.print( outChar );
              
              displayChars( numChars - 1, outChar );
             }
         }

      /**
       * Method that displays null or blank nodes for a tree at null locations
       * <p>
       * Note: used by displayAtTreeLevel
       * 
       * @param nodeHeight height of tree plus two
       * for current height of nodes, including lowermost null children
       * 
       * @param displayLevel level of the tree at which
       * the display will be applied
       * 
       * @param workingLevel level of tree just below
       * non-null node at which method is currently working
       */
      private void displayEmptyNodeSpaces( int nodeHeight, 
                                          int displayLevel, int workingLevel )
         {
          int nodesToDisplay = toPower( 2, displayLevel - workingLevel ); 
          char charOut = SPACE;
          
          if( displayLevel == workingLevel )
             {
              charOut = DASH;
              
              if( treeCharDisplayCode == NODE_COLOR )
                 {
                  charOut = 'B';
                 }
             }
          
          while( nodesToDisplay > 0 )
             {
              displayValue( charOut, nodeHeight, displayLevel );
              
              nodesToDisplay--;
             }
         }
      
      /**
       * Provides inOrder traversal action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       */
      private void displayInOrder( RBT_Node localRoot )
         {
          if( localRoot != null )
             {
              displayInOrder( localRoot.leftChildRef );
              
              if( !rowStartFlag )
                 {
                  System.out.print( SEMICOLON_BREAK );
                 }

              rowStartFlag = false;

              System.out.print( localRoot.nodeData + SLASH_BREAK 
                                               + (char)localRoot.nodeColor );
              displayInOrder( localRoot.rightChildRef );
             }
         }
      
      /**
       * Provides postOrder traversal action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       */
      private void displayPostOrder( RBT_Node localRoot )
         {
          if( localRoot != null )
             {
              displayPostOrder( localRoot.leftChildRef );
              displayPostOrder( localRoot.rightChildRef );

              if( !rowStartFlag )
                 {
                  System.out.print( SEMICOLON_BREAK );
                 }

              rowStartFlag = false;

              System.out.print( localRoot.nodeData + SLASH_BREAK 
                                               + (char)localRoot.nodeColor );
             }
         }
      
      /**
       * Provides preOrder traversal action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       */
      private void displayPreOrder( RBT_Node localRoot )
         {
          if( localRoot != null )
             {
              if( !rowStartFlag )
                 {
                  System.out.print( SEMICOLON_BREAK );
                 }

              rowStartFlag = false;

              System.out.print( localRoot.nodeData + SLASH_BREAK 
                                               + (char)localRoot.nodeColor );
              displayPreOrder( localRoot.leftChildRef );
              displayPreOrder( localRoot.rightChildRef );
             }
         }
      
      /**
       * Displays text-graphical representation of one level/line
       * of the RBT tree
       * <p>
       * Note: choice of color or letter data is made with 
       * setTreeDisplayCharacter
       * 
       * @param workingNode node reference at current recursive level
       * 
       * @param nodeHeight height of tree plus two
       * for current height of nodes, including lowermost null children
       * 
       * @param displayLevel level of tree at which the current line
       * of display is to be presented
       * 
       * @param workingLevel current level during recursive actions
       */
      private void displayAtTreeLevel( RBT_Node workingNode, int nodeHeight, 
                                         int displayLevel, int workingLevel )
         {
          char charOut = workingNode.nodeData;
          
          if( treeCharDisplayCode == NODE_COLOR )
             {
              charOut = (char)workingNode.nodeColor;      
             }
          
          if( workingLevel == displayLevel )
             {
              displayValue( charOut, nodeHeight, workingLevel );

              return;
             }
          
          if( workingNode.leftChildRef != null )
             {
              displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
                                            displayLevel, workingLevel + 1 );
             }
          
          else
             {
              displayEmptyNodeSpaces( nodeHeight, 
                                            displayLevel, workingLevel + 1 );
             }
              
          if( workingNode.rightChildRef != null )
             {
              displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
                          displayLevel, workingLevel + 1 );
             }

          else
             {
              displayEmptyNodeSpaces( nodeHeight, 
                                            displayLevel, workingLevel + 1 );
             }              
         }
      
      /**
       * Provides user with three ways to display RBTree data
       * 
       * @param traverseCode integer code for selecting RBTree traversal method,
       * accepts PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
       */
      public void displayTree( int traverseCode )
         {
          rowStartFlag = true;
          
          switch( traverseCode )
             {
                case PREORDER_TRAVERSE:
                   displayPreOrder( RBTree_Root );
                   break;
                   
                case POSTORDER_TRAVERSE:
                   displayPostOrder( RBTree_Root );
                   break;
                   
                default:
                   displayInOrder( RBTree_Root );
                   break;
             }
          
          System.out.println();
         }
      
      /**
       * Displays text-graphical representation of RBT tree
       *  
       */
      public void displayTreeStructure()
         {
          int displayLevel, nodeHeight = findTreeHeight() + 2;
          int workingLevel = 1;
          
          if( RBTree_Root != null )
             {
              for( displayLevel = 1; 
                                 displayLevel <= nodeHeight; displayLevel++ )
                 {
                  rowStartFlag = true;
                  
                  displayAtTreeLevel( RBTree_Root, nodeHeight, 
                                               displayLevel, workingLevel );
                  
                  System.out.println();
                 }
             }
          
          else
             {
              System.out.println( "\nEmpty Tree - No Display");
             }
         }
      
      /**
       * Method used to display a character or color letter
       * along with calculated leading spaces
       * <p>
       * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
       * 
       * @param data character value to display, either letter or color data
       * 
       * @param nodeHeight integer height of tree plus two
       * for current height of nodes, including lowermost null children
       * 
       * @param workingLevel integer current level during recursive actions
       */
      private void displayValue( char data, int nodeHeight, int workingLevel )
         {
          int leadingSpaces;
          
          if( rowStartFlag )
             {
              leadingSpaces = toPower( 2, nodeHeight - workingLevel );

              rowStartFlag = false;
             }
          
          else
             {
              leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
             }

          displayChars( leadingSpaces, SPACE );
          
          System.out.print( data );         
         }
      
      /**
       * Finds height of tree using helper method
       * 
       * @return height of tree
       */
      public int findTreeHeight()
         {
          return findTreeHeightHelper( RBTree_Root );
         }
      
      /**
       * Recursive tree height helper
       * <p>
       * Note: Empty tree height is -1; single root node tree height is 0
       * 
       * @param localRoot RBT_Node reference at current recursive level
       * 
       * @return integer height at a given level
       */
      private int findTreeHeightHelper( RBT_Node localRoot )
         {
          if( localRoot != null )
             {
              return getMax( 
                   findTreeHeightHelper( localRoot.leftChildRef ), 
                       findTreeHeightHelper( localRoot.rightChildRef ) ) + 1;
             }
          
          return -1;
         }
      
      /**
       * Finds maximum of two given numbers
       * 
       * @param one integer value of one of the values to be tested
       * 
       * @param other integer value of the other of the two values to be tested
       * 
       * @return integer greater of the two values
       */
      private int getMax( int one, int other )
         {
          int max = one;
          
          if( other > max )
             {
              max = other;
             }
          
          return max;
         }
      
      /**
       * Test for empty tree
       * 
       * @return Boolean result of test
       */
      public boolean isEmpty()
         {
          return RBTree_Root == null;
         }

      /** Insert method for RBTree
       * <p>
       * Note: uses insert helper method which returns
       * the inserted node reference to this method
       * <p>
       * Note: After value has been inserted as a Red node,
       * this method calls the resolveRBT_Issues method
       * to rebalance and/or recolor the tree
       * 
       * @param inData char data to be added to RBTree
       *  
       * @return Boolean result of insertion action
       */
      public boolean insert( char inData )
         {
          System.out.println( "\nInserting " + inData + " and resolving");                 
          
          RBT_Node insertedNode = insertHelper( RBTree_Root, inData );
          
          if( insertedNode != null )
             {
              resolveRBT_Issues( insertedNode );
              
              return true;
             }
          
          return false;
         }
      
      /**
       * Insert helper method for RBTree insert action
       * <p>
       * Note: Does not accept duplicate values
       *  
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       * 
       * @param inData char item to be added to RBTree
       * 
       * @return RBT_Node reference of inserted node
       */
      private RBT_Node insertHelper( RBT_Node localRoot, char inData )
         {
          int compareResult;
          
          if( RBTree_Root == null )
             {
              RBTree_Root = new RBT_Node( inData );
              
              RBTree_Root.parentRef = null;
              
              return RBTree_Root; 
             }
          
          compareResult = localRoot.nodeData - inData;
          
          if( compareResult > 0 )
             {
              if( localRoot.leftChildRef == null )
                 {
                  localRoot.leftChildRef = new RBT_Node( inData );
              
                  localRoot.leftChildRef.parentRef = localRoot;
                  
                  return localRoot.leftChildRef;
                 }
              
              return insertHelper( localRoot.leftChildRef, inData );
             }
          
          else if( compareResult < 0 )
             {
              if( localRoot.rightChildRef == null )
                 {
                  localRoot.rightChildRef = new RBT_Node( inData );
                  
                  localRoot.rightChildRef.parentRef = localRoot;
                  
                  return localRoot.rightChildRef;
                 }
              
              return insertHelper( localRoot.rightChildRef, inData );
             }
          
          return null;  // doesn't allow duplicate entries      
         }
      
      /**
       * Recursively rebalances and recolors tree as needed
       * <p>
       * Note: Indicates actions being taken by displaying them
       * 
       * @param localRef RBT_Node reference for the local tree being managed
       */
      private void resolveRBT_Issues( RBT_Node localRef )
        {
            displayTreeStructure();
            if(localRef.parentRef==null || localRef.parentRef.parentRef==null)
            {
                return;
            }
            RBT_Node parent_node = localRef.parentRef;
            localRef.parentRef=parent_node;
            RBT_Node grandParent_node =localRef.parentRef.parentRef;
            localRef.parentRef.parentRef=grandParent_node;
            RBT_Node relative = null;
            while((localRef != RBTree_Root)
                    && (localRef.nodeColor != COLOR_BLACK)
                    && (localRef.parentRef.nodeColor == COLOR_RED))
            {
                parent_node= localRef.parentRef;
                grandParent_node = localRef.parentRef.parentRef;
                
                if(grandParent_node.leftChildRef == parent_node)
                {
                    relative = localRef.parentRef.parentRef.leftChildRef;
                    if(relative !=null && relative.nodeColor == COLOR_RED)
                    {
                        grandParent_node.nodeColor = COLOR_RED;
                        parent_node.nodeColor = COLOR_BLACK;
                        relative.nodeColor = COLOR_BLACK;
                        localRef = grandParent_node;
                    }
                    else
                    {
                        if(localRef == parent_node.rightChildRef)
                        {
                            rotateLeft(localRef, parent_node, grandParent_node);
                            localRef = parent_node;
                            parent_node = localRef.parentRef;
                        }
                        else
                        {
                            rotateRight(localRef, parent_node, grandParent_node);
                            swapColors(parent_node, grandParent_node);
                            localRef= parent_node;
                        }
                    }
                }
                else
                {
                    relative = localRef.parentRef.parentRef.leftChildRef;
                    if(relative !=null && relative.nodeColor == COLOR_RED)
                    {
                        grandParent_node.nodeColor = COLOR_RED;
                        parent_node.nodeColor = COLOR_BLACK;
                        relative.nodeColor = COLOR_BLACK;
                        localRef = grandParent_node;
                    }
                    else
                    {
                        if(localRef == parent_node.leftChildRef)
                        {
                            rotateRight(localRef, parent_node, grandParent_node);
                            localRef = parent_node;
                            parent_node = localRef.parentRef;
                        }
                        else
                        {
                            rotateLeft(localRef, parent_node, grandParent_node);
                            swapColors(parent_node, grandParent_node);
                            localRef= parent_node;
                        }
                    }
                }
            }
            RBTree_Root.nodeColor = COLOR_BLACK;
        }
      
      /**
       * Rotates local tree left or CCW
       * 
       * @param localRef reference of current item
       * 
       * @param parRef reference of current item parent
       * 
       * @param grndParRef reference of current item grandparent
       */
      private void rotateLeft( RBT_Node localRef, 
                                  RBT_Node parRef, RBT_Node grndParRef )
        {
            if(localRef.rightChildRef==null) return;
            RBT_Node nodeToRight = localRef.rightChildRef;    
            localRef.rightChildRef = nodeToRight.leftChildRef;
            nodeToRight.leftChildRef = localRef;
            
            if(localRef.rightChildRef != null)
            {
                localRef.rightChildRef.parentRef = localRef;
            }
            nodeToRight.parentRef = localRef.parentRef;
            
            if(localRef.parentRef==null)
            {
                RBTree_Root=nodeToRight;
            }
            else if(localRef == localRef.parentRef.leftChildRef)
            {
                localRef.parentRef.leftChildRef = nodeToRight;
            }
            else
            {
                localRef.parentRef.rightChildRef = nodeToRight;
            }
            localRef.parentRef = nodeToRight;
            swapColors(parRef, grndParRef);
        }

      /**
       * Rotates local tree right or CW   
       * 
       * @param localRef reference of current item
       * 
       * @param parRef reference of current item parent
       * 
       * @param grndParRef reference of current item grandparent
       */
      private void rotateRight( RBT_Node localRef, 
                                  RBT_Node parRef, RBT_Node grndParRef )
         {
            if(localRef.rightChildRef==null) return;
            RBT_Node nodeToLeft = localRef.leftChildRef;  
            localRef.leftChildRef = nodeToLeft.rightChildRef;
            nodeToLeft.rightChildRef = localRef;
            
            if(localRef.leftChildRef != null)
            {
                localRef.leftChildRef.parentRef = localRef;
            }
            nodeToLeft.parentRef = localRef.parentRef;
            
            if(localRef.parentRef==null)
            {
                RBTree_Root=nodeToLeft;
            }
            else if(localRef == localRef.parentRef.leftChildRef)
            {
                localRef.parentRef.leftChildRef = nodeToLeft;
            }
            else
            {
                localRef.parentRef.rightChildRef = nodeToLeft;
            }
            localRef.parentRef = nodeToLeft;
            swapColors(parRef, grndParRef);
         }
      
      /** 
       * Searches for data in RBTree given char with necessary key
       * 
       * @param searchData char item containing key
       * 
       * @return char found data
       */
      public char search( char searchData )
         {
          return searchHelper( RBTree_Root, searchData );
         }
      
      /**
       * Helper method for RBTree search action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       * 
       * @param searchData char item containing key
       * 
       * @return Boolean result of search
       */
      private char searchHelper( RBT_Node localRoot, char searchData )
         {
          int compareResult;
          
          if( localRoot != null )
             {
              compareResult = localRoot.nodeData - searchData;
              
              if( compareResult > 0 )
                 {
                  return searchHelper( localRoot.leftChildRef, searchData );
                 }
              
              else if( compareResult > 0 )
                 {
                  return searchHelper( localRoot.rightChildRef, searchData );
                 }
              
              return localRoot.nodeData;
             }
                    
          return NULL_CHAR;
         }
      
      /**
       * Allows user to set tree display character
       * 
       * @param displayCode specifies whether to set tree
       * to display data character (NODE_DATA) 
       * or color letter (NODE_COLOR)
       */
      public void setTreeDisplayCharacter( int displayCode )
         {
          treeCharDisplayCode = displayCode;          
         }
      
      /**
       * Swaps colors between two RBT nodes
       * 
       * @param one one of the RBT nodes
       * 
       * @param other other of the RBT nodes
       */
      public void swapColors( RBT_Node one, RBT_Node other )
         {
          int tempColor = one.nodeColor;
          
          one.nodeColor = other.nodeColor;
          
          other.nodeColor = tempColor;
         }

      /**
       * Local recursive method to calculate exponentiation
       * with integers
       * 
       * @param base base of exponentiation
       * 
       * @param exponent exponent of exponentiation
       * 
       * @return result of exponentiation calculation
       */
      private int toPower( int base, int exponent )
         {
          if( exponent > 0 )
             {
              return toPower( base, exponent - 1 ) * base;
             }
          
          return 1;
         }
   }

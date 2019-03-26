package p7_package;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author djg32
 * @param <GenericData>
 */
public class Generic_BST_Class <GenericData extends java.lang.Comparable
<GenericData>>
{
    /**
     * 
     */
    private BST_Node BST_Root;
    
    /**
     * 
     */
    private class BST_Node
    {
        private BST_Node leftChildRef;
        
        private GenericData nodeData;
        
        private BST_Node rightChildRef;
        
        /**
         * Copy constructor for data
         * @param copied Data from copied BST_Node
         */
        BST_Node(BST_Node copied)
        {
            leftChildRef=copied.leftChildRef;
            rightChildRef=copied.rightChildRef;
            nodeData=copied.nodeData;
        }
        
        /**
         * Initialization constructor for data
         * @param inData GenericData quantity
         */
        BST_Node(GenericData inData)
        {
            nodeData=inData;
            leftChildRef=rightChildRef=null;
        }
    }
    
    /**
     * Default class constructor, initializes BST
     */
    Generic_BST_Class()
    {
        BST_Root = new BST_Node(<GenericData>);
    }
    
    /**
     * Clears tree
     */
    void clearTree()
    {
        BST_Root.leftChildRef=BST_Root.rightChildRef=null;
    }
    
    /**
     * Provides inOrder traversal action
     * <p>
     * Note: Calls displayInOrderHelper
     */
    void displayInOrder()
    {
        System.out.print("InOrder: ");
        BST_Node BST_Temp = new BST_Node(BST_Root);
        displayInOrderHelper(BST_Temp);
    }
    
    /**
     * Provides inOrder traversal action using recursion
     * @param localRoot BST_Node tree root reference at the current recursion
     * level
     */
    private void displayInOrderHelper(BST_Node localRoot)
    {
        if(localRoot==null)
        {
            return;
        }
        displayInOrderHelper(localRoot.leftChildRef);
        System.out.print(localRoot.nodeData + " ");
        displayInOrderHelper(localRoot.rightChildRef);
    }
    
    /**
     * Provides postOrder traversal action
     * <p>
     * Note: Calls displayPostOrderHelper
     */
    void displayPostOrder()
    {
        System.out.print("PostOrder: ");
        BST_Node BST_Temp = new BST_Node(BST_Root);
        displayPostOrderHelper(BST_Temp);
    }
    
    /**
     * Provides postOrder traversal action using recursion
     * @param localRoot BST_Node tree root reference at the current recursion
     * level
     */
    private void displayPostOrderHelper(BST_Node localRoot)
    {
        if(localRoot==null)
        {
            return;
        }
        displayPostOrderHelper(localRoot.leftChildRef);
        displayPostOrderHelper(localRoot.rightChildRef);
        System.out.print(localRoot.nodeData + " ");
    }
    
    /**
     * Provides preOrder traversal action
     * <p>
     * Note: Calls displayPreOrderHelper
     */
    void displayPreOrder()
    {
        System.out.print("PreOrder: ");
        BST_Node BST_Temp = new BST_Node(BST_Root);
        displayPreOrderHelper(BST_Temp);
    }
    
    /**
     * Provides preOrder traversal action using recursion
     * @param localRoot BST_Node tree root reference at the current recursion
     * level
     */
    private void displayPreOrderHelper(BST_Node localRoot)
    {
        if(localRoot==null)
        {
            return;
        }
        System.out.print(localRoot.nodeData + " ");
        displayPostOrderHelper(localRoot.leftChildRef);
        displayPostOrderHelper(localRoot.rightChildRef);
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: uses insert helper method which returns root reference
     * <p>
     * Note: uses search to verify that key is not already in tree; if key is
     * already in tree, insert is not conducted
     * @param inData GenericData data to be added to BST
     */
    void insert(GenericData inData)
    {
        if(search(inData).equals(BST_Root.nodeData)) return;
        BST_Node BST_Temp = new BST_Node(inData);
        if(isEmpty()) 
        {
            BST_Root=BST_Temp;
            return;
        }
        BST_Root = insertHelper(BST_Temp, inData);
    }
    
    /**
     * Insert helper method for BST insert action
     * <p>
     * Note: Recursive method returns updated local root to maintain tree
     * linkage
     * @param localRoot BST_Node tree root reference at the current recursion
     * level
     * @param inData GenericData item to be added to BST
     * @return BST_Node reference used to maintain tree linkage
     */
    private BST_Node insertHelper(BST_Node localRoot, GenericData inData)
    {
        GenericData temp = compare(inData, localRoot.nodeData);
        if(temp.equals(localRoot.nodeData))
        {
            if(localRoot.leftChildRef==null)
            {
                localRoot.leftChildRef= new BST_Node(inData);
                return localRoot;
            }
            else
            {
                return insertHelper(localRoot.leftChildRef, inData);
            }
        }
        else if(temp.equals(inData))
        {
            if(localRoot.rightChildRef==null)
            {
                localRoot.rightChildRef= new BST_Node(inData);
                return localRoot;
            }
            else
            {
                return insertHelper(localRoot.rightChildRef, inData);
            }
        }
        return localRoot;
    }
    
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    boolean isEmpty()
    {
        if(BST_Root==null) return true;
        return false;
    }
    
    /**
     * Searches tree from given node to maximum value node below it, stores
     * data value found, and then unlinks the node
     * @param maxParent BST_Node reference to current node
     * @param maxLoc BST_Node reference to child node to be tested
     * @return BST_Node reference containing removed node
     */
    private BST_Node removeFromMax(BST_Node maxParent, BST_Node maxLoc)
    {
        return null;
    }
    
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * <p>
     * Note: uses search initially to get value, if it is in tree; if value 
     * found, remove helper method is called, otherwise returns null
     * @param inData GenericData that includes the necessary key
     * @return GenericData result of remove action
     */
    GenericData removeItem(GenericData inData)
    {
        BST_Node BST_Temp = new BST_Node(search(inData));
        return removeItemHelper(BST_Root, inData).nodeData;
    }
    
    /**
     * Remove helper for BST remove action
     * <p>
     * Note: Recursive method returns updated local root to maintain tree linkage
     * <p>
     * Note: uses removeFromMax method
     * @param localRoot BST_Node tree root reference at the current recursion
     * level
     * @param outData GenericData item that includes the necessary key
     * @return BST_Node reference result of remove helper action
     */
    private BST_Node removeItemHelper(BST_Node localRoot, GenericData outData)
    {
        return null;
    }
    
    /**
     * Searches for data in BST given GenericData with necessary key
     * @param searchData GenericData item containing key
     * @return GenericData reference to found data
     */
    GenericData search(GenericData searchData)
    {
        if(isEmpty())
        {
            return null;
        }
        BST_Node BST_Temp = new BST_Node(BST_Root);
        return searchHelper(BST_Temp, searchData);
    }
    
    /**
     * Helper method for BST search action
     * @param localRoot BST_Node tree root reference at the current recursion
     * level
     * @param searchData GenericData item containing key
     * @return GenericData item found
     */
    private GenericData searchHelper(BST_Node localRoot, GenericData searchData)
    {
        if(localRoot.leftChildRef!=null)
        {
            searchHelper(localRoot.leftChildRef, searchData);
        }
        if(localRoot.nodeData.equals(searchData)) return localRoot.nodeData;
        if(localRoot.rightChildRef!=null)
        {
            searchHelper(localRoot.rightChildRef, searchData);
        }
        if(localRoot.nodeData.equals(searchData)) return localRoot.nodeData;
        return null;
    }
            
}

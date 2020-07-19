package BST;

public class BST<T extends Comparable<? super T>> {
    private BinaryNode<T> root;

    BST()
    {
        root = null;
    }

    BST(T value)
    {
        root = new BinaryNode<T>(value);
    }

    public BinaryNode<T> getRoot()
    {
        return root;
    }

    public String toString()
    {
        return root.toString();
    }

    // #####     #####     #####     #####     #####     #####     #####     #####
    //      #####     #####     #####     #####     #####     #####     #####
    // #####     #####     #####     #####     #####     #####     #####     #####

    public void insertRec(T value)
    {
        root = _insertRec(root, value);
    }

    private BinaryNode<T> _insertRec(BinaryNode<T> currentNode, T value)
    {
        if (currentNode == null) return new BinaryNode<T>(value);

        if (value.compareTo(currentNode.getValue()) < 0) // >
            currentNode.leftChild = _insertRec(currentNode.leftChild, value);
        else
            currentNode.rightChild = _insertRec(currentNode.rightChild, value);

        return currentNode;
    }

    public void insert(T value)
    {
        BinaryNode<T> currentNode = root;
        BinaryNode<T> newNode = new BinaryNode<T>(value);

        if (root == null)
        {
            root = newNode;
            return;
        }

        BinaryNode<T> parent = null;
        while (currentNode != null)
        {
            parent = currentNode;
            if (value.compareTo(currentNode.getValue()) < 0)
                currentNode = currentNode.leftChild;
            else
                currentNode = currentNode.rightChild;
        }
        if (value.compareTo(parent.getValue()) < 0)     // >
            parent.leftChild = newNode;
        else
            parent.rightChild = newNode;

    }

    // #####     #####     #####     #####     #####     #####     #####     #####
    //      #####     #####     #####     #####     #####     #####     #####
    // #####     #####     #####     #####     #####     #####     #####     #####

    public Boolean isIn(T value)
    {
        BinaryNode<T> currentNode = root;

        while ((currentNode != null) && (value.compareTo(currentNode.getValue()) != 0))
        {
            if (value.compareTo(currentNode.getValue()) < 0)
                currentNode = currentNode.leftChild;
            else
                currentNode = currentNode.rightChild;
        }

        return (currentNode != null);
    }

    // #####     #####     #####     #####     #####     #####     #####     #####
    //      #####     #####     #####     #####     #####     #####     #####
    // #####     #####     #####     #####     #####     #####     #####     #####

    public void deleteIter(T value)
    {
        BinaryNode<T> parent = root;
        BinaryNode<T> currentNode = root;

        while (currentNode != null)
        {
            if (currentNode.getValue().compareTo(value) == 0)
            {
                break;
            }
            parent = currentNode;
            if (value.compareTo(currentNode.getValue())< 0)
            {
                currentNode = currentNode.leftChild;
            }
            else {
                currentNode = currentNode.rightChild;
            }
        }
        if (currentNode == null) return;
        else {
            if (parent.rightChild == currentNode)
            {
                if (currentNode.leftChild == null && currentNode.rightChild == null)
                {
                    parent.rightChild = null;
                }
                else if (currentNode.leftChild == null)
                {
                    parent.rightChild = currentNode.rightChild;
                }
                else if (currentNode.rightChild == null) {
                    parent.rightChild = currentNode.leftChild;
                }
                else {
                    T replacement = getMinValue(currentNode.rightChild);
                    deleteIter(replacement);
                    currentNode.setValue(replacement);
                }
            }
            else
            {
                if (currentNode.leftChild == null && currentNode.rightChild == null)
                {
                    parent.leftChild = null;
                }
                else if (currentNode.leftChild == null)
                {
                    parent.leftChild = currentNode.rightChild;
                }
                else if (currentNode.rightChild == null) {
                    parent.leftChild = currentNode.leftChild;
                }
                else {
                    T replacement = getMinValue(currentNode.rightChild);
                    deleteIter(replacement);
                    currentNode.setValue(replacement);
                }
            }
        }
    }
    public void delete(T value)
    {
        root = _delete(root, value);
    }

    private BinaryNode<T> _delete(BinaryNode<T> currentNode, T value)
    {
        if (currentNode == null) return null;

        if (value.compareTo(currentNode.getValue()) == 0)
        {
            if ((currentNode.leftChild == null) && (currentNode.rightChild == null)) return null;
            if (currentNode.leftChild == null) return currentNode.rightChild;
            if (currentNode.rightChild == null) return currentNode.leftChild;

            T replacement = getMinValue(currentNode.rightChild);
            currentNode.setValue(replacement);
            currentNode.rightChild = _delete(currentNode.rightChild, replacement);
        }
        else if (value.compareTo(currentNode.getValue()) < 0) // >
            currentNode.leftChild = _delete(currentNode.leftChild, value);
        else
            currentNode.rightChild = _delete(currentNode.rightChild, value);

        return currentNode;
    }

    private T getMinValue(BinaryNode<T> forNode)
    {
        while(forNode.leftChild != null)
        {
            forNode = forNode.leftChild;
        }
        return forNode.getValue();
    }
}
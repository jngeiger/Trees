package Splay;

import javax.swing.plaf.IconUIResource;

public class SplayTree<T extends Comparable<? super T>> {
    public SplayNode<T> root;
    SplayTree()
    {
        root = null;
    }
    SplayTree(T value)
    {
        root = new SplayNode<T>(value);
    }
    public String toString()
    {
        return root.toString();
    }
    public void insert(T value)
    {

        if (root == null)
        {
            root = new SplayNode<T>(value);
            return;
        }
        SplayNode<T> newNode = new SplayNode<T>(value);

        // Knoten oder des Mutter in die Wurzel splayen
        root = splay(root, value);

        // Knoten und gesplayten Baum zusammenbauen
        if (root.getValue().compareTo(value) >= 0)
        {
            newNode.rightChild = root;
            newNode.leftChild = root.leftChild;
            root.leftChild = null;
            root = newNode;
        }
        else {
            newNode.leftChild = root;
            newNode.rightChild = root.rightChild;
            root.rightChild = null;
            root = newNode;
        }
    }
    public Boolean isIn(T value)
    {
        root = splay(root, value);
        return (root.getValue().compareTo(value) == 0);
    }
    public void delete(T value)
    {
        if (isIn(value))
        {
            if (root.leftChild == null && root.rightChild == null)
                root = null;
            else if (root.leftChild == null)
                root = root.rightChild;
            else if (root.rightChild == null)
                root = root.leftChild;
            else {
                T replacement = getMinValue(root.rightChild);
                root.setValue(replacement);
                root.rightChild = deleteMinNode(root.rightChild);
            }
        }
    }
    private T getMinValue(SplayNode<T> forNode)
    {
        while(forNode.leftChild != null)
        {
            forNode = forNode.leftChild;
        }
        return forNode.getValue();
    }
    private SplayNode<T> deleteMinNode(SplayNode<T> currentNode)
    {
        if (currentNode.leftChild != null)
            currentNode.leftChild = deleteMinNode(currentNode.leftChild);
        else {
            return currentNode.rightChild;
        }
        return currentNode;
    }
    private SplayNode<T> splay(SplayNode<T> node, T value)
    {
        if ((node == null) || (value.compareTo(node.getValue()) == 0)) return node;

        if (node.getValue().compareTo(value) > 0) // node.value > value
        {
            // Node nicht im Baum
            if (node.leftChild == null) return node;

            // Zig-Zig (Left Left)
            if (node.leftChild.getValue().compareTo(value) > 0) // node.left.value > value
            {
                node.leftChild.leftChild = splay(node.leftChild.leftChild, value);

                // Erste Rotation
                node = rightRotation(node);
            }
            else if (node.leftChild.getValue().compareTo(value) < 0) // Zig-Zag (Left Right)
            {
                node.leftChild.rightChild = splay(node.leftChild.rightChild, value);

                // Rotation fuer node.left
                if (node.leftChild.rightChild != null)
                    node.leftChild = leftRotation(node.leftChild);
            }

            // Zweite Rotation fuer node
            if (node.leftChild == null)
                return node;
            else
                return rightRotation(node);
        }
        else
        {
            // Node nicht im Baum
            if (node.rightChild == null) return node;

            // Zag-Zig (Right Left)
            if (node.rightChild.getValue().compareTo(value) > 0)
            {
                node.rightChild.leftChild = splay(node.rightChild.leftChild, value);

                // Erste Rotation fuer node.right
                if (node.rightChild.leftChild != null)
                    node.rightChild = rightRotation(node.rightChild);
            }
            else if (node.rightChild.getValue().compareTo(value) < 0)// Zag-Zag (Right Right)
            {
                node.rightChild.rightChild = splay(node.rightChild.rightChild, value);
                node = leftRotation(node);
            }

            // Zweite Rotation
            if (node.rightChild == null)
                return node;
            else
                return leftRotation(node);
        }
    }

    private SplayNode<T> leftRotation(SplayNode<T> node)
    {
        SplayNode<T> pivot = node.rightChild;
        node.rightChild = pivot.leftChild;
        pivot.leftChild = node;

        return pivot;
    }

    private SplayNode<T> rightRotation(SplayNode<T> node)
    {
        SplayNode<T> pivot = node.leftChild;
        node.leftChild = pivot.rightChild;
        pivot.rightChild = node;

        return pivot;
    }
    public void printWayToNode(T value)
    {
        // Ausgabe der Knoten von der Wurzel zum Knoten mit "value"
        _printWayToNode(root, value);
    }
    private void _printWayToNode(SplayNode<T> currentNode, T value)
    {
        if (currentNode == null) return;

        System.out.println(currentNode.getValue());
        if (value.compareTo(currentNode.getValue()) == 0)
            return;
        else if (value.compareTo(currentNode.getValue()) < 0)
            _printWayToNode(currentNode.leftChild, value);
        else
            _printWayToNode(currentNode.rightChild, value);
    }
    public void printWayBack(T value)
    {
        // Ausgabe von Wurzel rauf zum Knoten
        _printWayBack(root,value);
    }
    private void _printWayBack(SplayNode<T> currentNode, T value)
    {
        if (currentNode == null) return;

        if (value.compareTo(currentNode.getValue()) == 0)
        {
            System.out.println(currentNode.getValue());
            return;
        }
        else if (value.compareTo(currentNode.getValue()) < 0)
            _printWayBack(currentNode.leftChild, value);
        else
            _printWayBack(currentNode.rightChild, value);
        System.out.println(currentNode.getValue());
    }
}

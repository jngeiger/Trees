package BST;

import java.util.function.Consumer;
import java.util.function.Function;

public class BinaryNode<T> {
    private T value;
    public BinaryNode<T> leftChild;
    public BinaryNode<T> rightChild;

    BinaryNode(T v)
    {
        value = v;
    }

    public T getValue()
    {
        return this.value;
    }

    public void setValue(T v)
    {
        this.value = v;
    }

    public String toString()
    {
        return desc(this, "", "", "");
    }

    // Inspired by Knuth
    private String desc(BinaryNode<T> node, String top, String root, String bottom)
    {
        if (node == null) return root + "null\n";

        if ((node.leftChild == null) && (node.rightChild == null))
            return root + node.value + "\n";

        return desc(node.rightChild,
                top + " ",
                top + "┌──",
                top + "│ ") +
                root + node.value + "\n" +
                desc(node.leftChild,
                        bottom + "| ",
                        bottom + "└──",
                        bottom + " ");
    }

    // #####     #####    #####     #####     #####     #####     #####     #####
    //      #####     #####    #####     #####     #####     #####     #####
    // #####    #####     #####     #####     #####     #####     #####     #####

    public int hoehe()
    {
        return _hoehe(this);
    }

    private int _hoehe(BinaryNode<T> node)
    {
        if (node == null) return -1;

        return 1+Math.max(_hoehe(node.leftChild), _hoehe(node.rightChild));
    }

    // #####     #####    #####     #####     #####     #####     #####     #####
    //      #####     #####    #####     #####     #####     #####     #####
    // #####    #####     #####     #####     #####     #####     #####     #####

   public void traversalPreOrder() {
       System.out.println(value);
       if (leftChild != null) leftChild.traversalPreOrder();
       if (rightChild != null) rightChild.traversalPreOrder();
   }
   public void traversalInOrder()
   {
       if (leftChild != null) leftChild.traversalInOrder();
       System.out.println(value);
       if (rightChild != null) rightChild.traversalInOrder();
   }
   public void traversalPostOrder()
    {
        if (leftChild != null) leftChild.traversalPostOrder();
        if (rightChild != null) rightChild.traversalPostOrder();
        System.out.println(value);
    }
}
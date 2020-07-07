package Splay;

import AVL.AVLTree;

public class First {

    public static void main(String args[])
    {
        var complexTree = new SplayTree<Character>();
        Character[] array = {'E','X','P','A','N','S','E'};
        for (int i = 0; i < array.length;i++)
        {
            complexTree.insert(array[i]);
        }

        System.out.println(complexTree+"\n");
    }
}

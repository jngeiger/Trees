package Splay;

import AVL.AVLTree;

public class First {

    public static void main(String args[])
    {
        var complexTree = new SplayTree<Character>();
        Character[] array = {'E','G','R','W','Q','C','M','O','V','X','P','A','Z','F','G','L','J','N','S','E'};
        for (int i = 0; i < array.length;i++)
        {
            complexTree.insert(array[i]);
        }

//        System.out.println(complexTree+"\n");
        complexTree.isIn('G');

        System.out.println(complexTree);
        complexTree.delete('G');
        System.out.println(complexTree);
    }
}

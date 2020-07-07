package AVL;


public class First {

    public static void main(String args[])
    {
        var complexTree = new AVLTree<Character>();
        Character[] array = {'E','X','P','A','N','S','E'};
        for (int i = 0; i < array.length;i++)
        {
            complexTree.insertRec(array[i]);
        }

        System.out.println(complexTree+"\n");
    }
}
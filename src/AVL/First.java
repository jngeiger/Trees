package AVL;


public class First {

    public static void main(String args[])
    {
        var complexTreeInteger = new AVLTree<Integer>();
        Integer[] complexIntegers = {17,41,3,21,12,16,9};

        for (Integer x : complexIntegers)
        {
            complexTreeInteger.insertRec(x);
        }

        var complexTree = new AVLTree<Character>();
        Character[] array = {'E','X','P','A','N','S','E'};
        for (int i = 0; i < array.length;i++)
        {
            complexTree.insertRec(array[i]);
        }

//        System.out.println(complexTree+"\n");
        System.out.println(complexTreeInteger);
    }
}
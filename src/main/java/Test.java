public class Test {
    public static void main(String[] args) {
        int foo = (int) (Math.random() * 100);
        System.out.println("===========>> " + foo);
        if (foo < 10) // 0-79
            System.out.println("HHHHHHHHHIIIIIIIIIIII");
        else if (foo < 40) // 80-84
            System.out.println("BBBBYYYYYEEEEE");
        else // 85-99
            System.out.println("TTTEEESSSTTT");
    }
}

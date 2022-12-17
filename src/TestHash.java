import org.junit.Test;

public class TestHash {
    @Test
    public void test1() {
        String s1 = "1";
        String s2 = "2";
        Hash h1 = new Hash(s1);
        Hash h2 = new Hash(s2);
        System.out.println("Hash1 has size " + h1.hashLength + ": " + h1.toString());
        System.out.println("Hash2 has size " + h2.hashLength + ": " + h2.toString());
    }

    @Test
    public void test2() {
        String s1 = "1";
        String s2 = "1";
        Hash h1 = new Hash(s1);
        Hash h2 = new Hash(s2);
        System.out.println("Hash1 has size " + h1.hashLength + ": " + h1.toString());
        System.out.println("Hash2 has size " + h2.hashLength + ": " + h2.toString());
    }
}

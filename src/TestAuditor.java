import org.junit.Test;

/**
 * @author ZHONG Jiquan
 * @create 17/12/2022 - 04:52
 */
public class TestAuditor {
    @Test
    public void test1() {
        LogServer oldS = new LogServer("data/old.txt");
        LogServer newS = new LogServer("data/new.txt");
        Auditor a = new Auditor(oldS);
        if (a.isConsistent(newS)) System.out.println("new extends old");
        else System.out.println("new does not extend old");
    }

    @Test
    public void test2() {
        //1214.log is a log that was logged until December 14th
        LogServer oldS = new LogServer("data/1214.log");
        //1215.log is a log that was logged until December 15th
        LogServer newS = new LogServer("data/1215.log");
        Auditor b = new Auditor(oldS);
        if (b.isConsistent(newS)) System.out.println("new extends old");
        else System.out.println("new does not extend old");
    }

    @Test
    public void test3() {
        LogServer oldS = new LogServer("data/1214.log");
        LogServer newS = new LogServer("data/1214_tamper.log");
        Auditor b = new Auditor(oldS);
        if (b.isConsistent(newS)) System.out.println("new extends old");
        else System.out.println("new does not extend old");
    }
}

import java.util.Timer;
import java.util.concurrent.Callable;

/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/19 7:38 ÏÂÎç
 */
public class doubleThreadPrint {
    public doubleThreadPrint() {
    }

    public static void main(String[] args) {
        doubleThreadPrint demo = new doubleThreadPrint();
        demo.printStr();
    }


    public void printStr() {
        long startTimeMain = System.nanoTime();
        String characterStr = "abcdefghijklmnopqrstuvwxyz";
        String result = "";
        for (int i = 0; i < characterStr.length(); i++) {
            char c = characterStr.charAt(i);
            threadLow low = new threadLow(c);
            threadUp up = new threadUp(c);
            try {
                char templow = low.call();
                result += String.valueOf(templow);
                char temphigh = up.call();
                result += String.valueOf(temphigh);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTimeMain = System.nanoTime();
        long usedTimeMain = (endTimeMain - startTimeMain) / 1000;
        System.out.println(result);
        System.out.println("×ÜºÄÊ±(Î¢Ãë)=" + usedTimeMain);
    }

}

class threadLow implements Callable {

    char c;

    public threadLow(char c) {
        this.c = c;
    }

    @Override
    public Character call() throws Exception {
        return Character.isLowerCase(c) ? c : Character.toLowerCase(c);
    }
}

class threadUp implements Callable {
    char c;

    @Override
    public Character call() throws Exception {
        return Character.isUpperCase(c) ? c : Character.toUpperCase(c);
    }

    public threadUp(char c) {
        this.c = c;
    }
}
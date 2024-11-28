package thread.example.join;

import java.math.BigInteger;

public class JoiningThreadsTest {

    public static void main(String[] args) {
        JoiningThreadsTest jtt = new JoiningThreadsTest();
        BigInteger base1 = new BigInteger("2");
        BigInteger power1 = new BigInteger("10");
        BigInteger base2 = new BigInteger("3");
        BigInteger power2 = new BigInteger("20");

        BigInteger result = jtt.calculateResult(base1, power1, base2, power2);
        System.out.println(result);
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        BigInteger result;
        PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);

        thread1.start();
        thread2.start();

        try {
            thread1.join(); // 스레드1 선행 대기
            thread2.join(); // 스레드2 선행 대기
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = thread1.getResult().add(thread2.getResult()); // 스레드1 + 스레드2
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for (BigInteger i = BigInteger.ZERO;
                 i.compareTo(power) != 0;
                 i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}

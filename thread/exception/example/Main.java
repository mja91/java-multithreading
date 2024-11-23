package thread.exception.example;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> { // 스레드 생성 및 실행할 코드 정의
            throw new RuntimeException("Intentional Exception");
        });

        // 스레드 이름 설정
        thread.setName("Misbehaving thread");

        // 스레드에서 발생한 예외를 처리하기 위한 핸들러 설정
        thread.setUncaughtExceptionHandler((t, e) -> System.out.println("A Critical error happened in thread " + t.getName() + " the error is " + e.getMessage()));

        // 스레드 시작
        thread.start();

        /*
        * 실행 결과(Console)
        * A Critical error happened in thread Misbehaving thread the error is Intentional Exception
         */
    }
}

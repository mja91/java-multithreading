package thread.example.creation;

/**
 * Threads Creation - Thread Capabilities
 */
public class ThreadCreation {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> { // 스레드 생성 및 실행할 코드 정의
            System.out.println("We are now in thread " + Thread.currentThread().getName());
            System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
        });

        // 스레드 이름 설정
        thread.setName("New Worker Thread");

        // 스레드 우선순위 설정 (10 : 최우선)
        thread.setPriority(Thread.MAX_PRIORITY);

        // 신규 스레드 실행 전 메인 스레드에서 실행 중인 스레드 정보 출력
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");

        // 새 스레드 시작
        thread.start();

        // 신규 스레드 실행 후 메인 스레드에서 실행 중인 스레드 정보 출력
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        // 메인 스레드 10초 대기
        Thread.sleep(10000);

        /*
        * 실행 결과(Console)
        * We are in thread: main before starting a new thread
        * We are in thread: main after starting a new thread
        * We are now in thread New Worker Thread
        * Current thread priority is 10
         */
    }
}

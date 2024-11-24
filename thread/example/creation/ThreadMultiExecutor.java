package thread.example.creation;

import java.util.ArrayList;
import java.util.List;

public class ThreadMultiExecutor {
    private final List<Runnable> tasks;

    /**
     * @param tasks to executed concurrently
     */
    public ThreadMultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        List<Thread> threads = new ArrayList<>();
        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // 스레드 동작 순서 보장
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted : " + thread.getName());
            }
        }
    }

    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(new TaskThread1());
        tasks.add(new TaskThread2());

        ThreadMultiExecutor executor = new ThreadMultiExecutor(tasks);
        executor.executeAll();

        System.out.println("Main thread finished");
    }

    public static class TaskThread1 implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from new thread1");
        }
    }

    public static class TaskThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from new thread2");
        }
    }
}

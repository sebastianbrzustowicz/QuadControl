package org.sebastianbrzustowicz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // Inicjalizacja asynchronicznego listenera do websocketa
        WebSocketListener webSocketListener = new WebSocketListener();
        webSocketListener.startListening();

        // Inicjalizacja ScheduledExecutorService dla wątku obliczeń co 2 ms
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new ComputationTask(), 0, 2, TimeUnit.MILLISECONDS);
    }

    static class WebSocketListener {
        // Implementacja asynchronicznego listenera do websocketa
        public void startListening() {
            // Tu implementuj logikę nasłuchiwania na zdarzenia z websocketa
            // Na przykład, korzystając z biblioteki do obsługi WebSocket (np. Jetty WebSocket API).
            // CompletableFuture.runAsync(() -> {
            //    while (true) {
            //        // Tu obsługuj zdarzenia z websocketa asynchronicznie
            //    }
            // });
            // Pamiętaj o odpowiednim obsłużeniu błędów i zamykaniu zasobów.
        }
    }

    static class ComputationTask implements Runnable {
        private int i = 0;
        @Override
        public void run() {
            // Kod obsługujący obliczenia na danych co 2 ms
            i += 1;
            System.out.println("Wartość zmiennej i: " + i);
            //System.out.println("Wykonywanie obliczeń na danych...");
        }
    }
}
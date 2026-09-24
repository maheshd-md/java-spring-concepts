package com.java.concurrency;

import java.util.concurrent.atomic.AtomicReference;

record PortfolioState(long cash, long position) {
}

final class Portfolio {
    private final AtomicReference<PortfolioState> state =
            new AtomicReference<
                    >(new PortfolioState(100000, 0));

    boolean buy(long price, long quantity) {
        for (;;) {
            PortfolioState old = state.get();
            long cost = price * quantity;
            if (old.cash() < cost) return false;
            PortfolioState next = new PortfolioState(
                    old.cash() - cost, old.position() + quantity);
            if (state.compareAndSet(old, next)) return true;
        }
    }
}

public class AtomicReferenceDemo {

    public static void main(String[] args) {

        Portfolio portfolio = new Portfolio();

        Runnable buyerTask = () -> {
            boolean success = portfolio.buy(100, 1000);
            System.out.println(
                    Thread.currentThread().getName()
                            + " buy result: "
                            + success
            );
        };

        Thread t1 = new Thread(buyerTask, "Buyer-1");
        Thread t2 = new Thread(buyerTask, "Buyer-2");
        Thread t3 = new Thread(buyerTask, "Buyer-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
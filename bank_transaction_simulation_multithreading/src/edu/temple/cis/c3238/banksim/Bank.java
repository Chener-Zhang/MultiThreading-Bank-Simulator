package edu.temple.cis.c3238.banksim;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 * @author Modified by Alexa Delacenserie
 * @author Modified by Tarek Elseify
 */

public class Bank {

    public static final int NTEST = 10;
    public final Account[] accounts;
    private long numTransactions = 0;
    private final int initialBalance;
    private final int numAccounts;

    //Create the lock variable  ------->
    public final ReentrantLock lock = new ReentrantLock(); // lock the code
    public boolean open = true;
    //Create the lock variable  ------->


    public Bank(int numAccounts, int initialBalance) throws InterruptedException {
        this.initialBalance = initialBalance;
        this.numAccounts = numAccounts;
        accounts = new Account[numAccounts];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, initialBalance, this);
        }
        numTransactions = 0;
    }

    public void transfer(int from, int to, int amount) throws InterruptedException {
        // check whether has enough money
        accounts[from].waitForSufficientFunds(amount);

        lock.lock();

        if (accounts[from].withdraw(amount)) {
            accounts[to].deposit(amount);
            System.out.println("From Account : " + from + " to " + to + ": " + "$" + amount + " the current thread is " + Thread.currentThread().toString() + " success transfer");
        }

        lock.unlock();


        // lock this area
        if (shouldTest()) { //if there is NTEST numbers of transaction; do the test
            lock.lock();
            Test t = new Test(accounts);
            t.trigger = 1;
            t.start();
            t.join();
            lock.unlock();
        }
        // if not, force the thread to wait
    }


    public void closeBank() {
        synchronized (this) {
            open = false;
        }
        for (Account account : accounts) {
            synchronized (account) {
                account.notifyAll();
            }
        }
    }

    public synchronized boolean isOpen() {
        return open;
    }

    public int getNumAccounts() {
        return numAccounts;
    }

    public boolean shouldTest() {
        return ++numTransactions % NTEST == 0;
    }

}

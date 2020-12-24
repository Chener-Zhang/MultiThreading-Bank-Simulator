package edu.temple.cis.c3238.banksim;

/**
 * @author Cay Horstmann
 * @author Modified by Paul Wolfgang
 * @author Modified by Charles Wang
 * @author Modified by Alexa Delacenserie
 * @author Modified by Tarek Elseify
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final ReentrantLock lock = new ReentrantLock();
    private volatile int balance;
    private final int id;
    public Bank myBank;

    public Account(int id, int initialBalance, Bank myBank) {
        this.id = id;
        this.balance = initialBalance;
        this.myBank = myBank;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized boolean withdraw(int amount) {

        if (amount <= balance) {
            int currentBalance = balance;
            // Thread.yield(); // Try to force collision
            int newBalance = currentBalance - amount;
            balance = newBalance;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void deposit(int amount) {
        int currentBalance = balance;
        //Thread.yield();   // Try to force collision
        int newBalance = currentBalance + amount;
        balance = newBalance;
        notifyAll();
    }

    // If amount > balance, wait till the account has money;
    public synchronized void waitForSufficientFunds(int amount) throws InterruptedException {
        while (myBank.isOpen() && amount >= balance) {
            wait();
        }
    }


    @Override
    public String toString() {
        return String.format("Account[%d] balance %d", id, balance);
    }
}

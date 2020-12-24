package edu.temple.cis.c3238.banksim;

public class Test extends Thread {

    Account[] accounts;
    int initialBalance = 10000;
    int numAccounts = 10;
    int trigger = 0;

    public Test(Account[] accounts) {
        this.accounts = accounts;
    }

    @Override
    public void run() {
        if (trigger == 0) {
            //do nothing
        } else {
            int totalBalance = 0;
            for (Account account : accounts) {
                System.out.printf("%-30s %s%n", Thread.currentThread().toString(), account.toString());
                totalBalance += account.getBalance();
            }
            System.out.printf("%-30s Total balance: %d\n", Thread.currentThread().toString(), totalBalance);
            // needs lock
            if (totalBalance != numAccounts * initialBalance) {
                System.out.printf("%-30s Total balance changed!\n", Thread.currentThread().toString());
                System.exit(0);
            } else {
                System.out.printf("%-30s Total balance unchanged.\n", Thread.currentThread().toString());
            }
            trigger = 0;
        }

    }

}

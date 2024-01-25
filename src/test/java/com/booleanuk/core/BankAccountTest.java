package com.booleanuk.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class BankAccountTest {

    @Test
    void generateStatementAcceptanceTest() {
        BankAccount bankAccount = new BankAccount("Savings");

        bankAccount.deposit(1000, "10-01-2012");
        bankAccount.deposit(2000, "13-01-2012");
        bankAccount.withdraw(500, "14-01-2012");

        String actualStatement = bankAccount.generateStatement();

        String expectedStatement = "date       || credit  || debit  || balance\n" +
                "14/01/2012 ||  || 500.00 || 2500.00\n" +
                "13/01/2012 || 2000.00 ||  || 3000.00\n" +
                "10/01/2012 || 1000.00 ||  || 1000.00";

        assertArrayEquals(expectedStatement.split("\n"), actualStatement.split("\n"));
    }
    @Test
    public void createBankAccount() {
        BankAccount bankAccount = new BankAccount("Current");
        assertNotNull(bankAccount);
        assertEquals("Current", bankAccount.getAccountType());
        assertEquals(0.0, bankAccount.getBalance());
    }
    @Test
    public void depositToBankAccount() {
        BankAccount bankAccount = new BankAccount("Current");
        bankAccount.deposit(500.0, "01-01-2023");
        assertEquals(500.0, bankAccount.getBalance());
    }
    @Test
    public void withdrawFromBankAccount() {
        BankAccount bankAccount = new BankAccount("Current");
        bankAccount.deposit(1000.0, "01-01-2023");
        bankAccount.withdraw(500.0, "02-01-2023");
        assertEquals(500.0, bankAccount.getBalance());
    }
    @Test
    public void createTransaction() {
        Transaction transaction = new Transaction("01-01-2023", "Deposit", 500.0);
        assertNotNull(transaction);
        assertEquals("01/01/2023", transaction.getDate());
        assertEquals("Deposit", transaction.getType());
        assertEquals(500.0, transaction.getAmount());
    }
}


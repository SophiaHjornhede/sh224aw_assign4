package stack;

import bankAccount.BankAccount;

/**
 * Assignment-4, exercise-2
 *
 * @author Sophia Hjörnhede
 * @version 1.11 10 Aug 2020
 */

import java.util.Arrays;

public class StackImpl implements Stack {
    BankAccount[] bankAccounts = new BankAccount[2];

    // Constructor
    public StackImpl() {
    }

    @Override
    public int size() {
        // Checking array for amount of non null elements
        int counter = 0;
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount != null) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return bankAccounts[0] == null;
    }

    @Override
    public void push(BankAccount account) {
        // if the array is full, make a bigger one and replace the old one
        if (bankAccounts[bankAccounts.length - 1] != null) {
            // Making bigger array
            bankAccounts = makeArrayBigger(bankAccounts);
        }

        // Place new account in the first spot that contains null value.
        for (int i = 0; i < bankAccounts.length; i++) {
            if (bankAccounts[i] == null) {
                bankAccounts[i] = account;
                break;
            }
        }
    }

    private BankAccount[] makeArrayBigger(BankAccount[] bankAccounts) {
        // Making a new account array 2 times bigger
        return Arrays.copyOf(bankAccounts, bankAccounts.length * 2);
    }

    @Override
    public BankAccount pop() {
        // Checking if array is empty

        if (isEmpty()) {
              //System.out.println("Error! There is no account in the stack! (Null pointer exception)");
              throw new ArrayIndexOutOfBoundsException();
         }
        try{
            int i = bankAccounts.length - 1;
            BankAccount baTemp;  // temporary bank account
            // finding the last element, deletes and returns it from array
            while (i >= 0) {
                if (bankAccounts[i] != null) {
                    baTemp = bankAccounts[i];
                    bankAccounts[i] = null;
                    return baTemp;
                }
                i--;
            }
            //throw new NullPointerException();
        }catch (Exception ignored){
        }
        finally {
            return new BankAccount("The account at the top is removed.",0);
        }
    }

    @Override
    public BankAccount peek() {
        // Checking if array is empty
        // This way avoids throwing an actual exception and just returns a default element
        // called error if the array is empty.
        if (isEmpty()) {
            System.out.println("Error! There is no account in the stack! (Null pointer exception)");
            BankAccount bankAccount=new BankAccount("Error",0);
            throw new ArrayIndexOutOfBoundsException();
            //return bankAccount;

        }

        int i = bankAccounts.length - 1;
        // return bankAccounts[1];
        // finding last element in array and returns it
        while (i >= 0) {
            if (bankAccounts[i] != null) {
                return bankAccounts[i];
            }
            i--;
        }
        // Need for a return value
        throw new NullPointerException();
    }
}

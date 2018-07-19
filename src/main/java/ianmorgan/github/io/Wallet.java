package ianmorgan.github.io;

import java.util.Collection;

interface Wallet {

    /**
     * Load money into my wallet (fixed sized denominations of any kind are acceptable)
     * (assume that [filename] is readable from the classpath)
     */
    void load(String filename);

    /**
     * Return the coins in the wallet.
     * (peek does NOT need to be performant as to be used solely for testing)
     */
    Collection<Long> peek();

    /**
     * Spend a [quantity] of money from Wallet and ensure the wallet is updated
     * (any change should be added back to the wallet as a single amount)
     * SpendSummary should include the collection of coins spent and a single change amount
     */
    SpendSummary spend(Long spendAmount);

    /**
     * Return current balance of my wallet
     */
    Long getBalance();

    class SpendSummary {
        Long change;
        Collection<Long> coinsSpent;
    }

}

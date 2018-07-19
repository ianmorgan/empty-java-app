package ianmorgan.github.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WalletImpl implements Wallet {

    private List<Long> coins = new ArrayList();
    @Override
    public void load(String filename) {

            String content = "";
            try
            {
                content = new String ( Files.readAllBytes( Paths.get(filename) ) );

                for (String coin : content.split(",")){
                    coins.add (Long.parseLong(coin));
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }

    @Override
    public Collection<Long> peek() {
        return coins;
    }

    @Override
    public SpendSummary spend(Long spendAmount) {
        long amountSpent = 0;
        List<Long> coinsSpent = new ArrayList<>();
        List<Long> working = new ArrayList<>(coins);

        for (long c : working){
            amountSpent += c;
            coinsSpent.add(c);

            coins.remove(c);
            if (amountSpent >= spendAmount){
                break;
            }
        }

        if (amountSpent < spendAmount){
            coins = working;
            throw new RuntimeException("You are too poor!");
        }

        // remove the coins used
        long change = amountSpent - spendAmount;
        if (change >0){
            coins.add(change);
        }

        SpendSummary result = new SpendSummary();
        result.change = change;
        result.coinsSpent = coinsSpent;


        return result;
    }

    @Override
    public Long getBalance() {
        long total = 0;
        for (long c : coins){
            total += c;
        }
        return total;
    }
}

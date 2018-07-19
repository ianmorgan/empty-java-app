package ianmorgan.github.io

import spock.lang.Specification

class WalletSpec extends Specification {

    def "should load coins " () {
        expect:

        def wallet = new WalletImpl()
        wallet.load("src/main/resources/coins.txt")
        wallet.peek() == [1,100,200,200,1000,1]

    }

    def "should get balance" () {
        expect:
        def wallet = new WalletImpl()
        wallet.load("src/main/resources/coins.txt")
        wallet.getBalance() == (long)(1+100+200+200+1000+1)
    }


    def "should buy a very cheap coffee" () {
        expect:

        def wallet = new WalletImpl()
        wallet.load("src/main/resources/coins.txt")

        def spend = wallet.spend(1)

        spend.change == 0
        spend.coinsSpent ==[1L]
        wallet.balance == (long)(100+200+200+1000+1)
    }


    def "should buy an expensive coffee" () {
        expect:

        def wallet = new WalletImpl()
        wallet.load("src/main/resources/coins.txt")
        def originalBalance = wallet.balance


        try {
            wallet.spend(2000)
        }
        catch (RuntimeException ex){
            1 == 1
        }
        wallet.balance == originalBalance

    }

    def "should buy a costa coffee" () {
        expect:

        def wallet = new WalletImpl()
        wallet.load("src/main/resources/coins.txt")

        def spend = wallet.spend(300)

        spend.change == 1
        spend.coinsSpent ==[1L,100L,200L]
        wallet.balance == (long)(1+200+1000+1)
    }
}

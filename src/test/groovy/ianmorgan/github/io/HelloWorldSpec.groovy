package ianmorgan.github.io

import spock.lang.Specification

class HelloWorldSpec extends Specification{

    def systemOut
    ByteArrayOutputStream myOut

    def setup (){
        systemOut = System.out
        myOut = new ByteArrayOutputStream()
        System.setOut(new PrintStream(myOut))
    }

    def cleanup() {
        System.setOut(systemOut)
    }

    def "should include name if provided"(){
        when:
        String[] args = ['Ian']
        HelloWorld.main(args)

        then:
        myOut.toString() == "Hello, Ian\r\n"

    }

    def "should generate default message if no name"(){
        when:
        String[] args = []
        HelloWorld.main(args)

        then:
        myOut.toString() == "Hello World\r\n"
    }

}
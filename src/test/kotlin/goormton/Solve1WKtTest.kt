package goormton

import autoInput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Solve1WKtTest {


    @Test
    @DisplayName("11 45")
    fun 테스트1() {
        //given
        autoInput(
            "3\n" +
                    "10 10\n" +
                    "50\n" +
                    "22\n" +
                    "23\n"
        )
        //when
        main0815(emptyArray())
        //then
        println("\n 나와야한다 11 45")
    }


    @Test
    @DisplayName("0 0")
    fun Test2() {
        //given
        autoInput(
            "4\n" +
                    "23 40\n" +
                    "1000\n" +
                    "1000\n" +
                    "880\n" +
                    "20"
        )
        //when
        main0815(emptyArray())
        //then
        println("\n나와야한다 0 0")
    }


}


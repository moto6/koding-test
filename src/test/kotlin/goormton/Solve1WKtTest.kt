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
    @DisplayName("데이터셋만들기")
    fun 데이터셋만들기() {
        //given

        //when

        //then
        for (i in 1..50000) {
            print(i)
            print(" ")
        }
    }


    @Test
    @DisplayName("0 0")
    fun Test2() {
        //given
        autoInput(
            ""
        )
        //when
        main0815(emptyArray())
        //then
        println("\n나와야한다 0 0")
    }


    // 0818 테스트


    @Test
    @DisplayName("0818 V1 효율성을 통과하지 못하는")
    fun test0818V1() {
        //given
        autoInput(
            ""
        )

        //when
        main0818V1(emptyArray())
        //then
    }

    @Test
    @DisplayName("0818 V2 효윯성을 통과하는")
    fun test0818V2() {
        //given
        autoInput(
            ""
        )
        //when
        main0818V1(emptyArray())
        //then
    }
}

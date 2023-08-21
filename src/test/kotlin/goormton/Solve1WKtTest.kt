package goormton

import autoInput
import org.junit.jupiter.api.*
import java.io.File
import java.io.FileWriter
import java.util.Locale
import java.lang.System as System1

class Solve1WKtTest {

    var executionTime:Long = System1.currentTimeMillis()
    val times:MutableList<Long> = mutableListOf()

    @BeforeEach
    fun beforeEach() {
        executionTime = System1.currentTimeMillis()
    }

    @AfterEach
    fun afterEach() {
        val durationTime = System1.currentTimeMillis() - executionTime
        println("\n수행시간 = $durationTime")
        times.add(durationTime)
    }

    @Disabled
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

    @Disabled
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

    @RepeatedTest(20)
    @DisplayName("0818 V1 효율성을 통과하지 못하는")
    fun test0818V1() {

        //given
        autoInput(
            readFromFile()
        )
        //when
        //then
        main0818V1(emptyArray())
    }

    @RepeatedTest(20)
    @DisplayName("0818 V2 효윯성을 통과하는")
    fun test0818V2() {
        executionTime = System1.currentTimeMillis()
        //given
        autoInput(
            readFromFile()
        )
        //when
        //then
        main0818V2(emptyArray())
    }

    @Test
    @DisplayName("")
    fun datasetGenerate() {
        val file = File(System1.getProperty("user.dir") + "/data.txt")
        val fileWriter = FileWriter(file)
        val count = 500000
        fileWriter.write("$count 33\n")
        for (i in 1..count) {
            fileWriter.write(i.toString())
            fileWriter.write(" ")
        }
        fileWriter.flush()
    }

    fun readFromFile(): String {
        return File(System1.getProperty("user.dir") + "/data.txt").readText()
    }
}

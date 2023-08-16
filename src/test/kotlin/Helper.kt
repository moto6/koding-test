import java.io.ByteArrayInputStream

fun autoInput(automatedInput : String) {
    System.setIn(ByteArrayInputStream(automatedInput.toByteArray()))
}

class Helper {

}

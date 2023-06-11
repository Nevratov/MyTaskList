import kotlin.system.exitProcess

fun main() {
    val taskList = TaskList()

    while (true) {
        println("Input an action (add, print, end):")
        when (readln().lowercase()) {
            "add" -> taskList.addTask()
            "print" -> taskList.printTaskList()
            "end" -> { print("Tasklist exiting!"); exitProcess(0) }
            else -> println("The input action is invalid")
        }
    }
}
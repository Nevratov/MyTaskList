import kotlin.system.exitProcess

fun main() {
    val taskList = TaskList()

    while (true) {
        println("Input an action (add, print, edit, delete, end):")
        when (readln().lowercase()) {
            "add" -> taskList.addTask()
            "print" -> taskList.printTaskList()
            "edit" -> taskList.edit()
            "delete" -> taskList.deleteTask()
            "end" -> { print("Tasklist exiting!"); exitProcess(0) }
            else -> println("The input action is invalid")
        }
    }
}
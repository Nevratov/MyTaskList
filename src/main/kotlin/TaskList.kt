import kotlin.system.exitProcess

class TaskList(val tasks: ArrayList<ArrayList<String>> = arrayListOf()) {


    fun action() {
        while (true) {
            println("Input an action (add, print, end):")
            when (readln().lowercase()) {
                "add" -> addTask()
                "print" -> printTaskList()
                "end" -> { print("Tasklist exiting!"); exitProcess(0) }
                else -> println("The input action is invalid")
            }
        }
    }

    fun addTask() {
        val arrayTask = arrayListOf<String>()
        println("Input a new task (enter a blank line to end):")
        do {
            val task = readln().trimIndent()
            if (task == "" && arrayTask.isEmpty()) {
                println("The task is blank")
                return
            } else if (task != "") arrayTask.add(task)
            else tasks.add(arrayTask)
        } while (task != "")
    }

    fun printTaskList() {
        if (tasks.isNotEmpty()) {
            for (i in tasks.indices) {
                if (i <= 8) println("${i + 1}  ${tasks[i][0]}")
                else println("${i + 1} ${tasks[i][0]}")

                for (j in tasks[i].indices) {
                    if (j > 0 && i <= 8) println("   ${tasks[i][j]}")
                    else if (j > 0) println("  ${tasks[i][j]}")
                }
                println()
            }
        } else println("No tasks have been input")
    }

}
fun main() {
    println("Input the tasks (enter a blank line to end):")
    val taskList = arrayListOf<String>()
    do {
        val task = readln().trimIndent()
        if (task != "") taskList.add(task)
    } while (task != "")

    if (taskList.size <= 1) {
        println("No tasks have been input")
    } else {
        for (i in taskList.indices) {
            if (i <= 8) println("${i + 1}  ${taskList[i]}")
            else println("${i + 1} ${taskList[i]}")
        }
    }
}
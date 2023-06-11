import kotlinx.datetime.*

class TaskList(private val tasks: ArrayList<ArrayList<String>> = arrayListOf()) {

    fun addTask() {
        val arrayTask = arrayListOf<String>()
        val priority = setPriorityTask()
        val date = setDeadlineTask()
        val time = setTimeTask()

        arrayTask.add("$date $time $priority")

        println("Input a new task (enter a blank line to end):")
        do {
            val task = readln().trimIndent()
            if (task == "" && arrayTask.size == 1) {
                println("The task is blank")
                return
            } else if (task != "") arrayTask.add(task)
            else tasks.add(arrayTask)
        } while (task != "")
    }

    private fun setPriorityTask(): String {
        var priority: String
        do {
            println("Input the task priority (C, H, N, L):")
            priority = readln().trimIndent().uppercase()
        } while (priority !in arrayOf("C", "H", "N", "L"))
        return priority
    }

    private fun setDeadlineTask(): String {
        do {
            println("Input the date (yyyy-mm-dd):")
            val inputDate = readln().trimIndent().split("-")
            val date: LocalDate

            try {
                date = LocalDate(inputDate[0].toInt(), inputDate[1].toInt(), inputDate[2].toInt())
                return date.toString()
            } catch (e: Exception) {
                println("The input date is invalid")
            }
        } while (true)
    }

    private fun setTimeTask(): String {
        do {
            println("Input the time (hh:mm):")
            val inputTime = readln().trimIndent().split(":")
            val time: LocalTime

            try {
                time = LocalTime(inputTime[0].toInt(), inputTime[1].toInt())
                return time.toString()
            } catch (e: Exception) { println("The input time is invalid") }
        } while (true)
    }

    fun printTaskList() {
        if (tasks.isNotEmpty()) {
            for (i in tasks.indices) {
                if (i <= 8) println("${i + 1}  ${tasks[i][0]}")
                else println("${i + 1} ${tasks[i][0]}")

                for (j in tasks[i].indices) {
                    if (j > 0 && i <= 8) println("   ${tasks[i][j]}")
                    else if (j > 0) println("   ${tasks[i][j]}")
                }
                println()
            }
        } else println("No tasks have been input")
    }
}
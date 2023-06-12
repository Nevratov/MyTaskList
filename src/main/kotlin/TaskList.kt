import kotlinx.datetime.*

class TaskList(private val tasks: ArrayList<ArrayList<String>> = arrayListOf()) {

    fun addTask() {
        val arrayTask = arrayListOf<String>()
        arrayTask.add(setFirstInfoLine())
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

    private fun setFirstInfoLine(): String {
        val priority = setPriorityTask()
        val date = setDeadlineTask()
        val time = setTimeTask()
        val dueTag = setDueTag(date.toLocalDate())
       return "$date $time $priority $dueTag"
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
            } catch (e: Exception) { println("The input date is invalid") }
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

    private fun setDueTag(date: LocalDate): String {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date
        return if (currentDate.daysUntil(date) == 0) "T" else if (currentDate.daysUntil(date) > 0) "I" else "O"
    }

    fun deleteTask() {
        if (tasks.isEmpty()) println("No tasks have been input")
        else {
            printTaskList()
            do {
                println("Input the task number (1-${tasks.size}):")
                try {
                    tasks.removeAt(readln().toInt() - 1)
                    println("The task is deleted")
                    return
                } catch (e: Exception) { println("Invalid task number") }
            } while (true)
        }
    }

    fun edit() {
        if (tasks.isEmpty()) println("No tasks have been input")
        else {
            printTaskList()
            do {
                println("Input the task number (1-${tasks.size}):")
                try {
                    val numberTask = readln().toInt() - 1
                    tasks[numberTask] // checking the existence of a task
                    do {
                        println("Input a field to edit (priority, date, time, task):")
                        when (readln()) {
                            "date" -> editInfoLine(numberTask, "date")
                            "time" -> editInfoLine(numberTask, "time")
                            "priority" -> editInfoLine(numberTask, "priority")
                            "task" -> editTask(numberTask)
                            else -> { println("Invalid field"); continue }
                        }
                        println("The task is changed")
                        return
                    } while (true)
                } catch (e: Exception) { println("Invalid task number") }
            } while (true)
        }
    }

    private fun editTask(numberTask: Int) {
        val arrayTask = arrayListOf<String>()
        arrayTask.add(tasks[numberTask][0])
        println("Input a new task (enter a blank line to end):")
        do {
            val task = readln().trimIndent()
            if (task == "" && arrayTask.size == 1) {
                println("The task is blank")
                return
            } else if (task != "") arrayTask.add(task)
            else tasks[numberTask] = arrayTask
        } while (task != "")
    }

    private fun editInfoLine(numberTask: Int, operation: String) {
        val firstLine = tasks[numberTask][0].split(" ").toMutableList()
        when (operation) {
            "date" -> firstLine[0] = setDeadlineTask()
            "time" -> firstLine[1] = setTimeTask()
            else -> firstLine[2] = setPriorityTask()
        }
        firstLine[3] = setDueTag(firstLine[0].toLocalDate())
        tasks[numberTask][0] = firstLine.joinToString(" ")
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
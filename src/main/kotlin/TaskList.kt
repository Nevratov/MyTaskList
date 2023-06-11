import kotlinx.datetime.*
import java.time.Instant
import java.time.format.DateTimeParseException
import kotlin.system.exitProcess

class TaskList(private val tasks: ArrayList<ArrayList<String>> = arrayListOf()) {

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

    private fun addTask() {
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

            var finalDate = ""
            val inputDate = readln().trimIndent()

            val dateList = inputDate.split("-")

            if (dateList.size != 3) {
                println("The input date is invalid")
                continue
            } else {
                if (dateList[0].length == 4) finalDate += "${dateList[0]}-"
                finalDate += if (dateList[1].length == 2) "${dateList[1]}-" else "0${dateList[1]}-"
                finalDate += if (dateList[2].length == 2) dateList[2] else "0${dateList[2]}"
            }
            try {
                Instant.parse(finalDate + "T00:00:00Z")
                return finalDate
            } catch (e: DateTimeParseException) {
                println("The input date is invalid")
            }
        } while (true)
    }

    private fun setTimeTask(): String {
        do {
            println("Input the time (hh:mm):")

            var finalTime = ""
            val inputDate = readln().trimIndent()

            val timeList = inputDate.split(":")

            if (timeList.size != 2) {
                println("The input time is invalid")
                continue
            } else {
                finalTime += if (timeList[0].length == 2 && timeList[0].toInt() in 0..23) "${timeList[0]}:" else "0${timeList[1]}:"
                finalTime += if (timeList[1].length == 2 && timeList[1].toInt() in 0..59) timeList[1] else "0${timeList[1]}"
            }

            try {
                Instant.parse("1999-08-30" + "T${finalTime}:00Z")
                return finalTime
            } catch (e: DateTimeParseException) {
                println("The input time is invalid")
            }
        } while (true)
    }

    private fun printTaskList() {
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
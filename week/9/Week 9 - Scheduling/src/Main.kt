import java.io.File

/*
Question 1

In this programming problem and the next you'll code up the greedy algorithms from lecture for minimizing the weighted
sum of completion times..

This file describes a set of jobs with positive and integral weights and lengths.  It has the format

[job_1_weight] [job_1_length]
[job_2_weight] [job_2_length]

For example, the third line of the file is "74 59", indicating that the second job has weight 74 and length 59.

You should NOT assume that edge weights or lengths are distinct.

Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the difference
(weight - length).  Recall from lecture that this algorithm is not always optimal.  IMPORTANT: if two jobs have equal
difference (weight - length), you should schedule the job with higher weight first.  Beware: if you break ties in a
different way, you are likely to get the wrong answer.  You should report the sum of weighted completion times of the
resulting schedule --- a positive integer --- in the box below.

ADVICE: If you get the wrong answer, try out some small test cases to debug your algorithm (and post your test cases to
the discussion forum).

// 69119377652

Question 2

For this problem, use the same data set as in the previous problem.

Your task now is to run the greedy algorithm that schedules jobs (optimally) in decreasing order of the ratio
(weight/length).  In this algorithm, it does not matter how you break ties.  You should report the sum of weighted
completion times of the resulting schedule --- a positive integer --- in the box below.

// 67311454237

 */

const val filename = "jobs.txt"

fun main() {
//    val jobs = listOf(
//        Job(1,9),
//        Job(9,1),
//        Job(3,3),
//        Job(4,2)
//    )
    val jobs = readInput(filename)

    val s = jobs.sortedWith(JobComparatorRatio)
//    s.forEachIndexed { index, job -> println("${index+1} -> $job") }

//    s.forEach { println("$it") }

    val wct = weightedCompletionTime(s)
    println("Total weighted completion time: $wct")
}

fun weightedCompletionTime(jobs: Collection<Job>): Long {
    var total = 0L
    var time = 0

    jobs.forEach {
        time += it.length
        val weightedCompletionTime = time * it.weight
        total += weightedCompletionTime
    }

    return total
}

fun readInput(filename: String): List<Job> {
    val result = mutableListOf<Job>()

    File(filename).forEachLine {
        val (wt, len) = it.split(' ')
        result.add(Job(wt.toInt(), len.toInt()))
    }

    return result
}

object JobComparatorDifference: Comparator<Job> {

    override fun compare(o1: Job, o2: Job): Int {
        val diffO1 = o1.weight - o1.length
        val diffO2 = o2.weight - o2.length

        return if (diffO1 != diffO2) {
            diffO1.compareTo(diffO2) * -1 // Descending order
        } else {
            o1.weight.compareTo(o2.weight) * -1 // Descending order
        }
    }
}

object JobComparatorRatio: Comparator<Job> {

    override fun compare(o1: Job, o2: Job): Int {
        val ratioO1 = o1.weight.toDouble() / o1.length.toDouble()
        val ratioO2 = o2.weight.toDouble() / o2.length.toDouble()

        return if (ratioO1 != ratioO2) {
            ratioO1.compareTo(ratioO2) * -1 // Descending order
        } else {
            o1.weight.compareTo(o2.weight) * -1 // Descending order
        }
    }
}

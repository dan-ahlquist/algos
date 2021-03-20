import twoSat.Clause
import twoSat.Instance
import java.io.File
import kotlin.math.abs

/*
In this assignment you will implement one or more algorithms for the 2SAT
problem.  Here are 6 different 2SAT instances:

2sat1.txt
2sat2.txt
2sat3.txt
2sat4.txt
2sat5.txt
2sat6.txt

Header removed by me: 100000
The file format is as follows.  In each instance, the number of variables and
the number of clauses is the same, and this number is specified on the first
line of the file.  Each subsequent line specifies a clause via its two literals,
with a number denoting the variable and a "-" sign denoting logical "not".  For
example, the second line of the first data file is "-16808 75250", which
indicates the clause ¬x_16808 ∨ x_75250.

Your task is to determine which of the 6 instances are satisfiable, and which
are unsatisfiable.  In the box below, enter a 6-bit string, where the ith bit
should be 1 if the ith instance is satisfiable, and 0 otherwise.  For example,
if you think that the first 3 instances are satisfiable and the last 3 are not,
then you should enter the string 111000 in the box below.

DISCUSSION: This assignment is deliberately open-ended, and you can implement
whichever 2SAT algorithm you want.  For example, 2SAT reduces to computing the
strongly connected components of a suitable graph (with two vertices per
variable and two directed edges per clause, you should think through the
details).  This might be an especially attractive option for those of you who
coded up an SCC algorithm in Part 2 of this specialization.  Alternatively, you
can use Papadimitriou's randomized local search algorithm.  (The algorithm from
lecture is probably too slow as stated, so you might want to make one or more
simple modifications to it --- even if this means breaking the analysis given in
lecture --- to ensure that it runs in a reasonable amount of time.)  A third
approach is via backtracking.  In lecture we mentioned this approach only in
passing; see Chapter 9 of the Dasgupta-Papadimitriou-Vazirani book, for example,
for more details.
 */

const val filename = "2sat1.txt"

fun main() {
    val instance = readInput(filename)
    println(instance)
}

fun readInput(filename: String): Instance {
    val vars = mutableSetOf<Int>()
    val clauses = mutableSetOf<Clause>()

    File(filename).forEachLine {
        val (a, b) = it.split(' ').map(String::toInt)

        val var1 = abs(a)
        val cond1 = a < 0
        vars.add(var1)

        val var2 = abs(b)
        val cond2 = b < 0
        vars.add(var2)

        clauses.add(Clause(var1, cond1, var2, cond2))
    }

    return Instance(vars, clauses)
}

import planarGraph.*
import java.io.File
import java.util.*
import kotlin.Comparator

/*
Question 1
In this assignment you will implement one or more algorithms for the traveling
salesman problem, such as the dynamic programming algorithm covered in the video
lectures.  Here is a data file describing a TSP instance.

tsp.txt
The first line indicates the number of cities.  Each city is a point in the plane,
and each subsequent line indicates the x- and y-coordinates of a single city.

The distance between two cities is defined as the Euclidean distance --- that is,
two cities at locations (x,y) and (z,w) have distance sqrt{(x-z)^2 + (y-w)^2}
between them.

In the box below, type in the minimum cost of a traveling salesman tour for this
instance, rounded down to the nearest integer.

OPTIONAL: If you want bigger data sets to play with, check out the TSP instances
from around the world here.  The smallest data set (Western Sahara) has 29 cities,
and most of the data sets are much bigger than that.  What's the largest of these
data sets that you're able to solve --- using dynamic programming or, if you like,
a completely different method?

HINT: You might experiment with ways to reduce the data set size.  For example,
trying plotting the points.  Can you infer any structure of the optimal solution?
Can you use that structure to speed up your algorithm?

1 point

 */

const val filename = "tsp.txt"

fun main() {
    val points = readInput(filename)

//    val superSet = IntegerSet(1,2,3,4,5,6)
//    val intSet = IntegerSet(1,2,5)
//    val bitString = intSet.toInt(superSet)
//    println(Integer.toBinaryString(bitString).padStart(6,'0'))

    val G: PlanarGraph = LazyPlanarGraph(points)

    val tf: TourFinder = BellmanHeldKarp()

    val shortest = tf.findShortestTourLength(G)

    println("Shortest tour length = $shortest")
}

fun readInput(filename: String): SortedSet<Point> {
    val result = mutableSetOf<Point>()
    var label = 1
    File(filename).forEachLine {
        val (x, y) = it.split(' ').map(String::toDouble)
        result.add(Point(label++, x, y))
    }
    return result.toSortedSet { o1, o2 -> o1.label.compareTo(o2.label)}
}



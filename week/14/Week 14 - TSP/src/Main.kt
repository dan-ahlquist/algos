import planarGraph.*
import java.io.File
import java.util.*

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

    /*
     *   This data set contains 25 points, which as mentioned
     *   in lecture is well beyond the processing power of a PC.
     *   Therefore, as suggest on the course forums, we can
     *   identify a likely path and split it into 2. The files
     *   g1 & g2.txt contains points (1..13) and (12..25)
     *   from the original, respectively. That makes the edge
     *   (12, 13) redundant, so we subtract it twice at the end.
     */

    val points_g1 = readInput("g1.txt")
    val points_g2 = readInput("g2.txt")

    val g1: PlanarGraph = LazyPlanarGraph(points_g1)
    val g2: PlanarGraph = LazyPlanarGraph(points_g2)

    val tf: TourFinder = BellmanHeldKarp()

    val shortest_g1 = tf.findShortestTourLength(g1)
    println("G1 Shortest tour length = $shortest_g1")

    val shortest_g2 = tf.findShortestTourLength(g2)
    println("G2 Shortest tour length = $shortest_g2")

    val p11 = Point(11, 23883.3333, 14533.3333)
    val p12 = Point(12, 24166.6667, 13250.0000)
    val g3: PlanarGraph = LazyPlanarGraph(setOf(p11, p12))
    val d_11_12 = g3.distance(11, 12)
    val total = shortest_g1 + shortest_g2 - (2*d_11_12)
    println("Total shortest tour length = $total")
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



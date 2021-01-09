import java.io.File

/*
Question 2
In this question your task is again to run the clustering algorithm from lecture, but on a MUCH bigger graph.  So big,
in fact, that the distances (i.e., edge costs) are only defined implicitly, rather than being provided as an explicit list.

clustering_big.txt
 The format is:

### Deleted by me ### [# of nodes] [# of bits for each node's label]
[first bit of node 1] ... [last bit of node 1]
[first bit of node 2] ... [last bit of node 2]
...

For example, the third line of the file "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1" denotes the 24 bits associated
with node #2.

The distance between two nodes uu and vv in this problem is defined as the Hamming distance--- the number of differing
bits --- between the two nodes' labels.  For example, the Hamming distance between the 24-bit label of node #2 above and
the label "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1" is 3 (since they differ in the 3rd, 7th, and 21st bits).

The question is: what is the largest value of kk such that there is a kk-clustering with spacing at least 3?  That is,
how many clusters are needed to ensure that no pair of nodes with all but 2 bits in common get split into different clusters?

NOTE: The graph implicitly defined by the data file is so big that you probably can't write it out explicitly, let alone
sort the edges by cost.  So you will have to be a little creative to complete this part of the question.  For example,
is there some way you can identify the smallest distances without explicitly looking at every pair of nodes?

Tried: 7806, 7437, 6118

 */

const val filename = "clustering_big.txt"
const val width = 24

fun main() {
    val nodes = readInput()

    val k = Clusterizer().clusterize(nodes)
    println("k = $k")
}

private fun readInput(): Map<Int, Node> {

    File(filename).useLines {
        val result = mutableMapOf<Int, Node>()

        val checkSet = mutableSetOf<String>()
        var index = 0
        it.forEach { line ->
            val bits = line.split(' ')
                    .filterNot(String::isNullOrBlank)
                    .map(String::toInt)
            val newNode = Node(index, bits)
            val bitString = newNode.bitString
            if (!checkSet.contains(bitString)) {
                result[index] = newNode
                checkSet.add(bitString)
                index++
            }

            assert(bits.size == width)
                {"Declared width = $width but entry #$index has ${bits.size}!"}
        }

        println("Read in ${result.size} entries.")

        return result
    }
}

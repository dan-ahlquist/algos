
typealias Edge<T> = Pair<Node<T>, Node<T>>

class Node<T> (
    val data: T,
    var isExplored: Boolean = false,
    var finishingTime: Int = 0,
    var containingScc: Int = 0
) {
    val neighborsFrom = mutableSetOf<Node<T>>()
}

interface MutableDirectedGraph<T> {
    val nodeCount: Int
    fun getNodes(): Set<Node<T>>
    fun hasNode(a: T): Boolean
    fun hasEdge(a: T, b: T): Boolean
    fun getNeighborsFrom(a: Node<T>): Set<Node<T>>
    fun addNode(a: T)

    fun addEdge(a: T, b: T)

    fun resetExplored()
    fun reverse()

    fun print()
}
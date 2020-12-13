class Node (
    val label: Int,
    val edgesFrom: MutableSet<Edge> = mutableSetOf(),
    var shortestPath: Int = Int.MAX_VALUE
)
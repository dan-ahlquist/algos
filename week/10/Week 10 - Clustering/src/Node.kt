class Node (
    val label: Int,
    val edgesFrom: MutableSet<Edge> = mutableSetOf(),
)
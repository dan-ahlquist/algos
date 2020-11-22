interface MutableGraph<T> {
    fun hasNode(a: T): Boolean

    fun hasEdge(a: T, b: T): Boolean

    fun getNeighborsOf(a: T): List<T>

    fun addNode(a: T)

    fun addEdge(a: T, b: T)

    fun deleteNode(a: T): Boolean

    fun deleteEdge(a: T, b: T): Boolean

    fun contractEdge(a: T, b: T, g: T = a): Boolean

    fun print()
}
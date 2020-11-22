import java.lang.IllegalStateException
import java.lang.StringBuilder

class AdjacencyListUndirectedGraph<T>: MutableUndirectedGraph<T> {

    private val nodes: MutableMap<T, Node<T>> = mutableMapOf()

    override val vertexCount: Int
        get() = nodes.size

    override val edgeCount: Int
        get() {
            val edgeEndCount = nodes.values.map { it.getNeighbors().size }.sum()
            if (edgeEndCount % 2 != 0) throw IllegalStateException("Odd number of edge-ends!")
            return edgeEndCount / 2
        }

    override fun hasNode(a: T): Boolean {
        return nodes.contains(a)
    }

    override fun hasEdge (a: T, b: T): Boolean {
        return nodes.contains(a) && nodes[a]?.hasNeighbor(b) ?: false
    }

    override fun getNeighborsOf(a: T): List<T> {
        return nodes[a]?.getNeighbors() ?: emptyList()
    }

    override fun getRandomEdge(): Pair<T, T> {
        val node = nodes.values.random()

        val neighbors = node.getNeighbors()
        val neighbor = neighbors.random()

        return Pair(node.name, neighbor)
    }

    override fun addNode(a: T) {
        if (nodes.contains(a))
            throw IllegalStateException("This Graph already contains a node $a")
        else
            nodes[a] = Node(a)
    }

    override fun addEdge (a: T, b: T) {
        addNodeEnd(a, b)
        addNodeEnd(b, a)
    }

    private fun addNodeEnd(x: T, y: T) {
        if(nodes.contains(x)) {
            nodes[x]?.addNeighbor(y)
        } else {
            val new = Node(x)
            new.addNeighbor(y)
            nodes[x] = new
        }
    }

    override fun deleteNode(a: T): Boolean {
        val result = nodes.contains(a)

        nodes.remove(a)
        for (node in nodes.values)
            node.deleteNeighbor(a)

        return result
    }

    override fun deleteEdge (a: T, b: T): Boolean {
        val result = this.hasEdge(a, b) || this.hasEdge(b, a)
        nodes[a]?.deleteNeighbor(b)
        nodes[b]?.deleteNeighbor(a)
        return result
    }

    override fun contractEdge(a: T, b: T, g: T): Boolean {
        if(!hasEdge(a, b)) return false

        val aNeighbors = getNeighborsOf(a)
        val bNeighbors = getNeighborsOf(b)
        val gNeighbors = aNeighbors + bNeighbors // non-distinct union, preserves existing parallel edges

        deleteNode(a)
        deleteNode(b)

        for(n in gNeighbors)
            if (n!=a && n!=b) addEdge(g, n) // no self-loops for g

        return true
    }

    override fun print() {
        println("Nodes: n=$vertexCount Edges m=$edgeCount")
        for (n in nodes.values)
            n.print()
    }

    private class Node<T> (val name: T) {
        private val neighbors: MutableList<T> = mutableListOf() // supports parallel edges

        fun hasNeighbor(n: T): Boolean { return neighbors.contains(n) }
        fun getNeighbors(): List<T> { return neighbors.toList() }
        fun addNeighbor(n: T) { if(n != name) neighbors.add(n) } // no self-loops
        fun deleteNeighbor(n: T): Boolean { return neighbors.removeIf {it == n} }

        fun print() {
            val sb = StringBuilder("$name -> ")
            for (n in neighbors)
                sb.append("${n.toString()} ")
            println(sb.toString())
        }
    }
}
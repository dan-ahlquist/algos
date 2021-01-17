
class IndependentSet(
        private val nodes: List<Node> = emptyList()
) {

    var totalWeight = nodes.sumBy { it.weight }

    fun join(node: Node): IndependentSet {
        val newList = nodes.toMutableList() // Shallow copy
        newList.add(node)
        return IndependentSet(newList)
    }

    fun contains(index: Int): Boolean {
        return nodes.any { it.index == index }
    }

    fun print() {
        nodes.forEach {
            it.print()
        }
        println("Independent Set with ${nodes.size} and total weight $totalWeight.")
    }
}

data class Node (
    val index: Int,
    val weight: Int,
): Comparable<Node> {
    fun print() {
        println("$index ($weight)")
    }

    override fun compareTo(other: Node): Int {
        return weight - other.weight
    }
}

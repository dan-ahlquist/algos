object Stuffer {
    fun stuff(capacity: Int, items: List<Item>): Int {
        var prevSolutions = IntArray(capacity+1) { 0 }
        items.forEachIndexed { i, item ->
            println("Processing item # $i")

            val newSolutions = IntArray(capacity+1) { 0 }
            (0..capacity).forEach { c ->
                if (item.weight > c) {
                    newSolutions[c] = prevSolutions[c] // Exclude
                } else {
                    val exclude = prevSolutions[c]
                    val include = prevSolutions[c-item.weight] + item.value
                    if (include > exclude) {
                        newSolutions[c] = include
                    } else {
                        newSolutions[c] = exclude
                    }
                }
            }
            prevSolutions = newSolutions
        }
        return prevSolutions[capacity]
    }
}
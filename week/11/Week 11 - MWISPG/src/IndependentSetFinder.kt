
class IndependentSetFinder {

    fun findMaxWeightIndependentSet(weights: List<Int>): IndependentSet {
        val nodes = weights.mapIndexed { index, weight ->
            Node(index+1, weight)
        }

        var S_2back = IndependentSet(listOf(nodes[0]))
        var S_1back = IndependentSet(listOf(maxOf(nodes[0], nodes[1])))

        for (i in 2 until weights.size) {
            val curr = nodes[i]
            val weightWithCurrNode = S_2back.totalWeight + curr.weight
            val weightWithoutCurrNode = S_1back.totalWeight

            // Note: tie-breaking is not addressed by the courseware.
            if (weightWithCurrNode > weightWithoutCurrNode) {
                val newMis = S_2back.join(curr)
                // Advance
                S_2back = S_1back
                S_1back = newMis
            } else {
                // Advance. Note, S_1back stays the same,
                //   because we're omitting the current node.
                S_2back = S_1back
            }
        }

        // The final MIS was assigned to S_1back by either branch.
        return S_1back
    }
}

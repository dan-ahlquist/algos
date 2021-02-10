package dev.ahlquist.graph

data class Path (
        val from: Node,
        val to: Node,
        val cost: Cost,
) {
    operator fun plus(e: Edge): Path {
        return Path(this.from, e.to, this.cost + Cost.Finite(e.weight))
    }
}

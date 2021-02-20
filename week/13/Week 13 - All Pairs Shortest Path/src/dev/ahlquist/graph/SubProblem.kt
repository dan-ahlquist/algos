package dev.ahlquist.graph

interface SubProblem {
    val size: Int
    operator fun get(v: Int, w: Int): Cost
    operator fun set(v: Int, w: Int, value: Cost)
    operator fun set(v: Int, w: Int, value: Int)
}

package dev.ahlquist.graph

sealed class Cost: Comparable<Cost> {

    abstract operator fun plus(other: Cost): Cost

    data class Finite (
        val value: Int
    ): Cost() {
        override fun compareTo(other: Cost): Int {
            return when (other) {
                is Finite ->
                    this.value - other.value
                is Infinite ->
                    if (other.isPositive) -1 else 1
            }
        }

        override fun plus(other: Cost): Cost {
            return when (other) {
                is Finite -> Finite(this.value + other.value)
                is Infinite -> Infinite(other.isPositive)
            }
        }
    }

    open class Infinite (
        val isPositive: Boolean = true
    ): Cost() {
        override fun compareTo(other: Cost): Int {
            return when (other) {
                is Finite ->
                    if (this.isPositive) 1 else -1
                is Infinite ->
                    this.compareTo(other as Infinite)
            }
        }

        fun compareTo(other: Infinite): Int {
            return when {
                this.isPositive && !other.isPositive -> 1
                other.isPositive && !this.isPositive -> -1
                else -> 0
            }
        }

        override fun plus(other: Cost): Cost {
            return when (other) {
                is Finite -> Infinite(this.isPositive)
                is Infinite -> this.plus(other as Infinite)
            }
        }

        fun plus(other: Infinite): Cost {
            return when {
                this.isPositive == other.isPositive -> Infinite(this.isPositive)
                else -> throw ArithmeticException("Adding -∞ to +∞ is undefined!")
            }
        }
    }

    object PositiveInfinity: Infinite(true)
    object NegativeInfinity: Infinite(false)
}

package twoSat

class Clause(
    val var1: Int,
    val cond1: Boolean,
    val var2: Int,
    val cond2: Boolean,
) {
    fun isSatisfiedBy(assignment: Assignment) =
        assignment[var1] == cond1 && assignment[var2] == cond2

    override fun equals(other: Any?): Boolean {
        return if (other is Clause) {
            this.var1 == other.var1 &&
            this.cond1 == other.cond1 &&
            this.var2 == other.var2 &&
            this.cond2 == other.cond2
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return toString().hashCode()
    }

    override fun toString(): String {
        val s1 = negationSign(cond1)
        val s2 = negationSign(cond2)
        return "${s1}x_${var1} ∨ ${s2}x_${var2}"
    }
}

private fun negationSign(cond: Boolean) = if (cond) "" else "¬"

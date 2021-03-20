package twoSat

data class Instance(
    val vars: Set<Int>,
    val clauses: Set<Clause>
) {
    override fun toString() =
        "2SAT Instance with ${vars.size} variables and ${clauses.size} clauses."

    fun isSatisfiedBy(assignment: Assignment): Boolean {
        return clauses.all { it.isSatisfiedBy(assignment) }
    }
}

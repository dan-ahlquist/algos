package twoSat

data class Instance(
    val vars: Set<Int>,
    val clauses: Set<Clause>
)
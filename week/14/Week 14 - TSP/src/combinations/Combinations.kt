package combinations

fun <E> Collection<E>.getSubsets(cardinality: Int): Set<Set<E>> {
    // Even if this is already a Set, do a shallow copy
    val superset = this.toSet()
    val subsetter = Subsetter<E>()
    return subsetter.getSubsets(superset, cardinality)
}
interface SCCCollector {
    fun <T> findSCCs(g: MutableDirectedGraph<T>)
}

fun main() {
    val g = MutableDirectedGraphImpl<Char>()

    g.addEdge('s', 'v')
    g.addEdge('s', 'w')
    g.addEdge('v', 't')
    g.addEdge('w', 't')

    val gRev = g.reverse()

    TopoSorterImpl().sort(gRev)
}
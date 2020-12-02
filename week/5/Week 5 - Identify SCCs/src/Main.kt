import java.io.File

fun main() {
//    val g = MutableDirectedGraphImpl<Char>()

//    g.addEdge('s', 'v')
//    g.addEdge('s', 'w')
//    g.addEdge('v', 't')
//    g.addEdge('w', 't')
//
//    g.addEdge('s', 'v')
//    g.addEdge('s', 't')
//    g.addEdge('t', 'w')
//    g.addEdge('v', 'w')
//    g.addEdge('w', 'a')
//    g.addEdge('a', 'v')

//    println("***** g *****")
//    g.print()
//    println()
//    g.getNodes().forEach {
//        println(it)
//    }
//    println()

    println("Reading input")
    val g = readInput("SCC.txt")

    println("Reversing g - > gRev")
    val gRev = g.reverse()

//    println("***** gRev *****")
//    gRev.print()
//    println()
//    gRev.getNodes().forEach {
//        println(it)
//    }
//    println()

    println("Sorting gRev")
    TopoSorterImpl().sort(gRev)

//    println("***** gRev - topo sorted *****")
//    gRev.print()
//    println()
//    gRev.getNodes().forEach {
//        println(it)
//    }
//    println()

    println("Reversing gRev -> gPrime")
    val gPrime = gRev.reverse() // original edge directions, now marked with topo ordering
    gPrime.resetExplored()

//    println("***** gPrime *****")
//    gPrime.print()
//    println()
//    gPrime.getNodes().forEach {
//        println(it)
//    }
//    println()

    println("Finding SCCs in gPrime")
    SCCCollectorImpl().findSCCs(gPrime)

//    println("***** gPrime - SCCed *****")
//    gPrime.print()

    val resultMap = mutableMapOf<Int, Int>() // SCC# -> count

    gPrime.getNodes()
        .sortedBy {
            it.containingScc
        }
        .forEach {
            val count = resultMap.getOrPut(it.containingScc, {0})
            resultMap[it.containingScc] = count + 1
        }

    val sortedResults = resultMap.toList().sortedByDescending { (_, value) -> value}.toMap()
    sortedResults.keys.take(100).forEach { k ->
        println("SCC # $k has ${sortedResults[k]} nodes.")
    }
}

fun readInput(filename: String): MutableDirectedGraphImpl<Int> {
    val result = MutableDirectedGraphImpl<Int>()

    File(filename).forEachLine {
        val (u, v) = it.split(' ')
        result.addEdge(u.toInt(), v.toInt())
    }

    return result
}
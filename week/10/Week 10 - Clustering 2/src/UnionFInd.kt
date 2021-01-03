// Cribbed from https://chercher.tech/kotlin/disjoint-set-kotlin

class UnionFInd(val size: Int) {
    private val parent = IntArray(size)
    private val rank = ByteArray(size)
    var count = size; private set // no touchy

    init {
        for (i in parent.indices) {
            parent[i] = i
        }
    }

    fun connected(v: Int, w: Int): Boolean {
        return find(v) == find(w)
    }

    fun find(v: Int): Int {
        var curr = v
        while (parent[curr] != curr) {
            parent[curr] = parent[parent[curr]]
            curr = parent[curr]
        }
        return curr
    }

    fun union(v: Int, w: Int) {
        val rootV = find(v)
        val rootW = find(w)
        if (rootV == rootW) return
        if (rank[rootV] > rank[rootW]) {
            parent[rootW] = rootV
        } else if (rank[rootW] > rank[rootV]) {
            parent[rootV] = rootW
        } else {
            parent[rootV] = rootW
            rank[rootW]++
        }
        count--
    }
}
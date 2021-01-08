
class UnionFind<T>(data: Collection<T>) {

    private val parent = IntArray(data.size)
    var count = data.size; private set // no touchy

    init {
        for (i in parent.indices) {
            parent[i] = i
        }
    }

    fun connected(v: Int, w: Int): Boolean {
        return find(v) == find(w)
    }

    fun find(v: Int): Int {
    }

    fun union(v: Int, w: Int) {
        val rootV = find(v)
        val rootW = find(w)
        if (rootV == rootW) return
        //TODO
        count--
    }
}
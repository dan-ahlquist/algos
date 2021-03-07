package combinations

fun main() {
    val s = setOf(1,2,3,4)
    val subs = s.getSubsets(4)
    subs.forEach {
        val s = it.joinToString(prefix = "{", postfix = "}")
        println(s)
    }
}

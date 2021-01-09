import java.util.BitSet
import java.util.zip.DataFormatException

typealias Node = LabeledBitSet<Int>

class LabeledBitSet<L>(val label: L, bits: List<Int>) : BitSet(bits.size) {

    private val size = bits.size
    val bitString
        get() = bits.joinToString("", transform = Int::toString)

    init {
        bits.forEachIndexed { index, value ->
            when (value) {
                0 -> this.set(index, false)
                1 -> this.set(index, true)
                else -> throw DataFormatException("Tried to set a bit to $value.")
            }
        }
    }

    val bits: List<Int>
        get() {
            val result = mutableListOf<Int>()
            for (i in 0 until this.size)
                result.add(boolToDigit(this.get(i)))
            return result
        }

    fun copy(): LabeledBitSet<L> {
        return LabeledBitSet(label, bits)
    }

    override fun toString(): String {
        val sb = StringBuffer()
        bits.forEach {
            sb.append(" $it")
        }
        return "[$label]$sb"
    }

    private fun boolToDigit(b: Boolean): Int = if(b) 1 else 0

}


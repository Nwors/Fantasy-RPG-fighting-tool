import java.lang.Exception

const val MORE_THAN = 0
const val MORE_OR_EQUAL_TO = 1
const val EQUAL_TO = 2
const val LESS_OR_EQUAL_TO = 3
const val LESS_THAN = 4
const val COMPARISON_FALSE_MODE = 5

class Comparison(val mode: Int, val value: Int) {
    companion object {
        fun merge(comparison1: Comparison, comparison2: Comparison) : List<Comparison> {
            val list = mutableListOf<Comparison>()
            if (comparison1.mode == comparison2.mode) {
                list.add(
                        if (comparison1.mode == EQUAL_TO && comparison1.value != comparison2.value)
                            Comparison(COMPARISON_FALSE_MODE, 0)
                            else
                            Comparison(comparison1.mode,
                                    when(comparison1.mode) {
                                        MORE_THAN, MORE_OR_EQUAL_TO -> Math.max(comparison1.value, comparison2.value)
                                        LESS_THAN, LESS_OR_EQUAL_TO -> Math.min(comparison1.value, comparison2.value)
                                        EQUAL_TO -> comparison1.value
                                        else -> throw Exception("Comparison mode exception")
                }))
            } else {
                list.add(comparison1)
                list.add(comparison2)
            }
            return list;
        }
    }
}

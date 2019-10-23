import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

fun main() {
    var krionel =  Player(
        "Krionel",
        PlayerStats(3,3,3,3,3),
        equipment = listOf(Armor("Chain-mail","Heavy",5,true),
            Weapon("Club", "Blunt", 3, true, fencing = false),
            Armor("Chain-leggings", "Heavy", 5, true),
            Shield ("TheBarrier", "Shield", 3, false))
    )

    var krionel2 =  Player(
        "Krionel",
        PlayerStats(3,3,3,3,3),
        equipment = listOf(Armor("Chain-mail","Heavy",5,true),
            Weapon("Club", "Blunt", 3, true, fencing = false),
            Armor("Chain-leggings", "Heavy", 5, true),
            Shield ("TheBarrier", "Shield", 3, false))
    )

    val required = arrayOf(
            hashMapOf(
                    1 to Pair(3, 4),
                    2 to Pair(3, 5)
            ), hashMapOf()
    )
    val effect = arrayOf(
            hashMapOf(
                    7 to hashMapOf(1 to 3),
                    8 to hashMapOf(2 to 5, 7 to 8)
            ), hashMapOf()
    )
    val ability = Ability("magic", true, false, true, required, effect);
    ability.conditionFulfiled(krionel, krionel2)
}

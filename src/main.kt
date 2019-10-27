import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

fun main() {

    var loras =  Player(
        "Loras",
        PlayerStats(6,4,5,6,4),
        equipment = listOf(Weapon("Hands", "Blade", 0, true, false),
            Armor("Chain-mail","Heavy",5,true))
        )

    var lanadel =  Player(
        "Lanadel",
        PlayerStats(4,3,6,5,6),
        equipment = listOf(Weapon("Hands", "Piercing", 6, true,false ), Armor("Chain-Mail", "Heavy",5, equipped = true))
    )

//    val required = arrayOf(
//            hashMapOf(
//                    1 to Pair(3, 4),
//                    2 to Pair(3, 5)
//            ), hashMapOf()
//    )
//    val effect = arrayOf(
//            hashMapOf(
//                    7 to hashMapOf(1 to 3),
//                    8 to hashMapOf(2 to 5, 7 to 8)
//            ), hashMapOf()
//    )
//    val ability = Ability("magic", true, false, true, required, effect);
//    ability.conditionFulfiled(krionel, krionel2)

FightingTool.fight(loras,lanadel);




}

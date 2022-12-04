import kotlin.math.abs

data class Football_match(var team_one_score :Int, 
                     var team_two_score :Int) {
    
    fun set_scores(team_one_score : Int,team_two_score : Int): Unit { // Функция для задания количества очков
        this.team_one_score = team_one_score
        this.team_two_score = team_two_score
    }
    fun score() {
        println("${this.team_one_score} : ${this.team_two_score}") // Функция для вывода счета в удобном виде
    }
    fun diff() :Int {
        return this.team_two_score-this.team_one_score //Превышение в очках второй команды над первой
    }
    fun offset() :Int {
        return abs(this.diff())
    }
    
}

fun main() {
    var football_match : Football_match = Football_match(1,1) // Создаем экземпляр матча
    
    football_match.score() // Выводим счет
    println()
    
    football_match.team_one_score = 2 //Изменение значения очков одного матча
    football_match.score()
    println()
    
    football_match.set_scores(2,5) //Изменение значения очков обоих матчей
    football_match.score()
    println()
    
    
    var football_matches = arrayListOf<Football_match>() // Создаем пустой массив матчей
    //Цикл для заполнения очков матчей случайными значениями
    println("Все матчи:")
    for (index in 0..9){
        football_matches.add(Football_match((0..5).random(),(0..5).random())) // Заполняем матчи рандомными значениями
        football_matches[index].score() //Выводим
    }
    println()
    var football_matches_copy_1 = football_matches
    
    println("Неповторяющиеся матчи с помощью цикла:")
    //Удаление повторений с помощью цикла
    var k :Int = 10
    var i : Int = 0
    var j : Int = 0
    while (i<k-1) {
        j = i+1
        while (j<k){
            if (football_matches_copy_1[i] == football_matches_copy_1[j]) {
                football_matches_copy_1.removeAt(j)
                k--
            }
            j++
        }
        i++
    }
    
    for (match in football_matches_copy_1){
        match.score()
    }
    println()
    
    //Удаление повторений с помощью set
    var football_matches_copy_2 = football_matches.toSet()
    
    println("Неповторяющиеся матчи с помощью set:")
    for (match in football_matches_copy_2){
        match.score()
    }
    println()
    
    println("Количество неповторявшихся матчей: $k \n")
    
    //Находим максимальный разрыв очков
    val max_offset = (football_matches_copy_1.maxBy{it.offset()}).offset()
    println("Максимальный разрыв очков: $max_offset\n")
    //Создаем пустой set
    var football_matches_with_max_offset = mutableSetOf<Football_match>()
    //Проверяем и записываем в массив матчи, у которых разрыв в очках совпадает с максимальным
    for (match in football_matches_copy_1){
        if (match.offset() == max_offset)
        	football_matches_with_max_offset.add(match)
    }
    
    //Выводим матчи с максмальным разрывом
    println("Матчи с максимальным разрывом в очках:")
    for (match in football_matches_with_max_offset){
        match.score()
    }
    println()
}
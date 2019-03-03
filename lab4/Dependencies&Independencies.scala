class Trans (r: String, a: String, n: String){
  val args = a
  val res = r
  val name = n
  def check_dependency (b: Trans): Boolean = (args contains b.res) || ( res contains b.args)
}

//Function generating set Dependencies, based on alphabet
def generate_D (alphabet: Set[Trans]) : scala.collection.mutable.Set[(String, String)] = {
  var D = scala.collection.mutable.Set[(String, String)]()
  for (trans1 <- alphabet) {
    for (trans2 <- alphabet){
      if(trans1.check_dependency(trans2)) D += ((trans1.name, trans2.name))
    }
  }
  D
}
//Function generating set Independencies, based on alphabet
def generate_I (alphabet: Set[Trans]) : scala.collection.mutable.Set[(String, String)] = {
  var I = scala.collection.mutable.Set[(String, String)]()
  for (trans1 <- alphabet) {
    for (trans2 <- alphabet){
      var I_1_2 = trans1.check_dependency(trans2)
      var I_2_1 = trans2.check_dependency(trans1)
      if(!I_1_2){
        if(!I_2_1) I += ((trans1.name, trans2.name))
      }
    }
  }
  I
}

//Generating Foata Classes is not ready
/*def generate_Foata_classes (D: Set[(String, String)], I: Set[(String, String)], w: String) = {
  var Foata_classes = Array[List[char]]()
  Foata_classes[0] = [w.charAt(0)]
  var j = 0
  for (c <- w) {
      for (trans <- Foata_classes[j]){
          if(I contains (c, trans)) Foata_classes[j] += c
      }
      j += 1
      Foata_classes :+ [c]
    }
  Foata_classes
}*/
def print_transaction_set (D: scala.collection.mutable.Set[(Trans, Trans)]) = {
  for (pair <- D){
    println("(" + pair._1.name  + "," + pair._2.name + ")")
  }

}
val a = new Trans ("x", "x + y", "a")
val b = new Trans ("y", "y + 2z", "b")
val c = new Trans ("x", "3x + z", "c")
val d = new Trans ("z", "y - z", "d")
val vars = Set("x", "y", "z")
val alphabet = Set(a, b, c, d)
val D = generate_D(alphabet)
val I = generate_I(alphabet)

println("D: ")
println(D)
println("I: ")
println(I)

package P {
  object X { val x = 1; val y = 2 }
}
package Q {
  object X { val x = true; val y = "" }
}
package P { // `X' bound by package clause
  import Console._ // `println' bound by wildcard import
  object A {
    println("L4: " + X) // `X' refers to `P.X' here
    object B {
      import Q._ // `X' bound by wildcard import
      println("L7: " + X) // `X' refers to `Q.X' here
      import X._ // `x' and `y' bound by wildcard import
      println("L8: " + x) // `x' refers to `Q.X.x' here
      object C {
        val x = 3 // `x' bound by local definition
        println("L12: " + x) // `x' refers to constant `3' here
        locally {
          import Q.X._ // `x' and `y' bound by wildcard import
          println("L14: " + x)   // error: reference to `x' is ambiguous here
          import X.y // `y' bound by explicit import
          println("L16: " + y) // `y' refers to `Q.X.y' here
          locally {
            import P.X._ // `x' and `y' bound by wildcard import
            val x = "abc" // `x' bound by local definition
            println("L19: " + y) // error: reference to `y' is ambiguous here
            println("L20: " + x) // `x' refers to string ``abc'' here
          }
        }
      }
    }
  }
}

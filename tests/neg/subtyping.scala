class B {
  class X
}
class A extends B

object Test {
  def test1(): Unit = {
    implicitly[B#X <:< A#X]
  }
  def test2(): Unit = {
    val a : { type T; type U } = ???
    implicitly[a.T <:< a.U]
  }
}

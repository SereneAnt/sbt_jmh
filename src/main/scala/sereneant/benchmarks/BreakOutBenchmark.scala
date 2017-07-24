package sereneant.benchmarks

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

import scala.util.Random

@State(Scope.Benchmark)
object Fixture {
  private def ints(n: Int): Seq[Int] = Seq.fill(n)(Random.nextInt)
  private def strs(n: Int, strLen: Int): Seq[String] = Seq.fill(n)(Random.nextString(strLen))
  private def tuples(n: Int, strLen: Int): Seq[(Int, String)] = ints(n) zip strs(n, strLen)

  val tiny: Seq[(Int, String)] = tuples(n = 1, strLen = 10)
  val small: Seq[(Int, String)] = tuples(n = 10, strLen = 10)
  val large: Seq[(Int, String)] = tuples(n = 1000, strLen = 10)
}

@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Array(Mode.AverageTime))
@Fork(1)
class BreakOutBenchmark {
  import Fixture._

  @Benchmark
  def tinyToMap(hole: Blackhole): Unit = {
    val result = tiny.map(identity).toMap
    hole.consume(result)
  }

  @Benchmark
  def tinyBreakOut(hole: Blackhole): Unit = {
    val result: Map[Int, String] = tiny.map(identity)(collection.breakOut)
    hole.consume(result)
  }

  @Benchmark
  def smallToMap(hole: Blackhole): Unit = {
    val result = small.map(identity).toMap
    hole.consume(result)
  }

  @Benchmark
  def smallBreakOut(hole: Blackhole): Unit = {
    val result: Map[Int, String] = small.map(identity)(collection.breakOut)
    hole.consume(result)
  }

  @Benchmark
  def largeToMap(hole: Blackhole): Unit = {
    val result = large.map(identity).toMap
    hole.consume(result)
  }

  @Benchmark
  def largeBreakOut(hole: Blackhole): Unit = {
    val result: Map[Int, String] = large.map(identity)(collection.breakOut)
    hole.consume(result)
  }

}

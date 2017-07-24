# sbt_jmh
sbt jmh microbenchmarking playground

## Run jmh tests
```bash
sbt ';jmh:compile ;jmh:run'
```

## Results 
MacbookPro-2016 13 inch, CPU i7 @ 2.4 GHz, RAM 16G

```bash
[info] Benchmark                        Mode  Cnt    Score    Error  Units
[info] BreakOutBenchmark.largeBreakOut  avgt   20  158.186 ±  7.472  us/op
[info] BreakOutBenchmark.largeToMap     avgt   20  166.793 ±  1.670  us/op
[info] BreakOutBenchmark.smallBreakOut  avgt   20    0.698 ±  0.019  us/op
[info] BreakOutBenchmark.smallToMap     avgt   20    0.758 ±  0.020  us/op
[info] BreakOutBenchmark.tinyBreakOut   avgt   20    0.018 ±  0.001  us/op
[info] BreakOutBenchmark.tinyToMap      avgt   20    0.030 ±  0.001  us/op
```
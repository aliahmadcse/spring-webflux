package codes.aliahmad.tweaking.spring.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class MyController
{
  @GetMapping("/hello")
  public Mono<String> getHello()
  {
    return computeHello()
            .zipWith(getNameFromDb())
            .map(tuple -> tuple.getT1() + tuple.getT2());
  }

  public Mono<String> computeHello()
  {
    return Mono.just("Hello").delayElement(Duration.ofSeconds(5));
  }

  public Mono<String> getNameFromDb()
  {
    return Mono.just("Ali").delayElement(Duration.ofSeconds(5));
  }

}

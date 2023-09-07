package com.learnreactiveprogramming.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {


    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal"))
                .log();
    }

    public Flux<String> namesFluxMap(){
        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> namesFluxImmuntability(){
        var namesFlux = Flux.fromIterable(List.of("Xasan","Zohid","Akmal"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public Flux<String> namesFluxFilteredBySize(int givenLength){
        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal","Oqil"))
                .map(String::toUpperCase)
                .filter(s->s.length()>=givenLength)
                .map(s->s.length() + "-" + s);

    }

    public Flux<String> namesFluxFlatMap(){
        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal"))
                .map(String::toUpperCase)
                .flatMap(s->mySplitString(s));
    }

    public Flux<String> namesFluxTransform(int givenLength){
        Function<Flux<String>, Flux<String>> fluxFunction = name -> name.map(String::toUpperCase)
                .filter(s->s.length()>=givenLength);

        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal","Oqil"))
                .transform(fluxFunction)
                .flatMap(s->mySplitString(s))
                .log();
    }
    public Flux<String> namesFluxTransformEmpty(int givenLength){
        Function<Flux<String>, Flux<String>> fluxFunction = name -> name.map(String::toUpperCase)
                .filter(s->s.length()>=givenLength);

        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal","Oqil"))
                .transform(fluxFunction)
                .flatMap(s->mySplitString(s))
                .defaultIfEmpty("Default")
                .log();
    }

    public Flux<String> namesFluxTransformSwitchIfEmpty(int givenLength){
        Function<Flux<String>, Flux<String>> fluxFunctionForName = name -> name
                .map(String::toUpperCase)
                .filter(s->s.length()>=givenLength)
                .flatMap(s->mySplitString(s));

        Function<Flux<String>, Flux<String>> fluxFunctionForDefault = name -> name
                .map(String::toUpperCase)
                .flatMap(s->mySplitString(s));

        var defaultFlux = Flux.just("default")
                .transform(fluxFunctionForDefault);

        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal","Oqil"))
                .transform(fluxFunctionForName)
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Flux<String> namesFluxFlatMapAscn(){
        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal"))
                .map(String::toUpperCase)
                .flatMap(s->mySplitStringWithDeley(s))
                .log();
    }


    public Flux<String> namesFluxConcatMap(){
        return Flux.fromIterable(List.of("Xasan","Zohid","Akmal"))
                .map(String::toUpperCase)
                .concatMap(s->mySplitStringWithDeley(s))
                .log();
    }


    public Mono<String> nameMono(){
        return Mono.just("Bobur");
    }

    public Mono<List<String>> nameMonoFlatMap(){
        return Mono.just("Bobur")
                .map(String::toUpperCase)
                .flatMap(this::mySplitStringForMono)
                .log();
    }
    public Flux<String> nameFlatMapMany(){
        return Mono.just("Bobur")
                .map(String::toUpperCase)
                .flatMapMany(this::mySplitString)
                .log();
    }

    public Mono<List<String>> mySplitStringForMono(String str){
        var charArr = str.split("");
        var charList = List.of(charArr);
        return Mono.just(charList);
    }
    public Flux<String> mySplitString(String str){
        var charArr = str.split("");
        return Flux.fromArray(charArr);
    }

    public Flux<String> mySplitStringWithDeley(String str){
        var charArr = str.split("");
        var deley = new Random().nextInt(1000);
        return Flux.fromArray(charArr)
                .delayElements(Duration.ofMillis(deley));
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> {
                    System.out.println(name);
                });

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> {
                    System.out.println("Mono name : " + name);
                });

    }
}

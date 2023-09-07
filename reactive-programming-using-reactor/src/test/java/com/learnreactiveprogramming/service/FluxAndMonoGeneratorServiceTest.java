package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFluxAndMono() {
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("Xasan","Zohid","Akmal")
                .verifyComplete();
    }

    @Test
    void namesFluxAndMonoMap() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxMap();
        StepVerifier.create(namesFlux)
                .expectNext("XASAN","ZOHID","AKMAL")
                .verifyComplete();
    }
    @Test
    void namesFluxAndMonoImmutablity() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxImmuntability();
        StepVerifier.create(namesFlux)
                .expectNext("Xasan","Zohid","Akmal")
                .verifyComplete();
    }

    @Test
    void namesFluxFilteredBySize() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFilteredBySize(5);
        StepVerifier.create(namesFlux)
                .expectNext("5-XASAN","5-ZOHID","5-AKMAL")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap();

        StepVerifier.create(namesFlux)
                .expectNext("X","A","S","A","N","Z","O","H","I","D","A","K","M","A","L")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapAscn() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMapAscn();

        StepVerifier.create(namesFlux)
//                .expectNext("X","A","S","A","N","Z","O","H","I","D","A","K","M","A","L")
                .expectNextCount(15)
                .verifyComplete();
    }

    @Test
    void namesFluxConcatMap() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxConcatMap();

        StepVerifier.create(namesFlux)
                .expectNext("X","A","S","A","N","Z","O","H","I","D","A","K","M","A","L")
                .expectNextCount(15)
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMap() {
        var namesMono = fluxAndMonoGeneratorService.nameMonoFlatMap();

        StepVerifier.create(namesMono)
                .expectNext(List.of("B","O","B","U","R"))
                .verifyComplete();
    }

    @Test
    void nameFlatMapMany() {

        var namesMono = fluxAndMonoGeneratorService.nameFlatMapMany();

        StepVerifier.create(namesMono)
                .expectNext("B","O","B","U","R")
                .verifyComplete();
    }

    @Test
    void namesFluxTransform() {

        var namesFlux = fluxAndMonoGeneratorService.namesFluxTransform(5);
        StepVerifier.create(namesFlux)
                .expectNext("X","A","S","A","N","Z","O","H","I","D","A","K","M","A","L")
                .verifyComplete();

    }

    @Test
    void namesFluxTransformEmpty() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxTransformEmpty(10);
        StepVerifier.create(namesFlux)
//                .expectNext("X","A","S","A","N","Z","O","H","I","D","A","K","M","A","L")
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void namesFluxTransformSwitchIfEmpty() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxTransformSwitchIfEmpty(10);
        StepVerifier.create(namesFlux)
//                .expectNext("X","A","S","A","N","Z","O","H","I","D","A","K","M","A","L")
                .expectNext("D","E","F","A","U","L","T")
                .verifyComplete();
    }
}
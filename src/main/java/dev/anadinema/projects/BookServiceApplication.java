package dev.anadinema.projects;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class BookServiceApplication {
    public static void main(String[] args) {
        Quarkus.run(args);
    }
}

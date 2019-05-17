package example.micronaut;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    @Introspected
    static class Conference {

        private String name;

        public Conference(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Singleton
    static class ConferenceService {

        private static final List<Conference> CONFERENCES = Arrays.asList(
                new Conference("Greach"),
                new Conference("GR8Conf EU"),
                new Conference("Micronaut Summit"),
                new Conference("Devoxx Belgium"),
                new Conference("Oracle Code One"),
                new Conference("CommitConf"),
                new Conference("Codemotion Madrid")
        );

        public Mono<Conference> randomConf() {
            return Mono.just(CONFERENCES.get(new Random().nextInt(CONFERENCES.size())));
        }
    }

    @RequiredArgsConstructor
    @Controller("/conferences")
    static class ConferenceController {

        private final ConferenceService conferenceService;

        @Get("/random")
        public Mono<Conference> randomConf() {
            return conferenceService.randomConf();
        }
    }
}



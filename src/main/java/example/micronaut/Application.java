package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }

    @Controller
    @RequiredArgsConstructor
    static class ConferenceController {

        private final ConferenceService conferenceService;

        @Get("/hw")
        public String hw() {
            return conferenceService.hw();
        }
    }

    @Singleton
    static class ConferenceService {
        String hw() {
            return "hw";
        }
    }
}
package application;

import annotations.Get;
import annotations.Param;

public class Application {

    @Get("/accueil")
    public String accueil() {
        return "<h1>Accueil</h1>";
    }

    @Get("/hello")
    public String hello(@Param("prenom") String nom) {
        return String.format("<p>Hello %s</p>", nom) ;
    }
}

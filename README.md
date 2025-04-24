
    Förklarar teststrategin du använt
    Teststrategin jag har använt är för att enkelt testa att om en "User" funkar att hämta i olika sammanhang med olika funktioner i olika typer av tester av namn,id,email.
    
    Motiverar vilka metoder du testat och varför
    
    Enhetstest 1 
    För att kolla att en användare inte ska kunna sparas flera gånger, så det inte går att spara flera av samma användare.
    
    Enhetstest 2 
    För att kolla så att med rätt användarnamn returnerar med rätt id så man vet att rätt id är ihop kopplat med rätt "User".

    Komponenttestet
    Liknande test som enehetsteserna men skillnaden är att den kollar funktionen getUserById returnerar rätt använadare isolerat, för att kunna hitta en user med id för att hitta user enklare.

    Intergrationtest
    Använder sig av HTTP för att se om en användare skapats och sedan hämtas för att sparas korrekt. För att se tex om ett företag vill spara användare som skapats på ett korrekt sätt utan att påverka den "riktiga" databasen.
    
    Beskriver hur man kör testerna
     Enhetstest 1 
     I början av testet så mockas Repot så man inte använder den riktiga med riktiga databaser. Sedan skapar man upp en användare som testat om det är falsk att användaren inte finns så ska användaren först skapas sen sparas.

      Enhetstest 2 
      Testet börjas med att kapa en "expectedUser" med id namn och email.
       Sedan så mockas Repot så man inte använder den riktiga med riktiga databaser.
       En ny istans av UserService skapas sen anropas findById, sen i slutet kollar den om id,namn och email är som förväntat.

      Komponenttestet
      Första som görs är att repot mockas för att kontrollera flödet utan en riktig databas,
      sedan skapas UserService och UserController manuellt.
      Sedan stimulerar den ett svar där den låtsas att den innehåller en specifik användare.
      Sen anroppar den kontrollen utan HTTP eller spring.
      Avslutas med att verifiera rätt namn och epost.
       
      Intergrationtest
      Testet börjar med att testa en viss specifik profil (test) för att inte köra i den riktiga för att sedan rensas efter varje körning.
      Sen sätts SpringBootTest till en randomport för att testa riktiga HTTP endpoints.
      Sen när tester börjar skapas en användare.
      Sen skickas en POST där vi skickar användaren med en RestTemplate. Sen veriferias den att användaren verkligen blev skapad.
      Sedan tar man ut användarens id för att sedan kunna hämta rätt användare med det id i nästa steg där man skickar ett GET request.
      GET skcias för att få användar ID sedan verifieras att statusen är HttpStatus.OK.
      Slutet av koden kollar den så att den matchar den POST som vi skickade in.

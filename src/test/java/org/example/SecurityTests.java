package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.fusesource.jansi.Ansi;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

/**
 * testuje podatności aplikacji internetowej na ataki takie jak SQL Injection, XSS, CSRF, HTTP Verb Tampering i Brute Force, sprawdzając czy system blokuje nieautoryzowane próby dostępu.
 */
public class SecurityTests {

    static final String BASE_URL = "https://api.meteo.pl:443";
    static final String URI = "/api/v1/model/wrf/grid/d02_XLONG_XLAT/coordinates/284,386/field/T2/level/0/date/2020-02-25T00/info/";
    static final String AuthorizationString = "Token 0b54c818fa6f804cf8cd695578f7edae02019d5a";

    /**
     * Metoda wykonująca żądanie REST
     *
     * @param uri                 - adres URI
     * @param payload             - treść żądania body
     * @param authorizationString - token autoryzacyjny
     * @return - odpowiedź z żądania klasy Response
     */
    private Response executeRequest(String uri, String payload, String authorizationString) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(payload)
                .header("Authorization", authorizationString)
                .post(uri);
    }


    private void showStatusCodeMessage(Response response, int expectedStatusCode) {
        if (response.getStatusCode() == expectedStatusCode) {
            System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Status jest prawidłowy i wynosi '" + response.getStatusCode() + "', komunikat: " + response.getBody().prettyPrint()).reset());
        } else {
            Assert.fail("Status jest nieprawidłowy. Jest '" + response.getStatusCode() + "" + "' a powinien być '" + expectedStatusCode + "'");
        }
    }

    private String generatorStrongFakePasswordWithRandomLength(int maxLength) {
        StringBuilder fakePassword = new StringBuilder();
        int passwordLength = (int) (Math.random() * maxLength + 1);
        for (int i = 0; i < passwordLength; i++) {
            fakePassword.append((char) (Math.random() * 26 + 'a'));
        }
        return fakePassword.toString();
    }

    private String[] fakePasswordsListGenerator(int counter, int passwordLength) {
        String[] fakePasswordsList = new String[counter + 1];
        for (int i = 0; i < counter; i++) {
            fakePasswordsList[i] = generatorStrongFakePasswordWithRandomLength(passwordLength);
        }
        return fakePasswordsList;
    }

    @Test
    public void sqlInjectionTest() {
        String payload = "{ \"input\": \"' OR 1=1 --\" }";
        showStatusCodeMessage(executeRequest(URI, payload, AuthorizationString), 401);
    }

    @Test
    public void xssTest() {
        String payload = "{ \"input\": \"<script>alert('XSS');</script>\" }";
        showStatusCodeMessage(executeRequest(URI, payload, AuthorizationString), 400);
    }

    @Test
    public void csrfTest() {
        String payload = "{ \"input\": \"' OR 1=1 --\" }";
        showStatusCodeMessage(executeRequest(URI, payload, "Token 0b54c818fa6f804cf8cd695578f7edae02019d4b"), 403);
    }

    @Test
    public void httpVerbTamperingTest() {
        showStatusCodeMessage(executeRequest(URI + generatorStrongFakePasswordWithRandomLength(4), "", AuthorizationString), 404);
    }

    @Test
    public void bruteForceTest() {
        String[] passwordList = fakePasswordsListGenerator(10, 20);
        int counter = 0;
        for (String password : passwordList) {
            String payload = "{ \"password\": \"" + password + "\" }";
            counter++;
            Response response = executeRequest(URI, payload, AuthorizationString);
            if (response.getStatusCode() != 200) {
                System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("Próba ataku Brute Force została zablokowana pod " + counter + " próbach kodem '" + response.getStatusCode() + "', komunikat: " + response.getBody().prettyPrint()).reset());
                return;
            } else {
                System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("Atakuję po raz " + counter + " hasłem '" + password + "'").reset());
            }
        }
        Assert.fail("Atak Brute Force nie został zablokowany po " + (counter - 1) + " próbach");
    }
}

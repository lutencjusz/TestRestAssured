package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    private static final Integer EXP_VALUE = 3;
    private static final Integer VALUE = 3;

    /**
     * Szkolenie REST API
     */

    @Test
    public void helloWorldTest() {
        RestAssured
                .given()
                    .baseUri("http://todoist.com")
                    .log().all()
                .when()
                    .get("/1410/year")
                .then()
                    .log().all();
    }

    @Test
    public void sampleRESTTest() {
        JsonPath response = RestAssured.given()
                .when()
                .baseUri("https://jsonplaceholder.typicode.com")
                .get("/posts")
                .then()
                .statusCode(200)
                .extract().body().jsonPath();
        List<Model> posts = response.getList("", Model.class);

        for (Model item : posts) {
            System.out.println("Tytuł(" + item.getId() + "): " + item.getTitle());
        }

        List<Model> searchedItem = posts.stream()
                .filter(i -> i.getId().equals(VALUE))
                .collect(Collectors.toList());

        Assert.assertEquals(searchedItem.get(0).getId(), EXP_VALUE);
        Reporter.log("Znaleziono id: " + searchedItem.get(0).getId() + ", tytuł: " + searchedItem.get(0).getTitle());
    }

    @Test
    public void simpleSOAPCalculator() {

        String req_body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        req_body += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
        req_body += "<soap:Body>";
        req_body += "<Add xmlns=\"http://tempuri.org/\">";
        req_body += "<intA>2</intA>";
        req_body += "<intB>5</intB>";
        req_body += "</Add>";
        req_body += "</soap:Body>";
        req_body += "</soap:Envelope>";

        System.out.println("Zapytanie: " + req_body);
        System.out.println("__________________________________________________");

        Response response = given()
                .contentType(ContentType.XML)
                .baseUri("http://www.dneonline.com")
                .header("Content-Type", "text/xml")
                .and()
                .body(req_body)
                .when()
                .post("/calculator.asmx?wsdl")
                .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();

        String responseBody = response.asString();
        int responseStatusCode = response.getStatusCode();
        System.out.println("__________________________________________________");
        System.out.println("Odpowiedz: " + responseBody + ", status code: " + responseStatusCode);
        String ret = new XmlPath(responseBody).getString("Envelope.Body.AddResponse.AddResult");
        System.out.println("__________________________________________________");
        System.out.println("Wynik dodawania wynosi: '" + ret + "'");
    }

    @Test
    public void simpleSOAPTest() {

//        List<TracksContext> contexts = new ArrayList<>();
//
//
//
//
//
//        String responseBody = response.asString();
//        int responseStatusCode = response.getStatusCode();
//        System.out.println("__________________________________________________");
//        System.out.println("Odpowiedz: " + responseBody + ", status code: " + responseStatusCode);
//        XmlPath xmlPath = new XmlPath(responseBody);
//        String ret = xmlPath.toString();
//
//        System.out.println("---------------------------------");
//        XmlPath jsXpath = new XmlPath(response.asString());
//        NodeChildren contextNodes = jsXpath.getNodeChildren("contexts.context");
//        for (Node contextNode : contextNodes.list()) {
//            TracksContext context = new TracksContext();
//            for (Node contextElement : contextNode.children().list()) {
//                if (contextElement.name().contentEquals("id")) {
//                    context.setId(contextElement.value());
//                }
//                if (contextElement.name().contentEquals("name")) {
//                    context.setName(contextElement.value());
//                }
//            }
//            contexts.add(context);
//            System.out.println("id: " + context.getId() + ", name: " + context.getName());
//        }
    }
}

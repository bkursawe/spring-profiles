package de.praxisit.tips.profiles.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.praxisit.tips.profiles.TipsSpringProfilesApplication;
import de.praxisit.tips.profiles.common.DateSupplier;
import de.praxisit.tips.profiles.config.Profiles;
import de.praxisit.tips.profiles.data.Patient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test for {@link PatientController}.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TipsSpringProfilesApplication.class)
@ActiveProfiles(Profiles.TEST)
@Transactional
@Sql(scripts = {"/db/schema.sql", "/db/patient.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class PatientControllerTest {

    private static final FieldDescriptor[] PATIENT = {
            fieldWithPath( "id" ).type( NUMBER ).description( "Unique id for patient" ),
            fieldWithPath( "firstName" ).type( STRING ).optional( ).description(
                    "First name(s) of patient" ),
            fieldWithPath( "lastName" ).type( STRING ).description( "Last name of patient" ),
            fieldWithPath( "birthday" ).type( STRING ).optional( ).description(
                    "Birthday of patient as ISO date (yyyy-MM-dd)" )};
    private static final ParameterDescriptor PATIENT_ID = parameterWithName( "id" ).description(
            "Unique id for patient" );
    private static final String PATIENT_PATH = "patient/{method-name}";

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
            "target/generated-snippets" );

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private RestDocumentationResultHandler documentationHandler;

    private Patient donald;

    @Before
    public void setup() {
        this.documentationHandler = document( PATIENT_PATH,
                preprocessRequest( Preprocessors.prettyPrint( ) ),
                preprocessResponse( Preprocessors.prettyPrint( ) ) );

        mvc = MockMvcBuilders.webAppContextSetup( context )
                .apply( documentationConfiguration( this.restDocumentation ) )
                .alwaysDo( documentationHandler )
                .build( );

        donald = new Patient( 1, "Donald", "Duck", LocalDate.of( 1905, 4, 1 ) );
    }

    @Test
    public void findPatient() throws Exception {
        mvc.perform( get( "/patient/{id}", 1 ).accept( APPLICATION_JSON_UTF8 ) )
                .andExpect( status( ).isOk( ) )
                .andExpect( jsonPath( "$.id", is( 1 ) ) )
                .andExpect( jsonPath( "$.firstName", is( "Donald" ) ) )
                .andExpect( jsonPath( "$.lastName", is( "Duck" ) ) )
                .andExpect( jsonPath( "$.birthday", is( "1905-04-01" ) ) )
                .andDo( documentationHandler.document( pathParameters( PATIENT_ID ),
                        responseFields( PATIENT ) ) );
    }

    @Test
    public void findPatientNotFound() throws Exception {
        mvc.perform( get( "/patient/{id}", 999 ).accept( APPLICATION_JSON_UTF8 ) )
                .andExpect( status( ).isNotFound( ) )
                .andDo( documentationHandler.document( pathParameters( PATIENT_ID ) ) );
    }

    @Test
    public void findAll() throws Exception {
        mvc.perform( get( "/patient" ).accept( APPLICATION_JSON_UTF8 ) ).andExpect( jsonPath(
                "$.length()",
                is( greaterThan( 3 ) ) ) ).andExpect( status( ).isOk( ) ).andDo(
                documentationHandler.document( responseFields( fieldWithPath( "[]" ).type( ARRAY )
                        .description( "List of patients" ) ).andWithPrefix( "[].", PATIENT ) ) );
    }

    @Test
    public void create() throws Exception {
        final Patient gundel = Patient.builder( )
                .firstName( "Gundel" )
                .lastName( "Gaukelei" )
                .birthday( LocalDate.of( 1899, 9, 9 ) )
                .build( );
        ObjectMapper objectMapper = new ObjectMapper( );
        mvc.perform( post( "/patient" )
                .content( "{\n" +
                        "\t\"firstName\": \"Gundel\",\n" +
                        "\t\"lastName\": \"Gaukelei\"\n" +
                        "}" )
                .contentType( APPLICATION_JSON_UTF8 )
                .accept( APPLICATION_JSON_UTF8 )
        ).andExpect( status( ).isCreated( ) );
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Configuration
    @ComponentScan(basePackageClasses = DateSupplier.class)
    public static class TestConfiguration {
        // nothing to do
    }


}
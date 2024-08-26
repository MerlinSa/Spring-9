
package ru.gb.timesheet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.ActiveProfiles;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;
import ru.gb.timesheet.rest.TimesheetController;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* todo hw#10
Протестировать TimesheetController
GET /timesheets/{id}
GET /timesheets
POST /timesheets
DELETE /timesheets
PUT /timesheets/{id}
 */

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimesheetControllerTest {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TimesheetRepository timesheetRepository;
    @Autowired
    TimesheetService service;
    @Autowired
    TimesheetController controller;


    @BeforeEach
    void deleteRepositories() {
        projectRepository.deleteAll();
        timesheetRepository.deleteAll();
    }

    @Test
    void testGetById() throws Exception { //GET /timesheets/{id}
        Timesheet timesheet = getTimesheet();
        Timesheet savedTimesheet = service.create(timesheet);
        HttpStatusCode actualStatusCode = controller.get(savedTimesheet.getId()).getStatusCode();
        assertEquals(HttpStatus.OK, actualStatusCode);

//        client.get()
//                .uri("/timesheets/" + savedTimesheet.getId())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(Timesheet.class)
//                .value(actual -> {
//                    assertEquals(savedTimesheet.getId(), actual.getId());
//                    assertEquals(savedTimesheet.getMinutes(), actual.getMinutes());
//                    assertEquals(savedTimesheet.getCreatedAt(), actual.getCreatedAt());
//                    assertEquals(savedTimesheet.getProjectId(), actual.getProjectId());
//                    assertEquals(savedTimesheet.getEmployeeId(), actual.getEmployeeId());
//                }); так не получ-сь
    }

    @Test
    void testGetAll() { //GET /timesheets
        HttpStatusCode actualStatusCode = controller.getAll().getStatusCode();
        assertEquals(HttpStatus.OK, actualStatusCode);
    }

    @Test
    void testCreate() { //POST /timesheets
        Timesheet timesheet = getTimesheet();
        HttpStatusCode actualStatusCode = controller.create(timesheet).getStatusCode();
        assertEquals(HttpStatus.CREATED, actualStatusCode);
    }



    @Test
    void testDelete() { //DELETE /timesheets
        Timesheet timesheet = getTimesheet();
        timesheetRepository.save(timesheet);
        HttpStatusCode actualStatusCode = controller.delete(timesheet.getId()).getStatusCode();
        assertEquals(HttpStatus.NO_CONTENT, actualStatusCode);
    }


    @Test
    void testUpdate() { //PUT /timesheets/{id}
        Timesheet timesheet = getTimesheet();
        timesheetRepository.save(timesheet);
        timesheet.setMinutes(20);
        HttpStatusCode actualStatusCode = controller.update(timesheet.getId(), timesheet).getStatusCode();
        assertEquals(HttpStatus.OK, actualStatusCode);
    }

    private Timesheet getTimesheet() {
        Timesheet timesheet = new Timesheet();
        timesheet.setMinutes(15);
        timesheet.setCreatedAt(LocalDate.now());
        timesheet.setEmployeeId(1L);
        Project project = projectRepository.save(new Project());
        timesheet.setProjectId(project.getId());
        return timesheet;
    }
}
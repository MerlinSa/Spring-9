/*package ru.gb.timesheet.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@Tag(name = "Timesheets", description = "API для взаимодействия с задачами")
@RequestMapping("/timesheets")
public class TimesheetController {

  // GET - получить - не содержит тела
  // POST - create
  // PUT - изменение
  // PATCH - изменение
  // DELETE - удаление

  // @GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
  // @DeleteMapping("/timesheets/{id}") // удалить конкретную запись по идентификатору
  // @PutMapping("/timesheets/{id}") // обновить конкретную запись по идентификатору

  private final TimesheetService service;


  @GetMapping("/{id}") // получить данные по задаче
  @Operation(summary = "Get Timesheet", description = "Получить данные по задаче")
  @Response.NotFoundResponse
  @Response.AccessTimesheetRequest
  @Response.InternalFailRequest
  public ResponseEntity<Timesheet> get(@PathVariable @Parameter(description = "Идентификатор задачи")Long id) {
    return service.findById(id)
            .map(timesheet -> ResponseEntity.status(HttpStatus.OK).body(timesheet))
            .orElseGet(() -> ResponseEntity.notFound().build());

  }


  @GetMapping // получить все
  @Operation(summary = "Get Timesheets", description = "Получить все задачи")
  @Response.AccessTimesheetRequest
  @Response.InternalFailRequest
  public ResponseEntity<List<Timesheet>> getAll(  ) {
    return ResponseEntity.ok(service.getAll());
  }

  // client -> [spring-server -> ... -> TimesheetController
  //                          -> exceptionHandler(e)
  // client <- [spring-server <- ...
  @PostMapping // создание нового ресурса
  @Operation(summary = "Post Timesheet", description = "Создание задачи")
  @Response.AccessTimesheetRequest
  @Response.InternalFailRequest
  @Response.NotFoundResponse
  public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
    final Timesheet created = service.create(timesheet);

    // 201 Created
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete Timesheet")
  @Response.NoContentRequest
  @Response.InternalFailRequest
  public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "Идентификатор задачи") Long id) {
    service.delete(id);

    // 204 No Content
    return ResponseEntity.noContent().build();
  }

//  @ExceptionHandler(NoSuchElementException.class)
//  public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
//    return ResponseEntity.notFound().build();
//  }



}
*/
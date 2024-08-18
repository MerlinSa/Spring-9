package ru.gb.timesheet.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Описание структуры json-ответа на REST-запросы.
 * Т.е. запросы, ответ на которые - JSON.
 */
@Data
@Entity
@Table(name = "timesheet")
@Schema(description = "Данные учета рабочего времени")
public class Timesheet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include

  @Schema(description = "Идентификатор работы")
  private Long id;
  @Schema(description = "Идентификатор проекта")
  private Long projectId;
  @Schema(description = "Количество затраченных минут")
  private Integer minutes;
  @Schema(description = "Дата регистрации работы")
  private LocalDate createdAt;
  @Schema(description = "Идентификатор сотрудника")
  private Long employeeId;

}

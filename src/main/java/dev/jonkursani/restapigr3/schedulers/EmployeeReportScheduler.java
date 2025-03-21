package dev.jonkursani.restapigr3.schedulers;

import dev.jonkursani.restapigr3.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//https://docs.spring.io/spring-framework/reference/integration/scheduling.html#scheduling-cron-expression
//        ┌───────────── second (0-59)
//        │ ┌───────────── minute (0 - 59)
//        │ │ ┌───────────── hour (0 - 23)
//        │ │ │ ┌───────────── day of the month (1 - 31)
//        │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
//        │ │ │ │ │ ┌───────────── day of the week (0 - 7)
//        │ │ │ │ │ │          (0 or 7 is Sunday, or MON-SUN)
//        │ │ │ │ │ │
//        * * * * * *
// https://crontab.guru/

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeReportScheduler {
    private final EmployeeRepository repository;

    // every monday 9 AM
    // ja percakton orarin (kohen) e ekzekutimit
//    @Scheduled(cron = "0 0 9 ? * MON")
//    @Scheduled(fixedRate = 10000)
    public void generateAvailabilityReport() {
        log.info("Generating employee availability report");

        var employee = repository.findAll();

        employee.forEach(e -> {
            log.info("Employee: {} {} is available", e.getFirstName(), e.getLastName());
        });

        log.info("Employee availability report generated: {}", LocalDate.now());
    }

    // every friday 10 AM
//    @Scheduled(cron = "0 0 10 ? * FRI")
    public void getHireReport() {

    }
}
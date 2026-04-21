package ru.rksp.chechulina.controller;

import ru.rksp.chechulina.repo.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/events")
public class CountController {

    private final EventRepository repo;
    private final String clickhouseUrl;
    private final String clickhouseUsername;
    private final String clickhousePassword;

    public CountController(
        EventRepository repo,
        @Value("${clickhouse.url}") String clickhouseUrl,
        @Value("${clickhouse.username}") String clickhouseUsername,
        @Value("${clickhouse.password}") String clickhousePassword
    ) {
        this.repo = repo;
        this.clickhouseUrl = clickhouseUrl;
        this.clickhouseUsername = clickhouseUsername;
        this.clickhousePassword = clickhousePassword;
    }

    @PostMapping("/count")
    public long count() throws SQLException {
        long count = repo.count();
        try (
            Connection connection = DriverManager.getConnection(clickhouseUrl, clickhouseUsername, clickhousePassword);
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO `агрегаты_событий_заказов` (`дата_и_время_записи`, `количество_записей`) VALUES (?, ?)"
            )
        ) {
            statement.setObject(1, LocalDateTime.now());
            statement.setLong(2, count);
            statement.executeUpdate();
        }
        return count;
    }
}

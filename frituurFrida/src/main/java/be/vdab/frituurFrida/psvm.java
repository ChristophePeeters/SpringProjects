package be.vdab.frituurFrida;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class psvm {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().toLocalDate());
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }
}

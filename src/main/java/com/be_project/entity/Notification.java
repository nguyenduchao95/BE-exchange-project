package com.be_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String type;
    @Lob
    private String content;
    private Date date = Date.valueOf(LocalDate.now());
    @ManyToOne
    private Account sender; // người gửi
    @ManyToOne
    private Account receiver; // người nhận
}
package com.andrei.cmr.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @Column(unique = true)
    private String slug;
    private String menuLabel;
    private String h1;
    @Column(columnDefinition = "TIMESTAMP")
    private Date publishedTime;
    private Integer priority;
    private boolean published;
    private byte[] htmlCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(id, page.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

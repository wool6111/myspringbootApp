package com.basic.myspringboot.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JacksonXmlProperty
    @NotEmpty(message = "Name(이름)은 필수 입력 항목입니다.")
    private String name;

    @Column(unique = true, nullable = false)
    @JacksonXmlProperty
    @NotEmpty(message = "Email(이메일)은 필수 입력 항목입니다.")
    private String email;
}

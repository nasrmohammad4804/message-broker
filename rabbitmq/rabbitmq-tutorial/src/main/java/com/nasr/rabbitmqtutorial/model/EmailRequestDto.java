package com.nasr.rabbitmqtutorial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto implements Serializable {

    private String to;
    private String content;
}

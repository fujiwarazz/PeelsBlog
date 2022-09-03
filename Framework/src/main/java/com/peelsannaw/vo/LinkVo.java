package com.peelsannaw.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private String address;
    private Long id;
    private String name;
    private String logo;
    private String description;
}

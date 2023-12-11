package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Member {
    int id;
    String userId;
    String password;
    String regDate;
}

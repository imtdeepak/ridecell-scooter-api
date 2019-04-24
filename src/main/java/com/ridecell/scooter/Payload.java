package com.ridecell.scooter;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Payload {
    private List<String> names;
}
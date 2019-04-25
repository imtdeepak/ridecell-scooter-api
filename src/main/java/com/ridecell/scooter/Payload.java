package com.ridecell.scooter;

import lombok.*;

import java.util.List;

/**
 * Created by deepakkumar on 4/24/19.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Payload {
    private List<String> names;
}

package com.papas.car;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResolvedHashtag {
    private Long hashtagId;
    private String name;

    private ResolvedHashtag(Long hashtagId, String name) {
        this.hashtagId = hashtagId;
        this.name = name;
    }

    public static ResolvedHashtag of(Long hashtagId, String name) {
        return new ResolvedHashtag(hashtagId, name);
    }
}

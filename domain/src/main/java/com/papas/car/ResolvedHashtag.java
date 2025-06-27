package com.papas.car;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResolvedHashtag that = (ResolvedHashtag) o;
        return Objects.equals(hashtagId, that.hashtagId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hashtagId);
    }
}

package com.papas.car;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
public class ResolvedCarImage {
    private Long imgId;
    private String imgFilePath; // 파일경로
    private String imgHashName; // 해쉬네임
    private String imgExt; // 확장자
    private Boolean isThumbnail;

    private ResolvedCarImage(Long imgId, String imgFilePath, String imgHashName, String imgExt, Boolean isThumbnail) {
        this.imgId = imgId;
        this.imgFilePath = imgFilePath;
        this.imgHashName = imgHashName;
        this.imgExt = imgExt;
        this.isThumbnail = isThumbnail;
    }

    public static ResolvedCarImage of(Long imgId, String imgFilePath, String imgHashName, String imgExt, Boolean isThumbnail) {
        return new ResolvedCarImage(imgId, imgFilePath, imgHashName, imgExt, isThumbnail);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResolvedCarImage that = (ResolvedCarImage) o;
        return Objects.equals(imgId, that.imgId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(imgId);
    }
}

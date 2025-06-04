package com.papas.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Data
public class SaveCarEvent {
    private Long userId; // 판매자 고유 아이디
    private Long carId; // 중고차 고유 아이디
}

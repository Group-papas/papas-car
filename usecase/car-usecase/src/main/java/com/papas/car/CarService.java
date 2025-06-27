package com.papas.car;

import com.papas.ApplicationException;
import com.papas.Errors;
import com.papas.carimage.CarImage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CarService implements CarUsecase {

    private static final String DOT = ".";
    private static final String SLASH = "/";

    @Value("${img.car.dir}")
    private String imgCarDir;

    // car rdb port
    private final CarPort carPort;
    // car kafka port
    private final CarMessageProducePort carMessageProducePort;

    /**
     * 중고차를 저장하는 메서드
     */
    @Override
    public Car create(Request request) {
        // 1. DB save
        Car car = carPort.saveCar(
                Car.generate(request.getPrice(), request.getYear(), request.getManufacturer(), request.getModel(),
                        request.getCondition(), request.getEngine(), request.getOdometer(), request.getTitleStatus(),
                        request.getTransmission(), request.getDrive(), request.getSize(), request.getType(), request.getColor(),
                        request.getBrand(), request.getVin(), request.getLat(), request.getLon(), request.getPostingDate(),
                        request.getRegion(), request.getUserId(), request.getImgIds(), request.getHashtags()
                )
        );
        // 2. Kafka message 발행처리. with CarMessageProducePort
        SaveCarEvent saveCarEvent = SaveCarEvent.of(car.getUserId(), car.getCarId());
        carMessageProducePort.sendCarMessage(saveCarEvent);

        return car;
    }

    /**
     * 중고차 이미지를 저장하는 메서드
     */
    @Override
    public Long saveCarImage(SaveImageRequest request) {
        String originalFilename = request.getOriginalFilename();
        if (!StringUtils.hasText(originalFilename)) throw new ApplicationException(Errors.BAD_REQUEST);

        int lastDot = originalFilename.lastIndexOf(".");
        String originNameWithoutExt = originalFilename.substring(0, lastDot);
        String ext = originalFilename.substring(lastDot + 1);

        String uuid = UUID.randomUUID().toString();
        String hashDir = generatePath(uuid);

        // 고유한 파일 이름 생성 (중복 방지용 UUID 등)
        String fileName = imgCarDir + SLASH + hashDir + SLASH + uuid + DOT + ext;
        Path directory = Path.of(fileName);
        Path filePath = directory.resolve(fileName);

        // 파일 저장

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(request.getData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CarImage carImage = carPort.saveCarImage(
                CarImage.generate(originNameWithoutExt, uuid, hashDir, ext, request.isThumbnail(), null));
        return carImage.getImgId();
    }


    private String generatePath(String uuid) {
        String dir1 = uuid.substring(0, 2);
        String dir2 = uuid.substring(2, 4);

        String hashDir = SLASH + dir1 + SLASH + dir2;
        String directoryPath = imgCarDir + hashDir;
        // 디렉토리 존재 확인 및 생성
        try {
            Files.createDirectories(Paths.get(directoryPath));
        } catch (IOException e) {
            throw new ApplicationException("Failed to create directories", e);
        }
        return hashDir;
    }
}

package carrot.market.model.csv;

import carrot.market.town.domain.Town;
import carrot.market.town.domain.TownEtc;
import carrot.market.town.repository.TownEtcRepository;
import carrot.market.town.repository.TownRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CsvDataService {

    private final ExcelUtil excelUtil;
    private final TownRepository townRepository;
    private final TownEtcRepository townEtcRepository;

    @Transactional
    public void insertExcelData(MultipartFile file) {

        // 파일이 존재하지 않는 경우
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }

        // 확장자 유효성 검사, 엑셀 파일만 가능
        String fileName = file.getOriginalFilename();

        if (!fileName.endsWith(".xlsx")) {
            throw new IllegalArgumentException("엑셀 파일만 가능합니다.");
        }

        List<Map<String, Object>> listMap = null;

        // 엑셀의 셀 데이터를 가져와서 dto에 저장
        listMap = excelUtil.getListData(file, 1, 7);

        // db insert to town, townEtc
        listMap.forEach(map -> {
            if (isNotExistOnEtc(map)) {
                Town town = createTown(map);
                townRepository.save(town);
                return;
            }

            Town town = createTown(map);
            TownEtc townEtc = createTownEtc(map, town);

            townRepository.save(town);
            townEtcRepository.save(townEtc);

        });
        }

    private static boolean isNotExistOnEtc(Map<String, Object> map) {
        return map.get("4").toString().equals("");
    }

    private static TownEtc createTownEtc(Map<String, Object> map, Town town) {
        TownEtc townEtc = TownEtc.builder()
            .town(town)
            .etc(map.get("4").toString())
            .build();
        return townEtc;
    }

    private static Town createTown(Map<String, Object> map) {
        Town town = Town.builder()
            .city(map.get("1").toString())
            .district(map.get("2").toString())
            .townName(map.get("3").toString())
            .lat(Float.valueOf(map.get("5").toString()))
            .lng(Float.valueOf(map.get("6").toString()))
            .build();
        log.info("real town data : lat = {}, lng = {}", town.getLat(), town.getLng());
        return town;
    }
}


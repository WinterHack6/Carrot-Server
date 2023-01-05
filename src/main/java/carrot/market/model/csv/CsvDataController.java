package carrot.market.model.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * CSV 동네 데이터를 관리하는 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class CsvDataController {

    private final CsvDataService csvDataService;

    // get excel data
    @PostMapping("/addExcel")
    public String addExcel(MultipartFile file) {
        csvDataService.insertExcelData(file);
        return "OK";
    }

}

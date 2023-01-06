package carrot.market.Item.controller;

import carrot.market.Item.service.S3ImageUploadService;
import carrot.market.model.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Api(tags="ImageUploadController : 상품 이미지 업로드")
@RequestMapping("/api/items")
@RestController
@Slf4j
public class ImageUploadController {

    private final S3ImageUploadService s3ImageUploadService;

    /**
     * Amazon S3에 파일 업로드
     * @return 성공 시 200 Success와 함께 업로드 된 파일의 파일명 리스트 반환
     */
    @ApiOperation(value = "Amazon S3에 파일 업로드", notes = "Amazon S3에 파일 업로드 ")
    @PostMapping("/images")
    public Response uploadFile(@ApiParam(value="파일들(여러 파일 업로드 가능)", required = true)
                               @RequestPart List<MultipartFile> multipartFiles) {
      return Response.success(s3ImageUploadService.uploadFile(multipartFiles));
    }

    /**
     * Amazon S3에 업로드 된 파일을 삭제
     * @return 성공 시 200 Success
     */
    @ApiOperation(value = "Amazon S3에 업로드 된 파일을 삭제", notes = "Amazon S3에 업로드된 파일 삭제")
    @DeleteMapping("/image")
    public Response deleteFile(@ApiParam(value="파일 하나 삭제, 파일 이름 필요 ex)item-images/~", required = true)
                               @RequestParam String fileName) {
        s3ImageUploadService.deleteFile(fileName);
        return Response.success(null);
    }


}

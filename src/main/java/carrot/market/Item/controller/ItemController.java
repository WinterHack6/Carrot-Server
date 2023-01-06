package carrot.market.Item.controller;

import carrot.market.Item.dto.ItemCreateRequestDto;
import carrot.market.Item.service.ItemService;
import carrot.market.model.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/items")
@Api(tags="ItemController : 상품 게시글")
@RestController
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="상품 게시글 추가", notes="상품 게시글을 추가할 수 있다.")
    public Response create(
            @RequestBody ItemCreateRequestDto req
    ) {
        itemService.create(req);
        return Response.success();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@PathVariable Long id) {
        return Response.success(itemService.read(id));
    }
}

package carrot.market.Item.controller;

import carrot.market.Item.dto.ItemCreateRequestDto;
import carrot.market.Item.service.ItemService;
import carrot.market.model.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/items")
@RestController
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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

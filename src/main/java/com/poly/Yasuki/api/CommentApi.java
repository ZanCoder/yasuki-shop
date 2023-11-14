package com.poly.Yasuki.api;

import com.poly.Yasuki.service.EvaluateService;
import com.poly.Yasuki.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApi {
    private final EvaluateService evaluateService;

    @DeleteMapping("/admin/manager-comment/{productId}/delete")
    public ResponseEntity<?> doDeleteProduct(
            @PathVariable(name = "productId") Integer productId,
            @RequestParam(name = "id") Integer idComment){
        try{
            evaluateService.deleteById(idComment);
        }catch(Exception e){
            return ResponseEntity.status(500).body(MessageUtils.ERROR_FOREIGN_KEY);
        }
        return ResponseEntity.status(204).body("DELETED");
    }
}

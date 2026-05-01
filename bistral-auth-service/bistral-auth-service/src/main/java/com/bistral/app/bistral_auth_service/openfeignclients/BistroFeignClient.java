package com.bistral.app.bistral_auth_service.openfeignclients;


import com.bistral.app.bistral_auth_service.dtos.BistroContextDto;
import com.bistral.app.bistral_auth_service.dtos.BistroContextRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "bistral-bistro-service",
        path = "/bistros"
)
public interface BistroFeignClient {

    @PostMapping("/internal/context")
    List<BistroContextDto> getBistroContext(@RequestBody BistroContextRequestDto bistroContextRequestDto);
}

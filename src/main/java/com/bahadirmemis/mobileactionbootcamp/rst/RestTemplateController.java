package com.bahadirmemis.mobileactionbootcamp.rst;

import com.bahadirmemis.mobileactionbootcamp.rbt.MailSendRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rest-template")
public class RestTemplateController {

    @GetMapping("")
    public String getMailInfo(){

        String url = "http://localhost:8081/api/v1/mail-sender/mail-address";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String body = responseEntity.getBody();

        String mailAddress = body;

        String name = "Sadık Bahadır Memiş";

        return name + " / Mail adresi: " + mailAddress;
    }

    @GetMapping("/mail-send-request-dto")
    public MailSendRequestDto getMailSendRequestDto(){

        String url = "http://localhost:8081/api/v1/mail-sender/mail-send-request-dto";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MailSendRequestDto> responseEntity = restTemplate.getForEntity(url, MailSendRequestDto.class);

        return responseEntity.getBody();
    }

    @PostMapping("/mail-sender")
    public boolean sendMail(@RequestBody MailSendRequestDto mailSendRequestDto){

        String url = "http://localhost:8081/api/v1/mail-sender";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<MailSendRequestDto> request = new HttpEntity<>(mailSendRequestDto);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, Boolean.class);
//        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, mailSendRequestDto, Boolean.class);

        Boolean isSuccess = responseEntity.getBody();

        return isSuccess;
    }

}

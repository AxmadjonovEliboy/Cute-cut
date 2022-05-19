package uz.pdp.cutecutapp.services.auth;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.cutecutapp.dto.auth.AuthUserPhoneDto;
import uz.pdp.cutecutapp.dto.auth.OtpDto;
import uz.pdp.cutecutapp.dto.auth.SessionDto;
import uz.pdp.cutecutapp.dto.responce.AppErrorDto;
import uz.pdp.cutecutapp.dto.responce.DataDto;
import uz.pdp.cutecutapp.properties.OtpProperties;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class OtpService {

    private final OtpProperties otpProperties;

    public OtpService(OtpProperties otpProperties) {
        this.otpProperties = otpProperties;
    }

    public DataDto<AuthUserPhoneDto> send(AuthUserPhoneDto dto){
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            String url = otpProperties.getApi();
            OtpDto otpDto = new OtpDto(dto.phoneNumber,otpProperties.getOriginator(),)
            HttpPost httppost = new HttpPost(url);
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("data")) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new DataDto<>(sessionDto);
            }
            return new DataDto<>(new AppErrorDto("bad Request", " ", HttpStatus.BAD_REQUEST));

        } catch (IOException e) {
            return new DataDto<>(new AppErrorDto("bad request", "", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

}

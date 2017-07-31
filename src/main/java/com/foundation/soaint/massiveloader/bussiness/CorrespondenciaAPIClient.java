package com.foundation.soaint.massiveloader.bussiness;

import com.foundation.soaint.massiveloader.web.domain.CorrespondenciaVO;
import org.springframework.web.client.RestTemplate;

/**
 * Created by g2o on 30-Jul-17.
 */
public class CorrespondenciaAPIClient {
  public static void main(String[] argv) {
    final String uri = "http://192.168.3.242:28080/correspondencia-business-services/services/correspondencia-web-api/correspondencia";

    CorrespondenciaVO newEmployee = new CorrespondenciaVO();

    RestTemplate restTemplate = new RestTemplate();
    CorrespondenciaVO result = restTemplate.postForObject( uri, newEmployee, CorrespondenciaVO.class);

    System.out.println(result);
  }


}

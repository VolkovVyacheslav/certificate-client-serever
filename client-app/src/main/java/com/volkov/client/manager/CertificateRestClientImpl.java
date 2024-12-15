package com.volkov.client.manager;

import com.sun.javafx.binding.StringFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import javax.security.cert.Certificate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
public class CertificateRestClientImpl implements CertificateRestClient {

  private static List<Certificate> certificateModelList ;

  private final RestTemplate restTemplate;

  private final String url;

    @Override
    public List<Certificate> findAllCertificates(String filter) throws URISyntaxException {
        String uri = StringFormatter.format(url, filter).getValue();
        return this.restTemplate
                .getForObject(new URI(uri), this.certificateModelList.getClass());
    }
}

package com.ezhiyang.sdk.demo.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.ezhiyang.sdk.core.cache.RedisAuthCache;
import com.ezhiyang.sdk.core.context.SdkContext;
import com.ezhiyang.sdk.core.model.ClientConfig;

@Configuration
public class EzySdkConfiguration {

  @Value("${ezhiyang.url:https://testapis.ezhiyang.com/}")
  private String url;
  @Value("${ezhiyang.clientId:hoketest3}")
  private String clientId;
  @Value("${ezhiyang.secret:30b810b6-8097-4edc-a24e-869adab42549}")
  private String secret;
  @Value("${ezhiyang.privateKey:MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDqs0Gk0DyXu73UimAzUooKtWbq5EkLbjyOohGUTCrClok1YF/8hs9TTyRM5nOO2Ta6IdHWkAYUnvYULjDsL7H9rQibuZ4xUgW0b9OkOoDGmwk2XsUtpRKS5nZqOy7GGZLpht45gg0LyWzcSE0Ex0kEMGCXrRDxEcXlDp+jpRlikhjUSqx8ROi3YdiTGXMPhkl4q+vRWdsMCN5QlH2VcAQ4C4Mp/KGHlQrOq5LX3r0zB2xfqJ2Vt4jwWRmzFExBvEVIDFzgldpIqaTuXuG1zaOjoxKKpNmi/KwVNpPivWmRXicGXAMHPyG3VgN/ScJvGh0QWdRnrKcLlFD7hnHJh5mRAgMBAAECggEAbuCKpYMe7QvrrWpF8lkPD+oNGfEJNRY6E6QrK+Uj2lPltEq4bgN+FLxxyMaiVxz1BtANYxEdXkaOuI/t2tbZfp9tTqxNOL2oJksiMBbXWXxKyN3SxzKR6MZpsNwOx9WzGxH1EDQJV8Ur4ZFimVxQeWMlbuJ6+3hLBmB3OIc5y9+Thi/KJ/MLJ90j8N/NlqiBzHuMLtSILMwAqvAUkNetYZTOOdKGdrEEuraJdTX/5XCCf+nUe5e5gw2ydCwlJSK8lpbPbZ3bpYx9X2n/D0i5SaJutFynJzn5MeLYgyEww0sb3wpljPGaCjHnPj702TbrJo/vUd3v94mUFPvZokyKdQKBgQD+3bKApmJBTiNmlDQB22nG4DzM4+YVsT6wcC7Q+H2nxCKRnV/T1/+kQwpp1z3I0Vn+IuYnizvbcjk0tLmNXCTsnMtlZyiWn1PcG52TDZykJaQeJEu3gWkD9auEPLxZv//W10i7LLMe/eqIF3eG9QbqvElqtyDzkmAYloGDfYXVGwKBgQDrvpbpXhC7tgGEPnueWHePCAOgD7PcGTYJmnCTgrm0KZuiji+CLStan+B2TnKgIWlddyP54uzQdB42OVyP56Jtqw1SW5eBdwMXmQWI47WpLLgdrXyzM9Djtxh5MhUr7Te5EXJ6mky3NccvGlgSvralBkpLCCPEj3mHnDH/RVUywwKBgGxiHyzylMoszoUq9Urs4fi/F1wSdqA29e/WZTJc2iLkF8dQ45WVP5O9JsqHzDCARwL6GGIspdHWq1ksmdPxoYo1y5Fd4zbanrD11XbZzQlJMJfY25I4KE6UxNHBel+4dLYKa1Wd1raRSJGDWUM9JCni1F3bbtnmH5OVPZKLaMSNAoGAFoWFu1O+O4Ce0nh5rN38xhBY8FCvuSY7cF1txdnmTdzvvm7hAQm5q5W8hvDlqICopUI9kKDqSNvfCwUd362caee+hRC0/6xUAkwfKD3A3CGmkUdCs4gOu8KBNwre1Ox7t1WAX1AgZhxgJBLbOSc5IbjhHhvv9tZSIO9+I0oHYqsCgYAxy5/vkwIavhkJ+yY2H1mvCpfYX9oBN+u7x6EzpvLNNFN2YWDpn0jz0mZcP6RkDp4uA+PKa+6J1lhH+bUAsn3BzoGx9UORCdczKncdD2goN0duc/5z+6BMdKuUGFJPDjxoIzkY1j2H9TCQ79FJjZFvCnCtpsexOng6CL956V+1Pg==}")
  private String privateKey;
  
  @Bean
  public ClientConfig clientConfig() {
    return new ClientConfig(url, clientId, secret, privateKey);
  }
  
  /**
   * init context used default cache
   * @param clientConfig
   * @return
   */
//  @Bean
//  public SdkContext sdkContext(ClientConfig clientConfig) {
//    return new SdkContext(clientConfig);
//  }
  
  /**
   * init context used redis cache
   * @param clientConfig
   * @param redisTemplate
   * @return
   */
  @Bean
  public SdkContext sdkContext(ClientConfig clientConfig,RedisTemplate<Object, Object> redisTemplate) {
    RedisAuthCache cache = new RedisAuthCache(redisTemplate);
    return new SdkContext(clientConfig,cache);
  }
  
  
  
}

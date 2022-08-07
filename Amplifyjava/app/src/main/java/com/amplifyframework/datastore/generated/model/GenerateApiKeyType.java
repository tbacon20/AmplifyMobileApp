package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the GenerateApiKeyType type in your schema. */
public final class GenerateApiKeyType {
  private final String api_key;
  public String getApiKey() {
      return api_key;
  }
  
  private GenerateApiKeyType(String api_key) {
    this.api_key = api_key;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      GenerateApiKeyType generateApiKeyType = (GenerateApiKeyType) obj;
      return ObjectsCompat.equals(getApiKey(), generateApiKeyType.getApiKey());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getApiKey())
      .toString()
      .hashCode();
  }
  
  public static ApiKeyStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(api_key);
  }
  public interface ApiKeyStep {
    BuildStep apiKey(String apiKey);
  }
  

  public interface BuildStep {
    GenerateApiKeyType build();
  }
  

  public static class Builder implements ApiKeyStep, BuildStep {
    private String api_key;
    @Override
     public GenerateApiKeyType build() {
        
        return new GenerateApiKeyType(
          api_key);
    }
    
    @Override
     public BuildStep apiKey(String apiKey) {
        Objects.requireNonNull(apiKey);
        this.api_key = apiKey;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String apiKey) {
      super.apiKey(apiKey);
    }
    
    @Override
     public CopyOfBuilder apiKey(String apiKey) {
      return (CopyOfBuilder) super.apiKey(apiKey);
    }
  }
  
}

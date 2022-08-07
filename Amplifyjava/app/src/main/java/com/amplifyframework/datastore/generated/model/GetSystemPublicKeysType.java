package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the GetSystemPublicKeysType type in your schema. */
public final class GetSystemPublicKeysType {
  private final String system_date_time_expiring;
  private final String rsa_key_id;
  private final String rsa_public_key;
  private final String ecc_key_id;
  private final String ecc_public_key;
  public String getSystemDateTimeExpiring() {
      return system_date_time_expiring;
  }
  
  public String getRsaKeyId() {
      return rsa_key_id;
  }
  
  public String getRsaPublicKey() {
      return rsa_public_key;
  }
  
  public String getEccKeyId() {
      return ecc_key_id;
  }
  
  public String getEccPublicKey() {
      return ecc_public_key;
  }
  
  private GetSystemPublicKeysType(String system_date_time_expiring, String rsa_key_id, String rsa_public_key, String ecc_key_id, String ecc_public_key) {
    this.system_date_time_expiring = system_date_time_expiring;
    this.rsa_key_id = rsa_key_id;
    this.rsa_public_key = rsa_public_key;
    this.ecc_key_id = ecc_key_id;
    this.ecc_public_key = ecc_public_key;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      GetSystemPublicKeysType getSystemPublicKeysType = (GetSystemPublicKeysType) obj;
      return ObjectsCompat.equals(getSystemDateTimeExpiring(), getSystemPublicKeysType.getSystemDateTimeExpiring()) &&
              ObjectsCompat.equals(getRsaKeyId(), getSystemPublicKeysType.getRsaKeyId()) &&
              ObjectsCompat.equals(getRsaPublicKey(), getSystemPublicKeysType.getRsaPublicKey()) &&
              ObjectsCompat.equals(getEccKeyId(), getSystemPublicKeysType.getEccKeyId()) &&
              ObjectsCompat.equals(getEccPublicKey(), getSystemPublicKeysType.getEccPublicKey());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getSystemDateTimeExpiring())
      .append(getRsaKeyId())
      .append(getRsaPublicKey())
      .append(getEccKeyId())
      .append(getEccPublicKey())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(system_date_time_expiring,
      rsa_key_id,
      rsa_public_key,
      ecc_key_id,
      ecc_public_key);
  }
  public interface BuildStep {
    GetSystemPublicKeysType build();
    BuildStep systemDateTimeExpiring(String systemDateTimeExpiring);
    BuildStep rsaKeyId(String rsaKeyId);
    BuildStep rsaPublicKey(String rsaPublicKey);
    BuildStep eccKeyId(String eccKeyId);
    BuildStep eccPublicKey(String eccPublicKey);
  }
  

  public static class Builder implements BuildStep {
    private String system_date_time_expiring;
    private String rsa_key_id;
    private String rsa_public_key;
    private String ecc_key_id;
    private String ecc_public_key;
    @Override
     public GetSystemPublicKeysType build() {
        
        return new GetSystemPublicKeysType(
          system_date_time_expiring,
          rsa_key_id,
          rsa_public_key,
          ecc_key_id,
          ecc_public_key);
    }
    
    @Override
     public BuildStep systemDateTimeExpiring(String systemDateTimeExpiring) {
        this.system_date_time_expiring = systemDateTimeExpiring;
        return this;
    }
    
    @Override
     public BuildStep rsaKeyId(String rsaKeyId) {
        this.rsa_key_id = rsaKeyId;
        return this;
    }
    
    @Override
     public BuildStep rsaPublicKey(String rsaPublicKey) {
        this.rsa_public_key = rsaPublicKey;
        return this;
    }
    
    @Override
     public BuildStep eccKeyId(String eccKeyId) {
        this.ecc_key_id = eccKeyId;
        return this;
    }
    
    @Override
     public BuildStep eccPublicKey(String eccPublicKey) {
        this.ecc_public_key = eccPublicKey;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String systemDateTimeExpiring, String rsaKeyId, String rsaPublicKey, String eccKeyId, String eccPublicKey) {
      super.systemDateTimeExpiring(systemDateTimeExpiring)
        .rsaKeyId(rsaKeyId)
        .rsaPublicKey(rsaPublicKey)
        .eccKeyId(eccKeyId)
        .eccPublicKey(eccPublicKey);
    }
    
    @Override
     public CopyOfBuilder systemDateTimeExpiring(String systemDateTimeExpiring) {
      return (CopyOfBuilder) super.systemDateTimeExpiring(systemDateTimeExpiring);
    }
    
    @Override
     public CopyOfBuilder rsaKeyId(String rsaKeyId) {
      return (CopyOfBuilder) super.rsaKeyId(rsaKeyId);
    }
    
    @Override
     public CopyOfBuilder rsaPublicKey(String rsaPublicKey) {
      return (CopyOfBuilder) super.rsaPublicKey(rsaPublicKey);
    }
    
    @Override
     public CopyOfBuilder eccKeyId(String eccKeyId) {
      return (CopyOfBuilder) super.eccKeyId(eccKeyId);
    }
    
    @Override
     public CopyOfBuilder eccPublicKey(String eccPublicKey) {
      return (CopyOfBuilder) super.eccPublicKey(eccPublicKey);
    }
  }
  
}

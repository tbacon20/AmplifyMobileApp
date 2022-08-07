package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the sendUserPublicKeysType type in your schema. */
public final class sendUserPublicKeysType {
  private final String user_id;
  private final String ecc_key_id;
  private final String ecc_public_key;
  private final Integer ttl;
  public String getUserId() {
      return user_id;
  }
  
  public String getEccKeyId() {
      return ecc_key_id;
  }
  
  public String getEccPublicKey() {
      return ecc_public_key;
  }
  
  public Integer getTtl() {
      return ttl;
  }
  
  private sendUserPublicKeysType(String user_id, String ecc_key_id, String ecc_public_key, Integer ttl) {
    this.user_id = user_id;
    this.ecc_key_id = ecc_key_id;
    this.ecc_public_key = ecc_public_key;
    this.ttl = ttl;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      sendUserPublicKeysType sendUserPublicKeysType = (sendUserPublicKeysType) obj;
      return ObjectsCompat.equals(getUserId(), sendUserPublicKeysType.getUserId()) &&
              ObjectsCompat.equals(getEccKeyId(), sendUserPublicKeysType.getEccKeyId()) &&
              ObjectsCompat.equals(getEccPublicKey(), sendUserPublicKeysType.getEccPublicKey()) &&
              ObjectsCompat.equals(getTtl(), sendUserPublicKeysType.getTtl());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getUserId())
      .append(getEccKeyId())
      .append(getEccPublicKey())
      .append(getTtl())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(user_id,
      ecc_key_id,
      ecc_public_key,
      ttl);
  }
  public interface BuildStep {
    sendUserPublicKeysType build();
    BuildStep userId(String userId);
    BuildStep eccKeyId(String eccKeyId);
    BuildStep eccPublicKey(String eccPublicKey);
    BuildStep ttl(Integer ttl);
  }
  

  public static class Builder implements BuildStep {
    private String user_id;
    private String ecc_key_id;
    private String ecc_public_key;
    private Integer ttl;
    @Override
     public sendUserPublicKeysType build() {
        
        return new sendUserPublicKeysType(
          user_id,
          ecc_key_id,
          ecc_public_key,
          ttl);
    }
    
    @Override
     public BuildStep userId(String userId) {
        this.user_id = userId;
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
    
    @Override
     public BuildStep ttl(Integer ttl) {
        this.ttl = ttl;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String userId, String eccKeyId, String eccPublicKey, Integer ttl) {
      super.userId(userId)
        .eccKeyId(eccKeyId)
        .eccPublicKey(eccPublicKey)
        .ttl(ttl);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder eccKeyId(String eccKeyId) {
      return (CopyOfBuilder) super.eccKeyId(eccKeyId);
    }
    
    @Override
     public CopyOfBuilder eccPublicKey(String eccPublicKey) {
      return (CopyOfBuilder) super.eccPublicKey(eccPublicKey);
    }
    
    @Override
     public CopyOfBuilder ttl(Integer ttl) {
      return (CopyOfBuilder) super.ttl(ttl);
    }
  }
  
}

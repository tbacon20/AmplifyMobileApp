package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the GetSignedSessionIDType type in your schema. */
public final class GetSignedSessionIDType {
  private final String signed_session_id;
  private final String clear_session_id;
  public String getSignedSessionId() {
      return signed_session_id;
  }
  
  public String getClearSessionId() {
      return clear_session_id;
  }
  
  private GetSignedSessionIDType(String signed_session_id, String clear_session_id) {
    this.signed_session_id = signed_session_id;
    this.clear_session_id = clear_session_id;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      GetSignedSessionIDType getSignedSessionIdType = (GetSignedSessionIDType) obj;
      return ObjectsCompat.equals(getSignedSessionId(), getSignedSessionIdType.getSignedSessionId()) &&
              ObjectsCompat.equals(getClearSessionId(), getSignedSessionIdType.getClearSessionId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getSignedSessionId())
      .append(getClearSessionId())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(signed_session_id,
      clear_session_id);
  }
  public interface BuildStep {
    GetSignedSessionIDType build();
    BuildStep signedSessionId(String signedSessionId);
    BuildStep clearSessionId(String clearSessionId);
  }
  

  public static class Builder implements BuildStep {
    private String signed_session_id;
    private String clear_session_id;
    @Override
     public GetSignedSessionIDType build() {
        
        return new GetSignedSessionIDType(
          signed_session_id,
          clear_session_id);
    }
    
    @Override
     public BuildStep signedSessionId(String signedSessionId) {
        this.signed_session_id = signedSessionId;
        return this;
    }
    
    @Override
     public BuildStep clearSessionId(String clearSessionId) {
        this.clear_session_id = clearSessionId;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String signedSessionId, String clearSessionId) {
      super.signedSessionId(signedSessionId)
        .clearSessionId(clearSessionId);
    }
    
    @Override
     public CopyOfBuilder signedSessionId(String signedSessionId) {
      return (CopyOfBuilder) super.signedSessionId(signedSessionId);
    }
    
    @Override
     public CopyOfBuilder clearSessionId(String clearSessionId) {
      return (CopyOfBuilder) super.clearSessionId(clearSessionId);
    }
  }
  
}

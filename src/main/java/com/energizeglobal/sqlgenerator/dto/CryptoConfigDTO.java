package com.energizeglobal.sqlgenerator.dto;

public class CryptoConfigDTO {

  private long id;

  private String protocolOne;

  private String ProtocolTwo;

  private String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProtocolOne() {
    return protocolOne;
  }

  public void setProtocolOne(String protocolOne) {
    this.protocolOne = protocolOne;
  }

  public String getProtocolTwo() {
    return ProtocolTwo;
  }

  public void setProtocolTwo(String protocolTwo) {
    ProtocolTwo = protocolTwo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "CryptoConfigDTO{"
        + "id="
        + id
        + ", protocolOne='"
        + protocolOne
        + '\''
        + ", ProtocolTwo='"
        + ProtocolTwo
        + '\''
        + ", description='"
        + description
        + '\''
        + '}';
  }
}

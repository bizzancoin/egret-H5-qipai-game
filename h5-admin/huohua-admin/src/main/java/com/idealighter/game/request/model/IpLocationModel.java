package com.idealighter.game.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class IpLocationModel {
  private int ret;
  @JsonProperty(required = false)
  private Integer start;
  @JsonProperty(required = false)
  private Integer end;
  @JsonProperty(required = false)
  private String country;
  @JsonProperty(required = false)
  private String province;
  @JsonProperty(required = false)
  private String city;
  @JsonProperty(required = false)
  private String district;
  @JsonProperty(required = false)
  private String isp;
  @JsonProperty(required = false)
  private String type;
  @JsonProperty(required = false)
  private String desc;
}

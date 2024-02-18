package com.idealighter.game.dao.manage.po;

import java.io.Serializable;

/**
 * @author
 */
public class Config implements Serializable {
  private Integer id;

  private String shortname;

  private String companyname;

  private String tel1;

  private String tel2;

  private String qq1;

  private String qq2;

  private String qq3;

  private String email;

  private String icp;

  private String statisticalcode;

  private static final long serialVersionUID = 1L;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getShortname() {
    return shortname;
  }

  public void setShortname(String shortname) {
    this.shortname = shortname;
  }

  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }

  public String getTel1() {
    return tel1;
  }

  public void setTel1(String tel1) {
    this.tel1 = tel1;
  }

  public String getTel2() {
    return tel2;
  }

  public void setTel2(String tel2) {
    this.tel2 = tel2;
  }

  public String getQq1() {
    return qq1;
  }

  public void setQq1(String qq1) {
    this.qq1 = qq1;
  }

  public String getQq2() {
    return qq2;
  }

  public void setQq2(String qq2) {
    this.qq2 = qq2;
  }

  public String getQq3() {
    return qq3;
  }

  public void setQq3(String qq3) {
    this.qq3 = qq3;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIcp() {
    return icp;
  }

  public void setIcp(String icp) {
    this.icp = icp;
  }

  public String getStatisticalcode() {
    return statisticalcode;
  }

  public void setStatisticalcode(String statisticalcode) {
    this.statisticalcode = statisticalcode;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    Config other = (Config) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        && (this.getShortname() == null ? other.getShortname() == null
            : this.getShortname().equals(other.getShortname()))
        && (this.getCompanyname() == null ? other.getCompanyname() == null
            : this.getCompanyname().equals(other.getCompanyname()))
        && (this.getTel1() == null ? other.getTel1() == null
            : this.getTel1().equals(other.getTel1()))
        && (this.getTel2() == null ? other.getTel2() == null
            : this.getTel2().equals(other.getTel2()))
        && (this.getQq1() == null ? other.getQq1() == null : this.getQq1().equals(other.getQq1()))
        && (this.getQq2() == null ? other.getQq2() == null : this.getQq2().equals(other.getQq2()))
        && (this.getQq3() == null ? other.getQq3() == null : this.getQq3().equals(other.getQq3()))
        && (this.getEmail() == null ? other.getEmail() == null
            : this.getEmail().equals(other.getEmail()))
        && (this.getIcp() == null ? other.getIcp() == null : this.getIcp().equals(other.getIcp()))
        && (this.getStatisticalcode() == null ? other.getStatisticalcode() == null
            : this.getStatisticalcode().equals(other.getStatisticalcode()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getShortname() == null) ? 0 : getShortname().hashCode());
    result = prime * result + ((getCompanyname() == null) ? 0 : getCompanyname().hashCode());
    result = prime * result + ((getTel1() == null) ? 0 : getTel1().hashCode());
    result = prime * result + ((getTel2() == null) ? 0 : getTel2().hashCode());
    result = prime * result + ((getQq1() == null) ? 0 : getQq1().hashCode());
    result = prime * result + ((getQq2() == null) ? 0 : getQq2().hashCode());
    result = prime * result + ((getQq3() == null) ? 0 : getQq3().hashCode());
    result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
    result = prime * result + ((getIcp() == null) ? 0 : getIcp().hashCode());
    result =
        prime * result + ((getStatisticalcode() == null) ? 0 : getStatisticalcode().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", shortname=").append(shortname);
    sb.append(", companyname=").append(companyname);
    sb.append(", tel1=").append(tel1);
    sb.append(", tel2=").append(tel2);
    sb.append(", qq1=").append(qq1);
    sb.append(", qq2=").append(qq2);
    sb.append(", qq3=").append(qq3);
    sb.append(", email=").append(email);
    sb.append(", icp=").append(icp);
    sb.append(", statisticalcode=").append(statisticalcode);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}

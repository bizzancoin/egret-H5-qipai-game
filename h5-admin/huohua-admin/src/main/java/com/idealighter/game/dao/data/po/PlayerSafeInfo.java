package com.idealighter.game.dao.data.po;

import java.io.Serializable;

/**
 * @author 
 */
public class PlayerSafeInfo implements Serializable {
    /**
     * 玩家id
     */
    private Integer id;

    /**
     * 玩家id
     */
    private Long playerId;

    /**
     * 是否校验手机号
     */
    private Boolean validatePhone;

    /**
     * 是否校验email
     */
    private Boolean validateEmail;

    /**
     * 密保第一个问题
     */
    private String firstPwdProtectQ;

    /**
     * 密保第一个问题答案
     */
    private String firstPwdProtectA;

    /**
     * 密保第二个问题
     */
    private String secondPwdProtectQ;

    /**
     * 密保第二个问题答案
     */
    private String secondPwdProtectA;

    /**
     * 登录手机验证
     */
    private Boolean loginPhoneVerify;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Boolean getValidatePhone() {
        return validatePhone;
    }

    public void setValidatePhone(Boolean validatePhone) {
        this.validatePhone = validatePhone;
    }

    public Boolean getValidateEmail() {
        return validateEmail;
    }

    public void setValidateEmail(Boolean validateEmail) {
        this.validateEmail = validateEmail;
    }

    public String getFirstPwdProtectQ() {
        return firstPwdProtectQ;
    }

    public void setFirstPwdProtectQ(String firstPwdProtectQ) {
        this.firstPwdProtectQ = firstPwdProtectQ;
    }

    public String getFirstPwdProtectA() {
        return firstPwdProtectA;
    }

    public void setFirstPwdProtectA(String firstPwdProtectA) {
        this.firstPwdProtectA = firstPwdProtectA;
    }

    public String getSecondPwdProtectQ() {
        return secondPwdProtectQ;
    }

    public void setSecondPwdProtectQ(String secondPwdProtectQ) {
        this.secondPwdProtectQ = secondPwdProtectQ;
    }

    public String getSecondPwdProtectA() {
        return secondPwdProtectA;
    }

    public void setSecondPwdProtectA(String secondPwdProtectA) {
        this.secondPwdProtectA = secondPwdProtectA;
    }

    public Boolean getLoginPhoneVerify() {
        return loginPhoneVerify;
    }

    public void setLoginPhoneVerify(Boolean loginPhoneVerify) {
        this.loginPhoneVerify = loginPhoneVerify;
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
        PlayerSafeInfo other = (PlayerSafeInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getValidatePhone() == null ? other.getValidatePhone() == null : this.getValidatePhone().equals(other.getValidatePhone()))
            && (this.getValidateEmail() == null ? other.getValidateEmail() == null : this.getValidateEmail().equals(other.getValidateEmail()))
            && (this.getFirstPwdProtectQ() == null ? other.getFirstPwdProtectQ() == null : this.getFirstPwdProtectQ().equals(other.getFirstPwdProtectQ()))
            && (this.getFirstPwdProtectA() == null ? other.getFirstPwdProtectA() == null : this.getFirstPwdProtectA().equals(other.getFirstPwdProtectA()))
            && (this.getSecondPwdProtectQ() == null ? other.getSecondPwdProtectQ() == null : this.getSecondPwdProtectQ().equals(other.getSecondPwdProtectQ()))
            && (this.getSecondPwdProtectA() == null ? other.getSecondPwdProtectA() == null : this.getSecondPwdProtectA().equals(other.getSecondPwdProtectA()))
            && (this.getLoginPhoneVerify() == null ? other.getLoginPhoneVerify() == null : this.getLoginPhoneVerify().equals(other.getLoginPhoneVerify()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getValidatePhone() == null) ? 0 : getValidatePhone().hashCode());
        result = prime * result + ((getValidateEmail() == null) ? 0 : getValidateEmail().hashCode());
        result = prime * result + ((getFirstPwdProtectQ() == null) ? 0 : getFirstPwdProtectQ().hashCode());
        result = prime * result + ((getFirstPwdProtectA() == null) ? 0 : getFirstPwdProtectA().hashCode());
        result = prime * result + ((getSecondPwdProtectQ() == null) ? 0 : getSecondPwdProtectQ().hashCode());
        result = prime * result + ((getSecondPwdProtectA() == null) ? 0 : getSecondPwdProtectA().hashCode());
        result = prime * result + ((getLoginPhoneVerify() == null) ? 0 : getLoginPhoneVerify().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", playerId=").append(playerId);
        sb.append(", validatePhone=").append(validatePhone);
        sb.append(", validateEmail=").append(validateEmail);
        sb.append(", firstPwdProtectQ=").append(firstPwdProtectQ);
        sb.append(", firstPwdProtectA=").append(firstPwdProtectA);
        sb.append(", secondPwdProtectQ=").append(secondPwdProtectQ);
        sb.append(", secondPwdProtectA=").append(secondPwdProtectA);
        sb.append(", loginPhoneVerify=").append(loginPhoneVerify);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
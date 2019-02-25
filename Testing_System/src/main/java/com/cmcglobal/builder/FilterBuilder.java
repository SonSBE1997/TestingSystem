package com.cmcglobal.builder;

import java.sql.Date;

public class FilterBuilder {
  private String categoryName;
  private String roleName;
  private Integer numberOfQuestion;
  private Date createAt;
  private Float duration;
  private String status;

  public Float getDuration() {
    return duration;
  }

  public Integer getNumberOfQuestion() {
    return numberOfQuestion;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public String getStatus() {
    return status;
  }

  public String getRoleName() {
    return roleName;
  }

  public Date getCreateAt() {
    return createAt;
  }

  /**
   * Author: ndvan.
   * Created date: Feb 23, 2019
   * Created time: 9:37:17 AM
   * Description: - .
   * @param builder - Builder.
   */
  public FilterBuilder(Builder builder) {

    this.categoryName = builder.categoryName;
    this.roleName = builder.roleName;
    this.createAt = builder.createAt;
    this.numberOfQuestion = builder.numberOfQuestion;
    this.duration = builder.duration;
    this.status = builder.status;
  }

  public static class Builder {

    private String categoryName;
    private String roleName;
    private Date createAt;
    private Integer numberOfQuestion;
    private Float duration;
    private String status;

    public Builder setNumberOfQuestion(Integer numberOfQuestion) {
      this.numberOfQuestion = numberOfQuestion;
      return this;
    }

    public Builder setDuration(Float duration) {
      this.duration = duration;
      return this;
    }

    public Builder setCaterogyName(String categoryName) {
      this.categoryName = categoryName;
      return this;
    }

    public Builder setStatus(String status) {
      this.status = status;
      return this;
    }

    public Builder setRoleName(String roleName) {
      this.roleName = roleName;
      return this;
    }

    public Builder setCreateAt(Date createAt) {
      this.createAt = createAt;
      return this;
    }

    public FilterBuilder builder() {
      return new FilterBuilder(this);
    }

  }

}

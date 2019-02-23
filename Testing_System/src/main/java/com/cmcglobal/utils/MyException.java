package com.cmcglobal.utils;

public class MyException extends Exception {
  private static final long serialVersionUID = 1L;

  private int idException;
  private String messException;

  /**
   * Author: hai95.
   * Created date: Feb 23, 2019
   * Created time: 6:24:07 PM
   * Description: - .
   * @param idException - exception.
   * @param messException - message.
   */
  public MyException(int idException, String messException) {
    super();
    this.idException = idException;
    this.messException = messException;
  }

  public MyException() {
    super();
  }

  public int getIdException() {
    return idException;
  }

  public void setIdException(int idException) {
    this.idException = idException;
  }

  public String getMessException() {
    return messException;
  }

  public void setMessException(String messException) {
    this.messException = messException;
  }
}

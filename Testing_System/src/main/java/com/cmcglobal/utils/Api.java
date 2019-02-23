/**
 * Project name: Testing_System
 * Package name: com.cmcglobal.utils
 * File name: Api.java
 * Author: Sanero.
 * Created date: Feb 20, 2019
 * Created time: 10:56:12 AM
 */

package com.cmcglobal.utils;

/*
 * @author Sanero.
 * Created date: Feb 20, 2019
 * Created time: 10:56:12 AM
 * Description: TODO - API.
 */
public interface Api {
  public static final String BASE_URL_CORS = "http://localhost:4200";

  /*
   * @author Sanero.
   * Created date: Feb 20, 2019
   * Created time: 10:56:37 AM
   * Description: TODO - API of Exam Module.
   */
  interface Exam {
    public static final String BASE_URL = "/exam";
    public static final String CREATE = "/create";
    public static final String GET_ALL = "/listExams";
    public static final String GET_BY_PAGE = "listExams/pagination";
    public static final String EXPORT = "/export/{id}";
    public static final String GET_ONE = "/{id}";
    public static final String APPROVE = "/approve";
    public static final String REMOVE_QUESTION = "/remove-question";
    public static final String ADD_QUESTION = "/add-question";
    public static final String RANDOM_QUESTION = "/random-question";
    public static final String IS_EMPTY_QUESTION = "/is-empty/{examId}";
    public static final String FILTER = "/filter";
    public static final String UPDATE_COMMON = "/update/update-common/{examId}";
    public static final String IMPORT_EXCEL = "/import-excel-file";
  }
}

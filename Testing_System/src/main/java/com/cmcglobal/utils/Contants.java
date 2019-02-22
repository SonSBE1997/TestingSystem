package com.cmcglobal.utils;

public interface Contants {

  interface Exam {
    // Query for list exam
    public static final String QUERY_PAGE_ALL = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId";
    public static final String QUERY_PAGE_ALL_SORT_BY_USER_CREATED_ASC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY u.fullName asc";
    public static final String QUERY_PAGE_ALL_SORT_BY_USER_CREATED_DESC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY u.fullName desc";
    public static final String QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_ASC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY c.categoryName asc";
    public static final String QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_DESC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY c.categoryName desc";
 
    //Name of colum
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ID = "examId";
    public static final String COLUMN_DURATION ="duration";
    public static final String COLUMN_NUMBER_OF_QUESTION = "numberOfQuestion";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_CREATE_AT ="createAt";
    public static final String COLUMN_USER_CREATED = "userCreated";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_CATEGORY_NAME = "category";
    
    public static final String STATUS_IS_ASCENDING = "asc";
    public static final String STATUS_IS_DESCENDING = "desc";
  
  }
}

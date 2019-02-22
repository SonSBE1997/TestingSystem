package com.cmcglobal.utils;

public interface Contants {

  interface Exam {
    // Query for list exam
    public static final String QUERY_PAGE_ALL = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId";
    public static final String QUERY_PAGE_ALL_SORT_BY_USER_CREATED_ASC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY u.fullName asc";
    public static final String QUERY_PAGE_ALL_SORT_BY_USER_CREATED_DESC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY u.fullName desc";
    public static final String QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_ASC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY c.categoryName asc";
    public static final String QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_DESC = "Select e FROM Exam e, User u, Category c WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) GROUP BY e.examId ORDER BY c.categoryName desc";
  }
  
  interface ExcelTemplate {
	  public static final String COLUMN_0 = "title";
	  public static final String COLUMN_1 = "duration";
	  public static final String COLUMN_2 = "categoryId";
	  public static final String COLUMN_3 = "note";
	  public static final String COLUMN_4 = "numberOfQuestion";
  }
}

package com.cmcglobal.utils;

public interface Constants {

  interface Exam {
    // Query for list exam
    public static final String QUERY_PAGE_ALL = "Select e FROM Exam e, User u, Category c "
        + "WHERE e.userCreated = u and e.category = c and (e.title like %:searchInput% "
        + "OR u.fullName like %:searchInput% OR c.categoryName like %:searchInput% "
        + "OR e.duration like %:searchInput% OR e.numberOfQuestion like %:searchInput% "
        + "OR e.status like %:searchInput%) GROUP BY e.examId";
    public static final String QUERY_PAGE_ALL_SORT_BY_USER_CREATED_ASC = "Select e "
        + "FROM Exam e, User u, Category c "
        + "WHERE e.userCreated = u and e.category = c and "
        + "(e.title like %:searchInput% OR u.fullName like %:searchInput% "
        + "OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% "
        + "OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) "
        + "GROUP BY e.examId ORDER BY u.fullName asc";
    public static final String QUERY_PAGE_ALL_SORT_BY_USER_CREATED_DESC = "Select e FROM Exam e,"
        + " User u, Category c WHERE e.userCreated = u and e.category = c and "
        + "(e.title like %:searchInput% OR u.fullName like %:searchInput% "
        + "OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% "
        + "OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) "
        + "GROUP BY e.examId ORDER BY u.fullName desc";
    public static final String QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_ASC = "Select e FROM Exam e, "
        + "User u, Category c WHERE e.userCreated = u and e.category = c and "
        + "(e.title like %:searchInput% OR u.fullName like %:searchInput% "
        + "OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% "
        + "OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) "
        + "GROUP BY e.examId ORDER BY c.categoryName asc";
    public static final String QUERY_PAGE_ALL_SORT_BY_CATEGORY_NAME_DESC = "Select e FROM Exam e, "
        + "User u, Category c WHERE e.userCreated = u and e.category = c and "
        + "(e.title like %:searchInput% OR u.fullName like %:searchInput% "
        + "OR c.categoryName like %:searchInput% OR e.duration like %:searchInput% "
        + "OR e.numberOfQuestion like %:searchInput% OR e.status like %:searchInput%) "

        + "GROUP BY e.examId ORDER BY c.categoryName desc";
    // Response Status
    public static final String OK = "Ok";
    public static final String NOT_OK = "Not ok";

    // Logging
    public static final String CREATE_FAILED = "create failed: ";
    public static final String GET_ALL_FAILED = "get all exam failed: ";
    public static final String GET_ONE_FAILED = "get one exam by id failed: ";
    public static final String DELETE_EXAM_FAILED = "delete exam failed: ";
    public static final String INSERT_EXAM_FAILED = "insert exam failed: ";
    public static final String UPDATE_EXAM_FAILED = "update exam failed: ";
    public static final String FILTER_EXAM_FAILED = "filter exam failed: ";
    public static final String APPROVE_FAILED = "approve exam failed: ";
    public static final String GET_PAGE_EXAM_FAILED = "get page exam failed: ";
    public static final String GET_EXAM_SORT_BY_USER_ASC_FAILED = "get exam sort "
        + "by user created asc failed: ";
    public static final String GET_EXAM_SORT_BY_USER_DES_FAILED = "get exam sort "
        + "by user created des failed: ";
    public static final String GET_EXAM_SORT_BY_CATEGORY_ASC_FAILED = "get exam sort "
        + "by user created des failed: ";
    public static final String GET_EXAM_SORT_BY_CATEGORY_DES_FAILED = "get exam sort "
        + "by user created des failed: ";
    public static final String RANDOM_QUESTION_FAILED = "random exam failed: ";
    public static final String REMOVE_QUESTION_FAILED = "remove question from exam failed: ";
    public static final String ADD_QUESTION_FAILED = "add question to exam failed: ";
    public static final String GENERATE_EXAM_ID_FAILED = "generate exam id failed: ";
    // Status
    public static final String STATUS_PUBLIC = "Public";
    public static final String STATUS_DRAFT = "Draft";

    // Generate Id.
    public static final String ID_START = "Exam001";
    public static final String ID_NINE = "Exam00";
    public static final String ID_DECADE = "Exam0";
    public static final String ID_HUNDERED = "Exam";

    // Sort order.
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    // Column name
    public static final String TITLE = "title";
    public static final String EXAM_ID = "examId";
    public static final String DURATION = "duration";
    public static final String STATUS = "status";
    public static final String CREATED_AT = "createAt";
    public static final String USER_CREATED = "userCreated";
    public static final String NUMBER_OF_QUESTION = "numberOfQuestion";
    public static final String CATEGORY = "category";
  }

  // Static for read excel
  interface ExcelTemplate {
    public static final int COLUMN_INDEX_TITLE = 0;
    public static final int COLUMN_INDEX_DURATION = 1;
    public static final int COLUMN_INDEX_CATEGORYID = 2;
    public static final int COLUMN_INDEX_NOTE = 3;
    public static final int COLUMN_INDEX_NUMBEROFQUES = 4;

    public static final String COLUMN_0 = "title";
    public static final String COLUMN_1 = "duration";
    public static final String COLUMN_2 = "categoryId";
    public static final String COLUMN_3 = "note";
    public static final String COLUMN_4 = "numberOfQuestion";
  }
}

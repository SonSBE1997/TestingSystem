package com.cmcglobal.service;

import java.util.List;

import com.cmcglobal.entity.Test;
import com.cmcglobal.entity.TestDetail;

/**
 * @author User
 *
 */
public interface TestService {

    /**
     * Create by: VuThuy - CMC
      Create date: Feb 18, 2019
      Modifier: VuThuy
      Modified date: Feb 18, 2019
      Description: ....
      Version 1.0
      @return
     */
    List<Test> findAll();

    /**
     * Create by: VuThuy - CMC
      Create date: Feb 18, 2019
      Modifier: VuThuy
      Modified date: Feb 18, 2019
      Description: ....
      Version 1.0
      @param test
     */
    public ServiceResult insertTest(Test test);

    /**
     * Create by: VuThuy - CMC
      Create date: Feb 18, 2019
      Modifier: VuThuy
      Modified date: Feb 18, 2019
      Description: ....
      Version 1.0
      @param id
     */
    public ServiceResult deleteTest(Integer id);
    
    /**
     * Create by: VuThuy - CMC
      Create date: Feb 19, 2019
      Modifier: VuThuy
      Modified date: Feb 19, 2019
      Description: ....
      Version 1.0
      @param id
      @return
     */
    Test findOne(Integer id);
    
    /**
     * Create by: VuThuy - CMC
      Create date: Feb 19, 2019
      Modifier: VuThuy
      Modified date: Feb 19, 2019
      Description: findBySemesterID in table test.
      Version 1.0
      @param id
      @return
     */
    List<Test> findBySemesterID(String id);
    
    List<TestDetail> getTestDetail(String userId, String semesterId);
    
}

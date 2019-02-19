package com.cmcglobal.custorm;

import java.util.List;

import com.cmcglobal.builder.FilterBuilder;
import com.cmcglobal.entity.Exam;

public interface FilterCustorm {
	List<Exam> findAll(FilterBuilder builder);
}

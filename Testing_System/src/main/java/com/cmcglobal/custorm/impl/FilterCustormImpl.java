package com.cmcglobal.custorm.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.cmcglobal.builder.FilterBuilder;
import com.cmcglobal.custorm.FilterCustorm;
import com.cmcglobal.entity.Exam;

@Repository
public class FilterCustormImpl implements FilterCustorm {
  @PersistenceContext
  private EntityManager entitymanager;

  @SuppressWarnings("unchecked")
  @Override
  public List<Exam> findAll(FilterBuilder filterBuilder) {
    StringBuilder builder = new StringBuilder("SELECT ex FROM Exam ex ");
    if (StringUtils.isNotBlank(filterBuilder.getCategoryName())) {
      builder.append("JOIN ex.category ca");
    }

    builder.append(" WHERE 1=1 ");
    builder = buildQuery(builder, filterBuilder);
    Query query = entitymanager.createQuery(builder.toString());
    return query.getResultList();
  }

  private StringBuilder buildQuery(StringBuilder sql,
      FilterBuilder filterBuilder) {
    Field[] fields = FilterBuilder.class.getDeclaredFields();
    for (Field field : fields) {
      if (!"categoryName".equals(field.getName())) {

        // get Type
        String fieldType = field.getType().getName();

        if ("java.lang.String".equals(fieldType)) {

          String value = getValueFiled(field, filterBuilder, String.class);

          if (StringUtils.isNotBlank(value)) {
            sql.append("AND LOWER(ex." + field.getName() + ") LIKE '%"
                + value.toLowerCase() + "%'");
          }

        } else if ("java.lang.Integer".equals(fieldType)) {
          Integer value = getValueFiled(field, filterBuilder, Integer.class);
          if (value != 0) {
            sql.append(" AND ex." + field.getName() + " = " + value + "");
          }
        } else if ("java.lang.Float".equals(fieldType)) {
          Float value = getValueFiled(field, filterBuilder, Float.class);
          if (value != 0) {
            sql.append(" AND ex." + field.getName() + " = " + value + "");
          }
        } else if ("java.sql.Date".equals(fieldType)) {
          Date value = getValueFiled(field, filterBuilder, Date.class);
          if (value != null) {
            sql.append("AND date(ex." + field.getName() + ") = '" + value + "'");
          }
        }
      }
    }
    // bang khac cho xuong duoi

    if (StringUtils.isNotBlank(filterBuilder.getCategoryName())) {
      sql.append(
          " AND ca.categoryName = '" + filterBuilder.getCategoryName() + "'");
    }

    return sql;
  }

  private <T> T getValueFiled(Field field, FilterBuilder userBuilder,
      Class<T> type) {
    Object result = null;
    Method getter = getGetter(field, userBuilder);
    if (getter != null) {
      try {
        result = getter.invoke(userBuilder);
      } catch (IllegalAccessException | InvocationTargetException e) {

      }
    }
    return type.cast(result);
  }

  private Method getGetter(Field field, FilterBuilder builder) {
    String getterMethod = "get" + StringUtils.capitalize(field.getName());
    try {
      return builder.getClass().getMethod(getterMethod);
    } catch (Exception e) {
      return null;
    }
  }

}

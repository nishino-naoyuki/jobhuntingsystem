package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.Date;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import jp.ac.asojuku.jobhuntingsystem.entity.EventEntity;

public class EventSpecifications {

    public static Specification<EventEntity> companyContains(String companyName) {
        return (StringUtils.isEmpty(companyName) ? null : 
        		 ( root,  query, cb) -> cb.like(root.get("name"),  "%" + companyName + "%" ));
    }
    

    public static Specification<EventEntity> startDateGreaterThan(Date startDate) {
        return startDate == null ? null : 
        			( root,  query, cb) -> cb.greaterThanOrEqualTo(root.get("startDatetime"), startDate);
    }

    public static Specification<EventEntity> endDateLessThan(Date endDate) {
        return endDate == null ? null : 
        			( root,  query, cb) -> cb.lessThanOrEqualTo(root.get("endDatetime"), endDate);
    }

    public static Specification<EventEntity> stepContains(List<Integer> stepList) {
        return (stepList.isEmpty() ? null : 
        			( root,  query, cb) -> root.join("stepTbl").get("stepId").in(stepList)  );
    }

    public static Specification<EventEntity> industryContains(List<Integer> industryList) {
        return (industryList.isEmpty() ? null : 
        			( root,  query, cb) -> root.join("companyTbl").join("companyIndustryTbl").get("industrykindId").in(industryList)  );
    }
}

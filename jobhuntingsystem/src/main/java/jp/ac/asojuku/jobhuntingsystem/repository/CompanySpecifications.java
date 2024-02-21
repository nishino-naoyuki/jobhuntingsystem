package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;

public class CompanySpecifications {

    public static Specification<CompanyEntity> companyContains(String companyName) {
        return (StringUtils.isEmpty(companyName) ? null : 
        		 ( root,  query, cb) -> cb.like(root.get("name"),  "%" + companyName + "%" ));
    }
    

    public static Specification<CompanyEntity> adressContains(String address) {
        return (StringUtils.isEmpty(address) ? null : 
        			( root,  query, cb) -> cb.like(root.get("address"),  "%" + address + "%" ));
    }


    public static Specification<CompanyEntity> industryContains(List<Integer> industryList) {
        return (industryList.isEmpty() ? null : 
        			( root,  query, cb) -> root.join("companyIndustryTbl").get("industrykindId").in(industryList)  );
    }

}

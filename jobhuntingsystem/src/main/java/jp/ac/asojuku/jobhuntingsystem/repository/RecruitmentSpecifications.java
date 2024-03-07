package jp.ac.asojuku.jobhuntingsystem.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;
import jp.ac.asojuku.jobhuntingsystem.entity.RecruitmentEntity;

public class RecruitmentSpecifications {

    public static Specification<RecruitmentEntity> companyContains(String companyName) {
        return (StringUtils.isEmpty(companyName) ? null : 
        		 ( root,  query, cb) -> cb.like(root.join("companyTbl").get("name"),  "%" + companyName + "%" ));
    }
    

    public static Specification<RecruitmentEntity> companyKanaContains(String companyNameKana) {
        return (StringUtils.isEmpty(companyNameKana) ? null : 
        		 ( root,  query, cb) -> cb.like(root.join("companyTbl").get("furigana"),  "%" + companyNameKana + "%" ));
    }
    


    public static Specification<RecruitmentEntity> industryContains(List<Integer> industryList) {
        return (industryList.isEmpty() ? null : 
        			( root,  query, cb) -> {
        				Predicate[] predicates = {
							root.get("industryKindId1").in(industryList),
							root.get("industryKindId2").in(industryList),
							root.get("industryKindId3").in(industryList),
							root.get("industryKindId4").in(industryList),
        	            };

        	            return cb.or(predicates);
        			});
    }

}

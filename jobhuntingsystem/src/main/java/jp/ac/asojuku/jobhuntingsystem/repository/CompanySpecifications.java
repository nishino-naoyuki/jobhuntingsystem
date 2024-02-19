package jp.ac.asojuku.jobhuntingsystem.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import jp.ac.asojuku.jobhuntingsystem.entity.CompanyEntity;

public class CompanySpecifications {

    public static Specification<CompanyEntity> mailContains(String companyName) {
        return StringUtils.isEmpty(companyName) ? null : new Specification<CompanyEntity>() {
			@Override
			public Predicate toPredicate(Root<CompanyEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				// TODO 自動生成されたメソッド・スタブ
				return cb.like(root.get("companyName"),  "%" + companyName + "%" );
			}

        };
    }
}

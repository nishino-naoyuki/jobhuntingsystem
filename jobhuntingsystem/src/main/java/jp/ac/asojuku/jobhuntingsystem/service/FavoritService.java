package jp.ac.asojuku.jobhuntingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.asojuku.jobhuntingsystem.dto.FavoritDto;
import jp.ac.asojuku.jobhuntingsystem.entity.FavoritEntity;
import jp.ac.asojuku.jobhuntingsystem.repository.FavoritRepository;
import jp.ac.asojuku.jobhuntingsystem.util.Exchange;

@Service
public class FavoritService {
	
	@Autowired
	FavoritRepository favoritRepository;
	
	/**
	 * お気に入り登録
	 * @param studentId
	 * @param companyId
	 */
	public void insert(Integer studentId,Integer companyId) {
		FavoritEntity entity = new FavoritEntity();
		
		entity.setCompanyId(companyId);
		entity.setStudentId(studentId);
		entity.setRegdatetime(Exchange.nowDate());
		
		favoritRepository.save(entity);
	}
	
	/**
	 * お気に入りリストを取得する
	 * @param studentId
	 * @return
	 */
	public List<FavoritDto> getList(Integer studentId){
		List<FavoritDto> list = new ArrayList<>();
		
		List<FavoritEntity> entityList = 
				favoritRepository.findByStudentIdOrderByRegdatetimeDesc(studentId);
		
		for(FavoritEntity entity : entityList) {
			FavoritDto dto = new FavoritDto();
			
			dto.setCompanyId( entity.getCompanyId() );
			dto.setCompanyName( entity.getCompanyTbl().getName() );
			dto.setStudentId( entity.getCompanyId() );
			dto.setStudentName( entity.getStudentTbl().getName() );
			dto.setRegistryDatetime( Exchange.toFormatString(entity.getRegdatetime()) );
			
			list.add(dto);
		}
		
		return list;
	}
	
	/**
	 * 既に登録済みかどうかを取得する
	 * @param studentId
	 * @param companyId
	 * @return
	 */
	public boolean isAlreadyRegi(Integer studentId,Integer companyId) {
		FavoritEntity entity = favoritRepository.findByStudentIdAndCompanyId(studentId, companyId);
		return (entity != null);
	}
	
	/**
	 * お気に入り削除
	 * @param studentId
	 * @param companyId
	 */
	public void delete(Integer studentId,Integer companyId) {
		FavoritEntity entity = favoritRepository.findByStudentIdAndCompanyId(studentId, companyId);
		if( entity != null ) {
			favoritRepository.delete(entity);
		}
	}
}

package hospital.businesslogic;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.ICT19Dtl;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicSurgeryLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.entity.*;

import javax.ejb.Stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicSurgery", mappedName = "hospital.businesslogic.LogicSurgery")
public class LogicSurgery extends logic.SuperBusinessLogic implements
hospital.businesslogic.interfaces.ILogicSurgery, ILogicSurgeryLocal {
	@Resource
	SessionContext sessionContext;

	public LogicSurgery() {
	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public void saveSurgeryType(SurgeryType surgeryType) throws Exception {
		if (Tool.ADDED.equals(surgeryType.getStatus())) {
			surgeryType.setPkId(Tools.newPkId());
			insert(surgeryType);
		} else if (Tool.MODIFIED.equals(surgeryType.getStatus())) {
			update(surgeryType);
		} else if (Tool.DELETE.equals(surgeryType.getStatus())) {
			deleteByPkId(SurgeryType.class, surgeryType.getPkId());
		}
	}

	@Override
	public List<SurgeryType> getSurgeryTypes() throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT A FROM SurgeryType A ");

		return getByQuery(SurgeryType.class, jpql.toString(), parameters);
	}

	@Override
	public void saveSurgery(Surgery surgery, SurgeryPrice price,
			List<SurgeryDoctor> doctors, LoggedUser lu) throws Exception {
		if (Tool.ADDED.equals(surgery.getStatus())) {
			surgery.setPkId(Tools.newPkId());
			surgery.setOrganizationPkId(lu.getOrganization().getPkId());
			surgery.setCreatedBy(lu.getUser().getPkId());
			surgery.setCreatedDate(new Date());
			surgery.setUpdatedBy(lu.getUser().getPkId());
			surgery.setUpdatedDate(new Date());
			insert(surgery);

			if(price != null && price.getPkId() == null && price.getPrice() != null) {
				price.setPkId(Tools.newPkId());
				price.setSurgeryPkId(surgery.getPkId());
				price.setCreatedBy(lu.getUser().getPkId());
				price.setCreatedDate(new Date());
				price.setUpdatedBy(lu.getUser().getPkId());
				price.setUpdatedDate(new Date());
				insert(price);
			} 
			if(doctors != null)
				for(SurgeryDoctor doctor: doctors) {
					doctor.setPkId(Tools.newPkId());
					doctor.setSurgeryPkId(surgery.getPkId());
					doctor.setCreatedBy(lu.getUser().getPkId());
					doctor.setCreatedDate(new Date());
					doctor.setUpdatedBy(lu.getUser().getPkId());
					doctor.setUpdatedDate(new Date());
					insert(doctor);
				}
		} else if (Tool.MODIFIED.equals(surgery.getStatus())) {
			update(surgery);
			if(price != null && price.getPkId() == null) {
				price.setPkId(Tools.newPkId());
				price.setSurgeryPkId(surgery.getPkId());
				price.setCreatedBy(lu.getUser().getPkId());
				price.setCreatedDate(new Date());
				price.setUpdatedBy(lu.getUser().getPkId());
				price.setUpdatedDate(new Date());
				insert(price);
			}
			if(doctors != null)
				for(SurgeryDoctor doctor: doctors) {
					if(Tool.ADDED.equals(doctor.getStatus())) {
						doctor.setPkId(Tools.newPkId());
						doctor.setSurgeryPkId(surgery.getPkId());
						doctor.setCreatedBy(lu.getUser().getPkId());
						doctor.setCreatedDate(new Date());
						doctor.setUpdatedBy(lu.getUser().getPkId());
						doctor.setUpdatedDate(new Date());
						insert(doctor);
					} else if(Tool.MODIFIED.equals(doctor.getStatus())) {
						doctor.setUpdatedBy(lu.getUser().getPkId());
						doctor.setUpdatedDate(new Date());
						update(doctor);
					} else if(Tool.DELETE.equals(doctor.getStatus())) {
						deleteByPkId(SurgeryDoctor.class, doctor.getPkId());
					}
				}
		} else if (Tool.DELETE.equals(surgery.getStatus())) {
			deleteByPkId(Surgery.class, surgery.getPkId());
			deleteByAnyField(SurgeryPrice.class, "surgeryPkId", surgery.getPkId());
			deleteByAnyField(SurgeryDoctor.class, "surgeryPkId", surgery.getPkId());
			deleteByAnyField(SurgeryIctMap.class, "surgerPkId", surgery.getPkId());
		}
	}

	@Override
	public List<Surgery> getSurgery(BigDecimal surgeryTypePkId,BigDecimal organizationPkId ,boolean withPrice) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("organizationPkId", organizationPkId);
		jpql.append("SELECT NEW hospital.entity.Surgery(A, B)");
		jpql.append("FROM Surgery A ");
		jpql.append("LEFT JOIN View_Surgery B ON B.surgeryPkId = A.pkId ");
		jpql.append("LEFT JOIN SurgeryType C ON C.pkId = A.surgeryTypePkId ");
		jpql.append("WHERE A.organizationPkId = :organizationPkId ");
		if(!surgeryTypePkId.equals(BigDecimal.ZERO)) {
			parameters.put("surgeryTypePkId", surgeryTypePkId);
			jpql.append("AND A.surgeryTypePkId = :surgeryTypePkId ");
		}
		if(withPrice) {
			jpql.append("AND B.price IS NULL ");
		}

		return getByQuery(Surgery.class, jpql.toString(), parameters);
	}

	@Override
	public List<Surgery> getSurgery(String surgerySearchString,BigDecimal organizationPkId ,boolean withPrice) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("organizationPkId", organizationPkId);
		parameters.put("surgerySearchString", "%"+surgerySearchString+"%");
		jpql.append("SELECT NEW hospital.entity.Surgery(A, B)");
		jpql.append("FROM Surgery A ");
		jpql.append("LEFT JOIN View_Surgery B ON B.surgeryPkId = A.pkId ");
		jpql.append("LEFT JOIN SurgeryType C ON C.pkId = A.surgeryTypePkId ");
		jpql.append("WHERE A.organizationPkId = :organizationPkId ");
		jpql.append("AND A.name LIKE :surgerySearchString ");
		if(withPrice) {
			jpql.append("AND B.price IS NULL ");
		}

		return getByQuery(Surgery.class, jpql.toString(), parameters);
	}

	@Override
	public List<SurgeryDoctor> getSurgeryDoctor(BigDecimal surgeryPkId)
			throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("surgeryPkId", surgeryPkId);
		jpql.append("SELECT A ");
		jpql.append("FROM SurgeryDoctor A ");
		jpql.append("WHERE A.surgeryPkId = :surgeryPkId ");

		return getByQuery(SurgeryDoctor.class, jpql.toString(), parameters);
	}

	@Override
	public List<ICT19Dtl> getSurgeryIct19() throws Exception {
		CustomHashMap map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		builder.append("SELECT  NEW hospital.businessentity.ICT19Dtl(A.pkId,A.id,A.nameEn,A.nameMn) FROM ICT19  A ");
		builder.append("ORDER BY  A.id ASC");
		return getByQuery(ICT19Dtl.class, builder.toString(),map);
	}

	@Override
	public void saveSurgeryICt19(SurgeryIctMap ictMap, BigDecimal surgeryPkId, List<ICT19Dtl> ictDtl) throws Exception {
		// TODO Auto-generated method stub

		BigDecimal pkId = Tools.newPkId();
		for (ICT19Dtl dtl:ictDtl) {
			SurgeryIctMap map = new SurgeryIctMap();
			if (dtl.getPkId()!=null) {
				if (Tool.ADDED.equals(ictMap.getStatus())) {
					pkId = pkId.add(BigDecimal.ONE);
					map.setPkId(pkId);
					map.setSurgerPkId(surgeryPkId);
					map.setIctPkId(dtl.getPkId());
					map.setCreateDate(new Date());
					insert(map);
				}
				else if(Tool.MODIFIED.equals(ictMap.getStatus())){
					update(map);
				}
			}	
		}

	}

	@Override
	public List<ICT19Dtl> getICTDtlView(BigDecimal currentSurgeryPkId) throws Exception {
		// TODO Auto-generated method stub
		CustomHashMap  map =  new CustomHashMap();
		map.put("pkId", currentSurgeryPkId);
		StringBuilder  builder  =  new StringBuilder();
		builder.append("SELECT NEW hospital.businessentity.ICT19Dtl(A,B.id,B.nameEn,B.nameMn)  FROM SurgeryIctMap A ");
		builder.append("INNER JOIN ICT19  B  ON A.ictPkId  = B.pkId ");
		builder.append("WHERE  A.surgerPkId = :pkId ");
		return getByQuery(ICT19Dtl.class, builder.toString(),map);
	}

	@Override
	public void saveSurgeryModel(SurgeryIctModel model, List<ICT19Dtl> ict19Dtls, String name,boolean isModel) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(model.getStatus());
		if(isModel==true){
			
			if (Tool.ADDED.equals(model.getStatus())) {
				for (ICT19Dtl  dtl :ict19Dtls) {
					SurgeryIctModel modelDtl = new SurgeryIctModel();
					modelDtl.setPkId(Tools.newPkId());
					modelDtl.setSurgeryMapPkId(dtl.getIctMap().getPkId());
					modelDtl.setSurgeryName(name);
					modelDtl.setCreateDate(new Date());
					insert(modelDtl);
				}
			}
			else if(Tool.MODIFIED.equals(model.getStatus())){
				
				for (ICT19Dtl  dtl :ict19Dtls) {
					deleteByAnyField(SurgeryIctModel.class, "surgeryMapPkId", dtl.getPkId());
					SurgeryIctModel modelDtl = new SurgeryIctModel();
					modelDtl.setPkId(Tools.newPkId());
					modelDtl.setSurgeryMapPkId(dtl.getIctMap().getPkId());
					modelDtl.setSurgeryName(name);
					modelDtl.setCreateDate(new Date());
					insert(modelDtl);;
				}
			}
			
		}
	}

	@Override
	public List<ICT19Dtl> getSurgeryModelView() throws Exception {
		// TODO Auto-generated method stub
		StringBuilder  builder  =  new StringBuilder();
		builder.append("SELECT NEW hospital.businessentity.ICT19Dtl(B.surgeryName) ");
		builder.append("FROM SurgeryIctMap  A ");
		builder.append("INNER JOIN SurgeryIctModel B  ON A.pkId = B.surgeryMapPkId ");
		builder.append("GROUP BY B.surgeryName ");
		builder.append("ORDER BY B.surgeryName DESC ");
		return getByQuery(ICT19Dtl.class, builder.toString(),null);
				
	}

	@Override
	public List<ICT19Dtl> getSurgeryNameSearch(String surgeryName) throws Exception {
		CustomHashMap map  =  new CustomHashMap();
		map.put("surgeryName", surgeryName);
		StringBuilder builder  =  new StringBuilder();
		builder.append("SELECT  NEW hospital.businessentity.ICT19Dtl(A,B,C)  ");
		builder.append("FROM SurgeryIctMap A ");
		builder.append("INNER JOIN SurgeryIctModel  B  ON A.pkId = B.surgeryMapPkId ");
		builder.append("INNER JOIN  ICT19 C  ON A.ictPkId = C.pkId ");
		builder.append("WHERE  B.surgeryName = :surgeryName ");
		return getByQuery(ICT19Dtl.class, builder.toString(),map);
	}
}
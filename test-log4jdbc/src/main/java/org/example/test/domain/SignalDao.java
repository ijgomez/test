package org.example.test.domain;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.example.test.criteria.SignalCriteria;
import org.example.test.domain.base.HibernateDao;
import org.example.test.domain.base.HibernateDaoBase;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

@Repository
public class SignalDao extends HibernateDaoBase<Signal, Long> implements HibernateDao<Signal, Long> {
	
//	public List<Signal> loadAll() {
//		return super.list();
//	}

	public List<Signal> findByCriteria(SignalCriteria criteria) {

		List<Criterion> criterions;
		Order order;
		
		criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.between("creationTime", criteria.getFechaInicio(), criteria.getFechaFin()));
		
		order = Order.asc("creationTime");
		
		return super.findByCriteria(criterions, order);

	}
	
	public void insertBatchNativeSQL(List<Signal> signals) {
		BatchSqlUpdate sqlUpdate;
		DataSource dataSource;
		String sql;
		
		sql = "INSERT INTO SIGNAL (creationtime, dst, value, signalquality, iecnumber, elementfk, parentfk, signalinfofk) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		dataSource = SessionFactoryUtils.getDataSource(super.sessionFactory);
		
		sqlUpdate = new BatchSqlUpdate(dataSource, sql);
		sqlUpdate.setBatchSize(1000);
		sqlUpdate.declareParameter(new SqlParameter("creationtime", Types.TIMESTAMP));
		sqlUpdate.declareParameter(new SqlParameter("dst", Types.BOOLEAN));
		sqlUpdate.declareParameter(new SqlParameter("value", Types.VARCHAR));
		sqlUpdate.declareParameter(new SqlParameter("signalquality", Types.VARCHAR));
		sqlUpdate.declareParameter(new SqlParameter("iecnumber", Types.INTEGER));
		sqlUpdate.declareParameter(new SqlParameter("elementfk", Types.BIGINT));
		sqlUpdate.declareParameter(new SqlParameter("parentfk", Types.BIGINT));
		sqlUpdate.declareParameter(new SqlParameter("signalinfofk", Types.BIGINT));
		
		for (Signal signal : signals) {
			Object[] values = new Object[8];
			
			values[0] = signal.getCreationTime();
			values[1] = signal.getDst();
			values[2] = signal.getValue();
			values[3] = signal.getSignalQuality();
			values[4] = signal.getIecNumber();
			values[5] = signal.getElementFk();
			values[6] = signal.getParentFk();
			values[7] = signal.getSignalInfoFk();
					
			sqlUpdate.update(values);
		}
		
		sqlUpdate.flush();
	}

}

// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.example.test.andromda.domain;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.example.test.andromda.criteria.TraceCriteria;
import org.example.test.andromda.helpers.DateHelper;
import org.example.test.andromda.vo.TraceVO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * @see Trace
 */
public class TraceDaoImpl extends TraceDaoBase {


	public void toTraceVO(Trace source, TraceVO target) {

		super.toTraceVO(source, target);
		target.setDateTime(DateHelper.toCalendar(source.getDateTime()));
	}

	/**
	 * @see TraceDao#traceVOToEntity(TraceVO,
	 *      Trace)
	 */
	public void traceVOToEntity(TraceVO source,
			Trace target, boolean copyIfNull) {

		super.traceVOToEntity(source, target, copyIfNull);
		target.setDateTime(new Timestamp(source.getDateTime().getTimeInMillis()));
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private Trace loadTraceFromTraceVO(TraceVO traceVO) {

		if (traceVO != null && traceVO.getId() != null) {
		  Trace trace = this.load(traceVO.getId()); 
		  if (trace == null) { 
			  trace = Trace.Factory.newInstance(); 
			}
		  return trace;
		}
		return null;

	}

	/**
	 * @see TraceDao#traceVOToEntity(TraceVO)
	 */
	public Trace traceVOToEntity(TraceVO traceVO) {
		Trace entity = this.loadTraceFromTraceVO(traceVO);
		this.traceVOToEntity(traceVO, entity, true);
		return entity;
	}

	@Override
	protected Long handleCountByCriteria(TraceCriteria criteria) throws Exception {
		Session session;
		Criteria cr;
		Integer count;
		
		session = super.getSession(false);
		try {
			cr = session.createCriteria(Trace.class);

			this.toHibernateCriteria(cr, criteria);
			
			cr.setProjection(Projections.rowCount());
			count = (Integer) cr.uniqueResult();
			return count.longValue();
		} catch (HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}
	
	@Override
	protected List<?> handleFindByCriteria(TraceCriteria criteria, int transform) throws Exception {
		Session session;
		Criteria cr;
		List<?> list;
		
		session = super.getSession(false);
		try {
			cr = session.createCriteria(Trace.class);

			this.toHibernateCriteria(cr, criteria);
			
			list = cr.list();
			super.transformEntities(transform, list);
			return list;
		} catch (HibernateException ex) {
			throw super.convertHibernateAccessException(ex);
		}
	}
	
	private void toHibernateCriteria(Criteria cr, TraceCriteria criteria) {
		if (criteria != null) {
			
			if (criteria.getInitDateTime() != null && criteria.getLastDateTime() != null) {
				cr.add(Restrictions.between("dateTime", criteria.getInitDateTime(), criteria.getLastDateTime()));
			}
			
			if (criteria.getType() != null) {
				cr.add(Restrictions.eq("type", criteria.getType()));
			}
			
			if (!StringUtils.isBlank(criteria.getMessage())) {
				cr.add(Restrictions.ilike("message", criteria.getMessage()));
			}
		}
	}

}
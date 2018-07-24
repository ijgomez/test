// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.example.test.andromda.services;

import java.util.Collection;

import org.example.test.andromda.criteria.TraceCriteria;
import org.example.test.andromda.domain.TraceDao;
import org.example.test.andromda.vo.TraceVO;

/**
 * @see org.example.test.andromda.services.TraceService
 */
public class TraceServiceImpl extends TraceServiceBase {

	@Override
	protected TraceVO[] handleGetAllTraces() throws Exception {
		Collection<?> result = super.getTraceDao().loadAll(TraceDao.TRANSFORM_TRACEVO);
		return result.toArray(new TraceVO[result.size()]);
	}

	@Override
	protected TraceVO[] handleFindByCriteria(TraceCriteria criteria) throws Exception {
		Collection<?> result = super.getTraceDao().findByCriteria(criteria, TraceDao.TRANSFORM_TRACEVO);
		return result.toArray(new TraceVO[result.size()]);
	}
	
	@Override
	protected Long handleCountByCriteria(TraceCriteria criteria) throws Exception {
		return super.getTraceDao().countByCriteria(criteria);
	}

}
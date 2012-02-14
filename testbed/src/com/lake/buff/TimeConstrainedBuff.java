package com.lake.buff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class TimeConstrainedBuff<E> implements ConstrainedBuff<E> {

	private final long timeConstraintMillis;
	private final DelayQueue<TimestampedElement<E>> buff = 
		new DelayQueue<TimestampedElement<E>>();

	
	public TimeConstrainedBuff(long timeConstraintMillis) {
		this.timeConstraintMillis = timeConstraintMillis;
	}
	
	@Override
	public void add(E element) {
		TimestampedElement<E> timestampedElement = 
			new TimestampedElement<E>(element, timeConstraintMillis);
		buff.add(timestampedElement);
	}

	/* (non-Javadoc)
	 * @see com.lake.buff.ConstrainedBuff#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new TimeConstrainedIterator();
	}

	/* (non-Javadoc)
	 * @see com.lake.buff.ConstrainedBuff#takeExpired()
	 */
	@Override
	public E takeExpired() throws InterruptedException {
		TimestampedElement<E> timestampedElement = buff.take();
		return timestampedElement.get();
	}

	private class TimeConstrainedIterator implements Iterator<E> {
		
		final Iterator<TimestampedElement<E>> iterator;
		
		public TimeConstrainedIterator() {
			
			ArrayList<TimestampedElement<E>> a = new ArrayList<TimestampedElement<E>>(buff);
			
			for (Iterator<TimestampedElement<E>> it = a.iterator(); it.hasNext();) {
				TimestampedElement<E> item = it.next();
				if (item.getDelay(TimeUnit.NANOSECONDS) <= 0) {
					it.remove();
				}					
			}
			
			iterator = a.iterator();			
		}
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public E next() {
			TimestampedElement<E> item = iterator.next();
			return item.get();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove is not implimented");
		}
	}	
	
	public static class TimestampedElement<T> implements Delayed {
		private final T element;
		private final long delayExpiresTimeNano;
		
		public TimestampedElement(T element, long delayTimeMillis) {
			this.element = element;
			this.delayExpiresTimeNano = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(delayTimeMillis);
		}	
		
		public T get() {
			return element;
		}

		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Delayed other) {
			long thisDelay = this.getDelay(TimeUnit.NANOSECONDS);
			long otherDelay = other.getDelay(TimeUnit.NANOSECONDS);
			return (thisDelay<otherDelay ? -1 : (thisDelay==otherDelay ? 0 : 1));
		}

		/* (non-Javadoc)
		 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
		 */
		@Override
		public long getDelay(TimeUnit timeUnit) {
			return timeUnit.convert(delayExpiresTimeNano, TimeUnit.NANOSECONDS);
		}
	}
}
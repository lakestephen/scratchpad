package com.concurrentperformance.annotation;

import java.util.concurrent.Future;

/**
 * TODO
 * User: Stephen
 */
public interface TopologyService {

	@MessagePassing(call = CreateBagMessage.class,response = StringMessage.class)
	Future<String> createBag(String value);
}
